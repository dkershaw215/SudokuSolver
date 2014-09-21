package com.krush.sudokusolver.model

import scala.collection.immutable.TreeMap

  case class Pos(row: Char, col: Int) extends Ordered[Pos] {

    require(isValid(), "Invalid Element, this position is outside the 9x9 grid")
    
    def vPeers(): Set[Pos] = Grid.rowRef map(Pos(_, col)) filter (_.row != row ) toSet

    def hPeers(): Set[Pos] = Grid.colRef map (Pos(row, _)) filter (_.col != col ) toSet

    def squarePeers(): Set[Pos] = Grid.quadrants.filter(_.exists(p => p.col == col && p.row == row)).apply(0).filter(p => !(p.col == col && p.row == row)).toSet

    def peers(): Set[Pos] = squarePeers() ++ hPeers() ++ vPeers()

    def isValid(): Boolean = Grid.colRef.contains(col) && Grid.rowRef.contains(row)
    
    def compare(that: Pos): Int = this.row.toString + this.col.toString compare that.row.toString + that.col.toString

  }

object Grid {

    val rowRef = List('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
  
    val colRef = List.range(1,10)
    
    private def getQuadrants(): List[List[Pos]] = {
      for {
        rowGroup <- rowRef.grouped(3).toList
        colGroup <- colRef.grouped(3).toList
        q <- List(for ( r <- rowGroup; c <- colGroup ) yield Pos(r, c))
       } yield q
    }

  lazy val quadrants = getQuadrants()


}

trait Grid {

    /**
   * The State is represented as a function from positions to
   * booleans. The function returns `true` for every defined position that
   * is valid.
   */
  type State = Pos => Boolean

  type Grid =  TreeMap[Pos, List[Int]]

  /**
   * The current of this game. This value is left abstract.
   */

  
  def gridFunction(gridMap: Grid): State = p =>  (p.peers() forall (l => gridMap(p) != gridMap(l) ))
  
  //private def splitN[A](list: List[A], n: Int): List[List[A]] = if (n == 1) List(list) else List(list.head) :: splitN(list.tail, n - 1)
    
  val grid: Grid
  
  def isValid(g: Grid): Boolean = g.filter( _._2.size == 1) forall (x => gridFunction(g)(x._1))
  
  def makeString(): String = {
    var gridString = ""
	  for ( r <- Grid.rowRef) {
	      for (c <- Grid.colRef) {
	        val p = Pos(r, c)
			  if (grid(p).size > 1) gridString += "* " else gridString += grid(p)(0) + " "
			  if (List(3,6) contains c) {
			  gridString += "| ";
			  }
		  }
		  gridString += "\r\n";
		  if (List('C', 'F') contains r)
			  gridString += "------+-------+------\r\n";
	  }
    gridString
  }

  def makeString(currentValues: Grid): String = {
    var gridString = ""
	  for ( r <- Grid.rowRef) {
	      for (c <- Grid.colRef) {
	        val p = Pos(r, c)
    		  if (currentValues(p).size > 1) gridString += "* " else gridString += currentValues(p)(0) + " "
    		  if (List(3,6) contains c) {
    		  gridString += "| ";
    		  }
    	  }
    	  gridString += "\r\n";
    	  if (List('C', 'F') contains r)
    		  gridString += "------+-------+------\r\n";
      }
      gridString
  }

  
  def makeStringAllValues(currentValues: Grid): String = {
    var gridString = ""
	  for ( r <- Grid.rowRef) {
	      for (c <- Grid.colRef) {
	        val p = Pos(r, c)
    		  gridString += currentValues(p).mkString + " "
    		  if (List(3,6) contains c) {
    		  gridString += "| ";
    		  }
    	  }
    	  gridString += "\r\n";
    	  if (List('C', 'F') contains r)
    		  gridString += "------+-------+------\r\n";
      }
      gridString
  }
  
}