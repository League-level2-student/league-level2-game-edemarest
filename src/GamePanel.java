import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	
	Timer frameDraw;
	private BufferedImage background;
	private int backgroundHeight = 0; //You need this if you are scrolling up-down
	private int scrollSpeed = 5;
	int HEIGHT = GameRunner.HEIGHT;
	int WIDTH = GameRunner.WIDTH;
	int y1 = 7200-HEIGHT;
	int y2 = 7200;
	//Game state variables
    final int START = 0;
    final int GAME = 1;
    final int END = 2;
    
    int currentState = GAME;
	
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
		g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
	}
	
	void drawGameState(Graphics g) {
		 
	}
	
	void drawEndState(Graphics g)  {
		//g.setColor(Color.RED);
		//g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
	}
	
	@Override
	public void paintComponent(Graphics g){
		//g.fillRect(10, 10, 100, 100);
		g.drawImage(background,0, 0, WIDTH, HEIGHT, 0, y1, WIDTH, y2,
				this);
		//g.drawImage(background, 0, 0, null);
		
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
		moveBackground();
		repaint();
	}
	
	
	
	
}
