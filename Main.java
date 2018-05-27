import java.util.ArrayList;
import java.util.List;

/**
 *
 * Main class, hold an instance of ImageViewer. 
 * A method in this class that creates a list of filters which it passed to the Object.
 * */

public class Main {
	public static void main(String []args){
		
		ImageViewer viewer = new ImageViewer(createFilters());
		viewer.getClass();

		}
	/**
	 * Create and return a list with all the known filters.
	 * @return adds to the list and returns the list 
	 */

	@SuppressWarnings("unchecked")
	public static List<OFImage> createFilters()
	{
		@SuppressWarnings("rawtypes")
		List filterList = new ArrayList<>();
		//image filters
		filterList.add(new RedFilter("RedFilter"));
		filterList.add(new FlipXFilter("FlipXFilter"));
		filterList.add(new GrayScaleFilter("Grayscale"));
		filterList.add(new InvertFilter("InvertFilter"));
		filterList.add(new BrighterFilter("LighterFilter"));

		//predefined image patterns
		filterList.add(new BlackCircle("BlackCircle"));
		filterList.add(new Stripes("Stripes"));
		filterList.add(new ChessBoard("ChessBoard"));
		
		return filterList;
	}

	}
