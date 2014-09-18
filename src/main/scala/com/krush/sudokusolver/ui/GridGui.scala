package com.krush.sudokusolver.ui

import scala.swing.{TextField, GridPanel, SimpleSwingApplication, MainFrame, BorderPanel, Dimension, Swing}
import scala.swing.BorderPanel.Position.Center
import scala.swing.Alignment.{Center => hCenter}
import com.krush.sudokusolver.Solver

object GridGui extends SimpleSwingApplication {

   val solver = new Solver {
     val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005"
   }
  
   def top = new MainFrame {
     
     title = "Soduko Solver"

     val textField = new TextField {
      columns = 1
    }
    
     def gridPanel(l: List[String]) = new GridPanel(3, 3) {
        contents += new TextField {
          columns = 1
          text = l(0)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(1)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(2)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(3)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(4)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(5)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(6)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(7)
          horizontalAlignment = hCenter
        }
        contents += new TextField {
          columns = 1
          text = l(8)
          horizontalAlignment = hCenter
        }
        border = Swing.EmptyBorder(2) 
     }

    val cells = (for( r <- solver.rowRef; c <- solver.colRef) yield solver.Pos(r, c)) take 9
    val grid = solver.grid
    val cell1 = (cells foldRight List[Int]()) { (v, a) => solver.grid(v)(0) :: a } map {x => x.toString}
    val superGrid = new GridPanel(3,3) {
      contents += gridPanel(cell1)
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
      contents += gridPanel(solver.colRef  map {x => x.toString})
    }
    
    
    contents = new BorderPanel {
      layout(superGrid) = Center
    }
    
    size = new Dimension(300,320)    
    
   }  
}
 
 