
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
     * @return 
     */
    public OFImage apply(OFImage image)
    {
        Graphics g2d = image.createGraphics();
        g2d.fillRect(image.getWidth() ,image.getHeight(), image.getWidth(), image.getHeight());
        g2d.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(8);
        ((Graphics2D) g2d).setStroke(bs);
        for(int i=0;i<11;i++){

            g2d.drawLine((image.getWidth())/11*i, 0, (image.getWidth())/11*i,image.getHeight());

        }
		return image;

        
    }
}