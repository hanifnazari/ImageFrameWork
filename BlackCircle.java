import java.awt.Color;
import java.awt.Graphics;

/**
 * Black circle: A black circle with a diameter equal to the width of the image in the center of the image.
 * 
 */
public class BlackCircle extends Filter
{
	/**
	 * Constructor for objects of class BlackCirle
	 */
	public BlackCircle(String name)
    {
        super(name);
	}

    public void apply(OFImage image)
    {
        Graphics g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        int x=(image.getHeight()-image.getHeight())/2;
        int y=(image.getWidth()-image.getWidth())/2;
        g2d.fillRoundRect(x, y, image.getWidth(), image.getHeight(), image.getWidth(), image.getHeight());
        g2d.dispose();

    }
}