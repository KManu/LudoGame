package mainSrc;

import java.io.File;




import javax.sound.sampled.*;

public class AudioPlayer{
	
	private long songLength;
	private int numOfSongs;
	private int index;
	private boolean isPlaying;
	private final Object lockObj = new Object();
	String[] songTitles = {"nr", "cowboy", "festival"};
	private Thread soundThread;
	boolean nextSonged = false;
	//boolean previousSonged = false;

	
	AudioInputStream audioInputStream;
    Clip clip;
	
	public AudioPlayer() {
		songLength = 0;
		numOfSongs = songTitles.length;
		//System.out.println(numOfSongs);
		isPlaying = true;
		
		index = (0 + (int)(Math.random() * numOfSongs));
		createPlayList();
	}
	
	public long playSound(String url) {
		
		try 
		{	
			String file = "./res/audio/" + url;
			audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
	        songLength = clip.getMicrosecondLength()/1000;  
	    } 
		
		catch(Exception ex) 
		{
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	    
	    return songLength;
	}
	
	public void createPlayList()
	{	
		soundThread = new Thread(new Runnable() {

	        @Override
	        public void run() 
	        {
	        	while(isPlaying)
	    		{	
	        		if(index >= numOfSongs)
	        		{
	        			index = 0; //reset play list to first song after reaching the last
	        		}
	        		
	        		playSound(songTitles[index] + ".wav");
	    			
	    			synchronized (lockObj)
	    			{
	    				try
	    				{
	    					//Thread.sleep(songLength);
	    					lockObj.wait(songLength);
	    				}
	    				
	    				catch(Exception e)
	    				{
	    					
	    				}
	    			}
	    			if(nextSonged)
	    			{
	    				index = index - 1;
	    				nextSonged = false;
	    			}
	    			
	    			index++;
	    		}
	        }
		});
		soundThread.start();
	}
	
	public void stopSong()
	{
		clip.stop();
	}
	
	public void startSong()
	{
		clip.start();
	}
	
	public void nextSong()
	{	
		//System.out.println("Next song");
		
		synchronized (lockObj) 
		{	
			stopSong();
			
				index = index + 1;
				System.out.println(index);
				
			lockObj.notify();
			nextSonged = true;
		}
	}
	
	/*
	public void previousSong()
	{	
		synchronized (lockObj) 
		{	
			stopSong();
			if(index == 0)
			{
				index = numOfSongs-1;
			}
			
			else
			{
				index = index-1;
			}
			
			lockObj.notify();
		}
	}*/
}

/*
if(index >= numOfSongs)
		{
			index = 0;
			System.out.println(index);
		}
		
		else
		{
			index = index + 1;
			System.out.println(index);
			
		}
		
		synchronized (lockObj) 
		{
			lockObj.notify();
		}
*/
	