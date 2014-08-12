package Solve.MST.Heuristik

import Main.ITSP
import Solve.MST.edge
import Solve.MST.edgeList
import Solve.MST.MST
import Solve.MST.MinSpanTree

class MST_Heuristik(protected  val n:Int, minspantree : (List[edge],Int) => List[edge],protected val matrix: Array[Array[Double]] ) extends ITSP {
//  override val n = num;

  def solve(): (Double, List[Int]) = {
    val MST = new MST(minspantree)
//    val st = System.currentTimeMillis
    val minSpantree = MST.minSpanTree(makeEdgeList(matrix), matrix.size - 1)
//    System.err.println("Time for MST: " + (System.currentTimeMillis-st))
//    println(minSpantree.sortBy(_.weight))
//    println(minSpantree.foldLeft(0.0)((a,b)=> a + b.weight ))
//    val s = System.currentTimeMillis
    val way = MST.eulerTour(minSpantree)
//    println("Time for Euler-Tour: " + (System.currentTimeMillis-s))
    return (Math.round((distance(way)*100.0))/100.0, way)
  }

}