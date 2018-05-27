import java.awt.Graphics;

/**
 * A filter to make a ChessBoard over the picture and blank image.
 */
public class ChessBoard extends Filter
{
	/**
	 * Constructor for objects of class ChessBoard
	 */
	public ChessBoard(String name)
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
        Graphics g2d = image.createGraphics();
        g2d.fillRect(0, 0, 50, 50);
	     for(int i=50; i<=image.getWidth(); i+=100 ){
	    	 for(int j=50; j<=image.getHeight(); j+=100 ){
		    	 g2d.clearRect(i, j,50,50);
		     } 
	     }
	     for(int i=0; i<=image.getWidth(); i+=100 ){
	    	 for(int j=0; j<=image.getHeight(); j+=100 ){
	    		 	g2d.clearRect(i, j, 50, 50);
	    	 }
	   }
		return image;

    }
}