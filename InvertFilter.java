import java.awt.*;
/**
 * An image filter to take the inverse of the image.
 */
public class InvertFilter extends Filter
{
    public InvertFilter(String name)
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
                image.setPixel(x, y, new Color(255 - pix.getRed(),
                                               255 - pix.getGreen(),
                                               255 - pix.getBlue()));
            }
        }
		return image;
    }
}