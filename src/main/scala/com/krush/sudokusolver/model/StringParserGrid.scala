package com.krush.sudokusolver.model

trait StringParserGrid extends Grid {

  val gridString: String
  
  /**
   * This method returns grid function that represents the grid
   * in `gridList`. The list contains parsed version of the `grid`
   * string. For example, the following grid string
   * 
   *   val grid = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
   * 
   * is represented as
   * 
   *   List(List(4, 0, 0, 0, 0, 0, 8, 0, 5),
   *          List(0, 3, 0, 0, 0, 0, 0, 0, 0),
   *          List(0, 0, 0, 7, 0, 0, 0, 0, 0),
   *          List(0, 2, 0, 0, 0, 0, 0, 6, 0),
   *          List(0, 0, 0, 0, 8, 0, 4, 0, 0),
   *          List(0, 0, 0, 0, 1, 0, 0, 0, 0),
   *          List(0, 0, 0, 6, 0, 3, 0, 7, 0),
   *          List(5, 0, 0, 2, 0, 0, 0, 0, 0),
   *          List(1, 0, 4, 0, 0, 0, 0, 0, 0),
   *          )
   *
   * The resulting function should return `true` if the position `pos` is
   * a valid position inside the grid described by `gridList`.
   */
  
  lazy val grid: List[List[Int]] = buildGrid()
  
  def buildGrid(): List[List[Int]] = for ( s <- gridString.grouped(9) toList ) yield s map(_.asDigit) toList
    
  lazy val state: State = gridFunction(grid)

}