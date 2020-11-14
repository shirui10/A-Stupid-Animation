package tutorial3;

public class Image {

	// Instance variables
	protected Pixel[][] grid;
	protected int       width;
	protected int       height;
	private   Display   window; 
	public String S;
	
	// Constructor
	public Image(String filename) {
		// Ex of filename : "budgie.png"
		int[][][] arr;
		S=filename;
		arr = ImageUtil.readImage("images/"+filename);
		this.width = arr.length;
		this.height = arr[0].length;

		this.grid = new Pixel[this.height][this.width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r,g,b,alpha;
				r = arr[j][i][0];
				g = arr[j][i][1];
				b = arr[j][i][2];
				alpha = arr[j][i][3];
				this.grid[i][j] = new Pixel(r,g,b,alpha);
			}
		}
		this.window = null;
	}


	//public void display() {}
	// Getters and setters
	public int getHeight() { return this.height; }
	public int getWidth() { return this.width; }

	// Other methods
	public int[][][] export() {
		int[][][] arr = new int[width][height][4];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r,g,b,alpha;
				r = this.grid[i][j].getLevelRed();
				g = this.grid[i][j].getLevelGreen();
				b = this.grid[i][j].getLevelBlue();
				alpha = this.grid[i][j].getAlphaParameter();
				arr[j][i][0] = r;
				arr[j][i][1] = g;
				arr[j][i][2] = b;
				arr[j][i][3] = alpha;
				//this.grid[i][j] = new Pixel(r,g,b,alpha);
			}
		}
		return arr;
	}

	public void display() {
		// Creates a window and display the image inside this window
		// public static void displayImage(int[][][] tab){
		int[][][] arr = this.export();
		this.window = ImageUtil.displayImage(arr);
	}
	
	public void update() {
		// update the window
		this.window.update(this.export());
	}
	

	public void blackAndWhite() {
		int avg, r, g, b;
		for (int i = 0; i < this.grid.length;i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				r = this.grid[i][j].getLevelRed();
				g = this.grid[i][j].getLevelGreen();
				b = this.grid[i][j].getLevelBlue();
				avg = (r+g+b)/3;
				this.grid[i][j].setLevelRed(avg);
				this.grid[i][j].setLevelGreen(avg);
				this.grid[i][j].setLevelBlue(avg);
			}
		}
	}

	public void enlarge() {
		Pixel[][] grid2;
		grid2 = new Pixel[height][2*width];
		for (int i = 0; i < this.grid.length;i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				Pixel p = this.grid[i][j].clone();
				grid2[i][2*j]   = this.grid[i][j];
				grid2[i][2*j+1] = p;
			}
		}		
		this.grid = grid2;
		this.width *= 2;
	}

	public void superimpose(SuperImposedImage sii) {
		// We want to superimpose the image sii on the image 'this'
		// The coordinates of the upper left corner are inside sii
		// We replace some pixels of this with the pixels of sii
		int k, l;


		for (int i = 0; i < sii.height-1; i++) {
			for (int j = 0; j < sii.width-1; j++) {
				k = i+sii.getyPos();
				l = j+sii.getxPos();

				if (sii.grid[i][j].getAlphaParameter() != 0) {
					try {
						this.grid[k][l] = sii.grid[i][j];
					}
					catch (ArrayIndexOutOfBoundsException e) { }
				}
			}
		}


		/*for (int i = 0; i < sii.height-1; i++) {
			for (int j = 0; j < sii.width-1; j++) {
				k = i+sii.getyPos();
				l = j+sii.getxPos();

				if (k >= 0 && k < this.height && l >= 0 && l < this.width) {
					if (sii.grid[i][j].getAlphaParameter() != 0)
						this.grid[k][l] = sii.grid[i][j];
				}
			}
		}*/
	}

	public SuperImposedImage copy(int x,int y)
	{
		SuperImposedImage copy = new SuperImposedImage(x,y,S);
		return copy;
	}
	
}
