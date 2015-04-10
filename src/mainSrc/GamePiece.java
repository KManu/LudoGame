/*
 * Class to represent each game piece. 
 * Should be capable of drawing up the game piece by itself,
 * and translation.
 */
package mainSrc;

import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GamePiece extends JComponent implements BoardConstants{

	/**
	 * 
	 */
	//TODO: Find a way to make the piece move. A single step, in a direction. Can be chained.
	String type;
	Image  pieceImage;
	Graphics2D graph;
	ImageObserver callerObserver;
	private static final long serialVersionUID = 1L;
	
	public GamePiece(String type, Point location,Graphics2D graph2d, ImageObserver callerObs){
		this.type = type.toUpperCase();
		this.callerObserver = callerObs;
		getRes(type);
		this.graph= graph2d;
		placePiece(graph, location);
		
	}
	
	public void paintComponent(Graphics graph){
		super.paintComponent(graph);
		Graphics2D graph2d = (Graphics2D) graph;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);		
	    graph2d.setRenderingHints(rh);
	    
	    
	}
	
	private void getRes(String type){
		
		//Get and draw Image based on the type of the character you are creating
		
		if(type.contains("YELLOW")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/yellowPiece.png"));
			} catch (IOException e) {
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("red")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/redPiece.png"));
			} catch (IOException e) {
			
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("BLUE")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/bluePiece.png"));
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("GREEN")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/greenPiece.png"));
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
	}
	
	public void placePiece(Graphics2D graph2d,Point location){
		graph2d.drawImage(pieceImage,(int)location.getX()-3,(int)location.getY()-45,callerObserver);
		
	}
	
	public void movePiece(Point location){
		graph.translate(location.getX(),location.getY());
		update(graph);
	}
	
	
	

}
