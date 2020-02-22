import java.awt.Rectangle;

public class GameObject {
		int lane;
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed;
	 boolean isActive = true;
	 Rectangle collisionBox;
	 
	 public GameObject(int lane, int x, int y, int width, int height, int speed) {
		 this.lane = lane;
		 this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		 collisionBox = new Rectangle(x,y,width,height);
	 }
	 
	 public void update() {
		 collisionBox.setBounds(x, y, width, height);
		 
	 }
}
