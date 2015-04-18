package mainSrc;
import mainSrc.*;

import javax.swing.*;

import java.awt.*;

/*
 * Contains the game constants, mainly dimensions and points
 *  that are going to be used across the game classes. 
 * Maintains DRY.
 */

public interface BoardConstants {
	//Game constants
	public static final Color LUDO_RED = new Color(237,5, 44);
	public static final Color LUDO_YELLOW = new Color(255,228,0);
	public static final Color LUDO_BLUE = new Color(5,184,243);
	public static final Color LUDO_GREEN = new Color(16,243,5);
	
	//Board constants
	public static final Dimension BOARD_SIZE = new Dimension(900, 900);
	public static final Point BOARD_CENTER_POINT = new Point(BOARD_SIZE.width/2,BOARD_SIZE.height/2);
	public static final Dimension BOARD_JUMPSPOT_SIZE = new Dimension(40,40);
	public static final Dimension BOARD_BASE_SIZE = new Dimension(180,180);
	public static final int UNITGRID = 60;
	
	//Points on the board
		//Home Base jump spots
		Point [] BLUE_BASE_JUMPSPOTS= {
				new Point(200,630),
				new Point(200,690),
				new Point(260,630),
				new Point(260,690)		
		};
		Point [] GREEN_BASE_JUMPSPOTS= {
				new Point(620,210),
				new Point(620,270),
				new Point(680,210),
				new Point(680,270)		
		};
		Point [] YELLOW_BASE_JUMPSPOTS= {
				new Point(620,630),
				new Point(620,690),
				new Point(680,630),
				new Point(680,690)		
		};
		Point [] RED_BASE_JUMPSPOTS= {
				new Point(200,210),
				new Point(200,270),
				new Point(260,210),
				new Point(260,270)		
		};
		
		//Initial jump spots. 
		//ie, the spots where new pieces land as soon as they enter the game
		Point BLUE_INIT_JUMPSPOT = new Point(390,740);
		Point GREEN_INIT_JUMPSPOT = new Point(510,140);
		Point YELLOW_INIT_JUMPSPOT = new Point(750,500);
		Point RED_INIT_JUMPSPOT = new Point(150,380);
		
		//Path around the board
		
		//Win jump spots
			//listed in order from outer to inner, 0->3
		Point [] BLUE_WIN_JUMPSPOTS ={
			new Point(450,740),
			new Point(450,620),
			new Point(450,560),
			new Point(450,500)
		};
		Point [] GREEN_WIN_JUMPSPOTS ={
			new Point(450,200),
			new Point(450,260),
			new Point(450,320),
			new Point(450,380)	
		};
		Point [] YELLOW_WIN_JUMPSPOTS ={
			new Point(690,440),
			new Point(630,440),
			new Point(570,440),
			new Point(510,440)
		};
		Point [] RED_WIN_JUMPSPOTS ={
			new Point(210,440),
			new Point(270,440),
			new Point(330,440),
			new Point(390,440)
		};
	
	//Die constants
	public static final Dimension DIE_SIZE = new Dimension(50,50);
	public static final Point DIE_DRAW_START = new Point(BOARD_SIZE.width/2-27,BOARD_SIZE.height/2-53);
	public static final Dimension DIE_HOLE_SIZE = new Dimension(7,7);

	
	//Game status constants
	String PIECE_STATUS_ACTIVE = "Active";
	String PIECE_STATUS_BASED ="Based";
	String PIECE_STATUS_KICKED_OUT ="Dead";
	String PIECE_STATUS_SAFE ="Ascended";
	
	String PLAYER_STATUS_ACTIVE= "Active";
	String PLAYER_STATUS_WON="Won";
	String PLAYER_STATUS_LOST="Defeated";

}















