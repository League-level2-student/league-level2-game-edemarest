import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstacle extends GameObject{
	
	int speed = 10;
	int rand = new Random().nextInt(3);
	int y = -100;
	
	
	public Obstacle(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		y += speed;
		super.update();
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.RED);
        if(rand == 2) {
        	g.fillRect(840, y, width, height);
        }
        else if(rand == 1) {
        	g.fillRect(550, y, width, height);
        }
        else {
        	g.fillRect(200, y, width, height);
        }
	}
	
	
}
