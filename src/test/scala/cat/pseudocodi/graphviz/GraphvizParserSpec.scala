package cat.pseudocodi.graphviz

import org.scalatest.FlatSpec

/**
 * @author fede
 */
class GraphvizParserSpec extends FlatSpec {

  it should "produce a graph with list of nodes" in {

    val actual: DependencyGraph = GraphvizParser.parse(getClass.getResource("example.xml").getFile)

    val s1 = new Step("Break window")
    val s2 = new Step("Climb window")
    val s3 = new Step("Find oil can")
    val s4 = new Step("Oil hinges")
    val s5 = new Step("Open basement door")
    val s6 = new Step("Find key behind plant")
    val s7 = new Step("Unlock basement door")

    val expected: List[Step] = List[Step](s1, s2, s3, s4, s5, s6, s7)
    assert(actual.nodes.diff(expected).isEmpty)
  }

}
