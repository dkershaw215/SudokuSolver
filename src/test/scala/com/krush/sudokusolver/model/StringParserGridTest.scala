package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringParserGridTest extends FunSuite {

  	test("string parse grid") {
		new StringParserGrid {
		  val init: State = (pos: Pos) => true
		  val grid = "4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......"
		  assert(state(Pos('A',0, 4)), "0,0")
		  //assert(!state(Pos(4,11)), "4,11")
		}
	}

}