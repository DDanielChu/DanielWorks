//Allows for images to be used
import javax.swing.ImageIcon;

//The class that contains all of the images
public class Icons {

	//The images that are not pacman and the ghosts
	public static ImageIcon WALL = new ImageIcon("images/Wall.bmp");
	public static final ImageIcon FOOD = new ImageIcon("images/Food.bmp");
	public static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");
	public static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");
	public static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	public static final ImageIcon BERRIES = new ImageIcon("images/Cherry.bmp");
	public static final ImageIcon POWER = new ImageIcon("images/PowerPellet.png");
	public static final ImageIcon TIMESTOP = new ImageIcon("images/TimeStop.png");
	public static final ImageIcon TELEPORT = new ImageIcon("images/Teleport.png");
	public static final ImageIcon PAD = new ImageIcon("images/Pad.png");
	public static final ImageIcon TREE = new ImageIcon("images/tree.png");
	public static final ImageIcon GATE = new ImageIcon("images/GateWay.png");
	
	
	
	
	//The images for pacman
	public static final ImageIcon[] PACMAN = { 
			new ImageIcon("images/Pacman0.gif"), 
			new ImageIcon("images/Pacman1.gif"),
			new ImageIcon("images/Pacman2.gif"), 
			new ImageIcon("images/Pacman3.gif"), 
		};
	
	//The images for the ghosts
	public static final ImageIcon[] GHOST = { 
			new ImageIcon("images/Ghost0.bmp"), 
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp"),  
			new ImageIcon("images/BlueGhost.bmp"),

		};
	
	
	
}
