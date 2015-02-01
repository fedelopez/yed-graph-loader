package cat.pseudocodi.graphviz

import scala.collection.immutable.Seq
import scala.xml._


/**
 * @author fede
 */

case class Step(name: String)

case class Edge(nodeA: Step, nodeB: Step)

case class DependencyGraph(nodes: List[Step], edges: List[Edge])

object GraphvizParser {

  def parse(fileName: String): DependencyGraph = {
    val xml: Elem = XML.loadFile(fileName)
    val attributes: NodeSeq = xml \\ "section" \ "attribute"
    val names: NodeSeq = attributes filter (_ \ "@key" contains Text("label"))
    val map: Seq[Step] = names.filterNot(_.text.isEmpty).map((n: Node) => new Step(n.text.replaceAll("\n", " ").replaceAll("\\s+", " ").trim))
    new DependencyGraph(map.toList, List())
  }

}
