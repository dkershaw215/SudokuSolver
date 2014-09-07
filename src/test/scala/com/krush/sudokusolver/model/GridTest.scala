package com.krush.sudokusolver.model

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import collection.immutable.HashMap

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

    def buildGrid(): Map[Pos, List[Int]] = {
      Map() ++ ( for {r <- rowRef
                      c <- colRef } yield {
         (Pos(r, c) -> colRef)
      })
    }


      val grid = buildGrid()
		  val state: State = (pos: Pos) => true
		  val retVal = makeString()
		  assert(initGrid == retVal)
		  
		}
	}

	
	test("v peers Pos") {
		new Grid {
      val grid = HashMap( Pos('A', 1) -> colRef )
		  val state: State = (pos: Pos) => true  
		  val vPeers =  Set(Pos('B', 1), Pos('C', 1), Pos('D', 1), Pos('E', 1), Pos('F', 1), Pos('G', 1), Pos('H', 1), Pos('I', 1))

		  assert(vPeers == Pos('A', 1).vPeers())
		}
	}

		test("h peers Pos") {
		new Grid {
      val grid = HashMap( Pos('A', 1) -> colRef )
		  val state: State = (pos: Pos) => true  
		  val hPeers =  Set(Pos('A', 2), Pos('A', 3), Pos('A', 4), Pos('A', 5), Pos('A', 6), Pos('A', 7), Pos('A', 8), Pos('A', 9))

		  val testVal  = Pos('A', 1).hPeers()
		  assert(hPeers == testVal)
		}
	}

		test("square peers Pos") {
		new Grid {
      val grid = HashMap( Pos('A', 1) -> colRef )
		  val state: State = (pos: Pos) => true  
		  val sPeers =  Set(Pos('A', 2), Pos('A', 3), Pos('B', 1), Pos('B', 2), Pos('B', 3), Pos('C', 1), Pos('C', 2), Pos('C', 3) )

		  val testVal  = Pos('A', 1).squarePeers()
		  assert(sPeers == testVal)
		}
	}
		
		
}