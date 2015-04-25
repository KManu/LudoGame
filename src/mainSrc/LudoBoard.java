/*
 * JComponent that essentially draws the Board. 
 * That is, the spots, paths, and bases
 */
package mainSrc;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LudoBoard extends JFrame implements BoardConstants, MouseListener , KeyListener, ActionListener  {

	private static final long serialVersionUID = 1L;

	
	
	int screenX,screenY ;
	public static int sizeWidth=BoardConstants.BOARD_SIZE.width;
	public static int sizeHeight=BoardConstants.BOARD_SIZE.height;
	int jumpSpotWidth = BoardConstants.BOARD_JUMPSPOT_SIZE.width;
	int jumpSpotHeight = BoardConstants.BOARD_JUMPSPOT_SIZE.height;
	LudoDie ludoDie= new LudoDie();
	long songLength = 5000;
	AudioPlayer gameMusic = new AudioPlayer();

	//menu elements
	JMenu gameMenu;
	JMenu musicMenu;

	// Create and add simple menu item to one of the drop down menu
	JMenuItem nextSongAction;
//	JMenuItem previousSongAction;
	JMenuItem playSongAction;
	JMenuItem stopSongAction;
	JMenuItem aboutAction;
	JMenuItem helpAction;

	//Players
	Player [] players= new Player[4]; 

	
	//Game pieces
	GamePiece [] bluePieces = new GamePiece[4];
	GamePiece [] greenPieces= new GamePiece[4];
	GamePiece [] redPieces= new GamePiece[4];
	GamePiece [] yellowPieces= new GamePiece[4];
	
	int dieRollVal=0;
	int testIterator=0;
	Timer pieceAnimTimer=null;


	// audio player
	//private AudioPlayer gameMusic;
	

	
	int currentPlayerIndex=0;

	public LudoBoard(){		
		
		super("Super awesome Ludo game");
		
		//Menu Bar for Game
		//UIManager.put("MenuItem.background", Color.CYAN);
		//UIManager.put("MenuItem.opaque", true);

		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setBackground(LUDO_BLUE);

		// Add the menubar to the frame
		setJMenuBar(menuBar);

		// Define and add two drop down menu to the r
		gameMenu = new JMenu("Game");
		musicMenu = new JMenu("Music");
		menuBar.add(gameMenu);
		menuBar.add(musicMenu);

		// Create and add simple menu item to one of the drop down menu
		nextSongAction = new JMenuItem("Next Song");
	//	previousSongAction = new JMenuItem("Previous Song");
		playSongAction = new JMenuItem("Play");
		stopSongAction = new JMenuItem("Stop");
		
		helpAction = new JMenuItem("Help");
		aboutAction = new JMenuItem("About");
		
		gameMenu.add(helpAction);
		gameMenu.add(aboutAction);
		
		musicMenu.add(nextSongAction);
		//musicMenu.add(previousSongAction);
		musicMenu.add(playSongAction);
		musicMenu.add(stopSongAction);
		
		//Look and Feel of the menu bar
		String fontName = "Poor Richard";
		int fontStyle1 = Font.BOLD;
		int fontStyle2 = Font.PLAIN;
		int fontSize1 = 16;
		int fontSize2 = 14;
		gameMenu.setFont(new Font(fontName, fontStyle1, fontSize1));
		gameMenu.setForeground(MENU_COLOR);
		musicMenu.setFont(new Font(fontName, fontStyle1, fontSize1));
		musicMenu.setForeground(MENU_COLOR);
		nextSongAction.setFont(new Font(fontName, fontStyle2, fontSize2));
		playSongAction.setFont(new Font(fontName, fontStyle2, fontSize2));
		stopSongAction.setFont(new Font(fontName, fontStyle2, fontSize2));
		helpAction.setFont(new Font(fontName, fontStyle2, fontSize2));
		aboutAction.setFont(new Font(fontName, fontStyle2, fontSize2));
		
		/*
		nextSongAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the new action");
			}
		});
		*/
		nextSongAction.addActionListener(this);
		stopSongAction.addActionListener(this);
		//previousSongAction.addActionListener(this);
		playSongAction.addActionListener(this);
		helpAction.addActionListener(this);
		aboutAction.addActionListener(this);
		
		//Setting Jframe constants and basic init
		screenX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setUndecorated(true);
		setSize(sizeWidth,sizeHeight);
		getFrames()[0].setLocation(screenX/300,screenY/8);
		setResizable(true);
		getFrames()[0].setLocation(screenX/7,screenY/20);
		setResizable(true);
		addKeyListener(this);
		
		//init methods called here
		initBoard();
		//Setting up panes
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setSize(new Dimension(getRootPane().getMaximumSize()));
		mainPane.setBackground(new Color(61,61,61));
		//Die settings
		//ludoDie.setLoc(mainPane.getWidth()/2 -25, mainPane.getHeight()/2 -50);
		
		mainPane.add(ludoDie);
		//mainPane.addMouseListener(this);
		add(mainPane);
		setContentPane(mainPane);
		addMouseListener(this);
		setVisible(true);
	}
	
	/**
	 * This class is responsible for resetting the board and all pieces to the inital state. 
	 * It is called when the game is won.
	 * @param none
	 */
	private void reset(){
		
		
		 dieRollVal=0;
		 testIterator=0;
		 pieceAnimTimer=null;
		 currentPlayerIndex=0;
		 
		
		//Setting Jframe constants and basic init
		screenX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(sizeWidth,sizeHeight);

		getFrames()[0].setLocation(screenX/300,screenY/8);
		setResizable(true);

		getFrames()[0].setLocation(screenX/7,screenY/20);
		setResizable(false);

		
		addKeyListener(this);

		setVisible(true);	

		
		//init methods called here
		initBoard();
		//Setting up panes
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setSize(new Dimension(getRootPane().getMaximumSize()));
		mainPane.setBackground(new Color(61,61,61));
		//Die settings
		//ludoDie.setLoc(mainPane.getWidth()/2 -25, mainPane.getHeight()/2 -50);

		
		mainPane.add(ludoDie);
		mainPane.addMouseListener(this);
		add(mainPane);
		setContentPane(mainPane);
		setVisible(true);
	}
	
	/**
	 * Responsible for initializing the arrays of players and game pieces
	 * @param none 
	 */
	private void initBoard(){
		
		//init the players
		players[0]= new Player("blue");
		players[1] = new Player("red");
		players[2]= new Player("green");
		players[3]= new Player("yellow");
				
		//Set the pieces to their initial board positions
		//blue pieces
		Graphics2D graph2d= (Graphics2D) getGraphics();
		bluePieces[0]= new GamePiece(players[0].color, graph2d, BLUE_BASE_JUMPSPOTS[0], this,0);
		bluePieces[1]= new GamePiece(players[0].color, graph2d, BLUE_BASE_JUMPSPOTS[1], this,1);
		bluePieces[2]= new GamePiece(players[0].color, graph2d, BLUE_BASE_JUMPSPOTS[2], this,2);
		bluePieces[3]= new GamePiece(players[0].color, graph2d, BLUE_BASE_JUMPSPOTS[3], this,3);
		
		//green pieces
		greenPieces[0]= new GamePiece(players[2].color, graph2d, GREEN_BASE_JUMPSPOTS[0], this,0);
		greenPieces[1]= new GamePiece(players[2].color, graph2d, GREEN_BASE_JUMPSPOTS[1], this,1);
		greenPieces[2]= new GamePiece(players[2].color, graph2d, GREEN_BASE_JUMPSPOTS[2], this,2);
		greenPieces[3]= new GamePiece(players[2].color, graph2d, GREEN_BASE_JUMPSPOTS[3], this,3);

		//red pieces
		redPieces[0]= new GamePiece(players[1].color, graph2d, RED_BASE_JUMPSPOTS[0], this,0);
		redPieces[1]= new GamePiece(players[1].color, graph2d, RED_BASE_JUMPSPOTS[1], this,1);
		redPieces[2]= new GamePiece(players[1].color, graph2d, RED_BASE_JUMPSPOTS[2], this,2);
		redPieces[3]= new GamePiece(players[1].color, graph2d, RED_BASE_JUMPSPOTS[3], this,3);
			
		//yellow pieces
		yellowPieces[0]= new GamePiece(players[3].color, graph2d, YELLOW_BASE_JUMPSPOTS[0], this,0);
		yellowPieces[1]= new GamePiece(players[3].color, graph2d, YELLOW_BASE_JUMPSPOTS[1], this,1);
		yellowPieces[2]= new GamePiece(players[3].color, graph2d, YELLOW_BASE_JUMPSPOTS[2], this,2);
		yellowPieces[3]= new GamePiece(players[3].color, graph2d, YELLOW_BASE_JUMPSPOTS[3], this,3);
		
		
	}
	
	public void paint(Graphics graph){
		super.paint(graph);
		Graphics2D graph2d = (Graphics2D) graph;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);		
	    graph2d.setRenderingHints(rh);
		drawBoard(graph2d);
		placeBoardPieces(graph2d);
	    //drawGrid(graph2d);
	    scoreDisp(graph2d);
	}
	
	/**
	 * Responsible for drawing and displaying the current game scores.  
	 * @param graph2d
	 */
	private void scoreDisp(Graphics2D graph2d){
		//player order is
		//blue
		//red
		//green
		//yellow
		Font scoreFont = new Font(Font.SERIF,Font.BOLD,60);
		graph2d.setFont(scoreFont);
		graph2d.setColor(LUDO_BLUE);
		graph2d.drawString(players[0].score+"", (int)BLUE_SCORE_DISPLAY.getX(), (int)BLUE_SCORE_DISPLAY.getY());
		graph2d.setColor(LUDO_RED);
		graph2d.drawString(players[1].score+"", (int)RED_SCORE_DISPLAY.getX(), (int)RED_SCORE_DISPLAY.getY());
		graph2d.setColor(LUDO_GREEN);
		graph2d.drawString(players[2].score+"", (int)GREEN_SCORE_DISPLAY.getX(), (int)GREEN_SCORE_DISPLAY.getY());
		graph2d.setColor(LUDO_YELLOW);
		graph2d.drawString(players[3].score+"", (int)YELLOW_SCORE_DISPLAY.getX(), (int)YELLOW_SCORE_DISPLAY.getY());
		
		
	}
	
	/**
	 * Draws the game board, consisting of the jump spots, lines, and bases.
	 * @param graph2d
	 */
	private void drawBoard(Graphics2D graph2d){
		//Drawing paths
	    graph2d.setStroke(new BasicStroke(3));
	    graph2d.setColor(Color.WHITE);
	    graph2d.drawLine(sizeWidth/2, 150, sizeWidth/2, sizeHeight/2 -UNITGRID); // top center vert path
	    graph2d.drawLine(sizeWidth/2, 150+(6*UNITGRID), sizeWidth/2, sizeHeight/2 -UNITGRID+(6*UNITGRID)); //bottom center vert path
	    graph2d.drawLine(150, sizeHeight/2, 150+(4*UNITGRID), sizeHeight/2); // left center horz path
	    graph2d.drawLine(150+(6*UNITGRID), sizeHeight/2, 150+(4*UNITGRID)+(6*UNITGRID), sizeHeight/2); // right center horz path
	    graph2d.drawLine(sizeWidth/2-UNITGRID,150, sizeHeight/2 +UNITGRID, 150); // top horz path
	    graph2d.drawLine(sizeWidth/2-UNITGRID,150+(10*UNITGRID), sizeWidth/2 +UNITGRID, 150+(10*UNITGRID)); //bottom horz path
	    graph2d.drawLine(150, sizeHeight/2-UNITGRID, 150, sizeHeight/2 +UNITGRID); // left vert path
	    graph2d.drawLine(150+(10*UNITGRID), sizeHeight/2-UNITGRID, 150+(10*UNITGRID), sizeHeight/2 +UNITGRID); 
	    graph2d.drawLine(sizeWidth/2-UNITGRID, 150, sizeWidth/2-UNITGRID,sizeHeight/2 -UNITGRID); //top left vert path
	    graph2d.drawLine(sizeWidth/2+UNITGRID, 150, sizeWidth/2+UNITGRID,sizeHeight/2 -UNITGRID);  // top right vert path
	    graph2d.drawLine(sizeWidth/2-UNITGRID, 150+(6*UNITGRID), sizeWidth/2-UNITGRID,sizeHeight/2 -UNITGRID +(6*UNITGRID)); //bottom left vert path
	    graph2d.drawLine(sizeWidth/2+UNITGRID, 150+(6*UNITGRID), sizeWidth/2+UNITGRID,sizeHeight/2 -UNITGRID+(6*UNITGRID));  // bottom right vert path
	    graph2d.drawLine(150,sizeHeight/2-UNITGRID, sizeWidth/2-UNITGRID, sizeHeight/2-UNITGRID); //left top horz path
	    graph2d.drawLine(150,sizeHeight/2-UNITGRID+(2*UNITGRID), sizeWidth/2-UNITGRID, sizeHeight/2-UNITGRID+(2*UNITGRID));  // left bottom horz path
	    graph2d.drawLine(150+(6*UNITGRID),sizeHeight/2-UNITGRID, sizeWidth/2-UNITGRID+(6*UNITGRID), sizeHeight/2-UNITGRID); //right top horz path
	    graph2d.drawLine(150+(6*UNITGRID),sizeHeight/2-UNITGRID+(2*UNITGRID), sizeWidth/2-UNITGRID+(6*UNITGRID), sizeHeight/2-UNITGRID+(2*UNITGRID));  // right bottom horz path 
	    graph2d.setStroke(new BasicStroke(1));
	    
	    
		//drawing jump spots
	    drawSpots(sizeWidth/2, 150, 5, graph2d, "Vertical"); // top center jump spots
	    drawSpots(sizeWidth/2-UNITGRID, 150, 5, graph2d, "Vertical"); // top left jump spots
	    drawSpots(sizeWidth/2+UNITGRID,150,5,graph2d,"Vertical"); //top right jump spots
	    
	    drawSpots(sizeWidth/2, 150+(6*UNITGRID), 5, graph2d, "Vertical"); // bottom center jump spots
	    drawSpots(sizeWidth/2-UNITGRID, 150+(6*UNITGRID), 5, graph2d, "Vertical"); // bottom left jump spots
	    drawSpots(sizeWidth/2+UNITGRID,150+(6*UNITGRID),5,graph2d,"Vertical"); //bottom right jump spots
	    
	    drawSpots(150, sizeHeight/2, 5, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150, sizeHeight/2-UNITGRID, 5, graph2d, "Horizontal"); // left top jump spots
	    drawSpots(150, sizeHeight/2+UNITGRID, 5, graph2d, "Horizontal"); //left bottom jump spots
	    
	    drawSpots(150+(6*UNITGRID), sizeHeight/2, 5, graph2d, "Horizontal"); // right center jump spots
	    drawSpots(150+(6*UNITGRID), sizeHeight/2-UNITGRID, 5, graph2d, "Horizontal"); // right top jump spots
	    drawSpots(150+(6*UNITGRID), sizeHeight/2+UNITGRID, 5, graph2d, "Horizontal"); //right bottom jump spots
	    
	    //redrawing colored jump spots
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2, 150+UNITGRID, 4, graph2d, "Vertical"); // top center colored jump spots
	    drawSpots(sizeWidth/2+UNITGRID, 150, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(150+(6*UNITGRID), sizeHeight/2, 4, graph2d, "Horizontal"); // right center colored jump spots
	    drawSpots(sizeWidth/2-UNITGRID+(6*UNITGRID), sizeHeight/2-UNITGRID+(2*UNITGRID), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(sizeWidth/2, 150+(6*UNITGRID), 4, graph2d, "Vertical"); // bottom center colored jump spots
	    drawSpots(sizeWidth/2-UNITGRID,150+(10*UNITGRID), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_RED);
	    drawSpots(150+UNITGRID, sizeHeight/2, 4, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150,sizeHeight/2-UNITGRID,1, graph2d, "");
	    
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
	    drawSpots(150+UNITGRID, 150+UNITGRID, 1, graph2d, "");
	    drawSpots(150+UNITGRID, 150+UNITGRID+UNITGRID, 1, graph2d, "");
	    drawSpots(150+UNITGRID+UNITGRID, 150+UNITGRID, 1, graph2d, "");
	    drawSpots(150+UNITGRID+UNITGRID, 150+UNITGRID+UNITGRID, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(150+UNITGRID, sizeHeight/2 +(3*UNITGRID), 1, graph2d, "");
	    drawSpots(150+UNITGRID+UNITGRID, sizeHeight/2 +(3*UNITGRID), 1, graph2d, "");
	    drawSpots(150+UNITGRID, sizeHeight/2 +(3*UNITGRID)+UNITGRID, 1, graph2d, "");
	    drawSpots(150+UNITGRID+UNITGRID, sizeHeight/2 +(3*UNITGRID)+UNITGRID, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2 +(3*UNITGRID), 150+UNITGRID, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*UNITGRID)+UNITGRID, 150+UNITGRID, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*UNITGRID), 150+UNITGRID+UNITGRID, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*UNITGRID)+UNITGRID, 150+UNITGRID+UNITGRID,1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(sizeWidth/2+(3*UNITGRID), sizeHeight/2 +(3*UNITGRID), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*UNITGRID)+UNITGRID, sizeHeight/2 +(3*UNITGRID), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*UNITGRID), sizeHeight/2 +(3*UNITGRID)+UNITGRID, 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*UNITGRID)+UNITGRID, sizeHeight/2 +(3*UNITGRID)+UNITGRID, 1, graph2d, "");
	    
	}
	
	/**
	 * Helper function used to easily draw a number of jump spots, in a particular direction, 
	 * based on the parameters passed to it
	 * @param startX
	 * @param startY
	 * @param number
	 * @param graph2d
	 * @param orientation
	 */
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
			graph2d.fillOval(startX-20+(i*UNITGRID*horz), startY-20+(i*UNITGRID*vert), jumpSpotWidth,jumpSpotHeight);
		}
	}
	
	
	/**
	 * Draws a grid over the entire board, and also displays the coordinates of each grid line intersection.
	 * Very helpful for debugging interface design.
	 * @param graph2d
	 */
	private void drawGrid(Graphics graph2d){
		//Drawing guide grids
	    //Vertical grids. Sweep across from left to right
		char horzChar = 'j'; //offset to 10 chars ahead because the counter starts from -10
	    for(int i=-10; i <10;i++){
	    	
	    	graph2d.drawLine(sizeWidth/2 +(UNITGRID*i), 0, sizeWidth/2+(UNITGRID*i), sizeHeight);
	    	graph2d.setColor(Color.black);
	    }
	    //Horizontal grids. Sweep from top to bottom
	    for(int i=-10; i <10;i++){
	    	
			graph2d.drawLine(0, sizeHeight/2+(i*UNITGRID), sizeWidth, sizeHeight/2+(i*UNITGRID)); 
	    	graph2d.setColor(Color.black);
	    }
	    
	    //Putting up the labels on the grid
	    ((Graphics2D) graph2d).setRenderingHint(
	            RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	    graph2d.setColor(Color.magenta);
	    Point coordPoint = new Point();
	    String key= new String();
	    for(int i = -10;i<10;i++){
	    	//columns
	    	//System.out.println("Outer loop");
	    	for(int j=-10;j<20;j++){
	    	//	System.out.println("Inner loop");
	    		//rows
	    		int ascVal = (int) horzChar+i;
	    		//create points from the coordinates and then place those into the structures to hold the keys and values
	    		 key = Character.toString((char)ascVal) +"-"+ String.valueOf(i+10);
	    		 coordPoint = new Point(UNITGRID/2+(UNITGRID*j)-10, sizeHeight/2+(i*UNITGRID));
				// System.out.println(key+ " -> "+coordPoint.getX()+", "+coordPoint.getY());	  
	    		
	    		//placing character labels on the grid
	    		//graph2d.drawString(Character.toString((char)ascVal),sizeWidth/2 +(unitGrid*i)-10, (unitGrid*j)+unitGrid/2); 
	    		graph2d.drawString("("+(int)coordPoint.getY()+","+(int)coordPoint.getX()+")",sizeWidth/2 +(UNITGRID*i)-15, (UNITGRID*j)+UNITGRID/2);
	    		
	    		//placing number labels on the grid
	    		//graph2d.drawString(String.valueOf(i+10),unitGrid/2+(unitGrid*j)+5, sizeHeight/2+(i*unitGrid)); 	
				 
	    	}	  
	    }

	    
	    
	}
	
	/**
	 * Places the game pieces on the board, at their home bases.
	 * @param graph2d
	 */
	private void placeBoardPieces(Graphics2D graph2d){
		//Places the pieces at the points specified.
	
	bluePieces[0].placePiece(graph2d, bluePieces[0].location);
	bluePieces[1].placePiece(graph2d, bluePieces[1].location);
	bluePieces[2].placePiece(graph2d, bluePieces[2].location);
	bluePieces[3].placePiece(graph2d, bluePieces[3].location);
	
	greenPieces[0].placePiece(graph2d, greenPieces[0].location);
	greenPieces[1].placePiece(graph2d, greenPieces[1].location);
	greenPieces[2].placePiece(graph2d, greenPieces[2].location);
	greenPieces[3].placePiece(graph2d, greenPieces[3].location);
	
	redPieces[0].placePiece(graph2d, redPieces[0].location);
	redPieces[1].placePiece(graph2d, redPieces[1].location);
	redPieces[2].placePiece(graph2d, redPieces[2].location);
	redPieces[3].placePiece(graph2d, redPieces[3].location);
	
	yellowPieces[0].placePiece(graph2d, yellowPieces[0].location);
	yellowPieces[1].placePiece(graph2d, yellowPieces[1].location);
	yellowPieces[2].placePiece(graph2d, yellowPieces[2].location);
	yellowPieces[3].placePiece(graph2d, yellowPieces[3].location);

	}
	

	
	public static void main(String [] args){
		@SuppressWarnings("unused")
		LudoBoard test = new LudoBoard();
	}
	
	
	/**
	 * Class responsible for moving a piece along the board.
	 * Has a timer which moves the piece, one spot along the board path, the number of steps 
	 * @param piece
	 * @param steps
	 */
	public void boardPieceTranslate(GamePiece piece,int steps){
		//handles translating a piece along the board.
		//uses a counter to move the piece one step at a time, the number of steps
		if(piece.status.contains(PIECE_STATUS_SAFE)){
			return;
		}
		else{
			piece.setStatus(PIECE_STATUS_ACTIVE);
			if(pieceAnimTimer !=null){
				return;
			}
			pieceAnimTimer = new Timer(300,new ActionListener(){
				int frames =1;
				@Override
				public void actionPerformed(ActionEvent arg0) {	
					if(frames >= steps){
						pieceAnimTimer.stop();
						pieceAnimTimer=null;
						piece.setStatus(PIECE_STATUS_WAITING);
					}
					piece.movePiece();
					update((Graphics2D)getGraphics());
					frames++;
				}
			});
			pieceAnimTimer.start();
		}

	}
	
	//LISTENERS

	@Override
	public void mouseClicked(MouseEvent e) {
		//drawGrid((Graphics2D)getGraphics());
		Point me = e.getPoint();
		Rectangle bounds = new Rectangle(0,0,100,100);
		/*if((e.getX()>0 && e.getX()<100) && (e.getY()>0 && e.getY()<100)) //testing coordinate click
		{
			System.out.println("coordinates!");
		}*/
		
		if(bounds.contains(me))
		{
			System.out.println("coordinates!");
		}
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
	
	
	/**
	 * Responsible for automatically choosing the next player to play.
	 * @param selectedPiece
	 */
	public void selectNextPlayer(int selectedPiece){
		if(players[currentPlayerIndex].status.contains(PLAYER_STATUS_WON)){
			currentPlayerIndex= (currentPlayerIndex+1)%4;
		}
		
		if(currentPlayerIndex>=players.length-1){
			currentPlayerIndex =0;
		}
		else{
			currentPlayerIndex++;
		}
		//Select the next piece 
		setPlayersWaiting();
		players[currentPlayerIndex].setStatus(PLAYER_STATUS_ACTIVE);
		players[currentPlayerIndex].chosePiece(players[currentPlayerIndex].activePieceIndex);
	}

	
	/**
	 * Increases the specified player score by one, and if the player has a score
	 * of 4, triggers the win mechanism 
	 * @param player
	 */
	public void increasePlayerScore(Player player){
		if(player.score >=4){
			player.status = PLAYER_STATUS_WON;
			setPlayersLost(); // game is won
		}
		else{
			player.score = player.score +1;
		}
		
		
	}
	
	/**
	 * Returns the active game piece of the currently active player.
	 * If the currently active piece is safe, the player score is increased, and the next piece is selected 
	 */
	public GamePiece getGamePieces(){
		for(Player player: players){
			if(player.status.equals(PLAYER_STATUS_ACTIVE) && player.color.equals("BLUE")){
				if(bluePieces[player.activePieceIndex].status.contains(PIECE_STATUS_SAFE)){//if the current active piece is safe, increment the active index and return the next piece
					increasePlayerScore(player);
					player.activePieceIndex = (player.activePieceIndex+1)%4;
					return bluePieces[player.activePieceIndex];
				}
				else{
					return bluePieces[player.activePieceIndex];
				}
				
			}
			else if(player.status.equals(PLAYER_STATUS_ACTIVE )&& player.color.equals("GREEN")){
				if(greenPieces[player.activePieceIndex].status.contains(PIECE_STATUS_SAFE)){//if the current active piece is safe, increment the active index and return the next piece
					increasePlayerScore(player);
					player.activePieceIndex = (player.activePieceIndex+1)%4;
					return bluePieces[player.activePieceIndex];
				}
				else{
					return greenPieces[player.activePieceIndex];
				}		
			}
			else if(player.status.equals(PLAYER_STATUS_ACTIVE)&& player.color.equals("RED")){
				if(redPieces[player.activePieceIndex].status.contains(PIECE_STATUS_SAFE)){//if the current active piece is safe, increment the active index and return the next piece
					increasePlayerScore(player);
					player.activePieceIndex = (player.activePieceIndex+1)%4;
					return bluePieces[player.activePieceIndex];
				}
				else{
					return redPieces[player.activePieceIndex];
				}
			}
			else if(player.status.equals(PLAYER_STATUS_ACTIVE)&& player.color.equals("YELLOW")){
				if(yellowPieces[player.activePieceIndex].status.contains(PIECE_STATUS_SAFE)){//if the current active piece is safe, increment the active index and return the next piece
					increasePlayerScore(player);
					player.activePieceIndex = (player.activePieceIndex+1)%4;
					return bluePieces[player.activePieceIndex];
				}
				else{
					return yellowPieces[player.activePieceIndex];
				}
			}
			
		}
		return null;
	}
	
	
	/**
	 * Sets the status of all players to waiting. Called just before the next player is made active
	 */
	public void setPlayersWaiting(){
		for(Player player : players){
			if (player.status.equals(PLAYER_STATUS_LOST)|| player.status.equals(PLAYER_STATUS_WON)||player.status.equals(PLAYER_STATUS_WAITING)){
				continue;
			}
			else {
				player.setStatus(PLAYER_STATUS_WAITING);
			}
			
		}
	}
	
	/**
	 * Called when a player has won. Sets all the other players to a lost status,
	 * then displays a win dialog. After, resets the board
	 */
	public void setPlayersLost(){
		Player winner=null;
		for(Player player : players){
			if (player.status.equals(PLAYER_STATUS_WON)){
				winner = player;
				getGraphics().drawString(player.color+" has won!!", 440,440);
				continue;
			}
			else {
				player.setStatus(PLAYER_STATUS_LOST);
			}
			
		}		
		//y2k code here 
		String htmlColor = null;
		if(winner.color.contains("GREEN"))
		{
			htmlColor = "color:#10f305";
		}
		
		if(winner.color.contains("BLUE"))
		{
			htmlColor = "color:#105b8f3";
		}
		if(winner.color.contains("RED"))
		{
			htmlColor = "color:#f3052c";	
		}
		if(winner.color.contains("YELLOW"))
		{
			htmlColor = "color:#ffe400";	
		}
		
		String fontStyle = "<style>.myFont{font-family:'Calibri';font-size: 14px;} strong{color:#0082b3}</style>";
		String htmlStyle = "<style>.winFont{font-family:'Calibri';font-size: 14px;} strong{"+htmlColor+"}</style>";
		String aboutString = fontStyle+"<div class=\"myFont\"><strong>Congratulations!</strong>"
				+ "<hr>"
				+ htmlStyle+"<em class=\"winFont\">"+winner.color+"</em>"
				+ " won!"
				+ " Click OK to reset the game.</div>";
		JEditorPane aboutLabel = new JEditorPane();
		aboutLabel.setContentType("text/html; charset=utf-8");
		aboutLabel.setEditable(false);
		aboutLabel.setText(aboutString);
		aboutLabel.setOpaque(false);
		JOptionPane.showMessageDialog(this, aboutLabel,"Winner", JOptionPane.INFORMATION_MESSAGE);
		
		/*
		JOptionPane.showMessageDialog(this,
				"Congratulations, "+winner.color+" won! Click okay to reset the game.","Message",
				JOptionPane.INFORMATION_MESSAGE);*/
		reset();
	}
	
	
	/**
	 * Responsible for checking all the pieces for possible location conflicts. 
	 * Depending on the conflict detected, it either offsets the piece, or removes one piece 
	 * and returns it to it's base
	 */
	public void checkForConflict(){
		/*loop over all the game pieces, and check if there are any location conflicts.
		if of the same piece, offset. If different pieces, return to base*/
		
		
		
		ArrayList<GamePiece> totalPieces = new ArrayList<GamePiece>(); //arraylist to hold all board pieces in one data structure
		for(GamePiece piece:bluePieces){
			totalPieces.add(piece);
		}
		for(GamePiece piece:redPieces){
			totalPieces.add(piece);
		}
		for(GamePiece piece: greenPieces){
			totalPieces.add(piece);
		}
		for(GamePiece piece :yellowPieces){
			totalPieces.add(piece);
		}
			totalPieces.trimToSize();
			
		//binary search to find conflicts, if any
		for(int i=0; i< totalPieces.size();i++){
			
			for(int j =i+1; j<totalPieces.size();j++){
				if(totalPieces.get(i).pathIndex == totalPieces.get(j).pathIndex){ //conflict found
					if(!totalPieces.get(i).type.contains(totalPieces.get(j).type) ){//same color? then offset
						totalPieces.get(i).offsetPiece("left");
						totalPieces.get(j).offsetPiece("right");
						update((Graphics2D)getGraphics());
					}
					else{ //not the same color, knock off the waiting one, and leave the active one
						if(totalPieces.get(i).status.contains(PIECE_STATUS_WAITING)){
							totalPieces.get(i).reset();
							update((Graphics2D)getGraphics());
						}
						else if(totalPieces.get(j).status.contains(PIECE_STATUS_WAITING)){
							totalPieces.get(j).reset();
							update((Graphics2D)getGraphics());
						}
					}
				}
			}
			
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		/** 
		 * Set up actions on button clicks 
		 * SPACEBAR - Roll die 
		 */
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			

				dieRollVal =ludoDie.roll();
				//dieRollVal =40;
				selectNextPlayer(dieRollVal%4);
				boardPieceTranslate(getGamePieces(),dieRollVal);
				//checkForConflict();
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String fontStyle = "<style>.myFont{font-family:'Poor Richard';font-size: 14px;} strong{color:#0082b3}</style>";
		Object source = e.getSource();
		
		if(source == nextSongAction)
		{
			gameMusic.nextSong();
		}
		
		else if(source == stopSongAction)
		{
			gameMusic.stopSong();
			//System.out.println("stop");
		}
		
		/*
		else if(source == previousSongAction)
		{
			//gameMusic.previousSong();
			System.out.println("previous");
		}
		*/
		
		else if(source == playSongAction)
		{
			gameMusic.startSong();
			//System.out.println("play");
		}
		
		else if(source == aboutAction)
		{	
			//System.out.println("about");
			String aboutString = fontStyle+"<div class=\"myFont\"><strong>Developers</strong>"
					+ "<hr>"
					+ "Kwabena Manu<br>"
					+ "Thomas K. Collins<br><br>"
					+ "<strong>Supervisors</strong><br>"
					+ "<hr>"
					+ "Dr. Isaac Nti<br>"
					+ "Mr. Kwasi Adomako<br><br></div>";
			JEditorPane aboutLabel = new JEditorPane();
			aboutLabel.setContentType("text/html; charset=utf-8");
			aboutLabel.setEditable(false);
			aboutLabel.setText(aboutString);
			aboutLabel.setOpaque(false);
			JOptionPane.showMessageDialog(this, aboutLabel,"About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(source == helpAction)
		{	
			
			//System.out.println("help");
			String helpString = fontStyle+"<div class=\"myFont\"><strong>Controls</strong>"
					+ "<hr>"
					+ "Spacebar &nbsp;  ==> &nbsp; Toss die</div>";
			JEditorPane helpLabel = new JEditorPane();
			helpLabel.setContentType("text/html; charset=utf-8");
			helpLabel.setEditable(false);
			helpLabel.setText(helpString);
			helpLabel.setOpaque(false);
			JOptionPane.showMessageDialog(this, helpLabel, "Help", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	Image offScreenBuffer;
	public void update(Graphics g)
	{
		Graphics gr; 
		if (offScreenBuffer==null ||
				(! (offScreenBuffer.getWidth(this) == this.getWidth()
				&& offScreenBuffer.getHeight(this) == this.getHeight())))
		{
			offScreenBuffer = this.createImage(getWidth(), getHeight());
		}
		gr = offScreenBuffer.getGraphics();
		paint(gr); 
		g.drawImage(offScreenBuffer, 0, 0, this); 
	}
}
