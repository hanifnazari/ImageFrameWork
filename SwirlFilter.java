import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class SwirlFilter extends Filter
{
	
	public SwirlFilter(String name)
	{
		super(name);
	}
	
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
	class SliderListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {

			JSlider source = (JSlider)e.getSource();
			int value = (int)source.getValue();
			 apply(null, value);

		}
	public OFImage apply(OFImage image, int value)
	{
		int height = image.getHeight();
		int width = image.getWidth();

		double x0 = 0.5 * (width  - 1);
		double y0 = 0.5 * (height - 1);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {

				double dx = x - x0;
				double dy = y - y0;
				double r = Math.sqrt(dx*dx + dy*dy);
				double angle = Math.PI / 256 * value *r;

				int tx = (int) (+dx * Math.cos(angle) - dy * Math.sin(angle) + x0);
				int ty = (int) (+dx * Math.sin(angle) + dy * Math.cos(angle) + y0);

				if (tx >= 0 && tx < width && ty >= 0 && ty < height)
					image.setRGB(x, y, image.getRGB(tx, ty));

			}
		}
		return image;
	}
	}
}