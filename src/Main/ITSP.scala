package Main

import Solve.MST.edge

trait ITSP {
  protected val n:Int 
  def num = n
  
  protected val matrix: Array[Array[Double]] 
  
  
  def solve(): (Double,List[Int])
  
  def makeEdgeList(m: Array[Array[Double]]): List[edge] = {
    def inner(m: Array[Array[Double]], index: Int): List[edge] = {
      if (index == matrix.size - 1)
        return List()
      else {
        val list = List.tabulate(m.size - (index + 1))(j => new edge(index, index + 1 + j, m(index)(index + 1 + j)))
        return list ++ inner(m, index + 1)
      }
    }
    return inner(m, 0)
  }

  def distance(l: List[Int]): Double = {
    def innerDistance(l: List[Int], start: Int): Double = {
      l match {
        case first +: second +: rest => return matrix(first)(second) + innerDistance((second +: rest), start)
        case first +: rest => return matrix(first)(start)
        case Nil => return 0
      }
    }
    return innerDistance(l.tail, l.head) + matrix(l.head)(l.tail.head)
  }
}