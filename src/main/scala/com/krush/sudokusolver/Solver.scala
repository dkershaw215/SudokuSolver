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
                println(makeString(current))
  	            val p = Pos(r, c)
	              if (current(p).size > 1) {
	                 for (v <- current(p)) {
	                   val testMap = current.updated(p, List(v))
  	                 val testGrid = gridFunction(testMap)
	                   if (testGrid(p)) {
	                     val elimateMoreMap = eliminateRec(collection.mutable.Map(testMap.toSeq: _*), p, v)
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

    def eliminateRec(currentValues: scala.collection.mutable.Map[Pos, List[Int]], p: Pos, v: Int):scala.collection.mutable.Map[Pos, List[Int]] = {
        //println(currentValues.mkString("\n"))
        if (!(currentValues(p) contains v))
            currentValues
        else {
            if (currentValues(p).size != 1) {
              currentValues.put(p, currentValues(p).filter(_ != v))
            }
            //println(currentValues.mkString("\n"))
            if (currentValues(p).size == 1) {
              for (ps <- p.peers()) {
                eliminateRec(currentValues, ps, currentValues(p)(0))
              }
            }
            currentValues
        }
    }

    var map = collection.mutable.Map(grid.toSeq: _*) 
    //println(map.mkString("\n"))
    
    for ( (k, v) <- map.filter( _._2.size == 1) ) {
        map = eliminateRec(map, k, v(0))
    }
    
    println(map.mkString("\n"))

    solveRec(collection.immutable.Map(map.toSeq: _*) )

  }
 
}

object SolverMain {
  
  def main(args: Array[String]) {
        new Solver {
           val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
           val solution = solve()
           println("Solution is:\n" + makeString(solution))
        }
  }
  
}