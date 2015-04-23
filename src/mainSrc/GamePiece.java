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
	
	String type;
	Image  pieceImage;
	Graphics2D graph;
	ImageObserver callerObserver;
	Point location;
	String status;
	int pathIndex,winPathIndex;
	Color pieceColor;
	int pieceOffset ;
	int round;
	int arrayPos;
	private static final long serialVersionUID = 1L;
	
	public GamePiece(String type,
			Graphics2D graph2d, 
			Point location,
			ImageObserver callerObs,
			int arrayPos){
		this.type = type.toUpperCase();
		this.callerObserver = callerObs;
		this.location = location;
		this.graph = graph2d;
		this.status = PIECE_STATUS_BASED;
		this.winPathIndex=0;
		this.arrayPos = arrayPos;
		getRes(this.type);
		round = 1;
		//placePiece(graph2d, location);
		//pieceHalo = new ActiveHalo(, (int)this.location.getY()-10, UNITGRID/2, UNITGRID/2, this.pieceColor);
		//pieceBound = new Rectangle((int)this.location.getX(),(int)this.location.getY(),UNITGRID/2,UNITGRID/2);
		
	}
	
	public void reset(){
		this.status = PIECE_STATUS_BASED;
		this.winPathIndex =0;
		this.round =1;
		getRes(type);
	}
	
	
	private void getRes(String type){
		
		//Get and draw Image based on the type of the character you are creating
		// also, set the path index to the index of the color init jump spot
		if(type.contains("YELLOW")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/yellowPiece.png"));
				pathIndex =YELLOW_PIECE_STARTING_OFFSET; 
				pieceOffset = YELLOW_PIECE_STARTING_OFFSET; 
				pieceColor= LUDO_YELLOW;
			} catch (IOException e) {
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("RED")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/redPiece.png"));
				pathIndex= RED_PIECE_STARTING_OFFSET;
				pieceOffset = RED_PIECE_STARTING_OFFSET;
				pieceColor= LUDO_RED;
			} catch (IOException e) {
			
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("BLUE")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/bluePiece.png"));
				pathIndex = BLUE_PIECE_STARTING_OFFSET;
				pieceOffset = BLUE_PIECE_STARTING_OFFSET;
				pieceColor= LUDO_BLUE;
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
		else if(type.contains("GREEN")){
			try {
				pieceImage = ImageIO.read(new File(".//res/gamePieces/greenPiece.png"));
				pathIndex = GREEN_PIECE_STARTING_OFFSET;
				pieceOffset = GREEN_PIECE_STARTING_OFFSET;
				pieceColor= LUDO_GREEN;
			} catch (IOException e) {
				
				System.out.println("Game Piece IO Error");
			}
		}
	}
	
	
	/**
	 * @Doc The heart of the piece class. Responsible for redrawing a piece at a specified point.
	 * @param graph2d
	 * @param location
	 */
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
		//graph2d.setColor(Color.BLACK);
	//	graph2d.drawString("|"+(int)this.location.getX()+","+(int)this.location.getY()+"|"+this.pathIndex,(int)this.location.getX()+20,(int)this.location.getY()-45);
	}

	
	public void movePiece(Point newLocation){
		this.location = new Point((int)newLocation.getX()-10,(int)newLocation.getY()+10);
	}
	
	public void movePiece(){
		//increase the path location by the one, if the piece hasn't ascended
		
				
				
		if(this.status.contains(PIECE_STATUS_ACTIVE)||this.status.contains(PIECE_STATUS_WAITING)){
			
			if(round==2&&(pathIndex -pieceOffset==0)&&type.contains("BLUE")){ //piece is at ascend spot
				movePiece(BLUE_WIN_JUMPSPOTS[winPathIndex]);
				if(winPathIndex ==4-arrayPos){
					status = PIECE_STATUS_SAFE;
				}
				else{
					winPathIndex= (winPathIndex+1)%5;
				}
				
			}
			else if(round==2&&(pathIndex -pieceOffset==0) && type.contains("YELLOW")){ //piece is at ascend spot
				movePiece(YELLOW_WIN_JUMPSPOTS[winPathIndex]);
				if(winPathIndex ==4-arrayPos){
					status = PIECE_STATUS_SAFE;
				}
				else{
					winPathIndex= (winPathIndex+1)%5;
				}
			}
			else if(round==2&&(pathIndex -pieceOffset==0)&&type.contains("GREEN")){ //piece is at ascend spot
				movePiece(GREEN_WIN_JUMPSPOTS[winPathIndex]);
				if(winPathIndex ==4-arrayPos){
					status = PIECE_STATUS_SAFE;
				}
				else{
					winPathIndex= (winPathIndex+1)%5;
				}
			}
			else if(round==2&&(pathIndex -pieceOffset==0)&&type.contains("RED")){ //piece is at ascend spot
				movePiece(RED_WIN_JUMPSPOTS[winPathIndex]);
				if(winPathIndex ==4-arrayPos){
					status = PIECE_STATUS_SAFE;
				}
				else{
					winPathIndex= (winPathIndex+1)%5;
				}
			}
			else{
				//this.location = BOARD_PATH_JUMPSPOTS[pathIndex];
				movePiece(BOARD_PATH_JUMPSPOTS[pathIndex]);
				pathIndex = (pathIndex+1)%38;
				round = 2;
			}
			
		}
				
				
		
	}
	
	public void setStatus(String newStatus){
		this.status = newStatus;
		
	}
	
	public void offsetPiece(String direction){
		int offset=0;
		if(direction.contains("left")){
			offset = -5;
		}
		else if(direction.contains("right")){
			offset=5;
		}
		
		movePiece(new Point((int)location.getX()+offset,(int)location.getY()));
	}

	

}






















