import java.awt.Color;
import java.awt.Graphics;

public class Car extends GameObject{

	int skin; 
	
	public Car(int lane, int x, int y, int width, int height, int skin) {
		super(lane, x, y, width, height);
		this.skin = skin;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		
		if(skin == 1 || skin == 0) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
		}
		else if(skin == 2) {
	        g.setColor(Color.PINK);
	        g.fillRect(x, y, width, height);
			}
		
		else if(skin == 3) {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.BLACK);
		g.drawRect(collisionBox.x, collisionBox.y, width, height);
	}
	
	

}
