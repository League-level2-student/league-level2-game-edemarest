import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Car car;
	ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	Random rand = new Random();
	
	public ObjectManager(Car car) {
		this.car = car;
	}
	
	public void addObstacle(){
		obs.add(new Obstacle(rand.nextInt(GameRunner.WIDTH),0,100,100));

	}
	
	public void addCoin(){
		coins.add(new Coin(rand.nextInt(GameRunner.WIDTH),0,100,100));

	}
	
	public void update() {
		for(Obstacle ob: obs) {
			ob.update();
			if(ob.y > GameRunner.HEIGHT) {
				ob.isActive = false;
			}
		}
		for(Coin coin: coins) {
			coin.update();
			if(coin.y > GameRunner.HEIGHT) {
				coin.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	
	public void draw(Graphics g) {
		car.draw(g);
		for(Obstacle ob: obs) {
			ob.draw(g);
		}
		for(Coin coin: coins) {
			coin.draw(g);
		}
	}
	
	public void purgeObjects() {
		for(int i = 0; i < obs.size(); i++) {
			if(!(obs.get(i).isActive)) {
				obs.remove(i);
			}
		}
		for(int i = 0; i < coins.size(); i++) {
			if(!(coins.get(i).isActive)) {
				coins.remove(i);
			}
		}
	}
	
	public void checkCollision() {
		for(Obstacle ob: obs) {
			if(car.collisionBox.intersects(ob.collisionBox)){
				car.isActive = false;
			}
		}
		for(Coin coin: coins) {
			if(coin.collisionBox.intersects(car.collisionBox)) {
				coin.isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addObstacle();
		addCoin();
	}
	
	
}
