import javax.swing.JFrame;

public class GameRunner {
	
	JFrame frame;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	GamePanel panel;
	
	
	public GameRunner() {
		frame =  new JFrame();
		panel = new GamePanel(1200, 800);
		frame.addKeyListener(panel);
	}
	
	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		  GameRunner runner = new GameRunner();
		  
		  runner.setup();
	}
}
