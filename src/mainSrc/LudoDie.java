package mainSrc;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import mainSrc.*;

//Subject InLab6

public class LudoDie extends JComponent implements BoardConstants, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//die coordinates
	int X1 = BoardConstants.DIE_DRAW_START.x;
	int Y1 = BoardConstants.DIE_DRAW_START.y;
	//Just starting values the die would have when initialized
	int randVal1 = 1 ;
	int randVal2 = 5;
	//x and Y values for the size of the die
	int dieCubeWidth=BoardConstants.DIE_SIZE.width;
	int dieCubeHeight= BoardConstants.DIE_SIZE.height;
	//x and y values of the sizes the holes would have
	int holeWidth=BoardConstants.DIE_HOLE_SIZE.width
		,holeHeight=BoardConstants.DIE_HOLE_SIZE.height;
	
	//Timer to control animation
	Timer animTimer ;
	public LudoDie(){
		setSize(dieCubeWidth,dieCubeHeight);
		X1=LudoBoard.sizeWidth/2-32;
		Y1 = LudoBoard.sizeHeight/2-80;
		setVisible(true);
		//setLayout(new BorderLayout());
		setPreferredSize(new Dimension(dieCubeWidth, dieCubeHeight));
		addMouseListener(this);
		setSize(dieCubeWidth, dieCubeHeight);
	}
	
	
	protected void paintComponent(Graphics graph){
		super.paintComponent(graph);
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
		//repaint();
	}
	
	private void drawDie(Graphics2D graph, int val, int x, int y) {
		Color dieGrey = new Color(240,240,240);
		Color transCOlor = new Color(0.5f,0.3f,0.7f,0.5f);
		
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
		
		graph.setColor(transCOlor);


		if(val == 1){
			
			graph.fillOval(x22,y22,holeWidth,holeHeight);
		}
		else if(val == 2){
			graph.setColor(LUDO_GREEN);
			graph.fillOval(x11,y11,holeWidth,holeHeight);
			graph.fillOval(x33,y33,holeWidth,holeHeight);
		}
		else if(val == 3){
			graph.setColor(LUDO_YELLOW);
			graph.fillOval(x11,y11,holeWidth,holeHeight);
			graph.fillOval(x33,y33,holeWidth,holeHeight);
			graph.fillOval(x22,y22,holeWidth,holeHeight);
			
		}
		else if(val == 4){
			graph.setColor(LUDO_RED);
			graph.fillOval(x13,y13,holeWidth,holeHeight);
			graph.fillOval(x11,y11,holeWidth,holeHeight);
			graph.fillOval(x33,y33,holeWidth,holeHeight);
			graph.fillOval(x31,y31,holeWidth,holeHeight);
		}
		else if (val == 5){
			graph.setColor(LUDO_BLUE);
			graph.fillOval(x13,y13,holeWidth,holeHeight);
			graph.fillOval(x11,y11,holeWidth,holeHeight);
			graph.fillOval(x33,y33,holeWidth,holeHeight);
			graph.fillOval(x31,y31,holeWidth,holeHeight);
			graph.fillOval(x22,y22,holeWidth,holeHeight);
			
		}
		else if(val == 6){
			graph.fillOval(x11,y11,holeWidth,holeHeight);
			graph.fillOval(x13,y13,holeWidth,holeHeight);
			graph.fillOval(x21,y21,holeWidth,holeHeight);
			graph.fillOval(x23,y23,holeWidth,holeHeight);
			graph.fillOval(x31,y31,holeWidth,holeHeight);
			graph.fillOval(x33,y33,holeWidth,holeHeight);
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
			update(getGraphics());
			int []retVal = {randVal1,randVal2};
			return(retVal);
		
	}
	public int roll(){
		if (animTimer !=null){
			return 0;			
		}
		animTimer = new Timer(10,new ActionListener(){
			int frames =1;
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				if(frames == 10){
					animTimer.stop();
					animTimer= null;		
				}
				frames++;
				randVal1= initAnim()[0];	
			}
		});
		animTimer.start();
		return randVal1;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {		
		//roll();
		
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
