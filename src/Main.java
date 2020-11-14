import tutorial3.Image;
import tutorial3.ImageUtil;
import tutorial3.MovingImage;
import tutorial3.SuperImposedImage;

public class Main {

	public static void mySleep(int milliseconds) {
		try {
			Thread.sleep(250);
		}
		catch (InterruptedException e) { }
	}
	
	public static void main(String[] args) {
		Image prairie = new Image("prairie.png");
		SuperImposedImage background = new SuperImposedImage(0,0,"prairie.png");
		SuperImposedImage hut = new SuperImposedImage(300, 500,"hut-small.png");
		SuperImposedImage tree = new SuperImposedImage(650, 450,"tree2.png");
		MovingImage fox = new MovingImage(1100, 600,"fox-small.png");
		MovingImage bird = new MovingImage(0,380,"bird-small.png");
		
		int i;
				
		prairie.display();
		for(i=0;i<1100;i+=5)
		{
			prairie.superimpose(background);
			fox.xPos=1100-6*i;
			if(bird.xPos!=600)
			{
				bird.xPos=0+2*i;
				bird.yPos=380-i;
			}		
			prairie.superimpose(hut);
			prairie.superimpose(fox);
			prairie.superimpose(bird);
			prairie.superimpose(tree);
			prairie.superimpose(tree.copy(680,470));
			mySleep(250);
			prairie.update();
			
		}

	}
}
