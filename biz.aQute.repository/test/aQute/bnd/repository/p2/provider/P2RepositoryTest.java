package aQute.bnd.repository.p2.provider;

public class P2RepositoryTest {
	// File tmp = IO.getFile("generated/tmp");
	//
	// @Before
	// public void setUp() {
	// IO.delete(tmp);
	// tmp.mkdirs();
	// }
	//
	// @Test
	// public void testSimple() throws Exception {
	// try (P2Repository p2r = new P2Repository()) {
	// Workspace w = Workspace.createStandaloneWorkspace(new Processor(),
	// tmp.toURI());
	// w.setBase(tmp);
	// p2r.setRegistry(w);
	//
	// Map<String, String> config = new HashMap<>();
	// config.put("url", "https://dl.bintray.com/bndtools/bndtools/latest/");
	// config.put("name", "test");
	// p2r.setProperties(config);
	//
	// List<String> list = p2r.list(null);
	// assertNotNull(list);
	// assertTrue(list.size() > 1);
	// }
	// }
	//
	// @Test
	// public void testXtext() throws Exception {
	//
	// try (P2Repository p2r = new P2Repository()) {
	// Workspace w = Workspace.createStandaloneWorkspace(new Processor(),
	// tmp.toURI());
	// w.setTrace(true);
	// w.setBase(tmp);
	// p2r.setRegistry(w);
	//
	// Map<String, String> config = new HashMap<>();
	// config.put("url",
	// "http://download.eclipse.org/modeling/tmf/xtext/updates/releases/head/R201304180855/");
	// config.put("name", "test");
	// p2r.setProperties(config);
	// List<String> list = p2r.list(null);
	// assertTrue(w.check());
	// assertNotNull(list);
	// assertTrue(list.size() > 1);
	// }
	// }
}
