//These imports are necessary due to me handling files and sound input
import java.io.File;  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//https://www.geeksforgeeks.org/play-audio-file-using-java/ (MAJOR CONTRIBUTION TO THIS CODE. I had to make some minor changes to it to suit my code, but this was used as my base)
public class SoundArray {
	
	//Creates an array for the sound clips so that every class can use it
	static Clip[] sounds = new Clip[8];
	
	// keeps track of the last byte that was read
    AudioInputStream audioInputStream;
    
   
	public SoundArray(){
		
		//Creates a try and catch just in case something goes wrong
		try{
			
			//This loops through the whole array, saving each sound
			for (int music = 0; music < sounds.length; music ++) {
				
			//Gets the sound from the folder "sounds"
			audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/music" + music + ".wav").getAbsoluteFile());
		        
			
	        // create clip reference and places it into the array
	        sounds[music] = AudioSystem.getClip();
	          
	        // open audioInputStream to the clip and places it into the array
	        sounds[music].open(audioInputStream);
	       
	         

			}
	    }	
		
		//If anything goes wrong, this code will occur
		catch(Exception e ) {
			System.out.print("error");
		}
	}
	
	
	//This method is used to reopen the sound file so that it can be used again
	public void resetAudio(String text, int number) {
		try{
			
			//Gets the sound from the folder "sounds"
			audioInputStream = AudioSystem.getAudioInputStream(
					new File(text));
			
	        // create clip reference and places it into the array
			sounds[number] = AudioSystem.getClip();
			
	        // open audioInputStream to the clip and places it into the array
			sounds[number].open(audioInputStream);
		}
		
		//If anything goes wrong, this code will occur
		catch (Exception e){
		System.out.print("error");
		
		}
	}
	
}
