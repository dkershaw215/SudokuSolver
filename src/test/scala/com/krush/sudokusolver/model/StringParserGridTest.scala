package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringParserGridTest extends FunSuite {

  	test("string parse buildGrid") {
		new StringParserGrid {
		  val init: State = (pos: Pos) => true
		  val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
		  val testList = List(List(4, 0, 0, 0, 0, 0, 8, 0, 5),
							   List(0, 3, 0, 0, 0, 0, 0, 0, 0),
							   List(0, 0, 0, 7, 0, 0, 0, 0, 0),
							   List(0, 2, 0, 0, 0, 0, 0, 6, 0),
							   List(0, 0, 0, 0, 8, 0, 4, 0, 0),
							   List(0, 0, 0, 0, 1, 0, 0, 0, 0),
							   List(0, 0, 0, 6, 0, 3, 0, 7, 0),
							   List(5, 0, 0, 2, 0, 0, 0, 0, 0),
							   List(1, 0, 4, 0, 0, 0, 0, 0, 0))
   
			println(makeString())							   
		  assert(testList == buildGrid())
		}
	}
  	
  	
  	test("string parse state grid Function") {
		new StringParserGrid {
		  val init: State = (pos: Pos) => true
		  val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
		  assert(state(Pos('A',1, 4)))
		  assert(!state(Pos('A',2, 4)))
		  assert(!state(Pos('B',2, 4)))
		  assert(!state(Pos('B',3, 4)))
		}
	}

}