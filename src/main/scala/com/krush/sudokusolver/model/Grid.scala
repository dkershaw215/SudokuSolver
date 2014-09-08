package com.krush.sudokusolver.model

trait Grid {

  val rowRef = List('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
  
  val colRef = List.range(1,10)
  
  //val values = Map[Pos, List[Int]]()
  
  case class Pos(row: Char, col: Int) {

    require(isValid(), "Invalid Element, this position is outside the 9x9 grid")
    
    def vPeers(): Set[Pos] = rowRef map(Pos(_, col)) filter (_.row != row ) toSet

    def vPeers(peerGrid: Grid): Set[Pos] = rowRef map(Pos(_, col)) filter (_.row != row ) toSet

    def hPeers(): Set[Pos] = colRef map (Pos(row, _)) filter (_.col != col ) toSet

    def hPeers(peerGrid: Grid): Set[Pos] = colRef map (Pos(row, _)) filter (_.col != col ) toSet

    def squarePeers(): Set[Pos] = quadrants.filter(_.exists(p => p.col == col && p.row == row)).apply(0).filter(p => !(p.col == col && p.row == row)).toSet

    def squarePeers(peerGrid: Grid): Set[Pos] = getQuadrants(peerGrid).filter(_.exists(p => p.col == col && p.row == row)).apply(0).filter(p => !(p.col == col && p.row == row)).toSet

    def peers(): Set[Pos] = squarePeers() ++ hPeers() ++ vPeers()

    def peers(peerGrid: Grid): Set[Pos] = squarePeers(peerGrid) ++ hPeers(peerGrid) ++ vPeers(peerGrid)
    
    def isValid(): Boolean = colRef.contains(col) && rowRef.contains(row)

  }
  
    /**
   * The State is represented as a function from positions to
   * booleans. The function returns `true` for every defined position that
   * is valid.
   */
  type State = Pos => Boolean

  type Grid =  Map[Pos, List[Int]]

  /**
   * The current of this game. This value is left abstract.
   */

  
  def gridFunction(gridMap: Map[Pos, List[Int]]): Pos => Boolean = p => {
    val peers = p.peers(gridMap)
    (peers forall (l => gridMap(p) != gridMap(l) ))
  }
  
  private def splitN[A](list: List[A], n: Int): List[List[A]] = if (n == 1) List(list) else List(list.head) :: splitN(list.tail, n - 1)
  
  private def getQuadrants(): List[List[Pos]] = {
    for {
      rowGroup <- rowRef.grouped(3).toList
      colGroup <- List.range(1,10).grouped(3).toList
      q <- List(for ( r <- rowGroup; c <- colGroup ) yield Pos(r, c))
     } yield q
  }

  private def getQuadrants(peerGrid: Grid): List[List[Pos]] = {
    for {
      rowGroup <- rowRef.grouped(3).toList
      colGroup <- List.range(1,10).grouped(3).toList
      q <- List(for ( r <- rowGroup; c <- colGroup ) yield Pos(r, c))
     } yield q
  }

  lazy val quadrants = getQuadrants()
  
  val grid: Grid
  
  val state: State
  
  def makeString(): String = {
    var gridString = ""
	  for ( r <- rowRef) {
	      for (c <- colRef) {
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
      return gridString
  }

  def makeString(currentValues:  Map[Pos, List[Int]]): String = {
    var gridString = ""
	  for ( r <- rowRef) {
	      for (c <- colRef) {
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
      return gridString
  }

  
  def makeStringAllValues(currentValues:  Map[Pos, List[Int]]): String = {
    var gridString = ""
	  for ( r <- rowRef) {
	      for (c <- colRef) {
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
      return gridString
  }

  def makeString(currentValues:  collection.mutable.Map[Pos, List[Int]]): String = makeString(collection.immutable.Map(currentValues.toSeq: _*))

  def makeStringAllValues(currentValues:  collection.mutable.Map[Pos, List[Int]]): String = makeStringAllValues(collection.immutable.Map(currentValues.toSeq: _*))
  
}