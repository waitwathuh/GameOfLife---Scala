import java.awt.image.BufferedImage
import CellImage._

class Cell(
    val x : Int,
    val y : Int,
    var alive : Boolean
)
{
    val locationX : Int = x
    val locationY : Int = y
    var isAlive : Boolean = alive
    var cellImage : BufferedImage = null

    setAlive(alive)

    def setAlive(alive : Boolean) =
    {
        isAlive = alive
        cellImage = if (isAlive) aliveImage else deadImage
    }
}