package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite {

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
		  val init: State = (pos: Pos) => true  
		  assert(initGrid == makeString())
		}
	}

	
	test("v peers Pos") {
		new Grid {
		  val vPeers =  Set(Pos('B', 1, 0), Pos('C', 1, 0), Pos('D', 1, 0), Pos('E', 1, 0), Pos('F', 1, 0), Pos('G', 1, 0), Pos('H', 1, 0), Pos('I', 1, 0))
		  val init: State = (pos: Pos) => true  

		  assert(vPeers == Pos('A', 1, 0).vPeers())
		}
	}

		test("h peers Pos") {
		new Grid {
		  val hPeers =  Set(Pos('A', 2, 0), Pos('A', 3, 0), Pos('A', 4, 0), Pos('A', 5, 0), Pos('A', 6, 0), Pos('A', 7, 0), Pos('A', 8, 0), Pos('A', 9, 0))
		  val init: State = (pos: Pos) => true  

		  val testVal  = Pos('A', 1, 0).hPeers()
		  assert(hPeers == testVal)
		}
	}

}