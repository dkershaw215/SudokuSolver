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
		              Pos('A', 1) -> List(4), Pos('A', 2) -> Grid.colRef, Pos('A', 3) -> Grid.colRef, Pos('A', 4) -> Grid.colRef, Pos('A', 5) -> Grid.colRef, Pos('A', 6) -> Grid.colRef, Pos('A', 7) -> List(8), Pos('A', 8) -> Grid.colRef, Pos('A', 9) -> List(5),
		              Pos('B', 1) -> Grid.colRef, Pos('B', 2) -> List(3), Pos('B', 3) -> Grid.colRef, Pos('B', 4) -> Grid.colRef, Pos('B', 5) -> Grid.colRef, Pos('B', 6) -> Grid.colRef, Pos('B', 7) -> Grid.colRef, Pos('B', 8) -> Grid.colRef, Pos('B', 9) -> Grid.colRef,
		              Pos('C', 1) -> Grid.colRef, Pos('C', 2) -> Grid.colRef, Pos('C', 3) -> Grid.colRef, Pos('C', 4) -> List(7), Pos('C', 5) -> Grid.colRef, Pos('C', 6) -> Grid.colRef, Pos('C', 7) -> Grid.colRef, Pos('C', 8) -> Grid.colRef, Pos('C', 9) -> Grid.colRef,
		              Pos('D', 1) -> Grid.colRef, Pos('D', 2) -> List(2), Pos('D', 3) -> Grid.colRef, Pos('D', 4) -> Grid.colRef, Pos('D', 5) -> Grid.colRef, Pos('D', 6) -> Grid.colRef, Pos('D', 7) -> Grid.colRef, Pos('D', 8) -> List(6), Pos('D', 9) -> Grid.colRef,
		              Pos('E', 1) -> Grid.colRef, Pos('E', 2) -> Grid.colRef, Pos('E', 3) -> Grid.colRef, Pos('E', 4) -> Grid.colRef, Pos('E', 5) -> List(8), Pos('E', 6) -> Grid.colRef, Pos('E', 7) -> List(4), Pos('E', 8) -> Grid.colRef, Pos('E', 9) -> Grid.colRef,
		              Pos('F', 1) -> Grid.colRef, Pos('F', 2) -> Grid.colRef, Pos('F', 3) -> Grid.colRef, Pos('F', 4) -> Grid.colRef, Pos('F', 5) -> List(1), Pos('F', 6) -> Grid.colRef, Pos('F', 7) -> Grid.colRef, Pos('F', 8) -> Grid.colRef, Pos('F', 9) -> Grid.colRef,
		              Pos('G', 1) -> Grid.colRef, Pos('G', 2) -> Grid.colRef, Pos('G', 3) -> Grid.colRef, Pos('G', 4) -> List(6), Pos('G', 5) -> Grid.colRef, Pos('G', 6) -> List(3), Pos('G', 7) -> Grid.colRef, Pos('G', 8) -> List(7), Pos('G', 9) -> Grid.colRef,
		              Pos('H', 1) -> List(5), Pos('H', 2) -> Grid.colRef, Pos('H', 3) -> Grid.colRef, Pos('H', 4) -> List(2), Pos('H', 5) -> Grid.colRef, Pos('H', 6) -> Grid.colRef, Pos('H', 7) -> Grid.colRef, Pos('H', 8) -> Grid.colRef, Pos('H', 9) -> Grid.colRef,
		              Pos('I', 1) -> List(1), Pos('I', 2) -> Grid.colRef, Pos('I', 3) -> List(4), Pos('I', 4) -> Grid.colRef, Pos('I', 5) -> Grid.colRef, Pos('I', 6) -> Grid.colRef, Pos('I', 7) -> Grid.colRef, Pos('I', 8) -> Grid.colRef, Pos('I', 9) -> Grid.colRef
				  )
   
		  assert(testList == buildGrid())
		}
	}
  
}