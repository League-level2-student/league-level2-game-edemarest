import java.awt.Color;
import java.awt.Graphics;

public class Car extends GameObject{

	int skin; 
	
	public Car(int x, int y, int width, int height, int skin) {
		super(x, y, width, height);
		this.skin = skin;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		
		if(skin == 1) {
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
		
	}
	
	

}
