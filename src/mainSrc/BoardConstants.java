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
		
		
		
		//Path around the board
		 //List out all the jump spots on the board
		 //in traversal order. Starting from the blue jump spot
		Point [] BOARD_PATH_JUMPSPOTS={
				new Point(390,740),//0
				new Point(390,680),//1
				new Point(390,620),//2
				new Point(390,560),//3
				new Point(390,500),//4
				new Point(330,500),//5
				new Point(270,500),//6
				new Point(210,500),//7
				new Point(150,500),//8
				new Point(150,440),//9
				new Point(150,380),//10
				new Point(210,380),//11
				new Point(270,380),//12
				new Point(330,380),//13
				new Point(390,380),//14
				new Point(390,320),//15
				new Point(390,260),//16
				new Point(390,200),//17
				new Point(390,140),//18
				new Point(450,140),//19
				new Point(510,140),//20
				new Point(510,200),//21
				new Point(510,260),//22
				new Point(510,320),//23
				new Point(510,380),//24
				new Point(570,380),//25
				new Point(630,380),//26
				new Point(690,380),//27
				new Point(750,380),//28
				new Point(750,440),//29
				new Point(750,500),//30
				new Point(690,500),//31
				new Point(630,500),//32
				new Point(570,500),//33
				new Point(510,500),//34
				new Point(510,560),//35
				new Point(510,620),//36
				new Point(510,680),//37
				new Point(510,740),//38
				new Point(450,740)  //39
		};
		
		//Initial jump spots. 
			//ie, the spots where new pieces land as soon as they enter the game
		Point BLUE_INIT_JUMPSPOT = BOARD_PATH_JUMPSPOTS[0];
		Point RED_INIT_JUMPSPOT = BOARD_PATH_JUMPSPOTS[10]; //10
		Point GREEN_INIT_JUMPSPOT = BOARD_PATH_JUMPSPOTS[20];//20
		Point YELLOW_INIT_JUMPSPOT =BOARD_PATH_JUMPSPOTS[30];//30
		
		
		//Win jump spots
			//listed in order from outer to inner, 0->3
		Point [] BLUE_WIN_JUMPSPOTS ={
			new Point(450,740),	
			new Point(450,680),
			new Point(450,620),
			new Point(450,560),
			new Point(450,500)
		};
		Point [] GREEN_WIN_JUMPSPOTS ={
			new Point(450,140),	
			new Point(450,200),
			new Point(450,260),
			new Point(450,320),
			new Point(450,380)	
		};
		Point [] YELLOW_WIN_JUMPSPOTS ={
			new Point(750,440),	
			new Point(690,440),
			new Point(630,440),
			new Point(570,440),
			new Point(510,440)
		};
		Point [] RED_WIN_JUMPSPOTS ={
			new Point(150,440),	
			new Point(210,440),
			new Point(270,440),
			new Point(330,440),
			new Point(390,440)
		};
		
		//Lose Jump Spots. These are the resting places of the fallen
			//listed in order from bottom to top
		Point [] BLUE_LOSE_JUMPSPOTS ={
				new Point(90,740),	
				new Point(90,680),
				new Point(90,620),
				new Point(90,560)
		
			};
			Point [] GREEN_LOSE_JUMPSPOTS ={
				new Point(810,320),	
				new Point(810,260),
				new Point(810,200),
				new Point(810,140)
					
			};
			Point [] YELLOW_LOSE_JUMPSPOTS ={
				new Point(810,740),	
				new Point(810,680),
				new Point(810,620),
				new Point(810,560)
		
			};
			Point [] RED_LOSE_JUMPSPOTS ={
				new Point(90,320),	
				new Point(90,260),
				new Point(90,200),
				new Point(90,140)
				
			};
			
	//game piece starting path index offsets
			int BLUE_PIECE_STARTING_OFFSET =0;
			int GREEN_PIECE_STARTING_OFFSET =20;
			int RED_PIECE_STARTING_OFFSET =10;
			int YELLOW_PIECE_STARTING_OFFSET =30;
	
	//Die constants
	public static final Dimension DIE_SIZE = new Dimension(50,50);
	public static final Point DIE_DRAW_START = new Point(BOARD_SIZE.width/2-27,BOARD_SIZE.height/2-53);
	public static final Dimension DIE_HOLE_SIZE = new Dimension(7,7);

	
	//Game status constants
	String PIECE_STATUS_ACTIVE = "Active";
	String PIECE_STATUS_WAITING = "Waiting";
	String PIECE_STATUS_BASED ="Based";
	String PIECE_STATUS_SAFE ="Ascended";
	
	String PLAYER_STATUS_ACTIVE= "Active";
	String PLAYER_STATUS_WAITING ="Waiting";
	String PLAYER_STATUS_WON="Won";
	String PLAYER_STATUS_LOST="Defeated";

}















