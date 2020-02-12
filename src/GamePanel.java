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
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	private int backgroundHeight = 0; //You need this if you are scrolling up-down
	private int scrollSpeed = 50;
	int HEIGHT = GameRunner.HEIGHT;
	int WIDTH = GameRunner.WIDTH;
	int y1 = 7200-HEIGHT;
	int y2 = 7200;
	//Game state variables
    final int START = 0;
    final int GAME = 1;
    final int END = 2;
    
    int currentState = START;
	
	public GamePanel(int frameWidth, int frameHeight) {

		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
		
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
	
	
	
	
	void updateStartState() {
		
	}
	void updateGameState() {
		
	}
	void updateEndState()  {
		
	}
	
	void drawStartState(Graphics g) {
		g.setColor(Color.PINK);
		    loadImage ("Start Screen.png");
	}
	
	void drawGameState(Graphics g) {
		g.drawImage(background,0, 0, WIDTH, HEIGHT, 0, y1, WIDTH, y2,
				this);
		
	}
	
	void drawEndState(Graphics g)  {
		//g.setColor(Color.RED);
		//g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
	}
	
	@Override
	public void paintComponent(Graphics g){

		
		//Drawing game states
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
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(currentState == GAME) {
			moveBackground();
			repaint();
		}
		
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed");
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println(currentState);

			if(currentState == START) {
				currentState = GAME;
			}
			else if(currentState == GAME) {
				currentState = END;
			}
			else if (currentState == END) {
				currentState = START;
		    } 
		}
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	//method to load images
		void loadImage(String imageFile) {
			System.out.println("Loading image...");
		    if (needImage) {
		        try {
		            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			    gotImage = true;
			    System.out.println("Got Image");
		        } catch (Exception e) {
		            
		        }
		        needImage = false;
		    }
		}
	
	
	
}
