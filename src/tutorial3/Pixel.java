package tutorial3;
public class Pixel {
	// Class (static) variables & constants
	private static final int MAX = 255;
	
	// Instance variables
	private int levelRed   = 0;
	private int levelGreen = 0;
    private int levelBlue  = 0;
    private int alphaParameter = MAX;
	
	
	// Methods
	// Constructors
    public Pixel(int levelRed, int levelGreen, int levelBlue, int alpha) {
    	this.levelRed = levelRed;
    	this.levelGreen = levelGreen;
    	this.levelBlue = levelBlue;
    	this.alphaParameter = alpha;
    }
    
	
	// Getters and Setters
    public static int getMax() { return Pixel.MAX; }
	public int getLevelRed() { return levelRed;	}
	public int getLevelGreen() { return levelGreen;	}
	public int getLevelBlue() {	return levelBlue; }
	public int getAlphaParameter() { return alphaParameter;	}

    public void setLevelRed(int r)   { levelRed = r; }
    public void setLevelGreen(int g) { levelGreen = g; }
    public void setLevelBlue(int b)  { levelBlue = b; }
    
	// Other methods
	
    public Pixel clone() {
    	return new Pixel(levelRed, levelGreen, levelBlue, alphaParameter);
    }
    
    
    
    public String toString() {
    	return "("+this.levelRed
    			+", "+this.levelGreen
    			+", "+this.levelBlue
    			+", "+this.alphaParameter
    			+")";
    }
	
	
	
}
