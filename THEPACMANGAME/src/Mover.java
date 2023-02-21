//import the JLabel so that it can be used for pacman and the ghosts
import javax.swing.JLabel;


//Creates the ghosts and pacman
public class Mover extends JLabel{
	
	//Determines what row the mover is on
	private int row;

	//Determines what coloumn the mover is on
	private int coloumn;
	

	//Determines what row the mover is facing
	private int dRow;
	

	//Determines what coloumn the mover is facing
	private int dColoumn;
	
	

	//Checks whether the mover is dead
	private boolean isDead;
	
	//The constructor
	public Mover(int row, int coloumn) {
		super();
		this.row = row;
		this.coloumn = coloumn;
	}
	
	
	//The get and set methods
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColoumn() {
		return coloumn;
	}

	public void setColoumn(int coloumn) {
		this.coloumn = coloumn;
	}

	public int getdRow() {
		return dRow;
	}

	public void setdRow(int dRow) {
		this.dRow = dRow;
	}

	public int getdColoumn() {
		return dColoumn;
	}

	public void setdColoumn(int dColoumn) {
		this.dColoumn = dColoumn;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	
	
	//Allows for the movers to move 
	public void move() {
		row += dRow;
		coloumn += dColoumn;
	}
	
	//Sets the direction of where the mover will be moving
	public void setDirection (int direction) {
		 
		
		//ensures that the mover can only move by coloumns or by rows and not both at the same time
		dRow = 0;
		dColoumn = 0;
		
		if (direction == 0) {
			dColoumn = -1;
		}
		else if (direction == 2) {
			dColoumn = 1;
		}
		else if (direction == 1) {
			dRow = -1;
		}
		else if (direction == 3) {
			dRow = 1;
		}
		
	}
	
	
	//Gets the direction of where the mover is currently moving to
	public int getDirection() {
		
		if (dRow == 1) {
			return 3;
		}
		
		else if (dRow == -1) {
			return 1;
		}
		
		else if (dColoumn == 1) {
			return 2;
		}
		
		else {
			return 0;
		}
		
		
	}
	
	
	//The mover's next row
	public int getNextRow() {
		return row + dRow;
	}
	
	//The mvoer's next coloumn
	public int getNextColoumn() {
		return coloumn + dColoumn;
	}

	
	
	
}