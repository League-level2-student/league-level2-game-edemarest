import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class ObjectManager implements ActionListener{
	Car car;
	ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	Random rand = new Random();
	Random rand2 = new Random();
	int randomNum;
	int randomNum2;
	int score;
	BufferedImage puddle;
	BufferedImage pop;
	BufferedImage cone;
	BufferedImage skin1;
	BufferedImage skin2;
	BufferedImage skin3;
	BufferedImage coing;
	public static BufferedImage image;
	
	
	public ObjectManager(Car car) {
		this.car = car;
        puddle = loadImage ("Puddle.png");
        pop = loadImage ("Pop.png");
        cone = loadImage ("Cone.png");
        skin1 = loadImage ("Skin 1.png");
        skin2 = loadImage ("Skin 2.png");
        skin3 = loadImage ("Skin 3.png");
        coing = loadImage("goldcoin.png");

	}
	
	public void addObstacle(int lane){
		obs.add(new Obstacle(lane, 1,-150,150,150, 10));
		

	}
	
	public void addCoin(int lane){
			coins.add(new Coin(lane, 0,-100,150,150, 10));
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
		car.draw(g, skin1, skin2, skin3);
		System.out.println(coins.size());
		for(Obstacle ob: obs) {
			ob.draw(g, pop, puddle, cone);
		}
		for(Coin coin: coins) {
			coin.draw(g, coing);
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
				score++;
				coin.isActive = false;
			}
			for(Obstacle ob: obs) {
				if(coin.collisionBox.intersects(ob.collisionBox)) {
					coin.isActive = false;
				}
			}
		}
	}
	
	int getScore() {
		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
		int randomNum3 = rand.nextInt(3);
		if(randomNum3 == 0 || randomNum3 ==1) {
		randomNum = rand.nextInt(3);
		addObstacle(randomNum);
		}
		else {
		randomNum2 = rand2.nextInt(3);
		addCoin(randomNum2);
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
