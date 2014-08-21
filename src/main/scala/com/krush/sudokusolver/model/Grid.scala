package com.krush.sudokusolver.model

trait Grid {

  val rowRef = List('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
  
  case class Pos(row: Char, col: Int, value: Int) {

    require(peers() forall (p => this != p ), "Invalid Element, this position has a peer of the same value")
    
    require(isValid(), "Invalid Element, this position is outside the 9x9 grid")
    
    def vPeers(): Set[Pos] = rowRef map(Pos(_, col, 0)) filter (_.row != row ) toSet
    
    def hPeers(): Set[Pos] = List.range(1,10) map (Pos(row, _, 0)) filter (_.col != col ) toSet

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
  
  //val state: State
  
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