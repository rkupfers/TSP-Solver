package Solve.MST

object MinSpanTree {
  //Kruskal
  def kruskalMinSpanTree(theEdges: List[edge], size: Int): List[edge] = {
    def takeEdge(edges: List[edgeList], edge: edge): List[edgeList] = {
      // Kante verbunden
      for (e <- edges) {
        if (e.contains(edge)) {
          return edges
        } else if (e.containsOne(edge)) {
          //          println(edge)
          if (e.used.contains(edge.from)) {
            for (es <- edges.filter(_ != e)) {
              if (es.used.contains(edge.to)) {
                return e.takeIn(edge).mergeEdgeList(es) +: edges.filter(a => a != e || a != es)
              }
            }
            return e.takeIn(edge) +: edges.filter(_ != e)
          } else if (e.used.contains(edge.to)) {
            for (es <- edges.filter(_ != e)) {
              if (es.used.contains(edge.from)) {
                return e.takeIn(edge).mergeEdgeList(es) +: edges.filter(a => a != e || a != es)
              }
            }
            return e.takeIn(edge) +: edges.filter(_ != e)
          } else {
            return e.takeIn(edge) +: edges.filter(_ != e)
          }
        }
      }
      // neue Kante
      //      println("Neu: " + edge)
      return new edgeList(List(edge), List(edge.from, edge.to)) +: edges
    }
    val edges = theEdges.sortBy(_.weight)
    val e = edges.foldLeft(List(): List[edgeList])((list, element) =>
      if (list.isEmpty || list(0).edges.size < size)
        takeEdge(list, element)
      else list)

    return e(0).edges
  }
  //Prim
  def primMinSpanTree(theEdges: List[edge], size: Int): List[edge] = {

    def takeEdgeIn(used: List[Node], notUsed: List[Node], result: List[edge]): List[edge] = {
      val possible = used.flatMap(a => a.edges.filter(b => notUsed.foldLeft(false)((bool, c) => bool || c.compare(b.to) || c.compare(b.from))))
      possible match {
        case Nil => result
        case _ =>
          val next = possible.reduceLeft((a, b) => if (a.weight < b.weight) a else b)
          val (nextNode, rest) = notUsed.partition(a => a.n == next.to || a.n == next.from)
          val use = used :+ nextNode.head
          return takeEdgeIn(use, rest, result :+ next)
      }
    }
    val nodes = for (i <- (0 to size).toList) yield new Node(i, theEdges.filter(e => e.from == i || e.to == i))
    val res = takeEdgeIn(List(nodes.head), nodes.tail, List())

    return res
  }
  
  def primMinSpanTreewF(theEdges: List[edge], size: Int): List[edge] = {
    import org.jgrapht._
    import org.jgrapht.graph._
    import scala.collection.JavaConverters._
    class WeightedEdge(val x:Int,val y:Int, val w:Double) extends DefaultWeightedEdge
    val g = {
      val graph = new SimpleWeightedGraph[Int, DefaultWeightedEdge](classOf[DefaultWeightedEdge])
      for (i <- 0 to size) { graph.addVertex(i) }
      for (e <- theEdges) {
        val ed = graph.addEdge(e.from, e.to)
        graph.setEdgeWeight(ed, e.weight);
      }
      graph
    }
    val min = new org.jgrapht.alg.PrimMinimumSpanningTree(g)
    for( i <- min.getMinimumSpanningTreeEdgeSet().asScala.toList) yield
    	new edge(g.getEdgeSource(i),g.getEdgeTarget(i),g.getEdgeWeight(i))
    
  }
}