package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringParserGridTest extends FunSuite {

  	test("string parse buildGrid") {
		new StringParserGrid {
		  val gridString = "400000805030000000000700000020000060000080400000010000000603070500200000104000000"
		  val testList = Map(
		              Pos('A', 1) -> List(4), Pos('A', 2) -> colRef, Pos('A', 3) -> colRef, Pos('A', 4) -> colRef, Pos('A', 5) -> colRef, Pos('A', 6) -> colRef, Pos('A', 7) -> List(8), Pos('A', 8) -> colRef, Pos('A', 9) -> List(5),
		              Pos('B', 1) -> colRef, Pos('B', 2) -> List(3), Pos('B', 3) -> colRef, Pos('B', 4) -> colRef, Pos('B', 5) -> colRef, Pos('B', 6) -> colRef, Pos('B', 7) -> colRef, Pos('B', 8) -> colRef, Pos('B', 9) -> colRef,
		              Pos('C', 1) -> colRef, Pos('C', 2) -> colRef, Pos('C', 3) -> colRef, Pos('C', 4) -> List(7), Pos('C', 5) -> colRef, Pos('C', 6) -> colRef, Pos('C', 7) -> colRef, Pos('C', 8) -> colRef, Pos('C', 9) -> colRef,
		              Pos('D', 1) -> colRef, Pos('D', 2) -> List(2), Pos('D', 3) -> colRef, Pos('D', 4) -> colRef, Pos('D', 5) -> colRef, Pos('D', 6) -> colRef, Pos('D', 7) -> colRef, Pos('D', 8) -> List(6), Pos('D', 9) -> colRef,
		              Pos('E', 1) -> colRef, Pos('E', 2) -> colRef, Pos('E', 3) -> colRef, Pos('E', 4) -> colRef, Pos('E', 5) -> List(8), Pos('E', 6) -> colRef, Pos('E', 7) -> List(4), Pos('E', 8) -> colRef, Pos('E', 9) -> colRef,
		              Pos('F', 1) -> colRef, Pos('F', 2) -> colRef, Pos('F', 3) -> colRef, Pos('F', 4) -> colRef, Pos('F', 5) -> List(1), Pos('F', 6) -> colRef, Pos('F', 7) -> colRef, Pos('F', 8) -> colRef, Pos('F', 9) -> colRef,
		              Pos('G', 1) -> colRef, Pos('G', 2) -> colRef, Pos('G', 3) -> colRef, Pos('G', 4) -> List(6), Pos('G', 5) -> colRef, Pos('G', 6) -> List(3), Pos('G', 7) -> colRef, Pos('G', 8) -> List(7), Pos('G', 9) -> colRef,
		              Pos('H', 1) -> List(5), Pos('H', 2) -> colRef, Pos('H', 3) -> colRef, Pos('H', 4) -> List(2), Pos('H', 5) -> colRef, Pos('H', 6) -> colRef, Pos('H', 7) -> colRef, Pos('H', 8) -> colRef, Pos('H', 9) -> colRef,
		              Pos('I', 1) -> List(1), Pos('I', 2) -> colRef, Pos('I', 3) -> List(4), Pos('I', 4) -> colRef, Pos('I', 5) -> colRef, Pos('I', 6) -> colRef, Pos('I', 7) -> colRef, Pos('I', 8) -> colRef, Pos('I', 9) -> colRef
				  )
   
		  assert(testList == buildGrid())
		}
	}
  
}