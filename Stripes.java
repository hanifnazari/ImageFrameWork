
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Vertical stripes: 10 vertical stripes that cover the image. 
 * The strips shall have the same width as the distance to the adjacent edge.
 * 
 */
public class Stripes extends Filter
{
	/**
	 * Constructor for objects of class Stripes.
	 */
	public Stripes(String name)
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
        int dist =image.getWidth()/20;
        //int ctr=0;
    	Graphics g2d = image.createGraphics();
        g2d.fillRect(image.getWidth() ,image.getHeight(), image.getWidth(), image.getHeight());
        g2d.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(dist);
        ((Graphics2D) g2d).setStroke(bs);
        for(int i=0;i<image.getWidth(); i+=2*dist){

            g2d.fillRect(i, 0, dist, image.getHeight());

        }
		return image;

        
    }
}