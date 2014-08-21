package com.krush.sudokusolver.model

trait Grid {

  val rowRef = List('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
  
  case class Pos(row: Char, col: Int, value: Int) {

    require(isValid(), "Invalid Element, this position is outside the 9x9 grid")
    
    def vPeers(): Set[Pos] = rowRef map(findPos(_, col, vector)) filter (_.row != row ) toSet
    
    def hPeers(): Set[Pos] = List.range(1,10) map (findPos(row, _, vector)) filter (_.col != col ) toSet

    def squarePeers(): Set[Pos] = Set()

    def peers(): Set[Pos] = squarePeers() ++ hPeers() ++ vPeers()
    
    def ==(p: Pos): Boolean =  this.value == p.value

    def !=(p: Pos): Boolean =  this.value != p.value
    
    def isValid(): Boolean = col >= 1 && col <= 9 && "ABCDEFGHI".contains(row)

  }
  
    /**
   * The State is represented as a function from positions to
   * booleans. The function returns `true` for every defined position that
   * is valid.
   */
  type State = Pos => Boolean
  
  /**
   * The current of this game. This value is left abstract.
   */

  
  def gridFunction(gridVector: Vector[Vector[Int]]): Pos => Boolean = p => {
	  if (p.col < 0 || p.col > gridVector.length - 1) false
	  else if ( rowRef.indexOf(p.row) < 0 ) false
	  else gridVector.apply(p.col).apply(rowRef.indexOf(p.row)) != '0' && (p.peers() forall (l => p != l ))}
  
  def findPos(row: Char, col: Int, gridVector: Vector[Vector[Int]]): Pos = Pos(row, col, gridVector.apply(col).apply(rowRef.indexOf(row)))
  
  val vector: Vector[Vector[Int]]
  
  val state: State
  
  val init: State
  
  def makeString(): String = {
      var gridString = ""
	  for ( j <- 'A' to 'I') {
	      for (i <- 1 to 9) {
    		  gridString += ". ";
    		  if (List(3,6) contains i) {
    		  gridString += "| ";
    		  }
    	  }
    	  gridString += "\r\n";
    	  if (List('C', 'F') contains j)
    		  gridString += "------+-------+------\r\n";
      }
      return gridString
  }
  
}