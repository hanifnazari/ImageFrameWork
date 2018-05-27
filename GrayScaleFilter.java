import java.awt.*;

/**
 * An image filter to make the image GrayScale.
 * 
 */
public class GrayScaleFilter extends Filter
{
	/**
	 * Constructor for objects of class GrayScaleFilter
	 */
	public GrayScaleFilter(String name)
    {
        super(name);
	}

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     * @return the image that has been filtered
     */
    public OFImage apply(OFImage image)
    {
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color pix = image.getPixel(x, y);
                int avg = (pix.getRed() + pix.getGreen() + pix.getBlue()) / 3;
                image.setPixel(x, y, new Color(avg, avg, avg));
            }
        }
		return image;
    }
}