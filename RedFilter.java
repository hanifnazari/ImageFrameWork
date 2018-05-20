
import java.awt.*;

/**
 * An image filter to red only.
 * 
 */
public class RedFilter extends Filter
{
	/**
	 * Constructor for objects of class RedFilter
	 */
	public RedFilter(String name)
    {
        super(name);
	}

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
    	 int height = image.getHeight();
         int width = image.getWidth();
         for(int y = 0; y < height; y++) {
             for(int x = 0; x < width; x++) {
                 Color pix = image.getPixel(x, y);
                 int red   = pix.getRed();
                 image.setPixel(x, y, new Color(red, 0, 0));
            }
        }
    }
}