import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
  private Graph graph;

  @BeforeEach
  void setUp() throws Exception {
    graph = new Graph();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  /**
   * Basic insert
   */
  @Test
  void testAddVertexBasic() {
    graph.addVertex("ariel");
    assertTrue(graph.order() == 1);
  }

  /**
   * Test adding multiple vertices and confirming the order is increasing
   * linearly
   */
  @Test
  void testAddMultipleVertices() {
    graph.addVertex("pea");
    graph.addVertex("peas");
    graph.addVertex("peabody");
    graph.addVertex("peacocks");
    assertTrue(graph.order() == 4);
    Set<String> vertices = graph.getAllVertices();
    ArrayList<String> listOfVertices = new ArrayList<String>();
    listOfVertices.add("pea");
    listOfVertices.add("peas");
    listOfVertices.add("peabody");
    listOfVertices.add("peacocks");
    Object[] setToArray = vertices.toArray();
    for (int i = 0; i < listOfVertices.size(); i++) {
      if (vertices.contains(listOfVertices.get(i))) {
        vertices.remove(listOfVertices.get(i));
      }
    }
    // should remove all the items in the set
    assertTrue(vertices.size() == 0);

  }

  @Test
  void testAddNull() {
    try {
      graph.addVertex(null);
      assertTrue(graph.order() == 0);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testAddAVertexAlreadyInGraph() {
    graph.addVertex("ew peas");
    try {
      graph.addVertex("ew peas");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests an easy remove
   */
  @Test
  void testRemoveVertexEasy() {
    graph.addVertex("easy peasy");
    graph.removeVertex("easy peasy");
    assertTrue(graph.order() == 0);
  }

  /**
   * Tests removing a null vertex
   */
  @Test
  void testRemoveVertexNull() {
    try {
      graph.removeVertex(null);
      assertTrue(graph.order() == 0);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests removing a vertex that is not in the graph
   */
  @Test
  void testRemoveNotInGraph() {
    try {
      graph.removeVertex("nothing here");
      assertTrue(graph.order() == 0);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests inserting 10 vertices and removing all of them.
   */
  @Test
  void testInsertRemoveEverything() {
    for (int i = 0; i < 10; i++) {
      graph.addVertex(i + "");
    }
    assertTrue(graph.order() == 10);
    for (int i = 0; i < 10; i++) {
      graph.removeVertex(i + "");
    }
    assertTrue(graph.order() == 0);
  }

  /**
   * Tests inserting an edge without vertices in the graph
   */
  @Test
  void testAddEdgeWithNoVerticesInGraph() {
    graph.addEdge("start", "finish!");
    assertTrue(graph.size() == 1);
    assertTrue(graph.order() == 2);
    List<String> adjacentToStart = graph.getAdjacentVerticesOf("start");
    assertTrue(adjacentToStart.size() == 1);
    assertTrue(adjacentToStart.get(0).equals("finish!"));
  }

  @Test
  void testAddEdgeWithOtherVertices() {
    graph.addEdge("start", "finish");
    graph.addEdge("finish", "start");
    graph.addVertex("1");
    graph.addEdge("1", "finish");
    graph.addVertex("2");
    graph.addEdge("start", "2");
    graph.addVertex("5");
    graph.addEdge("5", "finish");
    graph.addVertex("12");
    graph.addEdge("start", "12");
    graph.addEdge("12", "2");
    graph.addEdge("2", "12");
    graph.addEdge("start", "1");

    // compare start's adjacent/dependent vertices
    List<String> startAdj = graph.getAdjacentVerticesOf("start");
    assertTrue(startAdj.get(0).equals("finish"));
    assertTrue(startAdj.get(1).equals("2"));
    assertTrue(startAdj.get(2).equals("12"));
    assertTrue(startAdj.get(3).equals("1"));
    assertTrue(startAdj.size() == 4);
    // look at finish's adjacent/dependent vertices
    List<String> finishAdj = graph.getAdjacentVerticesOf("finish");
    assertTrue(finishAdj.get(0).equals("start"));
    assertTrue(finishAdj.size() == 1);
  }

  @Test
  void testAddSameEdge() {
    try {
      graph.addEdge("1", "2");
      graph.addEdge("1", "2");
      assertTrue(graph.size() == 1);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testAddNullEdge() {
    try {
      graph.addEdge(null, null);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testRemoveEdge() {
    graph.addEdge("1", "2");
    graph.removeEdge("1", "2");
    assertTrue(graph.size() == 0);
    // Vertices should not be removed
    assertTrue(graph.order() == 2);
  }

  @Test
  void testRemoveEdgeNotInGraph() {
    try {
      graph.removeEdge("0", "2");
      assertTrue(graph.order() == 0);
      assertTrue(graph.size() == 0);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

}
