import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstacle extends GameObject{
	
	int speed = 10;
	int rand = new Random().nextInt(3);
	
	
	public Obstacle(int lane, int x, int y, int width, int height) {
		super(lane, x, y, width, height);
		// TODO Auto-generated constructor stub
		y = -100;
	}

	public void update() {
		y += speed;
		super.update();
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.RED);
        if(lane == 2) {
        	x = 840;
        }
        else if(lane == 1) {
        	x = 540;
        }
        else {
        	x = 200;
        }
        g.fillRect(x, y, width, height);
        //g.setColor(Color.BLACK);
        //g.drawRect(collisionBox.x, collisionBox.y, width, height);
        
	}
	
	
}
