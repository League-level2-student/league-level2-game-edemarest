import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Car extends GameObject{

	int skin; 

	public static BufferedImage image;
	
	public Car(int lane, int x, int y, int width, int height, int skin, int speed) {
		super(lane, x, y, width, height, speed);
		this.skin = skin;

        
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g, BufferedImage skin1, BufferedImage skin2, BufferedImage skin3) {
		
		if(skin == 1 || skin == 0) {
			g.drawImage(skin1, x, y, width, height, null);
		}
		else if(skin == 2) {
			g.drawImage(skin2, x, y, width, height, null);
			}
		
		else if(skin == 3) {
			g.drawImage(skin3, x, y, width, height, null);
		}
		//g.setColor(Color.BLACK);
		//g.drawRect(collisionBox.x, collisionBox.y, width, height);
	}
	
	BufferedImage loadImage(String imageFile) {
		BufferedImage image=null;
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	        } catch (Exception e) {
	            
	        }
	    return image;
	}

}
