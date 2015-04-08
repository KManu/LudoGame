package mainSrc;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.*;
public class BoardImageHandler extends LudoBoard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image yellowPiece, redPiece,bluePiece, greenPiece;
	public BoardImageHandler(){
		try{
			yellowPiece = ImageIO.read(new File(".//res/gamePieces/yellowPiece"));
			redPiece = ImageIO.read(new File(".//res/gamePieces/redPiece"));
			bluePiece = ImageIO.read(new File(".//res/gamePieces/bluePiece"));
			greenPiece = ImageIO.read(new File(".//res/gamePieces/greenPiece"));
		}
		catch(IOException r){
			System.out.println("Image file not found. Check to see it's in the res folder");
		}
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Image getYellowPiece() {
		return yellowPiece;
	}
	public Image getRedPiece() {
		return redPiece;
	}
	public Image getBluePiece() {
		return bluePiece;
	}
	public Image getGreenPiece() {
		return greenPiece;
	}
	

}
