import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Coin extends GameObject{
	
	int speed = 10;
	
	public Coin(int lane, int x, int y, int width, int height, int speed) {
		super(lane, x, y, width, height, speed);
		y = -100;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		y += speed;
		super.update();
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        if(lane == 2) {
        	x = 840;
        }
        else if(lane == 1) {
        	x = 550;
        }
        else {
        	x = 200;
        }
        //g.drawRect(x,  y,  width,  height);
		g.setColor(Color.YELLOW);
		g.fillRect(collisionBox.x, collisionBox.y, width, height);
	}
	
	
}
