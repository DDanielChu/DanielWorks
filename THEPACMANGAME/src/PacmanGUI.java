//These imports are either used to make the game look better or to make the swing components usable
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;


//The class that creates the frame where the pacman game will be 
public class PacmanGUI{
	
	//The class that creates the frame where the pacman game will be (it's static so that more features can be added in other classes
	static final JFrame gameFrame = new JFrame(); 
	
	//The score
	static JLabel scoreLabel;
	
	//The highscore
	static JLabel highScore;
	
	//The number of lives the user has
	static JLabel lives;
	
	//The panel where the lives, score, and highscore will appear on
	JPanel panel = new JPanel();
	
	//The pacman game's board 
	private Board board = new Board();
	
	//the constructor method
	public PacmanGUI(){
		
		//Sets the size, title, and layout of the frame
		gameFrame.setSize(600,500);
		gameFrame.setTitle("Mr.Chu's PacMan Game");
		gameFrame.setLayout(null);
		
		//Sets out a gridlayout for the panel
		panel.setLayout(new GridLayout(2,2, 400, 0));
		
		//Closes the program when you stop the program
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Sets the background to black
		gameFrame.setBackground(Color.black);
		
		
		//displays the score 
		scoreLabel = new JLabel ("Score:" + board.getScore());
		scoreLabel.setForeground(Color.blue);
		
		//displays the highscore
		highScore = new JLabel ("High Score: " + board.getHighScore());
		highScore.setForeground(Color.blue);
		
		//displays the number of lives
		lives = new JLabel ("Lives: " + board.life);
		lives.setForeground(Color.blue);
		
		//Beautifies the panel and adds the lives, score, and highscore
		panel.setBackground(Color.black);
		panel.add(lives);
		panel.add(scoreLabel);
		panel.add(highScore);
		panel.setBounds(0,0,600,50);
		
		//adds the panel to the frame
		gameFrame.add(panel);
		
		//sets the location of the pacman board and makes sure that it functions
		board.setBounds(0,50,600,400);
		gameFrame.addKeyListener(board);
		gameFrame.add(board);
				
		//makes the frame visible
		gameFrame.setVisible(true);
	}
	
	
}
