import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Obstacle extends GameObject{
	
	int speed = 10;
	int rand = new Random().nextInt(3);
	int rand2 = new Random().nextInt(3);

	
	
	
	public Obstacle(int lane, int x, int y, int width, int height, int speed) {
		super(lane, x, y, width, height, speed);
		// TODO Auto-generated constructor stub
		y = -100;

	}

	public void update() {
		y += speed;
		super.update();
	}
	
	public void draw(Graphics g, BufferedImage pop, BufferedImage puddle, BufferedImage cone) {
		if(rand2 == 0) {
			g.drawImage(puddle, x, y, width, height, null);
			setPos(g);
		}
		else if(rand2==1) {
			g.drawImage(pop, x, y, width, height, null);
			setPos(g);
		}
		else {
			g.drawImage(cone, x, y, width, height, null);
			setPos(g);
		}
        
        
        //g.setColor(Color.BLACK);
        //g.drawRect(collisionBox.x, collisionBox.y, width, height);
        
	}
	
	void setPos(Graphics g) {
		if(lane == 2) {
        	x = 840;
        	g.setColor(Color.ORANGE);
        }
        else if(lane == 1) {
        	x = 540;
        	g.setColor(Color.RED);
        }
        else {
        	x = 200;
        	g.setColor(Color.PINK);
        }
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
	

