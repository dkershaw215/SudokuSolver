package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.collection.immutable.TreeMap

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite {    
  
	test("make string blank grid") {
	    val initGrid =  """* * * | * * * | * * * 
                        |* * * | * * * | * * * 
                        |* * * | * * * | * * * 
                        |------+-------+------
                        |* * * | * * * | * * * 
                        |* * * | * * * | * * * 
                        |* * * | * * * | * * * 
                        |------+-------+------
                        |* * * | * * * | * * * 
                        |* * * | * * * | * * * 
                        |* * * | * * * | * * * 
	                      |""".stripMargin
		new Grid {

		    def buildGrid(): Grid = {
		      TreeMap(( for {r <- Grid.rowRef
		                      c <- Grid.colRef } yield {
		         (Pos(r, c) -> Grid.colRef)
		      }).toArray:_*)
		    }


	    	val grid = buildGrid()
			assert(initGrid == makeString())
		  
		}
	}

	
	test("v peers Pos") {
		new Grid {
		  val grid = TreeMap( Pos('A', 1) -> Grid.colRef )
		  val vPeers =  Set(Pos('B', 1), Pos('C', 1), Pos('D', 1), Pos('E', 1), Pos('F', 1), Pos('G', 1), Pos('H', 1), Pos('I', 1))
		  assert(vPeers == Pos('A', 1).vPeers())
		}
	}

		test("h peers Pos") {
		new Grid {
		  val grid = TreeMap( Pos('A', 1) -> Grid.colRef )
		  val hPeers =  Set(Pos('A', 2), Pos('A', 3), Pos('A', 4), Pos('A', 5), Pos('A', 6), Pos('A', 7), Pos('A', 8), Pos('A', 9))
		  assert(hPeers == Pos('A', 1).hPeers())
		}
	}

		test("square peers Pos") {
		new Grid {
		  val grid = TreeMap( Pos('A', 1) -> Grid.colRef )  
		  val sPeers =  Set(Pos('A', 2), Pos('A', 3), Pos('B', 1), Pos('B', 2), Pos('B', 3), Pos('C', 1), Pos('C', 2), Pos('C', 3) )
		  assert(sPeers == Pos('A', 1).squarePeers())
		}
	}
		
		
}