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
	public static final Color LUDO_RED = new Color(237,28, 36);
	public static final Color LUDO_YELLOW = new Color(248,230,7);
	public static final Color LUDO_BLUE = new Color(0,128,255);
	public static final Color LUDO_GREEN = new Color(0,240,0);
	
	//Board constants
	public static final Dimension BOARD_SIZE = new Dimension(900, 900);
	public static final Point BOARD_CENTER_POINT = new Point(BOARD_SIZE.width/2,BOARD_SIZE.height/2);
	public static final Dimension BOARD_JUMPSPOT_SIZE = new Dimension(40,40);
	public static final Dimension BOARD_BASE_SIZE = new Dimension(180,180); 
	//Points on the board
	
	
	//Die constants
	public static final Dimension DIE_SIZE = new Dimension(50,50);
	public static final Point DIE_DRAW_START = new Point(BOARD_SIZE.width/2-27,BOARD_SIZE.height/2-53);
	public static final Dimension DIE_HOLE_SIZE = new Dimension(7,7);

	
}
