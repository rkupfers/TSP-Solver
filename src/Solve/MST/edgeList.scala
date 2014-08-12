package Solve.MST

class edgeList(Edges: List[edge], Used: List[Int]) {
  val edges = Edges
  val used = Used
  
  def contains(edge: edge): Boolean = used.contains(edge.from) && used.contains(edge.to)
  
  def containsOne(edge: edge): Boolean = used.contains(edge.from) || used.contains(edge.to)
  
  def mergeEdgeList(list:edgeList):edgeList = new edgeList(edges ++ list.edges,used ++ list.used)
  
  def takeIn(edge: edge): edgeList = {
    if (used.contains(edge.to) || !used.contains(edge.from))
      return new edgeList(edges :+ edge, used :+ edge.from)
    else if (!used.contains(edge.to) || used.contains(edge.from))
      return new edgeList(edges :+ edge, used :+ edge.to)
    else return this
  }
}