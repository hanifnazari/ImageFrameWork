
/**
 * An image filter to make the image a bit Brighter everytime apply method is called.
 */
public class BrighterFilter extends Filter
{	
	
	/**
	 * Constructor for objects of class LighterFilter
	 */
	public BrighterFilter(String name) {
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
					for (int x = 0; x < image.getWidth(); x++) {
					    for (int y = 0; y < image.getHeight(); y++) 
					    {
					        image.setPixel(x, y, image.getPixel(x, y).brighter());

				}
			
		}
					return image;
	}
}