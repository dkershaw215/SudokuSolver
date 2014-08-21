package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringParserGridTest extends FunSuite {

  	test("string parse grid") {
		new StringParserGrid {
		  val init: State = (pos: Pos) => true
		  val grid = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
		  assert(state(Pos('A',1, 4)))
		  assert(state(Pos('A',2, 4)))
		}
	}

}