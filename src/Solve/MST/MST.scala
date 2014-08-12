package Solve.MST

import Main.ITSP

class MST(minspantree : (List[edge],Int) => List[edge] ) {

  def minSpanTree = minspantree

  def eulerTour(tree: List[edge]): List[Int] = {
    def innerEulerTour(tree: List[edge], tour: List[Int]): List[Int] = {
      val nextValues = tree.filter(e => e.from == tour.last || e.to == tour.last)
      if (nextValues.isEmpty) {
        if (tree.isEmpty) return tour
        val branch = innerEulerTour(tree, tour.dropRight(1))
        return (tour ++ branch.drop(tour.size - 1))
      }
      val ntree = tree.filter(_ != nextValues.head)
      if (nextValues.head.from == tour.last) {
        return innerEulerTour(ntree, tour :+ nextValues.head.to)
      } else {
        return innerEulerTour(ntree, tour :+ nextValues.head.from)
      }
    }
    val sort = tree.sortBy(_.weight)
    return innerEulerTour(sort, List(sort.head.from))
  }

}