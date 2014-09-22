package com.krush.sudokusolver.ui

import scala.swing.{TextField, GridPanel, SimpleSwingApplication, MainFrame, BorderPanel, Dimension, Swing, Button, Label, Font}
import scala.swing.event.{ButtonClicked, KeyTyped}
import scala.swing.BorderPanel.Position.{Center, East, South}
import scala.swing.Alignment.{Center => hCenter}
import com.krush.sudokusolver.Solver
import java.awt.{ Color }
import scala.collection.immutable.TreeMap

import com.krush.sudokusolver.model.Pos
import com.krush.sudokusolver.model.Grid
import scala.io.Source
import scala.util.Random

            
object GridGui extends SimpleSwingApplication {

   val initGridString = "000000000000000000000000000000000000000000000000000000000000000000000000000000000"
   val lines = Source.fromFile("src/test/resources/easy_puzzles.txt").getLines.toList

   var solver = new Solver {
     val gridString = Random.shuffle(lines).head
   }                  

   def newTextField(text: String): TextField = new TextField {
      columns = 1
      text = text
      font = new Font("Ariel", java.awt.Font.PLAIN, 24)
      horizontalAlignment = hCenter
      listenTo(keys)
      reactions += {
        case e: KeyTyped =>     
          {
             foreground = Color.black
             if (!e.char.isDigit || text.length == 1)
                  e.consume
          }
      }
   }

   val textfieldMap: TreeMap[Pos, TextField] = solver.grid map { case (k, v) => (k, newTextField("")) }
   
   def top = new MainFrame {
     
     title = "Soduko Solver"
     
     def gridPanel(m: Map[Pos, String]) = new GridPanel(3, 3) {
        contents ++= (for ((k, v) <- m) yield {
         val tf = textfieldMap(k)
         tf.text = v
         tf
        })
        border = Swing.EmptyBorder(2) 
     }
     
    def makeRow(l: List[Int]): Int = if (l.size > 1) 0 else l.head
    
    val cells = for (q <- Grid.quadrants) yield (q foldRight TreeMap[Pos, Int]()) { (v, a) =>  TreeMap(v -> makeRow(solver.grid(v))) ++ a } map { case (v, x) => (v, if (x == 0) "" else x.toString)}
    val superGrid = new GridPanel(3,3) {
      contents ++= (for ( c <- cells ) yield gridPanel(c))
    }

    val label = new Label {
      text = "Good Luck!"
      font = new Font("Ariel", java.awt.Font.ITALIC, 24)
    }
    
    val buttonSize = new Dimension(80,30)
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
    val customButton = new Button {
      text = "Custom"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      tooltip = "Enter your own puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    val saveButton = new Button {
      text = "Save"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      visible = false
      tooltip = "Save your own puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    val cancelButton = new Button {
      text = "Cancel"
      foreground = Color.blue
      background = Color.lightGray
      borderPainted = true
      enabled = true
      visible = false
      tooltip = "Go back to a new puzzle"
      preferredSize = buttonSize
      minimumSize = buttonSize
      maximumSize = buttonSize
    }
    
    val buttonPanel = new scala.swing.BoxPanel(scala.swing.Orientation.Vertical) {
      contents += newButton
      contents += checkButton
      contents += solveButton
      contents += customButton
      contents += saveButton
      contents += cancelButton
      border = Swing.EmptyBorder(4)
    }
    
    val textPanel = new scala.swing.BoxPanel(scala.swing.Orientation.Horizontal) {
      contents += label
      border = Swing.EmptyBorder(4)
    }
    
    def hideMainButtons() = {
      newButton.visible = false
      checkButton.visible = false
      solveButton.visible = false
      customButton.visible = false
      saveButton.visible = true
      cancelButton.visible = true
    }
    def showMainButtons() = {
      newButton.visible = true
      checkButton.visible = true
      solveButton.visible = true
      customButton.visible = true
      saveButton.visible = false
      cancelButton.visible = false
    }
    
    contents = new BorderPanel {
      layout(superGrid) = Center
      layout(buttonPanel) = East
      layout(textPanel) = South
    }
    
    size = new Dimension(610,540)
    
    listenTo(solveButton)
    listenTo(newButton)
    listenTo(checkButton)
    listenTo(customButton)
    listenTo(saveButton)
    listenTo(cancelButton)
    
    reactions += {
      case ButtonClicked(component) => {
        if (component == checkButton) {
            val solution = solver.solve()
            solution.foreach { 
              case (k, v) => {
                if (textfieldMap(k).text.size > 0 && textfieldMap(k).text != v.head.toString) {
                  textfieldMap(k).foreground = Color.red
                }
              }
            }
        }
        if (component == newButton || component == cancelButton) {
            showMainButtons()
            solver = new Solver {
               val gridString = Random.shuffle(lines).head
             }  
            solver.grid.foreach { 
              case (k, v) => {
                textfieldMap(k).text = if (v.size > 1) "" else v.head.toString }
                textfieldMap(k).foreground = Color.black
            }
        }
        if (component == solveButton) {
            val solution = solver.solve()
            solution.foreach { 
              case (k, v) => {
                if (textfieldMap(k).text.size > 0 && textfieldMap(k).text != v.head.toString) {
                  textfieldMap(k).foreground = Color.red
                }
                else if (textfieldMap(k).text.size == 0) {
                  textfieldMap(k).foreground = Color.blue
                }
                textfieldMap(k).text = v.head.toString
              }
            }
        }
        if (component == customButton) {
            hideMainButtons()
            solver = new Solver {
               val gridString = initGridString
             }  
            solver.grid.foreach { 
              case (k, v) => {
                textfieldMap(k).text = if (v.size > 1) "" else v.head.toString }
                textfieldMap(k).foreground = Color.black
            }
        }
        if (component == saveButton) {
          
            val enteredGrid = (for ( (k, v) <- textfieldMap ) yield if (v.text == "") "0" else v.text) 
            solver = new Solver {
               val gridString = enteredGrid.mkString
            }  
            
            if (solver.isValid(solver.grid)) {
              showMainButtons()
              label.text = "Good Luck!"
            }
            else {
              label.text = "Your puzzle is not valid!"
            }
        }
      }
    }
  }  
}
 
 