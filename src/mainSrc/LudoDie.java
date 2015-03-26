package mainSrc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

//Subject InLab6

public class LudoDie extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//die coordinates
	int X1 = 100;
	int Y1 = 100;
	//Just starting values the die would have when initialized
	int randVal1 = 6 ;
	int randVal2 = 5;
	//x and Y values for the size of the die
	int dieCubeWidth=50;
	int dieCubeHeight= 50;
	//x and y values of the sizes the holes would have
	int holeXSize=7 ,holeYSize=7;
	
	//Timer to control animation
	Timer animTimer ;

	public LudoDie(){
		setSize(dieCubeWidth,dieCubeHeight);
		setVisible(true);
		setLayout(new BorderLayout());
		addMouseListener(this);
		setSize(dieCubeWidth, dieCubeHeight);
	}
	
	
	public void paint(Graphics graph){
		//super.paint(graph);
		Graphics2D graph2d = (Graphics2D) graph;
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    graph2d.setRenderingHints(rh);		
		drawDie(graph2d, randVal1,X1, Y1);
	}
	
	public void setLoc(int xLoc, int yLoc){
		X1= xLoc;
		Y1 = yLoc;
		repaint();
	}
	
	private void drawDie(Graphics2D graph, int val, int x, int y) {
		Color dieGrey = new Color(240,240,240);
		
		//graph.fillRect(x, y, dieCubeSizeX, dieCubeSizeY);
		//creating the border for the die
		graph.setColor(Color.black);
		graph.fillRoundRect(x-1, y-1, dieCubeWidth+2, dieCubeHeight+2, 20, 20);
		//drawing the rounded rectangle for the die
		graph.setColor(dieGrey);
		graph.fillRoundRect(x, y, dieCubeWidth, dieCubeHeight, 20, 20);
		

		
		//Code to set the dot values based on the random value submitted
		//Create positions, and light up depending on the value
		
		//hole/dot positions
		int x11 = x+5;
		int x13 = x+38;
		int x21 = x+5;
		int x22 = x+ 21;
		int x23 = x+ 38;
		int x31 = x+5;
		int x33 = x+ 38;
		
		int y11 = y+5;
		int y13 = y+5;
		int y21 = y +21;
		int y22 = y+ 21;
		int y23 = y + 21;
		int y31 = y+38;
		int y33 = y+ 38;
		
		graph.setColor(Color.BLACK);

		if(val == 1){
			graph.fillOval(x22,y22,holeXSize,holeYSize);
		}
		else if(val == 2){
			graph.fillOval(x11,y11,holeXSize,holeYSize);
			graph.fillOval(x33,y33,holeXSize,holeYSize);
		}
		else if(val == 3){
			graph.fillOval(x11,y11,holeXSize,holeYSize);
			graph.fillOval(x33,y33,holeXSize,holeYSize);
			graph.fillOval(x22,y22,holeXSize,holeYSize);
			
		}
		else if(val == 4){
			graph.fillOval(x13,y13,holeXSize,holeYSize);
			graph.fillOval(x11,y11,holeXSize,holeYSize);
			graph.fillOval(x33,y33,holeXSize,holeYSize);
			graph.fillOval(x31,y31,holeXSize,holeYSize);
		}
		else if (val == 5){
			graph.fillOval(x13,y13,holeXSize,holeYSize);
			graph.fillOval(x11,y11,holeXSize,holeYSize);
			graph.fillOval(x33,y33,holeXSize,holeYSize);
			graph.fillOval(x31,y31,holeXSize,holeYSize);
			graph.fillOval(x22,y22,holeXSize,holeYSize);
			
		}
		else if(val == 6){
			graph.fillOval(x11,y11,holeXSize,holeYSize);
			graph.fillOval(x13,y13,holeXSize,holeYSize);
			graph.fillOval(x21,y21,holeXSize,holeYSize);
			graph.fillOval(x23,y23,holeXSize,holeYSize);
			graph.fillOval(x31,y31,holeXSize,holeYSize);
			graph.fillOval(x33,y33,holeXSize,holeYSize);
		}

	}
	
/*	public static void main(String [] args){
		Die test = new Die();
	}*/
	
	public int[] initAnim() {
	
			// Generate a random number,
			// call the die drawer, to draw the diff die faces
		
			Random randGen = new Random();
			randVal1 = 1+randGen.nextInt(6);
			randVal2 = 1+randGen.nextInt(6);
			repaint();
			int []retVal = {randVal1,randVal2};
			return(retVal);
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {		
		if (animTimer !=null){
			return;			
		}
		animTimer = new Timer(100,new ActionListener(){
			int frames =1;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				randVal1= initAnim()[0];
				frames++;
				if(frames == 10){
					animTimer.stop();
					animTimer= null;
					
				}
			}
			
		});
		animTimer.start();
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
