package com.krush.sudokusolver

import com.krush.sudokusolver.model.Grid
import com.krush.sudokusolver.model.StringParserGrid

trait Solver extends Grid with StringParserGrid {

  def solve(): Grid = {
    
    def done(checkGrid: Grid): Boolean = checkGrid.flatten.forall(_ != 0)
    
    def assign(pGrid: Grid, p: Pos): Grid = pGrid.updated(rowRef.indexOf(p.row), pGrid(rowRef.indexOf(p.row)).updated(p.col - 1, p.value)) 
    
    def solveRec(current: Grid):Grid = {
        println(makeString(current))
        if (done(current)) return current
        else {
        	  for ( r <- rowRef) {
      	      for (c <- colRef) {
	            val p = findPos(r, c, current)
	              if (p.value == 0) {
	                 val currentGrid = gridFunction(current)
	                 for (v <- colRef) {
	                   val testPos = Pos(r, c, v)
	                   if (currentGrid(testPos)) {
	                     val newGrid = assign(current, testPos) 
	                     val result = solveRec(newGrid)
	                     if (!result.isEmpty) return result
	                   }
	                 }
                   return List()
	              }
      	      }
        	  }      	      
            return List()
        }
    }
    solveRec(grid)

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