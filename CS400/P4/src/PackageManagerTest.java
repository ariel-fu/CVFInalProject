import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageManagerTest {
  private PackageManager pm;

  @BeforeEach
  void setUp() throws Exception {
    pm = new PackageManager();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testConstructGraph() {
    // test valid.json
  }

  @Test
  void testConstructCyclicGraph() {
    // test cyclic.json
  }

  // test case 1.

  // test case 2/

  @Test
  void testGetAllPackages() {
    fail("Not yet implemented");
  }

  @Test
  void testGetInstallationOrder() {
    fail("Not yet implemented");
  }

  @Test
  void testToInstall() {
    fail("Not yet implemented");
  }

  @Test
  void testGetInstallationOrderForAllPackages() {
    fail("Not yet implemented");
  }

  @Test
  void testGetPackageWithMaxDependencies() {
    fail("Not yet implemented");
  }

}
