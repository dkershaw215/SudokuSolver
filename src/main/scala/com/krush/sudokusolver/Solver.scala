package com.krush.sudokusolver

import com.krush.sudokusolver.model.Grid
import com.krush.sudokusolver.model.Pos
import com.krush.sudokusolver.model.StringParserGrid

import scala.collection.immutable.TreeMap

trait Solver extends Grid with StringParserGrid {

  def solve(): Grid = {
    
    def done(checkMap: Grid): Boolean = checkMap.forall(_._2.size == 1)
    
    def solveRec(current: Grid): Grid = {
        if (!isValid(current)) return TreeMap[Pos, List[Int]]()
        if (done(current)) return current
        else {
        	  for ( r <- Grid.rowRef; c <- Grid.colRef ) {
  	            val p = Pos(r, c)
	              if (current(p).size > 1) {
	                 for (v <- current(p)) {
	                   val testMap = current.updated(p, List(v))
	                   if (isValid(testMap)) {
	                     val result = solveRec(eliminateRec(testMap, p, v, true))
	                     if (!result.isEmpty) return result
	                   }
	                 }
                   return TreeMap[Pos, List[Int]]()
	              }
        	  }      	      
            return TreeMap[Pos, List[Int]]()
        }
    }

    var map = grid 
    
    if (!isValid(map)) throw new IllegalArgumentException("invalid start grid")
    
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
          }
          if (testValues(p).size == 1 && !gridFunction(testValues)(p)) {
        	  currentValues
          }
          if (initElim || testValues(p).size == 1) {
            for (ps <- p.peers()) {
              if (testValues(ps).size > 1) {
                testValues = eliminateRec(testValues, ps, testValues(p)(0), false) // this has infinite loop but is faster
              }
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
           //val gridString = "480300000000000071020000000705000060000200800000000000001076000300000400000050000" // really slow puzzle
           //val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000" //slow puzzle
           val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005" //fast puzzle
           //val gridString = "000090820100000509709010000062701090000060000080309140000080902804000030016030000" //not so fast puzzle
           //val gridString = "000090820010000509709010000062701090000060000080309140000080902804000030016030000" //invalid start grid
           val solution = solve()
           assert(isValid(solution))
           val t2 = System.currentTimeMillis
           println("Solution is:\n" + makeString(solution))
           println((t2 - t1) + " msecs")

        }
  }
  
}