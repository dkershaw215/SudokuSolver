package com.krush.sudokusolver

import com.krush.sudokusolver.model.Grid
import com.krush.sudokusolver.model.StringParserGrid

trait Solver extends Grid with StringParserGrid {

  def solve(): Grid = {
    
    def done(checkMap: Grid): Boolean = checkMap.forall(_._2.size == 1)
    
    def solveRec(current: Grid): Grid = {
        if (done(current)) return current
        else {
        	  for ( r <- rowRef; c <- colRef ) {
  	            val p = Pos(r, c)
	              if (current(p).size > 1) {
	                 for (v <- current(p)) {
	                   val testMap = current.updated(p, List(v))
	                   if (gridFunction(testMap)(p)) {
	                     val result = solveRec(eliminateRec(testMap, p, v, true))
	                     if (!result.isEmpty) return result
	                   }
	                 }
                   return Map[Pos, List[Int]]()
	              }
        	  }      	      
            return Map[Pos, List[Int]]()
        }
    }

    var map = grid 
    
    for ( (k, v) <- map.filter( _._2.size == 1) ) {
        map = eliminateRec(map, k, v(0), true)
    }
    
    solveRec(map)

  }

  
  def eliminateRec(currentValues: Grid, p: Pos, v: Int, initElim: Boolean): Grid = {
      if (!(currentValues(p) contains v))
          currentValues
      else {
          var testValues = currentValues
          if (testValues(p).size > 1) {
            testValues = currentValues.updated(p, currentValues(p).filter(_ != v))
            if (!gridFunction(testValues)(p)) {
              currentValues
            }
          }
          if (initElim || testValues(p).size == 1) {
            for (ps <- p.peers()) {
              eliminateRec(testValues, ps, testValues(p)(0), false)
            }
          }
          testValues
      }
  }
  
}

object SolverMain {
  
  def main(args: Array[String]) {
        new Solver {
          
           val t1 = System.currentTimeMillis
           //val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
           val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005"
           //val gridString = "000090820010000509709010000062701090000060000080309140000080902804000030016030000"
           val solution = solve()
           val t2 = System.currentTimeMillis
           println("Solution is:\n" + makeString(solution))
           println((t2 - t1) + " msecs")

        }
  }
  
}