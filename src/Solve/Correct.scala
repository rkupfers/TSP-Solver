package Solve

import scala.collection.immutable.List
import Main.ITSP

class Correct(protected  val n:Int,protected val matrix: Array[Array[Double]] ) extends ITSP {
//  override val n = num;
  def ways(max: Int): Iterator[List[Int]] = (0 to max - 1).toList.permutations

  def solve(): (Double, List[Int]) = {
    val min = ways(matrix.size).minBy(distance(_))
    return (Math.round((distance(min)*100))/100, min)
  }

  def all(): Unit = ways(matrix.size).foreach(e => println(e + ": Distance:" + distance(e)))

}