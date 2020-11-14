package tutorial3;

public class SuperImposedImage extends Image {
	public int xPos;
	public int yPos;
	
	public SuperImposedImage(int x, int y, String filename) {
		super(filename);
		this.xPos = x;
		this.yPos = y;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	
	
	
}
