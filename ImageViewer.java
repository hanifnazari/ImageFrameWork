import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *@author Hanif Nazari
 *@author Hanif Noak Pettersson
 * 
 * Advanced Object Oriented Programming Project
 * 
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initializes all other components.
 */
public class ImageViewer
{
	// static fields:
	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

	// fields:
	private JFrame frame;
	private ImagePanel imagePanel;
	private OFImage currentImage, newImage;
	File selectedFile;
	private List<?> filters;
	private ArrayList<OFImage> pastImages;
	private int pastImagesIndex=0;
	/**
	 * Create an ImageViewer and display its GUI on screen.
	 */
	public ImageViewer()
	{
		currentImage = null;
		filters = createFilters();
		makeFrame();
	}


	// ---- implementation of menu functions ----

	/**
	 * Open function: open a file chooser to select a new image file,
	 * and then display the cholen image.
	 */
	private void openFile()
	{
		int returnVal = fileChooser.showOpenDialog(frame);

		if(returnVal != JFileChooser.APPROVE_OPTION) {
			return;  // cancelled
		}
		selectedFile = fileChooser.getSelectedFile();
		currentImage = ImageFileManager.loadImage(selectedFile);
		imagePanel.setImage(currentImage);
		frame.pack();

	}
	
	/**
	 * Reset function: Undo filters and delete all the image filters.
	 */
	private void Reset(){

		if(currentImage!=null){
			currentImage = ImageFileManager.loadImage(selectedFile);
			imagePanel.setImage(currentImage);
		}else{
			System.out.println("No Image available, please upload an image");
		}
	}

	/*
	/**
	 * Close function: close image.
	 */
	private void close()
	{

			imagePanel.clearImage();

	}

	/**
	 * 'Save As' function: save the current image to a file.
	 */
	private void saveAs()
	{
		if(currentImage != null) {
			int returnVal = fileChooser.showSaveDialog(frame);

			if(returnVal != JFileChooser.APPROVE_OPTION) {
				return;  // cancelled
			}
			File selectedFile = fileChooser.getSelectedFile();
			ImageFileManager.saveImage(currentImage, selectedFile);

		}
	}


	/**
	 * Quit function: quit the application.
	 */
	private void quit()
	{
		System.exit(0);
	}

	
	/**
	 * Apply a given filter to the current image.
	 * 
	 * @param filter   The filter object to be applied.
	 */
	private void applyFilter(Filter filter)
	{		
		if(currentImage!=null){
			filter.apply(currentImage);
			frame.repaint();
		}
			
		}
	@SuppressWarnings("unused")
	private void Undofilter(){
		
			newImage.copyImage(currentImage);			
			imagePanel.setImage(newImage);
			
			frame.repaint();
		}



	/**
	 * 'About' function: show the 'about' box.
	 */
	private void showAbout()
	{
		JOptionPane.showMessageDialog(frame, 
				"This progam can open,save and filter with different filter choosen by the user\n",
				"About ImageViewer", 
				JOptionPane.INFORMATION_MESSAGE);

	}


	/**
	 * Create and return a list with all the known filters.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List createFilters()
	{
		List filterList = new ArrayList();
		//image filters
		filterList.add(new RedFilter("RedFilter"));
		filterList.add(new FlipXFilter("FlipXFilter"));
		filterList.add(new GrayScaleFilter("Grayscale"));
		filterList.add(new InvertFilter("InvertFilter"));
		filterList.add(new LighterFilter("LighterFilter"));

		//predefined image patterns
		filterList.add(new BlackCircle("BlackCircle"));
		filterList.add(new Stripes("Stripes"));
		filterList.add(new ChessBoard("ChessBoard"));
		return filterList;
	}
	
	/**settings for make frame layouts
	 * 
	 * */

	private void makeFrame()
	{
		frame = new JFrame("Image Viewer and manipulation");
		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setBorder(new EmptyBorder(40, 40, 40, 40)); // sets empty border layout
		contentPane.setBackground(Color.lightGray);
		makeMenuBar(frame);


		// Create the image pane in the center
		imagePanel = new ImagePanel();
		imagePanel.setBorder(new EtchedBorder());
		imagePanel.setOpaque(true);
		contentPane.add(imagePanel, BorderLayout.CENTER);

		// Create the toolbar with the buttons
		JPanel toolbar = new JPanel();
		//toolbar.setLayout(new GridLayout(1, 0));
		toolbar.repaint();
		
		// Add toolbar into panel with flow layout for spacing
		JPanel flow = new JPanel();
		flow.add(toolbar);
		// building is done
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
	
	/**
	 * Create the main frame's menu bar.
	 * 
	 * @param frame   The frame that the menu bar should be added to.
	 */
	private void makeMenuBar(JFrame frame)
	{

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JMenu menu;
		JMenuItem item;

		// create the File menu
		menu = new JMenu("File");
		menubar.add(menu);

		item = new JMenuItem("Open");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { openFile(); }
		});
		menu.add(item);

		item = new JMenuItem("Save");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { saveAs(); }
		});
		menu.add(item);

		
		item = new JMenuItem("Blank Image");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { close(); }
		});
		menu.add(item);
		menu.addSeparator();

		item = new JMenuItem("Exit");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { quit(); }
		});
		menu.add(item);
		

		// create the Filter menu
		menu = new JMenu("Filter");
		menubar.add(menu);

		for(Iterator<?> it=filters.iterator(); it.hasNext(); ) {
			final Filter filter = (Filter)it.next();

			item = new JMenuItem(filter.getName());
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
				applyFilter(filter);

				}
			});
			menu.add(item);
		}
		menu.addSeparator();
		// Slider
				item = new JMenuItem("SwirlSlider");
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {jsliderfram();	}
				});
				menu.add(item);
				
		// UndoFilter		
		menu.addSeparator();
		item = new JMenuItem("Reset");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset();
			}
		});
		menu.add(item);
		
		item = new JMenuItem("UndoFilter");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Undofilter();
				if(pastImagesIndex>0){
					pastImagesIndex--;
				}
				newImage = pastImages.get(pastImagesIndex);
				imagePanel.setImage(newImage);
			}

		});
		menu.add(item);

		// create the Help menu
		menu = new JMenu("Help");
		menubar.add(menu);

		item = new JMenuItem("About");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ showAbout(); }
		});
		menu.add(item);
	}
	/**new frame for Slider.
	 * 
	 * */
	public void jsliderfram(){
		JFrame fr = new JFrame("Swirl");
		JSlider slider = new JSlider(JSlider.HORIZONTAL,-10,10,0);
		slider.setMinorTickSpacing(1);
		slider.addChangeListener(new SliderListener());
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintTrack(true);
		slider.setPaintLabels(true);
		slider.setVisible(true);
		fr.add(slider,BorderLayout.CENTER);
		fr.setSize(300, 100);
		fr.setVisible(true);
		
	}
	
	/**change the swirls with slider
	 * 
	 * */
	class SliderListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {

			JSlider source = (JSlider)e.getSource();
			int fps = (int)source.getValue();
			setValue(fps);

		}

	}
	/**sets the value from slider in the swirl filter.
	 * 
	 * */
	private void setValue(float value) {

		int height = currentImage.getHeight();
		int width = currentImage.getWidth();

		double x0 = 0.5 * (width  - 1);
		double y0 = 0.5 * (height - 1);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {

				double dx = x - x0;
				double dy = y - y0;
				double r = Math.sqrt(dx*dx + dy*dy);
				double angle = Math.PI / 256 * value*r;

				int tx = (int) (+dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
				int ty = (int) (+dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

				if (tx >= 0 && tx < width && ty >= 0 && ty < height)
					currentImage.setRGB(x, y, currentImage.getRGB(tx, ty));
				frame.repaint();

			}
		}
	}

	
	public static void main(String []args){
		new ImageViewer();
	}

}