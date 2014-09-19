package com.krush.sudokusolver.ui

import scala.swing.{TextField, GridPanel, SimpleSwingApplication, MainFrame, BorderPanel, Dimension, Swing, Button}
import scala.swing.event.ButtonClicked
import scala.swing.BorderPanel.Position.{Center, East}
import scala.swing.Alignment.{Center => hCenter}
import com.krush.sudokusolver.Solver
import java.awt.{ Color }
import scala.collection.immutable.TreeMap

object GridGui extends SimpleSwingApplication {

   val solver = new Solver {
     val gridString = "600108203020040090803005400504607009030000050700803102001700906080030020302904005"
   }

   def newTextField(text: String): TextField = new TextField {
      columns = 1
      text = text
      horizontalAlignment = hCenter
   }

   val textfieldMap: Map[solver.Pos, TextField] = solver.grid map { case (k, v) => (k, newTextField("")) }
   
   def top = new MainFrame {
     
     title = "Soduko Solver"
     
     def gridPanel(m: Map[solver.Pos, String]) = new GridPanel(3, 3) {
        contents ++= (for ((k, v) <- m) yield {
         val tf = textfieldMap(k)
         tf.text = v
         tf
        })
        border = Swing.EmptyBorder(2) 
     }
     
    def makeRow(l: List[Int]): Int = if (l.size > 1) 0 else l.head
    
    val cells = for (q <- solver.quadrants) yield (q foldRight TreeMap[solver.Pos, Int]()) { (v, a) =>  TreeMap(v -> makeRow(solver.grid(v))) ++ a } map { case (v, x) => (v, if (x == 0) "" else x.toString)}
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
    
    listenTo(solveButton)
    
    reactions += {
      case ButtonClicked(component) if component == solveButton => { 
        val solution = solver.solve()
        solution.foreach { case (k, v) => textfieldMap(k).text = v.head.toString }
      }
      
        
    }
   }  
}
 
 