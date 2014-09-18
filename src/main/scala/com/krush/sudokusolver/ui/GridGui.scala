package com.krush.sudokusolver.ui

import scala.swing.{TextField, GridPanel, SimpleSwingApplication, MainFrame, BorderPanel, Dimension, Swing, Button}
import scala.swing.BorderPanel.Position.{Center, East}
import scala.swing.Alignment.{Center => hCenter}
import com.krush.sudokusolver.Solver
import java.awt.{ Color }

object GridGui extends SimpleSwingApplication {

   val solver = new Solver {
     val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005"
   }
  
   def top = new MainFrame {
     
     title = "Soduko Solver"

     def gridPanel(l: List[String]) = new GridPanel(3, 3) {
        contents ++= (for (i <- l) yield new TextField {
          columns = 1
          text = i
          horizontalAlignment = hCenter
        } )
        border = Swing.EmptyBorder(2) 
     }
    def makeRow(l: List[Int]): Int = if (l.size > 1) 0 else l(0)
    
    val cells = for (q <- solver.quadrants) yield (q foldRight List[Int]()) { (v, a) =>  makeRow(solver.grid(v)) :: a } map {x => if (x == 0) "" else x.toString}
    val superGrid = new GridPanel(3,3) {
      contents ++= (for ( c <- cells ) yield gridPanel(c))
    }

    val buttonSize = new Dimension(70,30)
    val solveButton = new Button {
      text = "Solve"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      tooltip = "Solve the puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    val newButton = new Button {
      text = "New"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      tooltip = "Start a new puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    val checkButton = new Button {
      text = "Check"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      tooltip = "Check your puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    
    val buttonPanel = new scala.swing.BoxPanel(scala.swing.Orientation.Vertical) {
      contents += newButton
      contents += checkButton
      contents += solveButton
    }
    contents = new BorderPanel {
      layout(superGrid) = Center
      layout(buttonPanel) = East
    }
    
    size = new Dimension(400,320)    
    
   }  
}
 
 