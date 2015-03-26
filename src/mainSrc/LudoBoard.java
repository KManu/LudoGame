package mainSrc;

import javax.swing.*;

import java.awt.*;

public class LudoBoard extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int screenX,screenY ;
	int locX, locY;
	LudoDie ludoDie= new LudoDie();
	public LudoBoard(){		
		super("Super awesome Ludo game");
		//Setting Jframe constants and basic init
		screenX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		getFrames()[0].setLocation(screenX/4,screenY/6);
		setResizable(false);
		setVisible(true);
		
		//Die settings
		ludoDie.setLoc(getContentPane().getWidth()/2 -25, getContentPane().getHeight()/2 -50);
		add(ludoDie);
		

	}
	
	public void paint(Graphics graph){
		super.paint(graph);
		Graphics2D graph2d = (Graphics2D) graph;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    graph2d.setRenderingHints(rh);
	    
	    
	}
	
	public static void main(String [] args){
		@SuppressWarnings("unused")
		LudoBoard test = new LudoBoard();
	}
	

}
