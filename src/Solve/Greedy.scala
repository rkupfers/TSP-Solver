package Solve

import Main.ITSP

class Greedy(protected  val n:Int, protected val matrix: Array[Array[Double]] ) extends ITSP {
//  override val n = num;
  
  def greedyWay(way: List[Int], used: List[Int], cur: Int, m: Array[Array[Double]]): List[Int] = {
    if (used.isEmpty) return way
    val min = getMin(used, cur, m)
    val col = m(cur).indexOf(min)
    val nextUsed = used.filter(_ != col)
    return greedyWay(way :+ col, nextUsed, col, m)
  }

  def getMin(used: List[Int], col: Int, m: Array[Array[Double]]): Double = {
    var min = Double.MaxValue
    for (i <- used) { if (m(col)(i) < min) min = m(col)(i) }
    return min;
  }

  def solve(): (Double, List[Int]) = {
    //println(getMin((0 to matrix.size - 1).toList, 0, matrix))
    val way = greedyWay(List(), (0 to matrix.size - 1).toList, 0, matrix)
    return (Math.round((distance(way)*100))/100, way)
  }

}