import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageManagerTest {
  private PackageManager pm;
  Graph graph;

  /**
   * Set up that inits the package manager and the graph, it also creates the
   * package manager to have a simple graph to test out more basic operations.
   * If need be, the test methods can create a new PackageManager to test more
   * complicated graphs
   * 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    pm = new PackageManager();
    pm.constructGraph(
        "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\valid.json");
    graph = new Graph();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  /**
   * Tests constructGraph from PackageManager for a valid json file
   */
  @Test
  void testConstructGraph() {
    try {
      pm = new PackageManager();
      // construct a graph using the package manager
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\valid.json");
      Set<String> pmGraph = pm.getAllPackages();
      // compare to the graph that i constructed myself
      graph.addEdge("A", "B");
      graph.addEdge("B", "C");
      graph.addEdge("B", "D");
      graph.addEdge("E", "B");
      Set<String> graphGraph = graph.getAllVertices();
      assertTrue(pmGraph.containsAll(graphGraph));

    } catch (IOException | ParseException e) {

      System.out.println(e.getMessage());
      fail("Something was thrown");
    }

    // test valid.json
  }

  /**
   * Tests constructing a graph that has a cycle in it It should not throw any
   * exceptions
   */
  @Test
  void testConstructCyclicGraph() {
    // test cyclic.json
    try {
      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\cyclic.json");
      Set<String> pmSet = pm.getAllPackages();
      graph.addEdge("A", "B");
      graph.addEdge("B", "A");
      Set<String> mySet = graph.getAllVertices();
      assertTrue(pmSet.containsAll(mySet));
    } catch (IOException | ParseException e) {
      // no exceptions should be thrown from making a cyclic graph...
      System.out.println(e.getMessage());
      fail("Something was thrown");

    }
  }

  @Test
  void testGetAllPackages() {
    Set<String> set = pm.getAllPackages();
    // compare to the graph that i constructed myself
    graph.addEdge("A", "B");
    graph.addEdge("B", "C");
    graph.addEdge("B", "D");
    graph.addEdge("E", "B");
    Set<String> setGraph = graph.getAllVertices();
    assertTrue(set.containsAll(setGraph));
  }

  /**
   * Tests a simple getInstallationOrder
   */
  @Test
  void testGetInstallationOrder() {
    try {
      java.util.List<String> testPM = pm.getInstallationOrder("A");
      ArrayList<String> hopeful = new ArrayList<String>();
      hopeful.add("C");
      hopeful.add("D");
      hopeful.add("B");
      hopeful.add("A");
      for (int i = 0; i < testPM.size(); i++) {
        System.out.println(testPM.get(i));
        System.out.println("--");
        assertTrue(hopeful.get(i).equals(testPM.get(i)));
      }
      assertTrue(testPM.containsAll(hopeful));
    } catch (CycleException | PackageNotFoundException e) {
      System.out.println("Something happened, one of ur exceptions was thrown");
      fail(e.getMessage());
    } catch (Exception e) {
      fail("Exception was not supposed to be thrown: " + e.getMessage());
    }
  }

  /**
   * Test getting an installation order that throws a cycle exception
   */

  @Test
  void testCycleInstallationOrder() {
    try {
      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\cyclic.json");
      List<String> cycleExceptionThrower = pm.getInstallationOrder("A");
      fail("CycleException was not thrown from this line");
    } catch (CycleException e) {
      // do nothing, we wanted this to happen
    } catch (PackageNotFoundException e) {
      fail("The package does exist...");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      fail("No other exceptions should have been thrown " + e.getMessage());
    }
  }

  /**
   * Test a package that is not in the graph
   */
  @Test
  void testInstallationOrderNotInGraph() {
    try {
      pm.getInstallationOrder("NOT In Graph");
      fail("PackageNotFoundException should have been thrown");
    } catch (PackageNotFoundException e) {
      // do nothing
    } catch (CycleException e) {
      fail("This exception should not have been thrown");
    }
  }

  /**
   * Tests a simple toInstall with two inputs that do exist in the graph
   */
  @Test
  void testToInstall() {
    try {
      java.util.List<String> installationlist = pm.toInstall("A", "C");
      ArrayList<String> expected = new ArrayList<String>();
      expected.add("D");
      expected.add("B");
      expected.add("A");
      for (int i = 0; i < installationlist.size(); i++) {
        assertTrue(installationlist.get(i).equals(expected.get(i)));
      }
      assertTrue(installationlist.containsAll(expected));
    } catch (CycleException e) {
      fail("there should not be a cycle " + e.getMessage());
    } catch (PackageNotFoundException e) {
      fail("The package should exist in the graph " + e.getMessage());
    }
  }

  /**
   * Test if it can detect a cycle
   */
  @Test
  void testCycleExceptionToInstall() {
    try {
      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\cyclic.json");
      pm.toInstall("A", "B");
      fail("Cycle exception should have been thrown here");
    } catch (CycleException e) {

    } catch (PackageNotFoundException e) {
      fail("I am 99% sure that both packages exist");
    } catch (Exception e) {
      fail("Close but not quite");
    }
  }

  /**
   * Tests if the method throws PackageNotFound exception when called and the
   * packages are not already in the graph
   */
  @Test
  void testPackageNotFoundToInstall() {
    try {
      pm.toInstall("Definetely not in the graph", "also not in there :)");
      fail("PackageNotFound exception should have been thrown here");
    } catch (CycleException e) {
      fail("200% sure there isn't a cycle");
    } catch (PackageNotFoundException e) {
      // yea! we threw an exception. well done.
    } catch (Exception e) {
      fail("Close but not quite");
    }
  }

  @Test
  void testGetInstallationOrderForAllPackages() {
    try {
      List<String> installOrder = pm.getInstallationOrderForAllPackages();
      ArrayList<String> ideally = new ArrayList<String>();
      ideally.add("C");
      ideally.add("D");
      ideally.add("B");
      ideally.add("A");
      ideally.add("E");
      for (int i = 0; i < installOrder.size(); i++) {
        System.out.println(installOrder.get(i));
        assertTrue(installOrder.get(i).equals(ideally.get(i)));
      }
      assertTrue(installOrder.containsAll(ideally));
    } catch (CycleException e) {
      fail("There should not be any cycles in the graph");
    }
  }

  /**
   * Tests getInstallationOrderForAllPackages when throwing a CycleException
   */
  @Test
  void testGetCycleGraph() {
    try {
      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\cyclic.json");
      pm.getInstallationOrderForAllPackages();
      fail("Cycle exception should have been thrown here");
    } catch (CycleException e) {

    } catch (Exception e) {
      System.out.println(e.getMessage());
      fail("Close but not quite");
    }
  }

  /**
   * Test getPackageWithMaxDependency
   */
  @Test
  void testGetPackageWithMaxDependencies() {
    try {
      String str = pm.getPackageWithMaxDependencies();
      assertTrue(str.equals("A"));
    } catch (CycleException e) {
      fail("There is no cycle...");
    } catch (Exception e) {
      fail("NOPE, not on my watch");
    }
  }

  /**
   * Tests if getPackageWithMaxDependencies will throw a CycleException
   */
  @Test
  void testCyclePackageMaxDependency() {
    try {

      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\cyclic.json");
      pm.getPackageWithMaxDependencies();
      fail("Cycle exception should have been thrown here");

    } catch (Exception e) {
    }

  }

  /**
   * Tests every method that detects and throws a CycleException
   */
  @Test
  void testCycle() {
    try {
      pm = new PackageManager();
      pm.constructGraph(
          "C:\\Users\\Ariel\\git\\Ariel\\CS400\\P4\\src\\testcyclic.json");
    } catch (Exception e) {
      fail("No exceptions from constructing");
    }
    // test getAllPackages
    try {
      pm.getAllPackages();
    } catch (Exception e) {
      fail("This method does not even throww ");
    }
  }
  // TODO: add test cases for what is not a cycle but looks like one
  // TODO: add test case for a cycle ?

}
