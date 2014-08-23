package com.krush.sudokusolver.model

trait Grid {

  val rowRef = List('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
  
  val colRef = List.range(1,10)
  
  case class Pos(row: Char, col: Int, value: Int) {

    require(isValid(), "Invalid Element, this position is outside the 9x9 grid")
    
    def vPeers(): Set[Pos] = rowRef map(findPos(_, col, grid)) filter (_.row != row ) toSet
    
    def hPeers(): Set[Pos] = colRef map (findPos(row, _, grid)) filter (_.col != col ) toSet

    def squarePeers(): Set[Pos] = quadrants.filter(_.exists(p => p.col == col && p.row == row)).apply(0).filter(p => !(p.col == col && p.row == row)).toSet

    def peers(): Set[Pos] = squarePeers() ++ hPeers() ++ vPeers()
    
    def ==(p: Pos): Boolean =  this.value == p.value

    def !=(p: Pos): Boolean =  this.value != p.value
    
    def isValid(): Boolean = colRef.contains(col) && rowRef.contains(row)

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

  
  def gridFunction(gridList: List[List[Int]]): Pos => Boolean = p => gridList.apply(p.col - 1).apply(rowRef.indexOf(p.row)) != 0 && (p.peers() forall (l => p != l ))
  
  def findPos(row: Char, col: Int, gridList: List[List[Int]]): Pos = Pos(row, col, gridList.apply(col - 1).apply(rowRef.indexOf(row)))

  
  private def splitN[A](list: List[A], n: Int): List[List[A]] = if (n == 1) List(list) else List(list.head) :: splitN(list.tail, n - 1)
  
  private def getQuadrants(): List[List[Pos]] = {
    for {
      rowGroup <- rowRef.grouped(3).toList
      colGroup <- List.range(1,10).grouped(3).toList
      q <- List(for ( r <- rowGroup; c <- colGroup ) yield findPos(r, c, grid))
     } yield q
  }

  lazy val quadrants = getQuadrants()
  
  val grid: List[List[Int]]
  
  val state: State
  
  val init: State
  
  def makeString(): String = {
    var gridString = ""
	  for ( r <- rowRef) {
	      for (c <- colRef) {
	        val p = findPos(r, c, grid)
    		  if (p.value == 0) gridString += ". " else gridString += p.value + " "
    		  if (List(3,6) contains c) {
    		  gridString += "| ";
    		  }
    	  }
    	  gridString += "\r\n";
    	  if (List('C', 'F') contains r)
    		  gridString += "------+-------+------\r\n";
      }
      return gridString
  }
  
}