//These imports are either used to make the game look better or to make the buttons function
import java.awt.Color; 
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//The picker class is used to allow the users to pick the levels, ai, and themes they desire
public class Picker extends JFrame implements ActionListener{
	
	//This controls the ai
	static int AI = 0;
	
	//This controls the level
	static int levelChosen = 0;
	
	//This controls the theme
	static int themeChosen = 0;
	
	
	//The title
	JLabel title = new JLabel ("DIFFICULTY");

	

	//The AI difficulty text and the radio buttons that separate into easy and hard
	JLabel levelAi = new JLabel ("AI Difficulty");
	JRadioButton ai1 = new JRadioButton ("Easy AI");
	JRadioButton ai2 = new JRadioButton ("Hard AI");

	

	//The Levels text and the radio buttons that separate into easy and hard
	JLabel level = new JLabel ("LEVELS");
	JRadioButton level1 = new JRadioButton ("Easy Level");
	JRadioButton level2 = new JRadioButton ("Hard Level");



	//The THEMES text and the radio buttons that separate into basic and christmas
	JLabel theme = new JLabel ("THEMES");
	JRadioButton theme1 = new JRadioButton ("Basic Theme");
	JRadioButton theme2 = new JRadioButton ("Christmas Theme");
	
	
	
	//The button that will allow the user to go the pacman game
	JButton submit = new JButton ("Choose");

	
	
	//Constructor method
	public Picker() {
		
		//Sets the size, title, and layout of the frame
		setSize(600,500);
		setTitle("Mr.Chu's Level Picker");
		setLayout (null);


		//Beautifies the title and to set the location of the JLabel
		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setForeground(Color.cyan);
		title.setBounds(50, -60, 500, 200);
		add(title);
		
		
		//Beautifies the AI Difficulty text and to set the location of the JLabel
		levelAi.setFont(new Font("Arial", Font.BOLD, 20));
		levelAi.setForeground(Color.cyan);
		levelAi.setBounds(50, 60, 500, 100);
		add(levelAi);
		
		
		//Beautifies the radio button ai1 and to set the location of the JRadioButton
		ai1.setFont(new Font("Arial", Font.BOLD, 12));
		ai1.setForeground(Color.cyan);
		ai1.setOpaque(false);
		ai1.setBounds(50, 130, 100, 50);
		add(ai1);
		

		//Beautifies the radio button ai1 and to set the location of the JRadioButton
		ai2.setFont(new Font("Arial", Font.BOLD, 12));
		ai2.setForeground(Color.cyan);
		ai2.setOpaque(false);
		ai2.setBounds(50, 160, 100, 50);
		add(ai2);
		
		//Creates a button group so that the user can only choose one option for the AI difficulty
		ButtonGroup group = new ButtonGroup();
		group.add(ai1);group.add(ai2);
		
		

		//Beautifies the LEVEL text and to set the location of the JLabel
		level.setFont(new Font("Arial", Font.BOLD, 20));
		level.setForeground(Color.cyan);
		level.setBounds(50, 200, 500, 100);
		add(level);
		

		//Beautifies the radio button Level1 and to set the location of the JRadioButton
		level1.setFont(new Font("Arial", Font.BOLD, 12));
		level1.setForeground(Color.cyan);
		level1.setOpaque(false);
		level1.setBounds(50, 270, 100, 50);
		add(level1);
		

		//Beautifies the radio button level2 and to set the location of the JRadioButton
		level2.setFont(new Font("Arial", Font.BOLD, 12));
		level2.setForeground(Color.cyan);
		level2.setOpaque(false);
		level2.setBounds(50, 300, 100, 50);
		add(level2);
		
		//Creates a button group so that the user can only choose one option for the Level difficult
		ButtonGroup levelGroup = new ButtonGroup();
		
		levelGroup.add(level1);levelGroup.add(level2);
		
		
		

		//Beautifies the THEME text and to set the location of the JLabel
		theme.setFont(new Font("Arial", Font.BOLD, 20));
		theme.setForeground(Color.cyan);
		theme.setBounds(300, 60, 500, 100);
		add(theme);
		

		//Beautifies the radio button theme1 and to set the location of the JRadioButton
		theme1.setFont(new Font("Arial", Font.BOLD, 12));
		theme1.setForeground(Color.cyan);
		theme1.setOpaque(false);
		theme1.setBounds(300, 130, 150, 50);
		add(theme1);
		

		//Beautifies the radio button theme2 and to set the location of the JRadioButton
		theme2.setFont(new Font("Arial", Font.BOLD, 12));
		theme2.setForeground(Color.cyan);
		theme2.setOpaque(false);
		theme2.setBounds(300, 160, 150, 50);
		add(theme2);
		
		//Creates a button group so that the user can only choose one option for the T
		ButtonGroup themeGroup = new ButtonGroup();
		themeGroup.add(theme1);themeGroup.add(theme2);
		
		
		
		//Beautifies the button submit and to set the location of the button and allows an action to happen when the user presses it
		submit.setFont(new Font("Arial", Font.BOLD, 12));
		submit.setForeground(Color.black);
		submit.setOpaque(false);
		submit.setBounds(470, 400, 100, 50);
		submit.addActionListener(this);
		add(submit);
		
		
		
		
		
		//Creates the background for the frame
		JLabel back = new JLabel(new ImageIcon(new ImageIcon("images/PacmanBackground.jpg").getImage()
				.getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
		back.setBounds(0, 0, 600, 500);
		add(back);
		
		
		//Makes the whole frame visible
		setVisible (true);
		
	}
	@Override
	
	//If user presses a button
	public void actionPerformed(ActionEvent e) {
		
		
		//Depending on what button is chose for Ai Difficulty, the AI will be selected
		if (ai1.isSelected()) {
			AI = 1;
		}

		else if (ai2.isSelected()) {
			AI = 2;
		}


		//Depending on what button is chose for Level Difficulty, the Level will be selected
		if (level1.isSelected()) {
			levelChosen = 1;
		}

		else if (level2.isSelected()) {
			levelChosen = 2;
		}
		
		

		//Depending on what button is chose for theme, the Theme will be selected
		if (theme1.isSelected()) {
			themeChosen = 1;
		}

		else if (theme2.isSelected()) {
			themeChosen = 2;
		}
		
		
		//If the user actually chooses a feature from all of the different categories, then the pacman game will run
		if (AI != 0 && levelChosen != 0 && themeChosen != 0) {
			
			//the pacman game
			new PacmanGUI();
			
			//gets rid of this frame
			dispose();
		}
	}
	
}
