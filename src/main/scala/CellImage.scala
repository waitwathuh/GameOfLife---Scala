import java.awt.image.BufferedImage
import java.awt.Graphics
import java.awt.Color

object CellImage
{
    val aliveImage : BufferedImage = buildAliveImage
    val deadImage : BufferedImage = buildDeadImage

    def buildAliveImage : BufferedImage =
    {
        buildImage(10, 10, Color.WHITE)
    }

    def buildDeadImage : BufferedImage =
    {
        buildImage(10, 10, Color.BLACK)
    }

    def buildImage(width: Int, height: Int, color: Color) : BufferedImage =
    {
        val image : BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val imageGraphics : Graphics = image.getGraphics
        imageGraphics.setColor(color);
        imageGraphics.fillRect(0, 0, width, height);
        image
    }

    def getAliveImage : BufferedImage =
    {
        aliveImage
    }

    def getDeadImage : BufferedImage =
    {
        deadImage
    }
}
