package com.krush.sudokusolver

import com.krush.sudokusolver.model.Grid
import com.krush.sudokusolver.model.StringParserGrid

trait Solver extends Grid with StringParserGrid {

  def solve(startingGrid: String): State = {
    val gridString = startingGrid
    val init = gridFunction(grid)
    init
  }
  
}