import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	Timer frameDraw;
	Timer obSpawn;
	//Timer coinSpawn;
	
	private BufferedImage background;
	private BufferedImage startScreen;
	public static BufferedImage image;	
	private int backgroundHeight = 0; //You need this if you are scrolling up-down
	BufferedImage selectionScreen;
	BufferedImage endScreen;
	BufferedImage start1;
	BufferedImage start2;
	BufferedImage start3;
	
	int scrollSpeed = 10;
	int HEIGHT = GameRunner.HEIGHT;
	int WIDTH = GameRunner.WIDTH;
	int y1 = 7200-HEIGHT;
	int y2 = 7200;
	static Font titleFont;
	static Font titleFont2;
	//Game state variables
    final int START = 0;
    final int GAME = 1;
    final int END = 2;
    
    //Car version variable
    int carSkin = 0;
    Car car = new Car(0, 500, 500, 200, 400, carSkin, 10);
    int lane = 2;
    
    ObjectManager manager = new ObjectManager(car);
    
    int currentState = START;
	
	public GamePanel(int frameWidth, int frameHeight) {

		frameDraw = new Timer(900/60,this);
	    frameDraw.start();

    	titleFont = new Font("Arial", Font.BOLD, 50);
    	titleFont2 = new Font("Arial", Font.BOLD, 100);

	        startScreen = loadImage ("Start Screen.png");
	    
	        start1 = loadImage ("Start1.png");
	        start2 = loadImage ("Start2.png");
	        start3 = loadImage ("Start3.png");
	        
	        endScreen = loadImage ("End Screen.png");
	    
	    
		
		//Hold on to background image
		try {
			Object imageFileName;
			background = ImageIO.read(getClass().getResource("Background.png"));
			backgroundHeight = background.getHeight(); //You need this if you are scrolling up-down
			}
			catch (Exception e) {
			System.out.println("Could not find background image"); 
			}	
		}
	
	
	
	
	//Updating states
	void updateStartState() {

	}
	void updateGameState() {
		moveBackground();
		manager.update();
		if(!car.isActive) {
			currentState = END;
		}

		 if(manager.getScore()>35) {
			frameDraw.setDelay(450/60);
		}
		else if(manager.getScore()>30) {
			frameDraw.setDelay(500/60);
		}
		else if(manager.getScore()>25) {
			frameDraw.setDelay(550/60);
		}
		else if(manager.getScore()>20) {
			frameDraw.setDelay(600/60);
		}
		else if(manager.getScore()>15) {
			frameDraw.setDelay(650/60);
		}
		else if(manager.getScore()>10) {
			frameDraw.setDelay(700/60);
		}
		else if(manager.getScore()>5) {
			frameDraw.setDelay(800/60);
		}
		
	}
	void updateEndState()  {
		
	}
	
	//Drawing states
	
	void drawStartState(Graphics g) {
		if(carSkin == 1) {
			g.drawImage(start1,0, 0, 1200, 800, null);
		}
		else if(carSkin == 2) {
			g.drawImage(start2,0, 0, 1200, 800, null);
		}
		else if(carSkin == 3) {
			g.drawImage(start3, 0, 0, 1200, 800, null);
		}
		else {
			g.drawImage(startScreen,0, 0, 1200, 800, null);
		}
	}
	
	void drawGameState(Graphics g) {
		g.drawImage(background,0, 0, WIDTH, HEIGHT, 0, y1, WIDTH, y2,
				this);
		car.skin = carSkin;
    	g.setColor(Color.pink);
    	g.setFont(titleFont);
    	g.fillRect(GameRunner.WIDTH-300, 0, 320, 70);
    	g.setColor(Color.white);
		g.drawString("Score: "+manager.getScore(), GameRunner.WIDTH-250, 50);
		manager.draw(g);
		
	}
	
	void drawEndState(Graphics g)  {
		g.drawImage(endScreen,0, 0, 1200, 800, null);
    	g.setColor(Color.RED);
    	g.setFont(titleFont2);
		g.drawString("Score: "+manager.getScore(), 400, 400);
	}

	//Paint component
	@Override
	public void paintComponent(Graphics g){

		
		//Checking game states
		if(currentState == START){
		    drawStartState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}

	}
	
	private void moveBackground() {
		if (y1 <= 0){ 
			y1 = (7200 - HEIGHT);
			y2 = 7200; 
			System.out.println("End of image");
		}
		
		else {
			y1 -= scrollSpeed;
			y2 -= scrollSpeed; }
		repaint();
		
	
	}
	
	
	void startGame() {
		obSpawn = new Timer(1000, manager);
		obSpawn.start();
		manager.score = 0;
		car.isActive = true;
		
		//coinSpawn = new Timer(1250 , manager);
		//coinSpawn.start();
	}
	

	//Checking actions
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == START){
		    updateStartState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Car selection control

			if(currentState == START) {
				if(e.getKeyCode()==KeyEvent.VK_1) {
				    //electionScreen = start1;
					
					
					carSkin = 1;
					repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_2) {
					carSkin = 2;
					repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_3) {
					carSkin = 3;
					repaint();
				}
			}

		
		
		//ENTER control
		if(e.getKeyCode()==KeyEvent.VK_S) {

			if(currentState == START) {
				currentState = GAME;
				startGame();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_M) {
			 if (currentState == END) {
			currentState = START;
			manager = new ObjectManager(car);
			car = new Car(0, 500, 500, 200, 400, carSkin, 10);
	    } 
		}
		
		//LEFT and RIGHT controls
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    if(lane == 3) {
		    	car.x = 500;
		    	lane = 2;
		    	
		    }
		    else if (lane == 2) {
		    	car.x = 180;
		    	lane = 1;
		    }
		    car.update();
		}
		//LEFT and RIGHT controls
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    if(lane == 1) {
		    	car.x = 500;
		    	lane = 2;
		    }
		    else if (lane == 2) {
		    	car.x = 820;
		    	lane = 3;
		    }
		    car.update();
		}
		
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	//method to load images
		BufferedImage loadImage(String imageFile) {
			BufferedImage image=null;
		        try {
		            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		        } catch (Exception e) {
		            
		        }
		    return image;
		}
	
	
	
}
