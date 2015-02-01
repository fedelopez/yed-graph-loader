package cat.pseudocodi.graphviz

import scala.collection.immutable.Seq
import scala.xml.{Elem, NodeSeq, Text, XML}


/**
 * @author fede
 */

case class Node(id: String, name: String)

case class Edge(nodeA: Node, nodeB: Node)

case class Graph(nodes: List[Node], edges: List[Edge])

object GraphvizParser {

  def parse(fileName: String): Graph = {
    val xml: Elem = XML.loadFile(fileName)
    val attributes: NodeSeq = xml \\ "section" \ "attribute"

    val names: Seq[String] = filter(attributes, "label").map((n: scala.xml.Node) => prettify(n.text)).filterNot(_.isEmpty)
    val nameIds: Seq[String] = filter(attributes, "id").map((n: scala.xml.Node) => n.text)
    val nodes: Seq[Node] = nameIds.zip(names).map((tuple: (String, String)) => new Node(tuple._1, prettify(tuple._2)))

    val sourceIds: Seq[String] = filter(attributes, "source").map(_.text)
    val targetIds: Seq[String] = filter(attributes, "target").map(_.text)
    val edges: Seq[Edge] = sourceIds.zip(targetIds).map((tuple: (String, String)) => new Edge(findNodeById(tuple._1, nodes), findNodeById(tuple._2, nodes)))

    new Graph(nodes.toList, edges.toList)
  }

  def prettify(t: String): String = t.replaceAll("\n", "").replaceAll("\\s+", " ").trim

  def filter(seq: NodeSeq, keyText: String): NodeSeq = {
    seq filter (_ \ "@key" contains Text(keyText))
  }

  def findNodeById(id: String, nodes: Seq[Node]): Node = {
    nodes.find((node: Node) => id.equals(node.id)).get
  }
}
