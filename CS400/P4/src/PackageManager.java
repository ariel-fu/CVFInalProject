import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename: PackageManager.java Project: p4 Authors:Ariel Fu
 * 
 * PackageManager is used to process json package dependency files and provide
 * function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json
 * file.
 * 
 * Package dependencies are important when building software, as you must
 * install packages in an order such that each package is installed after all of
 * the packages that it depends on have been installed.
 * 
 * For example: package A depends upon package B, then package B must be
 * installed before package A.
 * 
 * This program will read package information and provide information about the
 * packages that must be installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test
 * classes.
 * 
 * My implementation may differ from what comes first. Instead of having A's
 * dependencies point to A, A points to its dependencies. For example: if A
 * depends on B and C, A (in this graph) would have an edge that has a source at
 * A and a destination at B, and another edge that has a source at A and a
 * destination at C.
 */

public class PackageManager {

  /**
   * This class models a Stack that is used in my topological ordering algorithm
   * 
   * @author Ariel
   *
   */
  private class Stack {
    private ArrayList<String> stack; // Stack implemented as an ArrayList

    /**
     * No-arg constructor that inits the stack
     */
    private Stack() {
      stack = new ArrayList<String>();
    }

    /**
     * Adds a String to the top of the Stack
     * 
     * @param data - data of the String
     */
    private void push(String data) {
      stack.add(data);
    }

    /**
     * Gets the top of the stack without removing anything
     * 
     * @return the top of the stack
     */
    private String peek() {
      // the end of the list is the head of the stack
      return stack.get(stack.size() - 1);
    }

    /**
     * removes the end of the list aka the head of the stack
     * 
     * @return the head of the stack
     */
    private String pop() {
      String head = stack.get(stack.size() - 1);
      stack.remove(head);
      return head;
    }

    /**
     * Returns if the stack is empty
     * 
     * @return true if the stack is empty
     */
    private boolean isEmpty() {
      return stack.size() == 0;
    }
  }

  private Graph predecessorGraph;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    predecessorGraph = new Graph();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency
   * graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency
   *                     information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException           if the give file cannot be read
   * @throws ParseException        if the given json cannot be parsed
   * 
   */
  public void constructGraph(String jsonFilepath) throws FileNotFoundException,
      IOException, org.json.simple.parser.ParseException {
    Object object = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject json = (JSONObject) object;
    JSONArray listOfPackages = (JSONArray) json.get("packages");
    Package[] packages = new Package[listOfPackages.size()];
    for (int i = 0; i < listOfPackages.size(); i++) {
      // get the package from the json file
      JSONObject jsonPackage = (JSONObject) listOfPackages.get(i);
      // get the name from the package object in the JSON file
      String packageName = (String) jsonPackage.get("name");
      // get the JSONArray of the dependencies of the current package
      JSONArray jsonDependencies = (JSONArray) jsonPackage.get("dependencies");
      // convert the JSONArray to a String[]
      String[] dependencies = this.castToStringArray(jsonDependencies);
      // add the new package to the list of packages
      packages[i] = new Package(packageName, dependencies);
    }

    // builds both graphs with the list of packages from the JSON file
    this.buildGraph(packages);
  }

  /**
   * Builds the graph given an array of Packages
   * 
   * @param packages - list of packages to be put into the graph
   *
   */
  private void buildGraph(Package[] packages) {
    for (int packageIndex = 0; packageIndex < packages.length; packageIndex++) {
      Package currPackage = packages[packageIndex];
      // add the name of the package as a vertex in the graph
      predecessorGraph.addVertex(currPackage.getName());
      // get the String[] of dependencies to add to the graph
      String[] dependencies = currPackage.getDependencies();
      for (int depIndex = 0; depIndex < dependencies.length; depIndex++) {
        // add an edge between the dependency vertex and the name of the package
        predecessorGraph.addEdge(currPackage.getName(), dependencies[depIndex]);
      }
    }
  }

  /**
   * Takes in a JSONArrray and casts every element to a String
   * 
   * @param jsonDependencies - JSONArray to cast to a String[]
   * @return a String[] that contains everything in the JSONArray
   */
  private String[] castToStringArray(JSONArray jsonDependencies) {
    String[] dependencies = new String[jsonDependencies.size()];
    for (int i = 0; i < dependencies.length; i++) {
      dependencies[i] = (String) jsonDependencies.get(i);
    }
    return dependencies;
  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return predecessorGraph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation
   * order.
   * 
   * Valid installation order means that each package is listed before any
   * packages that depend upon that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException           if you encounter a cycle in the graph
   *                                  while finding the installation order for a
   *                                  particular package. Tip: Cycles in some
   *                                  other part of the graph that do not affect
   *                                  the installation order for the specified
   *                                  package, should not throw this exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in
   *                                  the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    Set<String> allPackages = predecessorGraph.getAllVertices();
    if (!allPackages.contains(pkg)) {
      throw new PackageNotFoundException();
    } else {
      // throws a CycleException if a cycle is detected in the graph
      return topologicalOrder(pkg);
    }
  }

  /**
   * Given two packages - one to be installed and the other installed, return a
   * List of the packages that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If
   * package A needs to be installed and packageB is already installed, return
   * the list ["A", "C"] since D will have been installed when B was previously
   * installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException           if you encounter a cycle in the graph
   *                                  while finding the dependencies of the
   *                                  given packages. If there is a cycle in
   *                                  some other part of the graph that doesn't
   *                                  affect the parsing of these dependencies,
   *                                  cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist
   *                                  in the dependency graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    // get the topo order for installed pkg and the topo order for new pkg
    // then get what is not installed and not new
    Set<String> allPackages = predecessorGraph.getAllVertices();
    if (!allPackages.contains(newPkg)) {
      throw new PackageNotFoundException();
    } else if (!allPackages.contains(installedPkg)) {
      throw new PackageNotFoundException();
    } else {
      // either one of these two could throw a CycleException that indicates
      // there is a cycle.
      List<String> topoOrderOfNewPkg = this.topologicalOrder(newPkg);
      List<String> topoOrderInstalledPkg = this.topologicalOrder(installedPkg);
      // if there is no cycle, return the packages that still need to be
      // installed for the new package to be installed.
      return differenceBetween(topoOrderOfNewPkg, topoOrderInstalledPkg);
    }

  }

  /**
   * Gets the difference of A-B TODO: may need to redo the javadocs if they
   * don't make sense
   * 
   * @param A - List that we want to take out the elements in B
   * @param B - elements in B that we want to take out of A
   * @return a list that has all the elements in A except for the elements that
   *         were shared by A and B
   */
  private List<String> differenceBetween(List<String> A, List<String> B) {
    List<String> ANotB = new ArrayList<String>();
    for (int i = 0; i < A.size(); i++) {
      if (!B.contains(A.get(i))) {
        ANotB.add(A.get(i));

      }
    }
    return ANotB;
  }

  /**
   * Return a valid global installation order of all the packages in the
   * dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all
   * the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */
  // TODO: is probably wrong... i would test this one multiple times
  public List<String> getInstallationOrderForAllPackages()
      throws CycleException {
    // #1: get the start vertex to start the topological order for the successor
    // graph.
    String startVertex = this.getVertexWithoutDependency();
    // if there is a cycle in the graph, the method above returns null.
    if (startVertex == null) {
      throw new CycleException();
    } else { // clear skies!
      // get the predecessors of each and merge them
      Set<String> allVertices = predecessorGraph.getAllVertices();
      String[] vertList = new String[allVertices.size()];
      // get all the vertices into an array to iterate through
      vertList = allVertices.toArray(vertList);
      List<String> topoOrder = new ArrayList<String>();
      // iterate through every vertex and add their predecessors to the list of
      // predecessors.
      for (int i = 0; i < vertList.length; i++) {
        topoOrder = this.merge(this.topologicalOrder(vertList[i]), topoOrder);
      }

      return topoOrder;
    }
  }

  /**
   * Merges two lists, one that gives and one that takes and merges them into
   * itself
   * 
   * @param giver  - gives the elements that are not already in merger
   * @param merger - takes elements from giver and merges them into itself
   * @return merger with all the elements from giver that it didn't originally
   *         have
   */
  private List<String> merge(List<String> giver, List<String> merger) {
    // if everything is null, return the first list.
    if (merger.size() == 0) {
      return giver;
    }
    for (int i = 0; i < giver.size(); i++) {
      // if the merger does not contain the current element,
      // add it to the end of the list
      if (!merger.contains(giver.get(i))) {
        merger.add(giver.get(i));
      }
    }
    return merger;
  }

  /**
   * Find and return the name of the package with the maximum number of
   * dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The
   * number of dependencies includes the dependencies of its dependencies. But,
   * if a package is listed in multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D.
   * Then, A has 3 dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    // for each vertex in the graph
    // do a BFS on it that will return an int, which would be how many
    // dependencies it has.
    // then compare and return which ever vertex had the biggest integer
    Set<String> allVertices = predecessorGraph.getAllVertices();
    // this will fail too...
    String[] verticesArray = new String[allVertices.size()];
    verticesArray = allVertices.toArray(verticesArray);

    int currHighestNum = 0;
    String packageName = "";
    for (int i = 0; i < verticesArray.length; i++) {
      int currNum = this.countDependencies(verticesArray[i]);
      if (currNum > currHighestNum) {
        currHighestNum = currNum;
        packageName = verticesArray[i];
      }
    }
    return packageName;
  }

  /**
   * Finds a vertex without a dependency
   * 
   * @return a vertex without a dependency or null, which indicates there is a
   *         cycle.
   */
  private String getVertexWithoutDependency() {
    Set<String> allVertices = predecessorGraph.getAllVertices();

    String[] vertices = new String[allVertices.size()];
    vertices = allVertices.toArray(vertices);
    // iterate over every vertex to find the vertex without a predecessor
    for (int i = 0; i < vertices.length; i++) {
      // if the size of the list of adjacent vertices to the current vertex is
      // 0, it has no predecessors.
      if (predecessorGraph.getAdjacentVerticesOf(vertices[i]).size() == 0) {
        // found a vertex that does not rely on another vertex
        return vertices[i];
      }
    }
    // if every vertex relies on another vertex, there must be a cycle.
    return null; // cycle?
  }

  /**
   * Counts the dependencies for the start vertex
   * 
   * @param startVertex - vertex to start at for the topological order
   * @return the number of dependencies the start vertex has
   * @throws CycleException - if there is a cycle in the graph
   */
  private int countDependencies(String startVertex) throws CycleException {
    Stack stack = new Stack();
    // keep track of the vertices in the stack
    List<String> verticesInStack = new ArrayList<String>();

    // keep track of the number of dependencies
    int numDependencies = 0;
    // keep track of visited vertices
    List<String> visited = new ArrayList<String>();

    // add the start vertex to the stack and the list of vertices in the stack.
    // Then set it to visited
    stack.push(startVertex);
    verticesInStack.add(startVertex);
    visited.add(startVertex);
    // create a list that holds the predecessors of this vertex (package)
    List<String> predecessors = new ArrayList<String>();

    // while the stack is not empty
    // do i even need index>predecessors.size() (?) pondor on this one
    while (!stack.isEmpty()) {
      String currHead = stack.peek(); // head of the stack
      // get all the predecessors of the current pacakge at the head of the
      // stack
      predecessors = predecessorGraph.getAdjacentVerticesOf(currHead);

      // check if there is a cycle
      if (this.isCycle(predecessors, verticesInStack)) {
        throw new CycleException();
      }

      // all predecessors have been visited
      if (visited.containsAll(predecessors)) {
        // pop of the head and remove it from the list of vertices in the stack.
        currHead = stack.pop();
        verticesInStack.remove(currHead);

        // increment the number of dependencies
        numDependencies++;
        // set the index in the predecessor list back to 0

      } else {
        String predescessor = this.getNextPredecessor(predecessors, visited);

        // mark as visited, add to stack, increment the index in the
        // predecessor list, and add to the list of vertices in the stack
        visited.add(predescessor);
        stack.push(predescessor);
        verticesInStack.add(predescessor);

      }
    }
    // return the list of the vertices in topological order
    return numDependencies;
  }

  /**
   * Gets the topological order of the start vertex
   * 
   * @param startVertex - vertex to start at for the topological order
   * @return a list of the vertices that startVertex depends on.
   * @throws CycleException - if there is a cycle in the graph
   */
  private List<String> topologicalOrder(String startVertex)
      throws CycleException {
    Stack stack = new Stack();
    // keep track of the vertices in the stack
    List<String> verticesInStack = new ArrayList<String>();

    // keep track of the order
    List<String> topoOrder = new ArrayList<String>();

    // keep track of visited vertices
    List<String> visited = new ArrayList<String>();

    // add the start vertex to the stack and the list of vertices in the stack.
    // Then set it to visited
    stack.push(startVertex);
    verticesInStack.add(startVertex);
    visited.add(startVertex);
    // create a list that holds the predecessors of this vertex (package)
    List<String> predecessors = new ArrayList<String>();
    // iterate through, adding and removing objects from the Stack
    while (!stack.isEmpty()) {
      String currHead = stack.peek(); // head of the stack
      // get all the predecessors of the current package at the head of the
      // stack
      predecessors = predecessorGraph.getAdjacentVerticesOf(currHead);

      // check if there is a cycle
      if (this.isCycle(predecessors, verticesInStack)) {
        throw new CycleException();
      }

      // all predecessors have been visited
      if (visited.containsAll(predecessors)) {
        // pop of the head and remove it from the list of vertices in the stack.
        currHead = stack.pop();
        verticesInStack.remove(currHead);
        // add to the ned of the list
        topoOrder.add(currHead);

        // set the index in the predecessor list back to 0

      } else {
        String predescessor = this.getNextPredecessor(predecessors, visited);

        // mark as visited, add to stack, increment the index in the
        // predecessor list, and add to the list of vertices in the stack
        visited.add(predescessor);
        stack.push(predescessor);
        verticesInStack.add(predescessor);
      }
    }
    // return the list of the vertices in topological order
    return topoOrder;
  }

  /**
   * Helper method to find the next unvisited predecessor
   * 
   * @param predecessors - list of predecessors
   * @param visited      - list of visited packages
   * @return the next unvisited predecessor
   */
  private String getNextPredecessor(List<String> predecessors,
      List<String> visited) {
    for (int i = 0; i < predecessors.size(); i++) {
      if (!visited.contains(predecessors.get(i))) {
        return predecessors.get(i);
      }
    }
    return null;
  }

  /**
   * Checks if any of the predecessors are also in the list of vertices already
   * in the Stack
   * 
   * @param predecessors    - list of predecessors
   * @param verticesInStack - list of vertices already in the Stack
   * @return true if any of the predecessors are also in the Stack list
   */
  private boolean isCycle(List<String> predecessors,
      List<String> verticesInStack) {
    for (int i = 0; i < predecessors.size(); i++) {
      if (verticesInStack.contains(predecessors.get(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Main method for tests (?)
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
