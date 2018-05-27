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
 * 
 * Advanced Object Oriented Programming Project
 * 
 * ImageViewer is class of the image viewer application. It builds and
 * displays the application GUI and initializes all other components.
 * 
 */
public class ImageViewer
{
	// static fields:
	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

	// fields:
	private JFrame frame;
	private ImagePanel imagePanel;
	private OFImage originalImage, newImage;
	private List<OFImage> filters;
	private ArrayList<OFImage> pastImages = new ArrayList<OFImage>();
	private int pastImagesIndex=0;
	
	File selectedFile;
	/**
	 * Create an ImageViewer and display its GUI on screen.
	 */

	@SuppressWarnings("unchecked")
	public ImageViewer(List<?> list)
	{
		originalImage = null;
		filters = (List<OFImage>) list;
		makeFrame();
	}


	// implementation of menu functions

	private OFImage ScaledImage(OFImage currentImage, int w, int h) {

		OFImage original =  currentImage;
		OFImage resized = new OFImage(w,h);
		Graphics2D g2 = resized.createGraphics();
		g2.drawImage(original, 0, 0, w, h, null);
		g2.dispose();
		return resized;

	}

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
		originalImage = ImageFileManager.loadImage(selectedFile);
		originalImage = ScaledImage(originalImage, imagePanel.getWidth(), imagePanel.getHeight());
		imagePanel.setImage(originalImage);
		frame.pack();

	}

	/**
	 * Reset function: Undo filters and delete all the image filters.
	 */
	private void Reset(){

		if(originalImage !=null){
			originalImage = ImageFileManager.loadImage(selectedFile);
			originalImage = ScaledImage(originalImage, imagePanel.getWidth(), imagePanel.getHeight());
			imagePanel.setImage(originalImage);
			pastImages.clear();
		}else{
			System.out.println("No Image available, please upload an image");
		}
	}


	/**
	 * Close function: close image.
	 */
	private void close()
	{
		Reset();

		imagePanel.clearImage();

	}

	/**
	 * 'Save As' function: save the current image to a file.
	 */
	private void saveAs()
	{
		if(originalImage != null) {
			int returnVal = fileChooser.showSaveDialog(frame);

			if(returnVal != JFileChooser.APPROVE_OPTION) {
				return;  // cancelled
			}
			File selectedFile = fileChooser.getSelectedFile();
			ImageFileManager.saveImage(originalImage, selectedFile);

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
		OFImage storedImage = copyImage(originalImage);
		storedImage =filter.apply(storedImage);
		imagePanel.setImage(storedImage);

		pastImagesIndex++;

		pastImages.add(storedImage);


		if(pastImagesIndex <pastImages.size()){
			int PIsize = pastImages.size();
			for(int i = PIsize-1; i > pastImagesIndex;i--){
				pastImages.remove(i);
			}
		}
		originalImage=copyImage(storedImage);
		frame.repaint();

	}




	/**
	 * Copies an image so it can be more copies of the same image.
	 * 
	 * @param image image to be copied
	 */
	private OFImage copyImage(OFImage image) {
		OFImage resimage= new OFImage(image);
		Graphics2D g= resimage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return resimage;

	}


	/**
	 * 'About' function: show the 'Help' box.
	 */
	private void showHelp()
	{
		JOptionPane.showMessageDialog(frame, 
				"This progam can open,save and filter with different filters chosen by the user,\n"
						+ "If Undofilter doesn't work anymore user must (PRESS RESE) to start OVER AGAIN, \n" +

				  			"User can add another filter by extending the Filter Class and in Main class add it to the list"
				  			+ "\n Sometimes when used SwirlFilter user maybe need to press Undofilter sveral time,"
				  			+ "\n since the program manages to make several copies during runtime",
				  			"About ImageViewer", 
				  			JOptionPane.INFORMATION_MESSAGE);

	}
	/**
	 * 'About' function: show the 'about' box.
	 */
	private void showAbout()
	{
		JOptionPane.showMessageDialog(frame, 
				"@authors Hanif Nazari & Noak Pettersson"
						+ " " +
				  			"\n  The two autors have done everything together"
				  			+ "\n",
				  			"", 
				  			JOptionPane.INFORMATION_MESSAGE);

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
			public void actionPerformed(ActionEvent e) {

				jsliderframe();

			}
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

				if(pastImagesIndex >0 ){
					pastImagesIndex --;


					newImage=pastImages.get(pastImagesIndex-1);
					imagePanel.setImage(newImage);
					frame.repaint();
				}
			}
		});

		menu.add(item);

		// create the Help menu
		menu = new JMenu("Help");
		menubar.add(menu);

		item = new JMenuItem("Help");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ showHelp(); }
		});
		menu.add(item);
		item = new JMenuItem("About");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ showAbout(); }
		});
		menu.add(item);
	}
	/**
	 * Gets the value from JSlider and passes it to setvalue method
	 * 
	 * @param Jslider gets value when sliding
	 */
	class SliderListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {

			JSlider source = (JSlider)e.getSource();

			int value = (int)source.getValue();
			setvalue(value);
		}
	}
	/**
	 *
	 * 
	 * @param jsliderframe  A frame for JSlider
	 */
	public void jsliderframe(){

		JFrame fr = new JFrame("Swirl");
		JSlider slider = new JSlider(JSlider.HORIZONTAL,0,5,0);
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
	/**
	 * Apply the SwirlFilter to the origanlImage.
	 * 
	 * @param setvalue gets value from jslider and swirls
	 */
	public void setvalue(int value) {

		int height = originalImage.getHeight();
		int width = originalImage.getWidth();
		double x0 = 0.5 * (width  - 1);
		double y0 = 0.5 * (height - 1);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {

				double dx = x - x0;
				double dy = y - y0;
				double r = Math.sqrt(dx*dx + dy*dy);
				double angle = Math.PI / 256 *value*r;

				int tx = (int) (+dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
				int ty = (int) (+dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

				if (tx >= 0 && tx < width && ty >= 0 && ty < height)
					originalImage.setRGB(x, y, originalImage.getRGB(tx, ty));

			}
		}
		newImage=originalImage;

		imagePanel.setImage(newImage);

		pastImages.add(newImage);

		pastImagesIndex++;
	}

}