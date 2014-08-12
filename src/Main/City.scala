package Main

import java.sql.DriverManager
import java.sql.Connection

class City(rand: Boolean) {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost/mysql"
  val username = "root"
  val password = "****"
  val connection = DriverManager.getConnection(url, username, password)
  val select = {
    if (rand)
      "SELECT name,lat,lng FROM opengeodb.city order by id asc"
    else "SELECT name,lat,lng FROM opengeodb.city order by rand()"
  }

  def getCitys(number: Int): List[(String, Double, Double)] = {
    try {
      Class.forName(driver)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(select)
      //      val resultSet = statement.executeQuery("SELECT name,lat,lng FROM opengeodb.city order by rand()")
      return List.fill(number)(if (resultSet.next)
        (resultSet.getString("name"), resultSet.getDouble("lat"), resultSet.getDouble("lng"))
      else null)
    } catch {
      case e: Exception =>
        e.printStackTrace
        return null
    }
  }

  //http://rosettacode.org/wiki/Haversine_formula#Scala
  def distance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double = {
    import scala.math._;
    val R = 6372.8 //radius in km
    val dLat = (lat2 - lat1).toRadians
    val dLon = (lng2 - lng1).toRadians

    if (dLat == 0 && dLon == 0)
      return Double.MaxValue

    val a = pow(sin(dLat / 2), 2) + pow(sin(dLon / 2), 2) * cos(lat1.toRadians) * cos(lat2.toRadians)
    val c = 2 * asin(sqrt(a))
    return (R * c * 100).round.toDouble / 100
  }

  def distanceMatrix(list: List[(String, Double, Double)]): Array[Array[Double]] = {
    def distances(c: (String, Double, Double), list: List[(String, Double, Double)]): Array[Double] = for (l <- (list).toArray) yield distance(c._2, c._3, l._2, l._3)
    for (l <- list.toArray)
      yield distances(l, list)
  }

}
