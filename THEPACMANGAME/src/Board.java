//these imports are necessary to use the sounds, creates the layout, allows for the keys to be used, allows for the game timer to happen, and to set out what the game will look like
import java.awt.Color; 
import javax.sound.sampled.Clip;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

//The class that will create a board that will display the pacman game and allow it to run
@SuppressWarnings("serial")
public class Board extends JPanel implements KeyListener, ActionListener {
	
	
	//calls in the SoundArray class so that I can use its array
	SoundArray soundClass = new SoundArray();

	
	//Will determine when the cherries will appear after the game starts
	int foodTime = 0;

	//Determines how long the power pellet's powers will last
	int powerTime = 0;

	
	//Controls the movement of the ghost's hard AI once pacman picks up a power pellet
	int change = 0;

	
	//Determines whether the ghosts should stop or not (ghosts will stop once the clock is picked up)
	int stop = 0;

	//Determines how long the ghosts will stop for
	int stopTimer = 0;

	//Creates the timer for the whole game, which allows for the pacman and ghosts to move and also causes everything else to work
	private Timer gameTimer = new Timer(170, this);

	// Creates the cells/layout of the whole board
	private Cell[][] mazeArray = new Cell[25][27];

	// Creates the pacman
	private Mover pacman;

	// Creates all of the ghosts
	private Mover[] ghostMan = new Mover[3];

	//The number of pellets on the board
	private int pellets = 0;

	//How much score the user has
	private int score = 0;

	
	//The number of lives the user has
	int life = 3;

	
	//The hi
	int highscore = 0;

	
	//Gets the current score
	public int getScore() {
		return score;
	}

	
	//Gets the highscore
	public int getHighScore() {
		//if the previous highscore is higher than the score currently, then nothing will change
		if (highscore > score) {
			return highscore;
			
			//Otherwise, the highscore will be set to the score and a new highscore will appear
		} else {
			highscore = score;
			return score;
		}
	}

	
	//Sets the layout of the board and calls out loadboard(); to load the board
	public Board() {
		setLayout(new GridLayout(25, 27));
		setBackground(Color.black);
		loadBoard();
	}

	
	//Loads the board
	private void loadBoard() {
		//Determines the row of the maze file so that it can go onto the next row for the mazeArray once one row is finished
		int row = 0;

		
		//creates a variableName for the scanner
		Scanner input;
		
		//If the user chose the easy level
		if (Picker.levelChosen == 1) {
			
			//Try and catch so that if an error is called, the code will stop running
			try {
				
				//Reads the text file maze.txt
				input = new Scanner(new File("maze.txt"));
				
				//While there is still more lines in the text file
				while (input.hasNext()) {
					
					
					//Reads the entire line and changes each letter into a character
					char[] lineArray = input.nextLine().toCharArray();
					
					
					//The for loop will iterate based off of the number of characters that are inside the lineArray
					for (int coloumn = 0; coloumn < lineArray.length; coloumn++) {
						
						//Creates a new Cell within the mazeArray and gives it the value of a char 
						mazeArray[row][coloumn] = new Cell(lineArray[coloumn]);
						
						
						//if the char is a pellet then the number of pellets will increase
						if (lineArray[coloumn] == 'F') {
							pellets++;
							
						} 
						//if the char is pacman, then it will set pacman to that location
						else if (lineArray[coloumn] == 'P') {
							//sets pacman to that location where the 'P' was found
							pacman = new Mover(row, coloumn);
							pacman.setIcon(Icons.PACMAN[0]); // left image
							pacman.setDirection(0); // start left
						}
						
						
						//Same thing with pacman but with the three different ghosts
						else if (lineArray[coloumn] == '0' || lineArray[coloumn] == '1' || lineArray[coloumn] == '2') {
							int gNum = Character.getNumericValue(mazeArray[row][coloumn].getItem());

							ghostMan[gNum] = new Mover(row, coloumn);
							ghostMan[gNum].setIcon(Icons.GHOST[gNum]);

						}
						
						//adds the image to the panel and this does it until the full panel's gridlayout is filled (that's how the program is coded)
						add(mazeArray[row][coloumn]);

					}
					
					
					//signifies that the mazeArray should go onto the next row and save the images for that row as well
					row++;

				}
				
				//closes the scanner
				input.close();

			} 
			//If the try doesn't work, then the code will stop and the next block of code will occur
			catch (FileNotFoundException e) {

				System.out.print("OHHH NOOOO");

			}
		}
		
		
		//If the user chooses the hard level, then this code will occur. IT'S EXACTLY THE SAME AS THE PREVIOUS CODE BUT WITH A DIFFERENT TEXT FILE
		else if (Picker.levelChosen == 2) {
			try {
				input = new Scanner(new File("maze2.txt"));
				while (input.hasNext()) {

					char[] lineArray = input.nextLine().toCharArray();

					for (int coloumn = 0; coloumn < lineArray.length; coloumn++) {
						mazeArray[row][coloumn] = new Cell(lineArray[coloumn]);

						if (lineArray[coloumn] == 'F') {
							pellets++;

						} else if (lineArray[coloumn] == 'P') {
							pacman = new Mover(row, coloumn);
							pacman.setIcon(Icons.PACMAN[0]); // left image
							pacman.setDirection(0); // start left
						}

						else if (lineArray[coloumn] == '0' || lineArray[coloumn] == '1' || lineArray[coloumn] == '2') {
							int gNum = Character.getNumericValue(mazeArray[row][coloumn].getItem());

							ghostMan[gNum] = new Mover(row, coloumn);
							ghostMan[gNum].setIcon(Icons.GHOST[gNum]);

						}

						add(mazeArray[row][coloumn]);

					}

					row++;

				}

				input.close();

			} catch (FileNotFoundException e) {

				System.out.print("OHHH NOOOO");

			}
		}
	}

	
	//This will run everytime the gameTimer runs
	public void actionPerformed(ActionEvent e) {
		
		//if the gameTimer is the function that runs then this block of code will happen
		if (e.getSource() == gameTimer) {
			//pacman will move
			performMove(pacman);

			//stop = 0 is the default, so the ghosts will move while pacman is alive and if the clock hasn't been picked up
			if (stop == 0) {
				moveGhost();
				
			
			} 
			
			//if the clock has been picked up, then stop will equal to 1 and the ghosts will stop moving
			else if (stop == 1) {
				
				//Adds 1 to the stoptimer everytime to count how long the timer will be activated for
				stopTimer += 1;
				
				//If the gameTimer has already activated 15 times since the clock has been collected, then this will run
				if (stopTimer == 15) {
					
					//The ghosts can move now
					stop = 0;
					
					//The stop timer is set to 0 so that the other clock abilities can work once picked up
					stopTimer = 0;
				}
			}
			
			//the foodTimer adds one everytime the gameTimer runs
			foodTime += 1;
			
			//Once the gameTimer iterates 15 times then the first cherry will appear in the top left
			if (foodTime == 15) {
				mazeArray[2][2].setItem('B');
				mazeArray[2][2].setIcon(Icons.BERRIES);

				//this ensures that the user can win even if they don't pick up the cherries
				pellets--;
			}
			

			//Once the gameTimer iterates 25 times then the first cherry will appear in the top right
			if (foodTime == 25) {
				mazeArray[2][24].setItem('B');
				mazeArray[2][24].setIcon(Icons.BERRIES);

				//this ensures that the user can win even if they don't pick up the cherries
				pellets--;

			}

			//Once the gameTimer iterates 15 times then the first cherry will appear in the bottom left
			if (foodTime == 15) {
				mazeArray[22][2].setItem('B');
				mazeArray[22][2].setIcon(Icons.BERRIES);
				
				//this ensures that the user can win even if they don't pick up the cherries
				pellets--;

			}
			
			//Once the gameTimer iterates 25 times then the first cherry will appear in the top right
			if (foodTime == 25) {
				mazeArray[22][24].setItem('B');
				mazeArray[22][24].setIcon(Icons.BERRIES);
				
				//this ensures that the user can win even if they don't pick up the cherries
				pellets--;

			}
			
			
			//If the powerpellet is picked up, then change will equal to 1
			if (change == 1) {
				
				//Power time will add 1 
				powerTime += 1;
				
				
				//Once power time equals to 18 then the ghosts will turn back to normal and powerTime and change will equal to 0 so that the next power pellet can work
				if (powerTime == 18) {
					ghostMan[0].setIcon(Icons.GHOST[0]);
					ghostMan[1].setIcon(Icons.GHOST[1]);
					ghostMan[2].setIcon(Icons.GHOST[2]);
					change = 0;
					powerTime = 0;
				}
			}
		}

	}
	
	
	//Determines how the ghost will move
	private void moveGhost() {
		
		//The is for the movement of the first ghost in hard Ai
		int movement = 1;
		

		//The is for the movement of the second ghost in hard Ai
		int movement1 = 2;
		
		
		//A for loop for all of the ghosts
		for (Mover ghost : ghostMan) {
			
			//the direction that the ghost will go in
			int dir = 0;
			
			//The current cell and next cell of the ghosts
			Cell currentCell = mazeArray[ghost.getRow()][ghost.getColoumn()];
			Cell nextCell = mazeArray[ghost.getNextRow()][ghost.getNextColoumn()];
			
			//If the user chooses easy AI the ghosts will move randomly
			if (Picker.AI == 1) {
				
				//The ghosts will move randomly, however, they cannot go back and forth
				do {
					dir = (int) (Math.random() * 4);

				} while (Math.abs(ghost.getDirection() - dir) == 2);

				//The ghost's direction is set
				ghost.setDirection(dir);

			} 
			
			
			//If the user decides to pick hard AI
			else if (Picker.AI == 2) {
				
				//If the user doesn't pick up the power pellet, then the AI will chase pacman (0 is the default value)
				if (change == 0) {
					
					//movement is separated into two categories: horizontal and vertical
					//If movement equals to 1, then the ghost will start off horizontally or chase the ghost horizontally at the beginning
					//In this case, the ghost will start off chasing pacman horiztonally because I set the intial value for movement to 1
					if (movement == 1) {
						//checks if pacman is in the same coloumn and if he is, then the movement switches to vertical
						if (ghostMan[0].getColoumn() == pacman.getColoumn()) {
							movement = 2;
						}

						
						//if the ghost is on the left side of pacman, then the ghost will start to go left
						if (ghostMan[0].getColoumn() < pacman.getColoumn()) {
							ghostMan[0].setDirection(2);
							
							//if the ghost reaches a wall, then they'll start to move vertically
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 2;
							}

						}
						
						
						//if the ghost is on the right side of pacman, then the ghost will start to go right
						if (ghostMan[0].getColoumn() > pacman.getColoumn()) {
							ghostMan[0].setDirection(0);
							
							//if the ghost reaches a wall, then they'll start to move vertically
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 2;
							}
						}

					}
					
					//Vertical movement
					if (movement == 2) {
						
						//if pacman is in the same row as the ghosts, then the ghosts will start to move horizontally
						if (ghostMan[0].getRow() == pacman.getRow()) {
							movement = 1;
						}

						
						//if the ghost is above pacman, then it'll start going downwards
						if (ghostMan[0].getRow() < pacman.getRow()) {
							ghostMan[0].setDirection(3);
							
							//if the ghost reaches a wall, it'll start to go horizontally
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 1;
							}

						}

						
						//if the ghost is below pacman, then it'll start going upwards
						if (ghostMan[0].getRow() > pacman.getRow()) {
							ghostMan[0].setDirection(1);
							
							//if the ghost reaches a wall, it'll start to go horizontally
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 1;
							}
						}

					}
					
					
					//THIS IS THE EXACT SAME CODE AS ABOVE BUT WITH THE SECOND GHOST
					//THE DIFFERENCE IS THAT THE SECOND GHOST WILL START GOING VERTICALLY 
					// AFTER ALL, MOVEMENT1 IS SET TO 2 AS DEFAULT
					// HORIZONTAL == 1 AND VERTICAL == 2
					if (movement1 == 1) {
						if (ghostMan[1].getColoumn() == pacman.getColoumn()) {
							movement1 = 2;
						}

						if (ghostMan[1].getColoumn() < pacman.getColoumn()) {
							ghostMan[1].setDirection(2);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 2;
							}

						}

						if (ghostMan[1].getColoumn() > pacman.getColoumn()) {
							ghostMan[1].setDirection(0);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 2;
							}
						}

					}
					
					//same code as the first ghost
					if (movement1 == 2) {
						if (ghostMan[1].getRow() == pacman.getRow()) {
							movement1 = 1;
						}

						if (ghostMan[1].getRow() < pacman.getRow()) {
							ghostMan[1].setDirection(3);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 1;
							}

						}

						if (ghostMan[1].getRow() > pacman.getRow()) {
							ghostMan[1].setDirection(1);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 1;
							}
						}

					}
					
					//this is for the third ghost. This ghost will have randomized movements
					//however, it won't be allowed to go back and forth
					do {
						dir = (int) (Math.random() * 4);

					} while (Math.abs(ghost.getDirection() - dir) == 2);
					ghostMan[2].setDirection(dir);

				}

				
				//if pacman eats a power pellet then the ghosts will run away from pacman
				else if (change == 1) {
					
					
					//if the movement equals to horizontal movement
					if (movement == 1) {
						
						//if they're in the same coloumn, then they'll go vertically
						if (ghostMan[0].getColoumn() == pacman.getColoumn()) {
							movement = 2;
						}

						
						//if pacman is on the right side, then the ghosts will go left
						if (ghostMan[0].getColoumn() < pacman.getColoumn()) {
							ghostMan[0].setDirection(0);
							
							//if they hit a wall, then they'll go vertically
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 2;
							}

						}
						
						
						//if pacman is on the left side, then the ghosts will go right
						if (ghostMan[0].getColoumn() > pacman.getColoumn()) {
							ghostMan[0].setDirection(2);
							
							//if they hit a wall, then they'll go vertically
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 2;
							}
						}

					}
					
					//if the movement is set to vertical
					if (movement == 2) {
						
						//if they're on the same row, then the ghosts will go horizontally
						if (ghostMan[0].getRow() == pacman.getRow()) {
							movement = 1;
						}
						
						
						//if pacman is below the ghost, then the ghosts will go upwards
						if (ghostMan[0].getRow() < pacman.getRow()) {
							ghostMan[0].setDirection(1);
							
							//if they hit a wall, then they'll go horizontally
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 1;
							}

						}

						//if pacman is above the ghost, then the ghosts will go downwards
						if (ghostMan[0].getRow() > pacman.getRow()) {
							ghostMan[0].setDirection(3);
							
							//if they hit a wall, then they'll go horizontally
							if (mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[0].getNextRow()][ghostMan[0].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement = 1;
							}
						}

					}

					
					//this is the exact same code as above but this is for the second ghost
					//the difference is that the second ghost will move away from pacman vertically first rather than horizontally
					//i set the default value of the movement to 2
					if (movement1 == 1) {
						if (ghostMan[1].getColoumn() == pacman.getColoumn()) {
							movement1 = 2;
						}

						if (ghostMan[1].getColoumn() < pacman.getColoumn()) {
							ghostMan[1].setDirection(0);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 2;
							}

						}

						if (ghostMan[1].getColoumn() > pacman.getColoumn()) {
							ghostMan[1].setDirection(2);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 2;
							}
						}

					}
					
					//this is vertical movement. Same code as the movement for the first ghost
					if (movement1 == 2) {
						if (ghostMan[1].getRow() == pacman.getRow()) {
							movement1 = 1;
						}

						if (ghostMan[1].getRow() < pacman.getRow()) {
							ghostMan[1].setDirection(1);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 1;
							}

						}

						if (ghostMan[1].getRow() > pacman.getRow()) {
							ghostMan[1].setDirection(3);
							if (mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
									.getIcon() == Icons.WALL
									|| mazeArray[ghostMan[1].getNextRow()][ghostMan[1].getNextColoumn()]
											.getIcon() == Icons.TREE) {
								movement1 = 1;
							}
						}

					}
					
					//ghost number 3 still has randomized movements
					do {
						dir = (int) (Math.random() * 4);

					} while (Math.abs(ghost.getDirection() - dir) == 2);
					ghostMan[2].setDirection(dir);

				}

			}
			
			
			//if the ghost is still within his home, and is on a tile that is defined as R, then the ghost will move right
			if (currentCell.getItem() == 'R') {
				dir = 2;
				ghost.setDirection(dir);

			} 

			//if the ghost is still within his home, and is on a tile that is defined as L, then the ghost will move left
			else if (currentCell.getItem() == 'L') {
				dir = 0;
				ghost.setDirection(dir);

			}

			//if the ghost is still within his home, and is on a tile that is defined as T or G, then the ghost will move up
			else if (currentCell.getItem() == 'T' || currentCell.getItem() == 'G') {
				dir = 1;
				ghost.setDirection(dir);

			}
			
			
			//if pacman is still alive, then the ghosts will move
			if (!pacman.isDead()) {
				performMove(ghost);
				
				//if they chose hard AI, then the third ghost with randomized movements, will have double the speed
				if (Picker.AI == 2) {
					performMove(ghostMan[2]);
				}
			}

		}

	}
	
	
	//This allows the movers (pacman and the ghosts) to move
	private void performMove(Mover mover) {
		
		
		//if the mover enters the leftmost door, then they'll be teleported to the rightmost door 
		if (mover.getColoumn() == 1) {
			mover.setColoumn(24);
			mazeArray[12][1].setIcon(Icons.DOOR);
		}

		//if the mover enters the rightmost door, then they'll be teleported to the leftmost door
		else if (mover.getColoumn() == 25) {
			mover.setColoumn(2);
			mazeArray[12][25].setIcon(Icons.DOOR);
		}

		
		//Where the mover is currently within the map
		Cell currentCell = mazeArray[mover.getRow()][mover.getColoumn()];


		//The next cell of the mover 
		Cell nextCell = mazeArray[mover.getNextRow()][mover.getNextColoumn()];

		//if the mover isn't facing a wall 
		if (nextCell.getIcon() != Icons.WALL && nextCell.getIcon() != Icons.TREE) {

			
			//if the is the ghost and they step on any items, then the items will reappear after they step off of it
			if (mover != pacman && (currentCell.getItem() == 'F' || currentCell.getItem() == 'B'
					|| currentCell.getItem() == 'G' || currentCell.getItem() == 'U' || currentCell.getItem() == 's'
					|| currentCell.getItem() == 't')) {

				if ((currentCell.getItem() == 'F')) {
					currentCell.setIcon(Icons.FOOD);

				} else if ((currentCell.getItem() == 'B')) {
					currentCell.setIcon(Icons.BERRIES);
				} else if ((currentCell.getItem() == 'G')) {
					currentCell.setIcon(Icons.GATE);
				} else if (currentCell.getItem() == 'U') {
					currentCell.setIcon(Icons.POWER);
				} else if ((currentCell.getItem() == 's')) {
					currentCell.setIcon(Icons.TIMESTOP);
				} else if (currentCell.getItem() == 't') {
					currentCell.setIcon(Icons.TELEPORT);
				}

			}

			
			//if it's pacman who steps on any item, then the item will disappear
			else {
				currentCell.setIcon(Icons.BLANK);
			}

			//if the pacman isn't facing the gate then pacman can move
			if (mover == pacman && nextCell.getIcon() != Icons.GATE) {
				pacman.move();

			}
			
			
			//The ghosts can move 
			if (mover != pacman) {
				mover.move();
			}
			
			
			//This is so pacman and ghosts aren't duplicated
			currentCell = mazeArray[mover.getRow()][mover.getColoumn()];

			
			//if pacman collides with the ghosts
			if (collide()) {
				
				
				//if pacman doesn't have his power pellet powers activated then pacman will die
				if (change == 0) {
					death();
				}
				
				
				//if pacman does have his power pellet powers activated then the ghosts will die
				if (change == 1) {
					ghostDeath();
				}
			}
			
			//refreshed the icon of the mover everytime they move
			else {
				currentCell.setIcon(mover.getIcon());
			}

			// if pacman decides to eat food or goes onto a special item
			if (mover == pacman && (currentCell.getItem() == 'F' || currentCell.getItem() == 'B'
					|| currentCell.getItem() == 'U' || currentCell.getItem() == 's')) {
				
				//if pacman eats a pellet then the number of pellets go down and the score increases by 10
				if (currentCell.getItem() == 'F') {
					score += 10;
					pellets--;
				} 
				
				//if pacman eats a cherry, then the score will increase by 100
				else if (currentCell.getItem() == 'B') {
					score += 100;

				} 
				
				//if pacman goes onto a power pellet
				else if (currentCell.getItem() == 'U') {
					
					//every ghost will turn blue and they will start to run away
					//the ghosts can now be eaten
					ghostMan[0].setIcon(Icons.GHOST[3]);
					ghostMan[1].setIcon(Icons.GHOST[3]);
					ghostMan[2].setIcon(Icons.GHOST[3]);
					change = 1;
				}
				
				
				//if the pacman goes onto a clock, then stop will equal to 1 and the ghosts will no longer move
				else if (currentCell.getItem() == 's') {
					stop = 1;
				}
				
				
				//The chomping sound for pacman
				soundClass.sounds[6].start();
				soundClass.sounds[6].loop(Clip.LOOP_CONTINUOUSLY);
				
				//refreshes the score everytime he goes onto something special (e.g. pellets)
				PacmanGUI.scoreLabel.setText("Score:" + getScore());
				
				
				//set the cell to 'E' so that the points won't increase on a blank space
				currentCell.setItem('E');
				
				
				//if pacman collects all of the pelllets then pacman has won
				if (pellets == 0) {
					
					//the timer will stop
					gameTimer.stop();
					
					//Pacman's chomping sound will stop 
					soundClass.sounds[6].close();
					
					//The sound when pacman collides with ghosts will end 
					soundClass.sounds[5].close();
					
					//The highscore will refresh and show the new highscore 
					PacmanGUI.highScore.setText("High Score: " + getHighScore());

					
					//After pacman wins, then it will show a dialogue saying that they won
					JOptionPane.showMessageDialog(this, "YOU WON");
					
					//ask the user if they want to continue
					int result = JOptionPane.showConfirmDialog(this, "DO YOU WANT TO CONTINUE?", "Restart?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
					//if they say yes, then restart everything
					if (result == JOptionPane.YES_OPTION) {
						
						
						removeAll();
						revalidate();
						
						score = 0;
						pellets = 0;
						life = 3;
						foodTime = 0;
						PacmanGUI.scoreLabel.setText("Score:" + getScore());
						PacmanGUI.lives.setText("Lives: " + life);						
						loadBoard();
						soundClass.resetAudio("sounds/music6.wav", 6);
						soundClass.resetAudio("sounds/music5.wav", 5);
					} 
					
					//if they say no, then close everything
					else if (result == JOptionPane.NO_OPTION) {
						PacmanGUI.gameFrame.dispose();
					} 
					
					//if they close the dialogue then close everything
					else {
						PacmanGUI.gameFrame.dispose();
					}
					
					
					
				}

			}
			
			//if pacman stands on the teleportation device then he will teleport
			if (mover == pacman && (currentCell.getItem() == 't' || currentCell.getItem() == 'p')) {
				if (currentCell.getItem() == 't') {
					currentCell.setIcon(Icons.TELEPORT);
					pacman = new Mover(7, 2);
					pacman.setIcon(Icons.PACMAN[2]); // left image
					pacman.setDirection(2); // start left

				}

			}

		}

	}

	private void death() {
		
		
		//if pacman's life isn't 1 then he will be revived
		if (life != 1) {
			
			//the death sound will play
			soundClass.sounds[5].start();
			soundClass.resetAudio("sounds/music5.wav", 5);
			
			//the number of lives pacman has will decrease
			life--;
			PacmanGUI.lives.setText("Lives: " + life);
			
			//pacman will be set back to the start
			mazeArray[pacman.getRow()][pacman.getColoumn()].setIcon(Icons.BLANK);
			pacman = new Mover(15, 13);
			pacman.setIcon(Icons.PACMAN[0]); // left image
			pacman.setDirection(0); // start left

		} 
		
		else {

			//lives will minus by 1 to equal to 0 
			life--;
			
			//The number of lives will show on the screen
			PacmanGUI.lives.setText("Lives: " + life);
			
			
			//game timer will stop and pacman will be set as dead
			gameTimer.stop();
			pacman.setDead(true);
			
			//the highscore will be refreshed
			PacmanGUI.highScore.setText("High Score: " + getHighScore());
			
			
			//Pacman's icon is set to blank
			mazeArray[pacman.getRow()][pacman.getColoumn()].setIcon(Icons.SKULL);
			
			//The ghosts' icons are set to blank
			mazeArray[ghostMan[0].getRow()][ghostMan[0].getColoumn()].setIcon(Icons.BLANK);
			mazeArray[ghostMan[1].getRow()][ghostMan[1].getColoumn()].setIcon(Icons.BLANK);
			mazeArray[ghostMan[2].getRow()][ghostMan[2].getColoumn()].setIcon(Icons.BLANK);
			
			//All the sounds stop
			soundClass.sounds[5].close();
			soundClass.sounds[6].close();
			
			
			//A dialogue telling the user they lost
			JOptionPane.showMessageDialog(this, "YOU LOST");
			
			
			//Asks them if they want to restart
			int result = JOptionPane.showConfirmDialog(this, "Do You Want To Continue?", "Restart?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			
			//If they say yes, then the code restarts
			if (result == JOptionPane.YES_OPTION) {
				removeAll();
				revalidate();

				pacman.setDead(false);
				score = 0;
				pellets = 0;
				life = 3;
				foodTime = 0;
				PacmanGUI.scoreLabel.setText("Score:" + getScore());
				PacmanGUI.lives.setText("Lives: " + life);
				
				
				loadBoard();
				
				
				soundClass.resetAudio("sounds/music6.wav", 6);
				soundClass.resetAudio("sounds/music5.wav", 5);

			} 
			
			//if they say no, then the frame disposes
			else if (result == JOptionPane.NO_OPTION) {

				PacmanGUI.gameFrame.dispose();
			} 
			
			
			//if they do anything else, then the frame disposes
			else {
				PacmanGUI.gameFrame.dispose();
			}
			
			

		}
	}
	
	
	//if they ghosts collide with pacman then this code will happen
	private boolean collide() {
		
		//checks the coordinates of each ghost and see if they've collided with pacman
		for (Mover ghost : ghostMan) {
			if (ghost.getRow() == pacman.getRow() && ghost.getColoumn() == pacman.getColoumn()) {
				return true;

			}

		}

		return false;
	}

	
	//this does nothing
	public void keyTyped(KeyEvent e) {

	}
	
	
	//if a key is pressed then this code will happen
	public void keyPressed(KeyEvent key) {
		//If pacman isn't dead and the game timer isn't running, then game timer will start
		if (gameTimer.isRunning() == false && pacman.isDead() == false)
			gameTimer.start();

		
		//if pacman isn't and all of the pellets haven't been collected
		if (pacman.isDead() == false && pellets != 0) {
			
			//places the left arrow key to 0, up key = 1, rightKey = 2, downKey = 3
			int direction = key.getKeyCode() - 37;
			
			
			//if the theme chosen is the basic theme 
			if (Picker.themeChosen == 1) {
				
				//gets pacmans next coloumn or row based on the direction the user chose 
				if ((direction == 0 && mazeArray[pacman.getRow()][pacman.getColoumn() - 1].getIcon() != Icons.WALL)

						|| (direction == 1
								&& mazeArray[pacman.getRow() - 1][pacman.getColoumn()].getIcon() != Icons.WALL)

						|| (direction == 2
								&& mazeArray[pacman.getRow()][pacman.getColoumn() + 1].getIcon() != Icons.WALL)

						|| (direction == 3
								&& mazeArray[pacman.getRow() + 1][pacman.getColoumn()].getIcon() != Icons.WALL)) {
					
					
					//sets the direction for pacman and resets the icon to that direction
					pacman.setIcon(Icons.PACMAN[direction]);
					pacman.setDirection(direction);
				}
			}
			
			//the same code as above but for the second theme
			else if (Picker.themeChosen == 2) {
				
				
				//checks for the trees rather than for walls
				if ((direction == 0 && mazeArray[pacman.getRow()][pacman.getColoumn() - 1].getIcon() != Icons.TREE)

						|| (direction == 1
								&& mazeArray[pacman.getRow() - 1][pacman.getColoumn()].getIcon() != Icons.TREE)

						|| (direction == 2
								&& mazeArray[pacman.getRow()][pacman.getColoumn() + 1].getIcon() != Icons.TREE)

						|| (direction == 3
								&& mazeArray[pacman.getRow() + 1][pacman.getColoumn()].getIcon() != Icons.TREE)) {

					pacman.setIcon(Icons.PACMAN[direction]);

					pacman.setDirection(direction);
				}
			}
		}
	}

	//does nothing
	public void keyReleased(KeyEvent e) {

	}

	
	//Helps the ghosts die when pacman eats them
	public void ghostDeath() {
		
		//if it's the first ghost, then its icon will be set to blank, then be moved to its starting position
		if (ghostMan[0].getColoumn() == pacman.getColoumn() && ghostMan[0].getRow() == pacman.getRow()) {
			mazeArray[ghostMan[0].getRow()][ghostMan[0].getColoumn()].setIcon(Icons.BLANK);
			ghostMan[0] = new Mover(13, 13);
			ghostMan[0].setIcon(Icons.GHOST[0]); // left image

		}
		//if it's the second ghost, then its icon will be set to blank, then be moved to its starting position
		if (ghostMan[1].getColoumn() == pacman.getColoumn() && ghostMan[1].getRow() == pacman.getRow()) {
			mazeArray[ghostMan[1].getRow()][ghostMan[1].getColoumn()].setIcon(Icons.BLANK);
			ghostMan[1] = new Mover(13, 13);
			ghostMan[1].setIcon(Icons.GHOST[1]); // left image

		}
		//if it's the third ghost, then its icon will be set to blank, then be moved to its starting position
		if (ghostMan[2].getColoumn() == pacman.getColoumn() && ghostMan[2].getRow() == pacman.getRow()) {
			mazeArray[ghostMan[2].getRow()][ghostMan[2].getColoumn()].setIcon(Icons.BLANK);
			ghostMan[2] = new Mover(13, 13);
			ghostMan[2].setIcon(Icons.GHOST[2]); // left image

		}
		
		
		//when pacman eats any ghost, then the score will raise by 500
		score += 500;
	}

}
