package com.krush.sudokusolver.model

trait StringParserGrid extends Grid {

  val grid: String
  
  /**
   * This method returns grid function that represents the grid
   * in `gridVector`. The vector contains parsed version of the `grid`
   * string. For example, the following grid
   * 
   *   val grid = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
   * 
   * is represented as
   * 
   *   Vector(Vector('4', '0', '0', '0', '0', '0', '8', '0', '5'),
   *          Vector('0', '3', '0', '0', '0', '0', '0', '0', '0'),
   *          Vector('0', '0', '0', '7', '0', '0', '0', '0', '0'),
   *          Vector('0', '2', '0', '0', '0', '0', '0', '6', '0'),
   *          Vector('0', '0', '0', '0', '8', '0', '4', '0', '0'),
   *          Vector('0', '0', '0', '0', '1', '0', '0', '0', '0'),
   *          Vector('0', '0', '0', '6', '0', '3', '0', '7', '0'),
   *          Vector('5', '0', '0', '2', '0', '0', '0', '0', '0'),
   *          Vector('1', '0', '4', '0', '0', '0', '0', '0', '0'),
   *          )
   *
   * The resulting function should return `true` if the position `pos` is
   * a valid position inside the grid described by `gridVector`.
   */
  
  lazy val vector: Vector[Vector[Int]] =
    Vector(lSplit(List(9, 18, 27, 36, 45, 54, 63, 72, 81), grid).map(str => Vector(str.map(_.toInt): _*)): _*)
    
  lazy val state: State = gridFunction(vector)
    
  def lSplit(pos: List[Int], s: String): List[String] = pos match {
    case x :: rest => s.substring(0,x) :: lSplit(rest.map(_ - x), s.substring(x))
    case Nil => List(s)
  }
}