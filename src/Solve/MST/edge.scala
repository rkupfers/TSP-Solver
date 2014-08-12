package Solve.MST

class edge(From: Int, To: Int, Weight: Double) {
  val from = From
  val to = To
  val weight = Weight

  override def toString(): String = return "(" + from + ", " + to + ", " + weight + ")"
}