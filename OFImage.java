import java.awt.*;
import java.awt.image.*;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 */
public class OFImage extends BufferedImage
{
	/**
	 * Create an OFImage copied from a BufferedImage.
	 */
	public OFImage(BufferedImage image)
	{
		super(image.getColorModel(), image.copyData(null), 
				image.isAlphaPremultiplied(), null);
	}

	/**
	 * Create an OFImage with specified size and unspecified content.
	 */
	public OFImage(int width, int height)
	{
		super(460, 340, TYPE_INT_RGB);
	}

	/**
	 * Set a given pixel of this image to a specified color. The
	 * color is represented as an (r,g,b) value.
	 */
	public void setPixel(int x, int y, Color col)
	{
		int pixel = col.getRGB();
		setRGB(x, y, pixel);
	}

	/**
	 * Get the color value at a specified pixel position.
	 */
	public Color getPixel(int x, int y)
	{
		int pixel = getRGB(x, y);
		return new Color(pixel);
	}


}