object Main {
  private var boardWidth: Int = -1
  private var boardHeight: Int = -1
  private var tileWidth: Int = -1
  private var tileHeight: Int = -1
  private var loopCount: Int = -1

  def main(args: Array[String]): Unit = {
    // TODO - Set these as defaults when prompting for values
    println("Recommended values:")
    println("\tboardWidth = 800")
    println("\tboardHeight = 600")
    println("\ttileWidth = 10")
    println("\ttileHeight = 10")
    println("\tloopCount = 15")

     boardSetup
     tileSetup
     loopSetup

//     boardWidth = 800
//     boardHeight = 600
//     tileWidth = 10
//     tileHeight = 10
//     loopCount = 15

    println("Board size: " + boardWidth + " x " + boardHeight)
    println("Tile size: " + tileWidth + " x " + tileHeight)
    println("Iteration count: " + loopCount)

    GameOfLife(boardWidth, boardHeight, tileWidth, tileHeight, loopCount)
  }

  def boardSetup = {
    boardWidthSetup
    boardHeightSetup
  }

  def boardWidthSetup = {
    var validBoardWidth: Boolean = false

    print("Enter board width: ")
    boardWidth = scala.io.StdIn.readInt()

    while (validBoardWidth == false) {
      if (boardWidth < 5) {
        println("Minimum board width is: 5")
        print("Enter board width: ")
        boardWidth = scala.io.StdIn.readInt()
      } else {
        validBoardWidth = true
      }
    }
  }

  def boardHeightSetup = {
    var validBoardHeight: Boolean = false

    print("Enter board height: ")
    boardHeight = scala.io.StdIn.readInt()

    while (validBoardHeight == false) {
      if (boardHeight < 5) {
        println("Minimum board height is: 5")
        print("Enter board height: ")
        boardHeight = scala.io.StdIn.readInt()
      } else {
        validBoardHeight = true
      }
    }
  }

  def tileSetup = {
    tileWidthSetup
    tileHeightSetup
  }

  def tileWidthSetup = {
    var validTileWidth: Boolean = false

    print("Enter tile width: ")
    tileWidth = scala.io.StdIn.readInt()

    // Validate tile width
    while (validTileWidth == false) {
      if (boardWidth % tileWidth != 0) {
        println(
          "Board width needs to be divisible by tile width. Board width is " + boardWidth
        )
        print("Enter tile width: ")
        tileWidth = scala.io.StdIn.readInt()
      } else {
        validTileWidth = true
      }
    }
  }

  def tileHeightSetup = {
    var validTileHeight: Boolean = false

    // Get tile height
    print("Enter tile height: ")
    tileHeight = scala.io.StdIn.readInt()

    while (validTileHeight == false) {
      if (boardHeight % tileHeight != 0) {
        println(
          "Board height needs to be divisible by tile height. Board height is " + boardHeight
        )
        print("Enter tile height: ")
        tileHeight = scala.io.StdIn.readInt()
      } else {
        validTileHeight = true
      }
    }
  }

  def loopSetup = {
    var validLoopCount: Boolean = false

    // Get tile height
    print("Enter iteration count: ")
    loopCount = scala.io.StdIn.readInt()

    while (validLoopCount == false) {
      if (loopCount < 5) {
        println("Minimum iteration count is: 5")
        print("Enter iteration count: ")
        loopCount = scala.io.StdIn.readInt()
      } else if (loopCount > 100) {
        println("Maximum iteration count is: 100")
        print("Enter iteration count: ")
        loopCount = scala.io.StdIn.readInt()
      } else {
        validLoopCount = true
      }
    }
  }
}
