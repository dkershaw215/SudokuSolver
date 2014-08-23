package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite {

    val blankGrid = List(List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0),
             List(0, 0, 0, 0, 0, 0, 0, 0, 0)) 
  
	test("make string blank grid") {
	    val initGrid =  """. . . | . . . | . . . 
                        |. . . | . . . | . . . 
                        |. . . | . . . | . . . 
                        |------+-------+------
                        |. . . | . . . | . . . 
                        |. . . | . . . | . . . 
                        |. . . | . . . | . . . 
                        |------+-------+------
                        |. . . | . . . | . . . 
                        |. . . | . . . | . . . 
                        |. . . | . . . | . . . 
	                      |""".stripMargin
		new Grid {
      val grid = blankGrid
		  val init: State = (pos: Pos) => true  
		  val state: State = (pos: Pos) => true  
		  assert(initGrid == makeString())
		}
	}

	
	test("v peers Pos") {
		new Grid {
	    val grid = blankGrid
		  val init: State = (pos: Pos) => true  
		  val state: State = (pos: Pos) => true  
		  val vPeers =  Set(Pos('B', 1, 0), Pos('C', 1, 0), Pos('D', 1, 0), Pos('E', 1, 0), Pos('F', 1, 0), Pos('G', 1, 0), Pos('H', 1, 0), Pos('I', 1, 0))

		  assert(vPeers == Pos('A', 1, 0).vPeers())
		}
	}

		test("h peers Pos") {
		new Grid {
	    val grid = blankGrid
		  val init: State = (pos: Pos) => true  
		  val state: State = (pos: Pos) => true  
		  val hPeers =  Set(Pos('A', 2, 0), Pos('A', 3, 0), Pos('A', 4, 0), Pos('A', 5, 0), Pos('A', 6, 0), Pos('A', 7, 0), Pos('A', 8, 0), Pos('A', 9, 0))

		  val testVal  = Pos('A', 1, 0).hPeers()
		  assert(hPeers == testVal)
		}
	}

		test("square peers Pos") {
		new Grid {
	    val grid = blankGrid
		  val init: State = (pos: Pos) => true  
		  val state: State = (pos: Pos) => true  
		  val sPeers =  Set(Pos('A', 2, 0), Pos('A', 3, 0), Pos('B', 1, 0), Pos('B', 2, 0), Pos('B', 3, 0), Pos('C', 1, 0), Pos('C', 2, 0), Pos('C', 3, 0) )

		  val testVal  = Pos('A', 1, 0).squarePeers()
		  assert(sPeers == testVal)
		}
	}
		
		
}