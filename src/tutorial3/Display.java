package tutorial3;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.image.*;
import java.awt.*;



/**
 * Class whose instances are windows displayed on screen
 * containing an image
 *
 */
public class Display extends JFrame{

	private static final long serialVersionUID = 1L;
	private int[] tab;
	private JPanel jp;

	/**
	 * @param pixels the image to display
	 */
	public Display(int[][][] pixels){
		int width, height;
		width = pixels.length;
		height = pixels[0].length;
		BufferedImage bim = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		int[] pixtab = arrayFromPix(pixels);
		tab = ( (DataBufferInt) bim.getRaster()
				.getDataBuffer() ).getData();
		System.arraycopy(pixtab, 0, tab, 0, pixtab.length);
		jp = new ImagePanel(bim);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.setPreferredSize(new Dimension(bim.getWidth(),bim.getHeight()));
		this.add(jp);
		this.pack();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		/*JFrame jframe = this;
		this.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
				  System.out.println("closing");
			    jframe.dispose();
			  }
			});*/
		
		this.setVisible(true);
	}
	/**
	 * Method displaying a new image in the window.
	 * 
	 * This image must have the same dimensions as the one passed as a  
	 * parameter to the constructor when creating the object on which the method is
	 * applied.
	 * @param pixels the image to display
	 * @throws IndexOutOfBoundsException may be thrown if pixels has not 
	 * the same dimension as the image previously displayed by the object.
	 */
	public void update(int[][][] pixels){
		int[] pixtab = arrayFromPix(pixels);
		System.arraycopy(pixtab, 0, tab, 0, pixtab.length);
		jp.revalidate();
		jp.repaint();
	}
	private int[] arrayFromPix(int[][][] pixels){
		int[] res = new int[pixels.length*pixels[0].length];
		for (int col = 0; col< pixels.length; col++){
			for (int lig=0; lig<pixels[0].length; lig++){
				res[pixels.length*lig+col] = intFromPixel(pixels[col][lig]);
			}
		}
		return res;
	}
	private int intFromPixel(int[] pix){
		int res = pix[3];
		res = (((res<<8) + pix[0]<< 8) + pix[1]);
		return (res<<8) + pix[2];
	}

	class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private BufferedImage image;
		public ImagePanel(BufferedImage i) {
			image=i;
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null); 
		}

	}

}
