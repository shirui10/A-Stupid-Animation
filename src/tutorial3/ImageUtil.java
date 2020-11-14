package tutorial3;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * Class designed for reading and writing files containing images.
 */
public class ImageUtil {
	/** Reads an image in the format JPEG or PNG.
	 * 
	 * @param path acces path to the file containing the image
	 * @return returns the image in the form of a 3-dimensional array: 
	 *   column, line and color component or opacity
	 * @throws IOException 
	 */
	public static int[][][] readImage(String path){
		int[][][] res;
		int[] tab;
		//path = new ImageReader().getClass().getResource("/" + path).getFile();
		try {
			BufferedImage bufi = ImageIO.read(new File(path));
			int width = bufi.getWidth();
			int height = bufi.getHeight();
			tab = bufi.getRGB(0,0,width,height,null,0,width);
			res = new int[width][height][];
			for (int i=0; i<tab.length; i++){
				res[i%width][i/width] = explodePixel(tab[i]);
			}
			return res;
		}catch (IOException ioe) {
			throw new IllegalArgumentException(ioe.getMessage());
		}
	}

	public static Display displayImage(int[][][] tab){
		return new Display(tab);
	}

	public static void writeImage(int[][][] tab, String typeFichier, String nomFich){
		try {
			BufferedImage bufi = new BufferedImage(tab.length,tab[0].length, BufferedImage.TYPE_INT_ARGB);
			int[] tabbis = packedFromNatural(tab);
			bufi.setRGB(0,0,tab.length,tab[0].length,tabbis,0,tab.length);
			ImageIO.write(bufi, typeFichier, new File(nomFich));
		} catch(IOException ioe) {
			throw new IllegalArgumentException(ioe.getMessage());
		}
	}


	private static int[] packedFromNatural(int [][][] tab){
		int[] res = new int[tab.length*tab[0].length];
		for (int col = 0; col<tab.length; col++){
			for (int lig=0; lig<tab[0].length; lig++){
				res[lig*tab.length+col] = packedFromArray(tab[col][lig]);
			}
		}
		return res;
	}

	private static int packedFromArray(int[] pix){
		int res = pix[3];
		res = (((res<<8) + pix[0]<< 8) + pix[1]);
		return (res<<8) + pix[2];
	}

	private static int[] explodePixel(int pix){
		int[] pt = new int[4];
		pt[3]= pix >> 24 & 0x000000FF;
		pt[0]= pix >> 16 & 0x000000FF;
		pt[1]= pix >> 8 & 0x000000FF;
		pt[2]= pix & 0x000000FF;
		return pt;
	}	
	
	public static void main(String[] args){
		int[][][] arr =  {{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}}};
		int[][][] budgies = readImage("images/budgie.png");
		displayImage(arr);
		displayImage(budgies);
		writeImage(arr, "png", "images/example.png");
		writeImage(budgies, "png", "images/bugdie_bis.png");
	}
}

