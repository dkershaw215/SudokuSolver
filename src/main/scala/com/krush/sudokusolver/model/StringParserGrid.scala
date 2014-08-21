package com.krush.sudokusolver.model

trait StringParserGrid extends Grid {

  val grid: String
  
  /**
   * This method returns grid function that represents the grid
   * in `gridVector`. The vector contains parsed version of the `grid`
   * string. For example, the following grid
   * 
   *   val grid = "4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......"
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
  def gridFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean = p => {
	  if (p.col < 0 || p.col > levelVector.length - 1) false
	  else if ( rowRef.indexOf(p.row) < 0 ) false
	  else levelVector.apply(p.col).apply(rowRef.indexOf(p.row)) != '0' && p.isValid() }
  
  private lazy val vector: Vector[Vector[Char]] =
    Vector(lSplit(List(9, 18, 27, 36, 45, 54, 63, 72, 81), grid).map(str => Vector(str: _*)): _*)

  lazy val state: State = gridFunction(vector)
    
  def lSplit(pos: List[Int], s: String): List[String] = pos match {
    case x :: rest => s.substring(0,x) :: lSplit(rest.map(_ - x), s.substring(x))
    case Nil => List(s)
  }
}