package Main

import Solve.MST.Heuristik.MST_Heuristik
import Solve.MST.Heuristik.Christophides_Heuristik
import Solve.Greedy
import Solve.MST.MinSpanTree
import java.io.PrintWriter
import Solve.Correct

object Solver extends App {
  val pw = new PrintWriter("Ergebnis.csv")

  val from = 1
  val to = 25
  val step = 1
  pw.println("MST;Greedy;Correct")
  for (i <- from to (to, step)) {
    pw.print(i+";")
    val c = new City(false)
    val citys = c.getCitys(10)
    val m = c.distanceMatrix(citys)
    val s1 = new MST_Heuristik(i,MinSpanTree.primMinSpanTreewF,m)
    val s2 = new Greedy(i,m)
    val s3 = new Correct(i,m)
//    for (j <- 1 to 25) {
//      val s = System.currentTimeMillis()
      val e1 = s1.solve
      val e2 = s2.solve
      val e3 = s3.solve
      pw.println(e1._1+";"+e2._1+";"+e3._1)
//      pw.print((System.currentTimeMillis() - s) + ";")
      println(i) 
      System.gc()
      //      println("Correct Value: " + e._1)
//    }
//    pw.println()
  }
  //  println("MST prim:")
  //  for (i <- from to (to, step)) {
  //    pw.print("MST Prim, " + i + " City's;")
  //    val s1 = new MST_Heuristik(i, MinSpanTree.primMinSpanTree)
  //    for (j <- 1 to 20) {
  //      val s = System.currentTimeMillis()
  //      val e = s1.solve
  //      pw.print((System.currentTimeMillis() - s) + ";")
  //      //      println("Correct Value: " + e._1)
  //    }
  //    pw.println()
  //  }
  //  println("Greedy Alg:")
  //  for (i <- from to (to, step)) {
  //    pw.print("Greedy Alg, " + i + " City's;")
  //    val s1 = new Greedy(i)
  //    for (j <- 1 to 20) {
  //      val s = System.currentTimeMillis()
  //      val e = s1.solve
  //      pw.print((System.currentTimeMillis() - s) + ";")
  //      //      println("Correct Value: " + e._1)
  //    }
  //    pw.println()
  //  }
  //  for (i <- 5 to 10) {
  //    pw.print("Correct Alg, " + i + " City's;")
  //    val s1 = new Correct(i)
  //    for (j <- 1 to 25) {
  //      val s = System.currentTimeMillis()
  //      val e = s1.solve
  //      pw.print((System.currentTimeMillis() - s) + ";")
  //      println(i)
  //    }
  //    pw.println()
  //  }
  //  println("MST kruskal:")
  //  for (i <- 1 to 50) {
  //    pw.print("MST Kruskal: ;")
  //    val s1 = new MST_Heuristik(50, MinSpanTree.kruskalMinSpanTree)
  //    val e = s1.solve
  //    pw.println("Correct Value: " + e._1)
  //    pw.println()
  //  }
  pw.flush()

}