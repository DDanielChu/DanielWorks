//Name: Daniel Chu

//Title: Pacman

//Description:
	//My game is based off of the game Pacman. You are able to choose several features within this game including the level, AI difficulty and theme.
	//To win, you must collect all of the pellets
	//To lose, you must collide with the ghosts three times as you start with three lives
	//If desired you'll be able to do different combinations with these features, but you must choose at least one feature from each category for this game to work (made it so that they had to choose one)
	//Level:
		//Easy Level:
			//The easy level has the basic layout of the normal pacman game
			//There will be several areas where pacman will be able to hide from the ghosts due to the walls
			//There will also be all of the added items 
			//The doors will also work, allowing pacman to teleport from one area to the other
			//Once all of the pellets are collected, the game will end
		//Hard Level:
			//The hard level is very similar to the easy level, but the layout it a bit different
			//There will be less walls, and more pellets for the pacman to eat
			//Thus, there will be less areas for the pacman to hide in and it will be harder to win the game
	
	//AI Difficulty:
		//Easy AI:
			//The ghost's movements will be randomized
		//Hard AI:
			//The first ghost will chase pacman starting from horizontal movement and will change their movements based off of pacman's current location
			//The second ghost will chase pacman starting from vertical movement and will change their movements based off of pacman's current location
			//The third ghost will have randomized movements; however, their speed will be doubled 
		
	//Themes: 
		//Basic Theme:
			//The basic theme is pretty much all of the normal visuals you would see in pacman
			//The walls are blue and the pellets are yellow
		//Christmas Theme
			//The only difference in the christmas theme is that the walls turn into trees
	
	//Items:
		//Power Pellet:
			//Allows for the user to turn into a ghost eating pacman, who will gain points if they eat ghosts for a specific period of time
		//Clock:
			//Stops the movements of the ghosts
		//Teleportation pad
			//Teleports pacman to a certain location on the map
		//Cherry Items:
			//Gives pacman extra points

//Major Skills:
	//Object Oriented Programming, if statements, for loops, while statements, do-while, calling in files, swing components (JFrame, JLabel, etc), calling in images, calling in sounds, public and static

//Added Features:
	//I pretty much explained everything that I wanted to explain in the description
	//One thing that I do want to mention is that I added all of the enhancements except for the last three concerning file management. (12 out of the 15 in total)
	//OH AND THE GHOSTS DON'T RUN IN EASY AI SO THAT IT'S EASIER TO CATCH THEM


//This class is the application class and pretty much starts the whole game
public class PacManGam {
	
	public static void main (String[] args) {
		//Creates an array with the sounds in it
		new SoundArray();

		//Creates the title screen where the players will start off
		new TitleScreen();
	}
}
