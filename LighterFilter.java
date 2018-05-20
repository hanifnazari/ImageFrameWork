
/**
 * An image filter to make the image a bit lighter,(subclass).
 */
public class LighterFilter extends Filter
{
	/**
	 * Constructor for objects of class LighterFilter
	 */
	public LighterFilter(String name)
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
					for (int x = 0; x < image.getWidth(); x++) {
					    for (int y = 0; y < image.getHeight(); y++) 
					    {
					        image.setPixel(x, y, image.getPixel(x, y).brighter());

				}
			
		}
	}
}