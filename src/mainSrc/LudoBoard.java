package mainSrc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LudoBoard extends JFrame implements BoardConstants, MouseListener , KeyListener  {

	private static final long serialVersionUID = 1L;

	int screenX,screenY ;
	public static int sizeWidth=BoardConstants.BOARD_SIZE.width;
	
	public static int sizeHeight=BoardConstants.BOARD_SIZE.height;
	int jumpSpotWidth = BoardConstants.BOARD_JUMPSPOT_SIZE.width;
	int jumpSpotHeight = BoardConstants.BOARD_JUMPSPOT_SIZE.height;
	LudoDie ludoDie= new LudoDie();
	
	//Points across the board
	
	public LudoBoard(){		
		super("Super awesome Ludo game");
		
		
		//Setting Jframe constants and basic init
		screenX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(sizeWidth,sizeHeight);
		getFrames()[0].setLocation(screenX/300,screenY/8);
		setResizable(false);
		
		addKeyListener(this);
		
		//Setting up panes
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setSize(new Dimension(getRootPane().getMaximumSize()));
		mainPane.setBackground(new Color(61,61,61));
		//Die settings
		//ludoDie.setLoc(mainPane.getWidth()/2 -25, mainPane.getHeight()/2 -50);
		mainPane.add(ludoDie);
		add(mainPane);
		setContentPane(mainPane);
		setVisible(true);
	}
	
	public void paint(Graphics graph){
		super.paint(graph);
		Graphics2D graph2d = (Graphics2D) graph;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);		
	    graph2d.setRenderingHints(rh);
	    
	    drawGrid(graph2d);
		
	    //Drawing paths
	    graph2d.setStroke(new BasicStroke(3));
	    graph2d.setColor(Color.WHITE);
	    graph2d.drawLine(sizeWidth/2, 150, sizeWidth/2, sizeHeight/2 -60); // top center vert path
	    graph2d.drawLine(sizeWidth/2, 150+(6*60), sizeWidth/2, sizeHeight/2 -60+(6*60)); //bottom center vert path
	    graph2d.drawLine(150, sizeHeight/2, 150+(4*60), sizeHeight/2); // left center horz path
	    graph2d.drawLine(150+(6*60), sizeHeight/2, 150+(4*60)+(6*60), sizeHeight/2); // right center horz path
	    graph2d.drawLine(sizeWidth/2-60,150, sizeHeight/2 +60, 150); // top horz path
	    graph2d.drawLine(sizeWidth/2-60,150+(10*60), sizeWidth/2 +60, 150+(10*60)); //bottom horz path
	    graph2d.drawLine(150, sizeHeight/2-60, 150, sizeHeight/2 +60); // left vert path
	    graph2d.drawLine(150+(10*60), sizeHeight/2-60, 150+(10*60), sizeHeight/2 +60); 
	    graph2d.drawLine(sizeWidth/2-60, 150, sizeWidth/2-60,sizeHeight/2 -60); //top left vert path
	    graph2d.drawLine(sizeWidth/2+60, 150, sizeWidth/2+60,sizeHeight/2 -60);  // top right vert path
	    graph2d.drawLine(sizeWidth/2-60, 150+(6*60), sizeWidth/2-60,sizeHeight/2 -60 +(6*60)); //bottom left vert path
	    graph2d.drawLine(sizeWidth/2+60, 150+(6*60), sizeWidth/2+60,sizeHeight/2 -60+(6*60));  // bottom right vert path
	    graph2d.drawLine(150,sizeHeight/2-60, sizeWidth/2-60, sizeHeight/2-60); //left top horz path
	    graph2d.drawLine(150,sizeHeight/2-60+(2*60), sizeWidth/2-60, sizeHeight/2-60+(2*60));  // left bottom horz path
	    graph2d.drawLine(150+(6*60),sizeHeight/2-60, sizeWidth/2-60+(6*60), sizeHeight/2-60); //right top horz path
	    graph2d.drawLine(150+(6*60),sizeHeight/2-60+(2*60), sizeWidth/2-60+(6*60), sizeHeight/2-60+(2*60));  // right bottom horz path 
	    graph2d.setStroke(new BasicStroke(1));
	    
	    
		//drawing jump spots
	    drawSpots(sizeWidth/2, 150, 5, graph2d, "Vertical"); // top center jump spots
	    drawSpots(sizeWidth/2-60, 150, 5, graph2d, "Vertical"); // top left jump spots
	    drawSpots(sizeWidth/2+60,150,5,graph2d,"Vertical"); //top right jump spots
	    
	    drawSpots(sizeWidth/2, 150+(6*60), 5, graph2d, "Vertical"); // bottom center jump spots
	    drawSpots(sizeWidth/2-60, 150+(6*60), 5, graph2d, "Vertical"); // bottom left jump spots
	    drawSpots(sizeWidth/2+60,150+(6*60),5,graph2d,"Vertical"); //bottom right jump spots
	    
	    drawSpots(150, sizeHeight/2, 5, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150, sizeHeight/2-60, 5, graph2d, "Horizontal"); // left top jump spots
	    drawSpots(150, sizeHeight/2+60, 5, graph2d, "Horizontal"); //left bottom jump spots
	    
	    drawSpots(150+(6*60), sizeHeight/2, 5, graph2d, "Horizontal"); // right center jump spots
	    drawSpots(150+(6*60), sizeHeight/2-60, 5, graph2d, "Horizontal"); // right top jump spots
	    drawSpots(150+(6*60), sizeHeight/2+60, 5, graph2d, "Horizontal"); //right bottom jump spots
	    
	    //redrawing colored jump spots
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2, 150+60, 4, graph2d, "Vertical"); // top center colored jump spots
	    drawSpots(sizeWidth/2+60, 150, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(150+(6*60), sizeHeight/2, 4, graph2d, "Horizontal"); // right center colored jump spots
	    drawSpots(sizeWidth/2-60+(6*60), sizeHeight/2-60+(2*60), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(sizeWidth/2, 150+(6*60), 4, graph2d, "Vertical"); // bottom center colored jump spots
	    drawSpots(sizeWidth/2-60,150+(10*60), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_RED);
	    drawSpots(150+60, sizeHeight/2, 4, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150,sizeHeight/2-60,1, graph2d, "");
	    
	    //Draw bases
	    graph2d.setStroke(new BasicStroke(3));
	    graph2d.drawOval(150,150, BoardConstants.BOARD_BASE_SIZE.width, BoardConstants.BOARD_BASE_SIZE.height);
	    graph2d.setColor(LUDO_GREEN);
	    graph2d.drawOval(sizeWidth/2+120,150, 180, 180);
	    graph2d.setColor(LUDO_BLUE);
	    graph2d.drawOval(150,sizeHeight/2 +120, 180, 180);
	    graph2d.setColor(LUDO_YELLOW);
	    graph2d.drawOval(sizeWidth/2 +120,sizeHeight/2 +120, 180, 180);
	    
	    //Drawing base spots
	    graph2d.setColor(LUDO_RED);
	    drawSpots(150+60, 150+60, 1, graph2d, "");
	    drawSpots(150+60, 150+60+60, 1, graph2d, "");
	    drawSpots(150+60+60, 150+60, 1, graph2d, "");
	    drawSpots(150+60+60, 150+60+60, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(150+60, sizeHeight/2 +(3*60), 1, graph2d, "");
	    drawSpots(150+60+60, sizeHeight/2 +(3*60), 1, graph2d, "");
	    drawSpots(150+60, sizeHeight/2 +(3*60)+60, 1, graph2d, "");
	    drawSpots(150+60+60, sizeHeight/2 +(3*60)+60, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2 +(3*60), 150+60, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*60)+60, 150+60, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*60), 150+60+60, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*60)+60, 150+60+60,1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(sizeWidth/2+(3*60), sizeHeight/2 +(3*60), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*60)+60, sizeHeight/2 +(3*60), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*60), sizeHeight/2 +(3*60)+60, 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*60)+60, sizeHeight/2 +(3*60)+60, 1, graph2d, "");
	}
	
	private void drawSpots(int startX, int startY, int number, Graphics2D graph2d, String orientation){
		//loop the creation of the jump spots along the paths
		int horz=1, vert=1;
		
		if(orientation.contains("Horizontal")){
			vert=0;
		}
		else if(orientation.contains("Vertical")){
			horz=0;
		}
		for(int i=0; i<number;i++){
			graph2d.fillOval(startX-20+(i*60*horz), startY-20+(i*60*vert), jumpSpotWidth,jumpSpotHeight);
		}
	}
	
	private void drawGrid(Graphics graph2d){
		//Drawing guide grids
	    //Vertical grids
	    
	    for(int i=-10; i <10;i++){
	    	graph2d.drawLine(sizeWidth/2 +(60*i), 0, sizeWidth/2+(60*i), sizeHeight); //vert center
	    }
	    //Horizontal grids
	    for(int i=-10; i <10;i++){
			graph2d.drawLine(0, sizeHeight/2+(i*60), sizeWidth, sizeHeight/2+(i*60)); //horizontal center
	    }
		
	}
	
	public static void main(String [] args){
		@SuppressWarnings("unused")
		LudoBoard test = new LudoBoard();
	}
	
	//LISTENERS

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		/** TODO 
		 * Set up actions on button clicks 
		 * SPACEBAR - Roll die 
		 */
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			//repaint();
			ludoDie.roll();
			//drawGrid(getGraphics());
			
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
