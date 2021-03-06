package bndtools.explorer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Display;

import aQute.lib.exceptions.Exceptions;
import aQute.lib.strings.Strings;
import aQute.libg.glob.Glob;
import bndtools.central.Central;

class Model {
	Glob					glob;
	IProject				selectedProject;
	String					prompt;
	String					message	= "initializing workspace";
	int						severity;
	String					filterText;
	final List<Runnable>	updates	= new ArrayList<>();
	final AtomicBoolean		dirty	= new AtomicBoolean(false);

	void setSelectedProject(IProject project) {
		if (project != selectedProject) {
			this.selectedProject = project;
			update();
		}
	}

	void closeProject(IProject selectedProject) {
		if (this.selectedProject == selectedProject)
			setSelectedProject(null);
	}

	void setFilterText(String value) {
		if (Objects.equals(this.filterText, value))
			return;
		this.filterText = value;
		if ( value == null)
			glob = null;
		else
			glob = new Glob(value);
		update();
	}

	void setPrompt(String prompt) {
		if (Objects.equals(prompt, this.prompt))
			return;

		this.prompt = prompt;
		updateMessage();
	}

	void setMessage(String message) {
		if (Objects.equals(this.message, message))
			return;
		this.message = message;
		update();
	}

	void updateMessage() {
		setMessage(getPrompt());
	}

	void setSeverity(int severity) {
		if (this.severity != severity) {
			this.severity = severity;
			update();
		}
	}

	private String getPrompt() {
		try {
			if (prompt == null || prompt.isEmpty())
				prompt = "<b>${basename;${workspace}}</b> ${def;Bundle-Version} <a href='prefs'>change macro def</a>";
			else if ("-".equals(prompt))
				return "";

			String s = Central.getWorkspace()
				.getReplacer()
				.process(prompt);
			s = Strings.removeQuotes(s);
			return s;
		} catch (Exception e) {
			throw Exceptions.duck(e);
		}
	}

	void onUpdate(Runnable runnable) {
		updates.add(runnable);
	}

	void update() {
		dirty.set(true);
		if (Display.getCurrent() == null) {
		Display.getDefault()
				.asyncExec(this::update0);
		} else {
			update0();
		}
	}

	private void update0() {
		if (dirty.getAndSet(false)) {
			updates.forEach(Runnable::run);
		}
	}

}
