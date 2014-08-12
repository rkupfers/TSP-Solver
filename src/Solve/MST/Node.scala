package Solve.MST

class Node(number: Int, Edges: List[edge]) {
  val edges = Edges
  val n = number
  override def toString() = "Node: " + number.toString + " Edges: " + edges.map(_.toString)
  def compare(i: Int): Boolean = n == i
}