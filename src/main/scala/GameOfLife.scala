import scala.util.Random
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.Graphics
import scala.collection.mutable.HashMap

import com.gif.GifSequenceWriter
import javax.imageio.stream.FileImageOutputStream
import javax.imageio.stream.ImageOutputStream

class GameOfLife(
    val boardWidth: Int,
    val boardHeight: Int,
    val tileWidth: Int,
    val tileHeight: Int,
    val loopCount: Int
) {
  createOutputFolder
  var gameMap: HashMap[String, Cell] = buildMap
  val output = new FileImageOutputStream(new File("tmp/myGif.gif"))
  val writer = new GifSequenceWriter(output, 1, 1000, true)

  createImage("tmp/0.jpg")
  runLoop

  writer.close()
  output.close()

  def createOutputFolder = {
    val tmpFolder: File = new File("tmp/")

    if (tmpFolder.exists() == false) {
      tmpFolder.mkdir()
    }
  }

  def buildMap: HashMap[String, Cell] = {
    var gameMap: HashMap[String, Cell] = new HashMap()
    val random: Random = new Random()

    for (i <- 0 until boardWidth) {
      for (j <- 0 until boardHeight) {
        val myCell: Cell = new Cell(i, j, random.nextBoolean)
        gameMap.put(i + "," + j, myCell)
      }
    }
    gameMap
  }

  def runLoop = {
    for (i <- 1 to (loopCount-1)) {
      createNextItteration
      createImage("tmp/" + i + ".jpg")
    }
  }

  def createNextItteration = {
    gameMap.map(gameMapValue => {
      val gameCell: Cell = gameMapValue._2

      val numberAliveNeighours: Int =
        determineAliveNeighours(gameCell.locationX, gameCell.locationY)
      val newState: Boolean =
        determineNewState(gameCell.isAlive, numberAliveNeighours)

      gameCell.setAlive(newState)
    })
  }

  def determineAliveNeighours(x: Int, y: Int): Int = {
    var alive: Int = 0

    // Check up
    if (y != 0) {
      val cellAbove: Cell = gameMap.getOrElse(
        x + "," + (y - 1),
        sys.error("unexpected key")
      )
      if (cellAbove.isAlive) {
        alive += 1
      }
    }

    // // Check up+right
    if (y != 0 && x != boardWidth - 1) {
      val cellAboveRight: Cell =
        gameMap.getOrElse((x + 1) + "," + (y - 1), sys.error("unexpected key"));
      if (cellAboveRight.isAlive) {
        alive += 1
      }
    }

    // // Check right
    if (x != boardWidth - 1) {
      val cellRight: Cell =
        gameMap.getOrElse((x + 1) + "," + y, sys.error("unexpected key"));
      if (cellRight.isAlive) {
        alive += 1
      }
    }

    // // Check right+down
    if (x != boardWidth - 1 && y != boardHeight - 1) {
      val cellRightBellow: Cell =
        gameMap.getOrElse((x + 1) + "," + (y + 1), sys.error("unexpected key"));
      if (cellRightBellow.isAlive) {
        alive += 1
      }
    }

    // // Check down
    if (y != boardHeight - 1) {
      val cellBellow: Cell =
        gameMap.getOrElse(x + "," + (y + 1), sys.error("unexpected key"));
      if (cellBellow.isAlive) {
        alive += 1
      }
    }

    // // Check down+left
    if (y != boardHeight - 1 && x != 0) {
      val cellBellowLeft: Cell =
        gameMap.getOrElse((x - 1) + "," + (y + 1), sys.error("unexpected key"));
      if (cellBellowLeft.isAlive) {
        alive += 1
      }
    }

    // // Check left
    if (x != 0) {
      val cellLeft: Cell =
        gameMap.getOrElse((x - 1) + "," + y, sys.error("unexpected key"));
      if (cellLeft.isAlive) {
        alive += 1
      }
    }

    // // Check left+up
    if (x != 0 && y != 0) {
      val cellLeftAbove: Cell =
        gameMap.getOrElse((x - 1) + "," + (y - 1), sys.error("unexpected key"));
      if (cellLeftAbove.isAlive) {
        alive += 1
      }
    }

    alive
  }

  def determineNewState(oldState: Boolean, neighbourCount: Int): Boolean = {
    // Live cell, 2 OR 3 live neighbours survive
    // Dead cell, 3 life neighbours reanimate
    // All other live cells die, dead cells stay dead
    if (oldState && (neighbourCount == 2 || neighbourCount == 3)) {
      true
    } else if (!oldState && neighbourCount == 3) {
      true
    } else {
      false
    }
  }

  def createImage(path: String) = {
    val image: BufferedImage =
      new BufferedImage(boardWidth, boardHeight, BufferedImage.TYPE_INT_RGB)
    val imageGraphics: Graphics = image.getGraphics

    gameMap.foreach(gameMapValue => {
      imageGraphics.drawImage(
        gameMapValue._2.cellImage,
        gameMapValue._2.locationX * 10,
        gameMapValue._2.locationY * 10,
        null
      )
    })

    val outputfile: File = new File(path)
    ImageIO.write(image, "jpg", outputfile)

    writer.writeToSequence(image)
  }
}
