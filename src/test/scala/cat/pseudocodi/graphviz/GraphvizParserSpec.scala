package cat.pseudocodi.graphviz

import org.scalatest.FlatSpec

/**
 * @author fede
 */

trait Setup {
  val graphvizFile: Graph = GraphvizParser.parse(getClass.getResource("example.xml").getFile)

  val n1 = new Node("0", "Break window")
  val n2 = new Node("1", "Climb window")
  val n3 = new Node("3", "Find oil can")
  val n4 = new Node("4", "Oil hinges")
  val n6 = new Node("2", "Find key behind plant")
  val n7 = new Node("5", "Unlock basement door")
  val n5 = new Node("6", "Open basement door")

  val e1 = new Edge(n1, n2)
  val e2 = new Edge(n2, n3)
  val e3 = new Edge(n2, n6)
  val e4 = new Edge(n3, n4)
  val e5 = new Edge(n7, n5)
  val e6 = new Edge(n6, n7)
  val e7 = new Edge(n4, n5)
}

class GraphvizParserSpec extends FlatSpec with Setup {

  it should "produce a graph with list of nodes" in {
    val expected = List(n1, n2, n3, n4, n5, n6, n7)
    val nodes: List[Node] = graphvizFile.nodes
    assert(expected.diff(nodes).isEmpty)
  }

  it should "produce a graph with list of edges" in {
    val expected = List(e1, e2, e3, e4, e5, e6, e7)
    val edges: List[Edge] = graphvizFile.edges
    assert(expected.diff(edges).isEmpty)
  }

}
