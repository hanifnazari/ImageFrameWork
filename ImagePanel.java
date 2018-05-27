import java.awt.*;
import javax.swing.*;

/**
 * An ImagePanel is a Swing component that can display an OFImage.
 * It is constructed as a subclass of JPanel with the added functionality
 * of setting an OFImage that will be displayed on the surface of this component.
 */
@SuppressWarnings("serial")
public class ImagePanel extends JComponent
{
	// The current width and height of this panel
	public int width, height;

	// An internal image buffer that is used for painting. For
	// actual display, this image buffer is then copied to screen.
	private OFImage panelImage;

	/**
	 * Create a new, empty ImagePanel.
	 */
	public ImagePanel()
	{
		width = 460;    // arbitrary size for empty panel
		height = 360;
		panelImage = null;
	}

	/**
	 * Set the image that this panel should show.
	 * 
	 * @param image  The image to be displayed.
	 */
	public void setImage(OFImage image)
	{
		if(image != null) {
			width = image.getWidth();
			height = image.getHeight();
			panelImage = image;

			repaint();
		}
	}
	/**
	 * Clear the image on this panel.
	 */
	public void clearImage()
	{
		
		Graphics imageGraphics = panelImage.createGraphics();
		imageGraphics.setColor(Color.white);
		imageGraphics.fillRect(0, 0, width, height);
		imageGraphics.dispose();
		repaint();
	}

	/**
	 * Tell the layout manager how big we would like to be.
	 * 
	 * @return The preferred dimension for this component.
	 */
		
	public Dimension getPreferredSize()
	{
		return new Dimension(width, height);
	}
		
	/**
	 * @param g The graphics context that can be used to draw on this component.
	 */
	public void paintComponent(Graphics g)
	{
		Dimension size = getSize();
		g.clearRect(0, 0, size.width, size.height);
		if(panelImage != null) {
			g.drawImage(panelImage, 0, 0, null);
		}
	}

}