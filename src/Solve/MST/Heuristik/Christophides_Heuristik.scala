package Solve.MST.Heuristik

import Main.ITSP
import Solve.MST.MST
import Solve.MST.MinSpanTree
import Solve.MST.edge
import java.util.ArrayList

class Christophides_Heuristik(protected  val n:Int,protected val matrix: Array[Array[Double]] ) extends ITSP {
//  override val n = num;
  
  def solve(): (Double, List[Int]) = {
    val MST = new MST(MinSpanTree.kruskalMinSpanTree)
    val edgelist = makeEdgeList(matrix)
    val minSpantree = MST.minSpanTree(edgelist, matrix.size - 1)
    val extendedMinSpanTree = Christophides(minSpantree, edgelist)
    println(extendedMinSpanTree)
    val way = MST.eulerTour(extendedMinSpanTree.sortBy(_.weight))
    return (distance(way).round, way)
  }

  def Christophides(minSpanTree: List[edge], fullNodes: List[edge]): List[edge] = {
    val unevenNodes = removeEvenNodes(minSpanTree)
    println(perfectMatching(unevenNodes, fullNodes))
    minSpanTree ++ perfectMatching(unevenNodes, fullNodes)
  }

  def removeEvenNodes(list: List[edge]): List[Int] = {
    def isEven(node: Int, list: List[edge]): Boolean = list.filter(a => a.from == node || a.to == node).size % 2 == 0
    (0 until matrix.size).toList.filterNot(isEven(_, list))
  }

  def perfectMatching(UnevenNodes: List[Int], fullNodes: List[edge]): List[edge] = {
//    import org.jgrapht._
//    import org.jgrapht.graph._
//    import scala.collection.JavaConverters._
//
//    val usedEdges = fullNodes.filter(a => UnevenNodes.contains(a.from) && UnevenNodes.contains(a.to))
//    
//    val g = {
//      val graph = new SimpleWeightedGraph[Int, DefaultWeightedEdge](classOf[DefaultWeightedEdge])
//      for (i <- UnevenNodes) { graph.addVertex(i) }
//      for (e <- usedEdges) {
//        val ed = graph.addEdge(e.from, e.to)
//        graph.setEdgeWeight(ed, e.weight);
//      }
//      graph
//    }
//    println(g)
//    
//    val ed = new EdmondsBlossomShrinking(g)
//    val perfectMatch = ed.findMatch(g).asScala
//    println(perfectMatch)
    List()
  }
}