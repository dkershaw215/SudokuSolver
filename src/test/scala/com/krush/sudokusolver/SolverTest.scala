package com.krush.sudokusolver

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class SolverTest extends FunSuite {    

    test("test many hard puzzles") {
      
      
      var count = 0
      var time = 0L

      for(line <- Source.fromFile("src/test/resources/easy_puzzles.txt").getLines()) {

        count = count + 1
  
     		new Solver {
 
            val t1 = System.currentTimeMillis
            val gridString = line
        		  
            val solution = solve()
            val t2 = System.currentTimeMillis
            
            println("Solution is:\n" + makeString(solution))
            assert(isValid(solution))
            //println((t2 - t1) + " msecs")
            println ("puzzle %s took %s millis" format (count, t2-t1))

            time = time + t2 - t1
    		  
      		}
      }		
      
      println ("%s puzzles took on average %s millis" format (count, time/count))
      assert(3000L > time/count)
	}
  
  
  
}