//These imports are either used to make the game look better or to make the buttons function
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Creates the title screen which is the first frame the user will see
public class TitleScreen extends JFrame implements ActionListener{
	
	//The title 
	JLabel title = new JLabel ("THE PACMAN GAME");
	
	//The button that starts the game
	JButton start = new JButton ("START");
	
	//the constructor method
	public TitleScreen(){
		
		//sets the size
		setSize(600,500);
		
		//Creates the title
		setTitle("Mr.Chu's PacMan Game");

		//Allows me to move the items around
		setLayout(null);
		
		
		//Beautifies the title and to set the location of the JLabel
		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setForeground(Color.white);
		title.setBounds(50, -60, 500, 200);
		add(title);
		
		

		//Beautifies the start button and to set the location of the JButton and also allows it to be used
		start.setFont(new Font ("Arial", Font.BOLD, 20));
		start.setForeground(Color.white);
		start.setBackground(Color.black);
		start.addActionListener(this);
		start.setBounds(230, 190, 150, 70);
		add(start);
		
		
		//Creates the background for the frame
		JLabel back = new JLabel(new ImageIcon(new ImageIcon("images/PacmanBackground.jpg").getImage()
				.getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
		back.setBounds(0, 0, 600, 500);
		add(back);
		
		//Makes the frame visible
		setVisible(true);
		
	}

	
	//If the user presses the start button this will happen
	public void actionPerformed(ActionEvent e) {
	

		//If the user presses the start button this will happen
		if (e.getSource() == start ) {
			
			//Calls in the picker class where the user will pick the difficulty
			new Picker();
			
			//Disposes of this frame
			dispose();
			
		}
		
	}
}
