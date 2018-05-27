import java.awt.*;

/**
 * An image filter to mirror (flip) the image horizontally.
 * 
 */
public class FlipXFilter extends Filter
{
	/**
	 * Constructor for objects of class FlipXFilter
	 */
	public FlipXFilter(String name)
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
        int width = image.getWidth();
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < width/2; x++) {
                Color left = image.getPixel(x, y);
                image.setPixel(x, y, image.getPixel(width-1-x, y));
                image.setPixel(width-1-x, y, left);
            }
        }
		return image;
    }
}