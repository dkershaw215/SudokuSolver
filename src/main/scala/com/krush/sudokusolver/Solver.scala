package com.krush.sudokusolver

import com.krush.sudokusolver.model.Grid
import com.krush.sudokusolver.model.StringParserGrid

import scala.collection.mutable.Map

trait Solver extends Grid with StringParserGrid {

  def solve(): Grid = {
    
    def done(checkMap: scala.collection.immutable.Map[Pos, List[Int]]): Boolean = checkMap.forall(_._2.size == 1)
    
    def solveRec(current: scala.collection.immutable.Map[Pos, List[Int]]):scala.collection.immutable.Map[Pos, List[Int]] = {
        if (done(current)) return current
        else {
        	  for ( r <- rowRef) {
      	      for (c <- colRef) {
  	            val p = Pos(r, c)
	              if (current(p).size > 1) {
	                 for (v <- current(p)) {
	                   val testMap = current.updated(p, List(v))
  	                 val testGrid = gridFunction(testMap)
	                   if (testGrid(p)) {
	                     val elimateMoreMap = eliminateRec(collection.mutable.Map(testMap.toSeq: _*), p, v, true)
	                     val result = solveRec(collection.immutable.Map(elimateMoreMap.toSeq: _*))
	                     if (!result.isEmpty) return result
	                   }
	                 }
                   return scala.collection.immutable.Map[Pos, List[Int]]()
	              }
      	      }
        	  }      	      
            return scala.collection.immutable.Map[Pos, List[Int]]()
        }
    }

    var map = collection.mutable.Map(grid.toSeq: _*) 
    
    for ( (k, v) <- map.filter( _._2.size == 1) ) {
        map = eliminateRec(map, k, v(0), true)
    }
    
    solveRec(collection.immutable.Map(map.toSeq: _*) )

  }

  
  def eliminateRec(currentValues: scala.collection.mutable.Map[Pos, List[Int]], p: Pos, v: Int, initElim: Boolean):scala.collection.mutable.Map[Pos, List[Int]] = {
      if (!(currentValues(p) contains v))
          currentValues
      else {
          if (currentValues(p).size > 1) {
            val values = currentValues(p)
            currentValues.put(p, currentValues(p).filter(_ != v))
            val currentGrid = gridFunction(collection.immutable.Map(currentValues.toSeq: _*))
            if (!currentGrid(p)) {
              currentValues.put(p, values)
              currentValues
            }
          }
          if (initElim || currentValues(p).size == 1) {
            for (ps <- p.peers()) {
              eliminateRec(currentValues, ps, currentValues(p)(0), false)
            }
          }
          currentValues
      }
  }
  
}

object SolverMain {
  
  def main(args: Array[String]) {
        new Solver {
          
           val t1 = System.currentTimeMillis
           val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
           //val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005"
           //val gridString = "000090820010000509709010000062701090000060000080309140000080902804000030016030000"
           val solution = solve()
           val t2 = System.currentTimeMillis
           println("Solution is:\n" + makeString(solution))
           println((t2 - t1) + " msecs")

        }
  }
  
}