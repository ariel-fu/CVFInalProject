import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filename: Graph.java Project: p4 Author(s):Ariel Fu Email address(es):
 * afu5@wisc.edu Due date: April 10th 2020 Known bugs: Directed and unweighted
 * graph implementation
 */

public class Graph implements GraphADT {

  /**
   * Private class that models a Vertex in the Graph
   * 
   * @author Ariel
   *
   */
  private class Vertex {
    private String data; // data of the Vertex
    private List<Vertex> adjacentVertices;

    /**
     * No-arg constructor that sets data to empty String and initializes the
     * adjacent vertices list.
     */
    private Vertex() {
      this.data = null;
      adjacentVertices = new ArrayList<Vertex>();
    }

    /**
     * Constructor that sets the data to the input and initializes the adjacent
     * vertices list.
     * 
     * @param data - data of the Vertex (name, label, etc.)
     */
    private Vertex(String data) {
      this.data = data;
      adjacentVertices = new ArrayList<Vertex>();
    }

    /**
     * Getter method for the data
     * 
     * @return - the data of the Vertex
     */
    private String getData() {
      return data;
    }

    /**
     * Setter method for the new data
     * 
     * @param newData - this Vertex's new data (name, label, etc.)
     */
    private void setData(String newData) {
      this.data = newData;
    }

    /**
     * Getter method for the adjacency vertices list.
     * 
     * @return the list of adjacency vertices
     */
    private List<String> getAdjacentVertices() {
      List<String> verticeData = new ArrayList<String>();
      for (int i = 0; i < adjacentVertices.size(); i++) {
        verticeData.add(adjacentVertices.get(i).getData());
      }
      return verticeData;
    }

    /**
     * Adds a vertex to this vertex's adjacent vertices list
     * 
     * @param vertex - another adjacent vertex
     */
    private void addAdjacentVertex(Vertex vertex) {
      adjacentVertices.add(vertex);
    }
//    TODO: possible idea to assign an index to a vertex
    // similar to hash table...
//    private int setIndex(int numInserted) {
//      return numInserted
//    }

  }

  /**
   * This class models an Edge which is really two vertices
   * 
   * @author Ariel
   *
   */
  private class Edge {
    Vertex source; // the start of an edge
    Vertex destination; // the vertex that the edge points to

    /**
     * No-arg constructor that sets the source and destination to a null Vertex
     */
    private Edge() {
      this.source = new Vertex();
      this.destination = new Vertex();
    }

    /**
     * A constructor that takes in a source and a destination
     * 
     * @param source      - source of the edge
     * @param destination - where the edge points to
     */
    private Edge(Vertex source, Vertex destination) {
      this.source = source;
      this.destination = destination;
    }

    /**
     * Gets the source of the edge
     * 
     * @return source of the edge
     */
    private Vertex getSource() {
      return source;
    }

    /**
     * Gets the destination of the edge
     * 
     * @return destination of the edge
     */
    private Vertex getDestination() {
      return destination;
    }

  }

  private class AdjacencyMatrix {
    boolean[][] matrix;

    /**
     * No-arg constructor that initializes the matrix to 20 by 20.
     */
    private AdjacencyMatrix() {
      matrix = new boolean[20][20];
    }

    /**
     * Inserts the vertex into the specified row and column
     * 
     * @param row - row is determined by the place it was inserted in the vertex
     *            list - source
     * @param col - determined by the place it was inserted in the vertex list -
     *            destination
     */
    private void insert(int row, int col) {
      // if either the source or the destination is null, do nothing
      if (col > matrix.length) {
        this.expand();
      } else if (row > matrix.length) {
        // if the row (also the col) is less than the length of the matrix,
        // expand the matrix
        this.expand();
      } else {
        // otherwise, set the [col][row] to true (there is an edge between the
        // source vertex and the destination vertex)
        matrix[col][row] = true;
      }
      return;
    }

    /**
     * Removes an edge from the matrix by turnign [col][row] to false
     * 
     * @param row - source of the edge
     * @param col - destination of the edge
     * @throws IllegalArgumentException - if the row or the col is out of bounds
     * @return true if it was successfully remove, false if there wasn't an edge
     * 
     */
    private boolean remove(int row, int col) {
      // if the row or the col is out of bounds, throw an
      // IllegalArgumentException
      if (row > matrix.length) {
        throw new IllegalArgumentException("row is out of bounds.");
      } else if (col > matrix.length) {
        throw new IllegalArgumentException("column is out of bounds.");
      } else {
        boolean hasEdge = matrix[row][col];
        if (hasEdge == true) {
          hasEdge = false;
          return true;
        }
      }
      return false;
    }

    /**
     * Returns whether there was an edge from the vertex at index row to the
     * vertex at index col
     * 
     * @param row - index of the source
     * @param col - index of the destination
     * @return
     */
    private boolean get(int row, int col) {
      if (row > matrix.length) {
        throw new IllegalArgumentException("row is out of bounds.");
      } else if (col > matrix.length) {
        throw new IllegalArgumentException("column is out of bounds.");

      } else {
        return matrix[col][row];
      }

    }

    /**
     * Expands the matrix to double the size
     */
    private void expand() {
      boolean[][] temp = matrix;
      matrix = new boolean[temp.length * 2][temp.length * 2];
      for (int col = 0; col < temp.length; col++) {
        for (int row = 0; row < temp[col].length; row++) {
          matrix[col][row] = temp[col][row];
        }
      }
    }
  }

  private List<Vertex> vertexList;
  private int numEdges;
  private AdjacencyMatrix am;

  /**
   * Default no-arg constructor, initializes the lists and the adjacency matrix
   */
  public Graph() {
    vertexList = new ArrayList<Vertex>();
    numEdges = 0;
    am = new AdjacencyMatrix();
  }

  /**
   * Adds a vertex to the graph, if the vertex is already in the graph, do
   * nothing! If the input is null, do nothing!
   * 
   * @param vertex - new vertex to add to the graph
   */
  @Override
  public void addVertex(String vertex) {
    if (vertex == null) {
      return;
    } else if (this.contains(vertex)) {
      return;
    }
    vertexList.add(new Vertex(vertex));
  }

  /**
   * Removes a vertex from the graph. If the vertex is null, do nothing. If the
   * vertex is not in the graph, do nothing.
   */
  @Override
  public void removeVertex(String vertex) {
    if (vertex == null) {
      return;
    } else if (!this.contains(vertex)) {
      return;
    } else {
      int vertexIndex = this.getIndexOf(vertex);
      vertexList.remove(vertexIndex);
    }
  }

  /**
   * Adds an edge to the graph. If either of them are null, do not add anything.
   * If vertex1 or vertex2 are not in the graph, add them. If the edge already
   * exists, do nothing.
   * 
   * @param vertex1 - the first vertex (src)
   * @param vertex2 - the second vertex (dst)
   * 
   */
  @Override
  public void addEdge(String vertex1, String vertex2) {
    // check if vertex1 or vertex2 is null
    if (vertex1 == null) {
      return;
    } else if (vertex2 == null) {
      return;
    }
    int srcIndex = this.getIndexOf(vertex1);
    int dstIndex = this.getIndexOf(vertex2);
    // check if a vertex1 and vertex2 are in the graph,
    // if they aren't, add them to the graph
    if (srcIndex == -1) {
      this.addVertex(vertex1);
    }
    if (dstIndex == -1) {
      this.addVertex(vertex2);
    }
    srcIndex = this.getIndexOf(vertex1);
    dstIndex = this.getIndexOf(vertex2);

    // return if the edge is already in the graph
    if (am.get(srcIndex, dstIndex)) {
      return;
    }
    // add a new edge to the adjacency matrix
    am.insert(srcIndex, dstIndex);
    // increment the number of edges in the graph
    numEdges++;

    // add the destination vertex to the src vertex's list of adjacent
    // vertices.
    Vertex srcVertex = vertexList.get(srcIndex);
    Vertex dstVertex = vertexList.get(dstIndex);
    srcVertex.addAdjacentVertex(dstVertex);

  }

  /**
   * Removes an edge from the graph. If the either vetex doesn't exist or the
   * edge doesn't exist, do nothing.
   * 
   * @param vertex1 - the source of the edge
   * @param vertex2 - the destination of the edge
   */
  @Override
  public void removeEdge(String vertex1, String vertex2) {
    if (vertex1 == null) {
      return;
    } else if (vertex2 == null) {
      return;
    }
    int srcIndex = this.getIndexOf(vertex1);
    int dstIndex = this.getIndexOf(vertex2);
    // check if a vertex1 and vertex2 are in the graph,
    // if they aren't, add them to the graph
    if (srcIndex == -1) {
      return;
    } else if (dstIndex == -1) {
      return;
    } else {
      // decrement the number of edges in the graph
      numEdges--;
      // set the edge in the adjacency matrix to false
      am.remove(srcIndex, dstIndex);
    }
  }

  /**
   * Returns all the vertices in the graph
   * 
   * @return a Set of all the vertices in the graph.
   */
  @Override
  public Set<String> getAllVertices() {
    Set verticesSet = new HashSet<String>();
    for (int i = 0; i < vertexList.size(); i++) {
      verticesSet.add(vertexList.get(i).getData());
    }
    return verticesSet;
  }

  /**
   * Gets all adjacent dependencies of the vertex
   * 
   * @param vertex - data of the vertex
   * @return a list of adjacent-dependencies of the vertex
   */
  @Override
  public List<String> getAdjacentVerticesOf(String vertex) {
    if (vertex == null) {
      return null;
      // TODO: ask on M OH
    }
    int vertexIndex = this.getIndexOf(vertex);
    if (vertexIndex == -1) {
      return null;
      // TODO: ask on M
    }
    Vertex actualVertex = vertexList.get(vertexIndex);
    return actualVertex.getAdjacentVertices();
  }

  /**
   * Size is the number of edges in the graph
   * 
   * @return number of edges in the graph
   */
  @Override
  public int size() {
    return numEdges;
  }

  /**
   * Order of a graph is the number of vertices in the graph
   * 
   * @return the number of vertices
   */
  @Override
  public int order() {
    return vertexList.size();
  }

  /**
   * Helper method that returns whether the graph contains a vertex with the
   * same data
   * 
   * @param data - data of the vertex to find
   * @return true if the graph contains a vertex with this data
   */
  private boolean contains(String data) {
    for (int i = 0; i < vertexList.size(); i++) {
      if (vertexList.get(i).getData().equals(data)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the index of a vertex given the vertex's data
   * 
   * @param data - data of the vertex to get
   * @return index of the vertex in the list of vertices, -1 if the vertex is
   *         not in the list.
   */
  private int getIndexOf(String data) {
    for (int i = 0; i < vertexList.size(); i++) {
      if (vertexList.get(i).getData().equals(data)) {
        return i;
      }
    }
    return -1;
  }

  // TODO: implement all the methods declared in GraphADT

}
