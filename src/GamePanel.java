import java.awt.Color;
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
	private BufferedImage background;
	private BufferedImage startScreen;
	public static BufferedImage image;	
	private int backgroundHeight = 0; //You need this if you are scrolling up-down
	BufferedImage selectionScreen;
	BufferedImage start1;
	BufferedImage start2;
	BufferedImage start3;
	
	private int scrollSpeed = 10;
	int HEIGHT = GameRunner.HEIGHT;
	int WIDTH = GameRunner.WIDTH;
	int y1 = 7200-HEIGHT;
	int y2 = 7200;
	
	//Game state variables
    final int START = 0;
    final int GAME = 1;
    final int END = 2;
    
    //Car version variable
    int carSkin = 1;
    
    Car car = new Car(500, 500, 200, 400, carSkin);
    
    int currentState = START;
	
	public GamePanel(int frameWidth, int frameHeight) {

		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    

	        startScreen = loadImage ("Start Screen.png");
	    
	        start1 = loadImage ("Start1.png");
	        start2 = loadImage ("Start2.png");
	        start3 = loadImage ("Start3.png");
	    
	    
		
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
		
	}
	void updateEndState()  {
		
	}
	
	//Drawing states
	
	void drawStartState(Graphics g) {
		if(carSkin == 2) {
			g.drawImage(start1,0, 0, 1200, 800, null);
		}
		else {
			g.drawImage(startScreen,0, 0, 1200, 800, null);
		}
	}
	
	void drawGameState(Graphics g) {
		g.drawImage(background,0, 0, WIDTH, HEIGHT, 0, y1, WIDTH, y2,
				this);
		car.skin = carSkin;
		car.draw(g);
	}
	
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
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
		}
		
		else {
			y1 -= scrollSpeed;
			y2 -= scrollSpeed; }
		repaint();
	
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
				}
			}

		
		
		//ENTER control
		if(e.getKeyCode()==KeyEvent.VK_S) {
			System.out.println(currentState);

			if(currentState == START) {
				currentState = GAME;
			}
			else if(currentState == GAME) {
				currentState = END;
				repaint();
			}
			else if (currentState == END) {
				currentState = START;
				repaint();
		    } 
		}
		
		//LEFT and RIGHT controls
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		}
		//LEFT and RIGHT controls
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		}
		
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	//method to load images
		BufferedImage loadImage(String imageFile) {
			System.out.println("Loading image...");
			BufferedImage image=null;
		        try {
		            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			    System.out.println("Got Image");
		        } catch (Exception e) {
		            
		        }
		    return image;
		}
	
	
	
}
