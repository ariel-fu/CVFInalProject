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
 * Filename: PackageManager.java Project: p4 Authors:
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

  /**
   * This class models the queue that I will use in my BFS
   * 
   * @author Ariel
   *
   */
  private class Queue {
    private ArrayList<String> queue;

    private Queue() {
      queue = new ArrayList<String>();
    }

    /**
     * Adds a String to the end of the queue
     * 
     * @param data - new String to add to the queue
     */
    private void enqueue(String data) {
      queue.add(data);
    }

    /**
     * Removes and returns the first element of the queue.
     * 
     * @return the first element in the queue
     */
    private String dequeue() {
      String returnValue = queue.get(0);
      queue.remove(0);
      return returnValue;
    }

    /**
     * Returns if the queue is empty
     * 
     * @return if the size of the list = 0
     */
    private boolean isEmpty() {
      return queue.size() == 0;
    }
  }

  private Graph graph;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
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

    // build a graph with the list of packages from the JSON file
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
      graph.addVertex(currPackage.getName());
      // get the String[] of dependencies to add to the graph
      String[] dependencies = currPackage.getDependencies();
      for (int depIndex = 0; depIndex < dependencies.length; depIndex++) {
        // add an edge between the dependency vertex and the name of the package
        graph.addEdge(dependencies[depIndex], currPackage.getName());
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
    return graph.getAllVertices();
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
    return null;
  }

  private String getStartVertex(String random) {
    List<String> predecessors = this.getPredecessor(random);
    String current = random;
    while (predecessors != null) {
      for (int i = 0; i < predecessors.size(); i++) {
        current = predecessors.get(i);
        predecessors = this.getPredecessor(predecessors.get(i));
      }
    }
    return current;

  }

  /**
   * 
   * @param startVertex
   * @return
   */
  private List<String> topologicalOrder(String startVertex) {
    int num = graph.order(); // get the total number of vertices
    Stack stack = new Stack();
    // keep track of the order
    List<String> topoOrder = new ArrayList<String>();
    // keep track of visited vertices
    boolean[] visited = new boolean[num];
    int currIndex = num - 1;

    // add the start vertex to the stack and set it to visited
    stack.push(startVertex);
    visited[currIndex] = true;
    currIndex--; // for the next vertex

    while (!stack.isEmpty()) {
      String headOfStack = stack.peek(); // head of the stack

    }

    return topoOrder;
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
    return null;
  }

  /**
   * Gets a list of predecessors of the vertex
   * 
   * @param vertex - get the predecessor of this vertex
   * @return a list of predecessors of the vertex. If tit does not have any, it
   *         returns null.
   */
  private List<String> getPredecessor(String vertex) {
    Set<String> allVertices = graph.getAllVertices();
    String[] vertices = (String[]) allVertices.toArray();
    List<String> predecessors = new ArrayList<String>();
    for (int i = 0; i < vertices.length; i++) {
      List<String> adjList = graph.getAdjacentVerticesOf(vertices[i]);
      for (int j = 0; j < adjList.size(); j++) {
        if (adjList.get(i).equals(vertex)) {
          predecessors.add(vertex);
        }
      }
    }
    if (predecessors.isEmpty()) {
      return null;
    }
    return predecessors;
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
  public List<String> getInstallationOrderForAllPackages()
      throws CycleException {
    return null;
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
    return "";
  }

  /**
   * Helper method that performs the BFS
   * 
   * @param v - start vertex
   * @return a list of the vertices in the BFS order - depth first
   */
  private List<String> BFS(String v) {
    Queue queue = new Queue();
    List<String> visited = new ArrayList<String>();
    List<String> order = new ArrayList<String>();
    // mark v as visited, and add it to the order
    visited.add(v);
    order.add(v);
    // add v to the queue
    queue.enqueue(v);

    // while loop until the queue is empty
    while (!queue.isEmpty()) {
      // remove a vertex from the queue
      String curr = queue.dequeue();
      List<String> unvisitedSucc = this.getUnvisitedSuccsessors(visited,
          graph.getAdjacentVerticesOf(curr));
      // for each unvisited successor, mark as visited and add it to the queue
      for (int i = 0; i < unvisitedSucc.size(); i++) {
        curr = unvisitedSucc.get(i);
        queue.enqueue(curr);
      }
    }

    return order;
  }

  /**
   * A private method that performs a DFS on the start vertex and all its
   * successors.
   * 
   * @param start - start vertex of the DFS
   * @return a list of the graph in a DFS order
   */
  private List<String> DFS(String start) {
    // keep a list of visited vertices, a list of unvisited successors,
    // and a list of vertices that are adjacent to it.
    List<String> visited = new ArrayList<String>(); // not sure about this one.
    List<String> successors = graph.getAdjacentVerticesOf(start);
    List<String> unvisited = this.getUnvisitedSuccsessors(visited, successors);

    // mark the starting vertex as visited
    visited.add(start);
    // iterate through all the unvisited successors and call DFS on them
    for (int i = 0; i < unvisited.size(); i++) {
      List<String> recurse = this.DFS(unvisited.get(i));
      visited = this.combineLists(visited, recurse);
    }
    return visited;
  }

  /**
   * Combines two lists
   * 
   * @param retriever - takes the elements from the other list
   * @param giver     - gives its elements to the retrieving list
   * @return a list that combines both lists.
   */
  private List<String> combineLists(List<String> retriever,
      List<String> giver) {
    for (int i = 0; i < giver.size(); i++) {
      retriever.add(giver.get(i));
    }
    return retriever;
  }

  /**
   * Takes in two lists, one a list of visited vertices and another the list of
   * successors, and determines which successors have not been visited
   * 
   * @param visited    - list of previously visited vertices
   * @param successors - list of successors
   * @return
   */
  private List<String> getUnvisitedSuccsessors(List<String> visited,
      List<String> successors) {
    List<String> unvisisted = new ArrayList<String>();
    for (int i = 0; i < successors.size(); i++) {
      String curr = successors.get(i);
      if (!visited.contains(curr)) {
        unvisisted.add(curr);
      }
    }
    return unvisisted;
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
