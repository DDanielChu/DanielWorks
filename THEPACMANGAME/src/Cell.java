//import every swing component there is
import javax.swing.*; 

public class Cell extends JLabel {

	//The item that is based off of the text file 
	private char item;

	
	//Constructor method
	public Cell(char item) {
		super();
		this.item = item;
		setCodeIcon();
	
	}
	
	
	//getters and setters
	public char getItem() {
		return item;
	}

	public void setItem(char item) {
		this.item = item;
	}

	
	//Will check what item the label is and will create an image for it
	public void setCodeIcon() {
		
		//If item is pacman
		if (item == 'P') {
			setIcon(Icons.PACMAN[0]);
			
		} 
		
		//if it's the ghosts
		else if (item == '0') {

			setIcon(Icons.GHOST[0]);
		} 
		
		
		else if (item == '1') {

			setIcon(Icons.GHOST[1]);
		}

		else if (item == '2') {

			setIcon(Icons.GHOST[2]);
		}

		
		//If it;s the walls
		else if (item == 'W') {
			if (Picker.themeChosen == 1) {
			setIcon(Icons.WALL);
			}
			else if (Picker.themeChosen == 2) {
				setIcon(Icons.TREE);
			}
			
		}

		
		//if it's the pellets
		else if (item == 'F') {

			setIcon(Icons.FOOD);
		}
		
		
		//if it's the door
		else if (item == 'D') {

			setIcon(Icons.DOOR);
		}
		
		
		//if it's the skull
		else if (item == 'S') {
			setIcon (Icons.SKULL);
		}
		
		
		//if it's the gateway
		else if (item == 'G') {
			setIcon (Icons.GATE);
		}
		
		//if it's the cherries/berries
		else if (item == 'B') {
			setIcon (Icons.BERRIES);
		}
		
		//if it's the powerpellet
		else if (item == 'U') {
			setIcon (Icons.POWER);
		}
		
		
		//if it's the clock
		else if (item == 's') {
			setIcon (Icons.TIMESTOP);
		}
		
		//if it's the teleportation pad
		else if (item == 't') {
			setIcon (Icons.TELEPORT);
		}
		
		
	}

}
