/*
 * Class to represent each game piece. 
 * Should be capable of drawing up the game piece by itself,
 * and translation.
 */
package mainSrc;

import javax.imageio.*;
import javax.naming.Context;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
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
	Point location;
	String status;
	int pathIndex;
	Rectangle pieceBound;
	Color pieceColor;
	private static final long serialVersionUID = 1L;
	
	public GamePiece(String type,Graphics2D graph2d, Point location,ImageObserver callerObs){
		this.type = type.toUpperCase();
		this.callerObserver = callerObs;
		this.location = location;
		this.graph = graph2d;
		this.status = PIECE_STATUS_BASED;
		getRes(this.type);
		//placePiece(graph2d, location);
		//pieceHalo = new ActiveHalo(, (int)this.location.getY()-10, UNITGRID/2, UNITGRID/2, this.pieceColor);
		pieceBound = new Rectangle((int)this.location.getX(),(int)this.location.getY(),UNITGRID/2,UNITGRID/2);
		
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
		// also, set the path index to the index of the color init jump spot
		if(type.contains("YELLOW")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/yellowPiece.png"));
				pathIndex =30; 
				pieceColor= LUDO_YELLOW;
			} catch (IOException e) {
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("RED")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/redPiece.png"));
				pathIndex= 10;
				pieceColor= LUDO_RED;
			} catch (IOException e) {
			
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("BLUE")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/bluePiece.png"));
				pathIndex = 0;
				pieceColor= LUDO_BLUE;
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("GREEN")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/greenPiece.png"));
				pathIndex = 20;
				pieceColor= LUDO_GREEN;
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
	}
	
	public void placePiece(Graphics2D graph2d,Point location){
		if(location != this.location){
			//graph2d.clearRect((int)this.location.getX(), (int)this.location.getY(), 30, 45);
		}
		this.location = location;
		this.graph = graph2d;
		if (status.equals(PIECE_STATUS_ACTIVE)){
			graph2d.setColor(Color.black);
			final Shape circle = new Ellipse2D.Float((int)this.location.getX()-5, (int)this.location.getY()-17, UNITGRID/2, UNITGRID/2);
			graph2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {7.0f, 15.0f}, 11.0f));
			graph2d.draw(circle);
		}
		
		graph2d.drawImage(pieceImage,(int)this.location.getX()-3,(int)this.location.getY()-45,callerObserver);
	}

	
	public void movePiece(Point newLocation){
		this.location = new Point((int)newLocation.getX()-10,(int)newLocation.getY()+10);
	}
	
	public void movePiece(){
		//loop through the steps, increasing the path location by the step
		
			
			if(pathIndex >39){
				pathIndex=0;
			}
			//this.location = BOARD_PATH_JUMPSPOTS[pathIndex];
			movePiece(BOARD_PATH_JUMPSPOTS[pathIndex]);
			pathIndex++;
		
		
	}
	
	public void setStatus(String newStatus){
		this.status = newStatus;
		
	}

	

}






















