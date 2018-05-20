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

    public void apply(OFImage image)
    {
        Graphics g2d = image.createGraphics();
        g2d.fillRect(0, 0, 50, 50);
	     for(int i=0; i<=image.getWidth(); i+=100 ){
	    	 for(int j=0; j<=image.getHeight(); j+=100 ){
		    	 g2d.clearRect(i, j,50,50);
		     } 
	     }
	     for(int i=0; i<=image.getWidth(); i+=100 ){
	    	 for(int j=0; j<=image.getHeight(); j+=100 ){
	    		 	g2d.clearRect(i, j, 50, 50);
	    	 }
	   }

    }
}