package mainSrc;

import javax.swing.*;

import java.awt.*;

public class BoardDrawer extends LudoBoard {
public static int sizeWidth=BoardConstants.BOARD_SIZE.width;
	
	public static int sizeHeight=BoardConstants.BOARD_SIZE.height;
	int jumpSpotWidth = BoardConstants.BOARD_JUMPSPOT_SIZE.width;
	int jumpSpotHeight = BoardConstants.BOARD_JUMPSPOT_SIZE.height;
	
	Graphics2D graph2d;
	JFrame frame;
	
	public BoardDrawer(Graphics2D graph2d,JFrame frame){
		this.graph2d = graph2d;
		this.frame = frame;
	}
	public void paint(){
		frame.paint(graph2d);
		
	    //Drawing paths
	    graph2d.setStroke(new BasicStroke(3));
	    graph2d.setColor(Color.WHITE);
	    graph2d.drawLine(sizeWidth/2, 150, sizeWidth/2, sizeHeight/2 -unitGrid); // top center vert path
	    graph2d.drawLine(sizeWidth/2, 150+(6*unitGrid), sizeWidth/2, sizeHeight/2 -unitGrid+(6*unitGrid)); //bottom center vert path
	    graph2d.drawLine(150, sizeHeight/2, 150+(4*unitGrid), sizeHeight/2); // left center horz path
	    graph2d.drawLine(150+(6*unitGrid), sizeHeight/2, 150+(4*unitGrid)+(6*unitGrid), sizeHeight/2); // right center horz path
	    graph2d.drawLine(sizeWidth/2-unitGrid,150, sizeHeight/2 +unitGrid, 150); // top horz path
	    graph2d.drawLine(sizeWidth/2-unitGrid,150+(10*unitGrid), sizeWidth/2 +unitGrid, 150+(10*unitGrid)); //bottom horz path
	    graph2d.drawLine(150, sizeHeight/2-unitGrid, 150, sizeHeight/2 +unitGrid); // left vert path
	    graph2d.drawLine(150+(10*unitGrid), sizeHeight/2-unitGrid, 150+(10*unitGrid), sizeHeight/2 +unitGrid); 
	    graph2d.drawLine(sizeWidth/2-unitGrid, 150, sizeWidth/2-unitGrid,sizeHeight/2 -unitGrid); //top left vert path
	    graph2d.drawLine(sizeWidth/2+unitGrid, 150, sizeWidth/2+unitGrid,sizeHeight/2 -unitGrid);  // top right vert path
	    graph2d.drawLine(sizeWidth/2-unitGrid, 150+(6*unitGrid), sizeWidth/2-unitGrid,sizeHeight/2 -unitGrid +(6*unitGrid)); //bottom left vert path
	    graph2d.drawLine(sizeWidth/2+unitGrid, 150+(6*unitGrid), sizeWidth/2+unitGrid,sizeHeight/2 -unitGrid+(6*unitGrid));  // bottom right vert path
	    graph2d.drawLine(150,sizeHeight/2-unitGrid, sizeWidth/2-unitGrid, sizeHeight/2-unitGrid); //left top horz path
	    graph2d.drawLine(150,sizeHeight/2-unitGrid+(2*unitGrid), sizeWidth/2-unitGrid, sizeHeight/2-unitGrid+(2*unitGrid));  // left bottom horz path
	    graph2d.drawLine(150+(6*unitGrid),sizeHeight/2-unitGrid, sizeWidth/2-unitGrid+(6*unitGrid), sizeHeight/2-unitGrid); //right top horz path
	    graph2d.drawLine(150+(6*unitGrid),sizeHeight/2-unitGrid+(2*unitGrid), sizeWidth/2-unitGrid+(6*unitGrid), sizeHeight/2-unitGrid+(2*unitGrid));  // right bottom horz path 
	    graph2d.setStroke(new BasicStroke(1));
	    
	    
		//drawing jump spots
	    drawSpots(sizeWidth/2, 150, 5, graph2d, "Vertical"); // top center jump spots
	    drawSpots(sizeWidth/2-unitGrid, 150, 5, graph2d, "Vertical"); // top left jump spots
	    drawSpots(sizeWidth/2+unitGrid,150,5,graph2d,"Vertical"); //top right jump spots
	    
	    drawSpots(sizeWidth/2, 150+(6*unitGrid), 5, graph2d, "Vertical"); // bottom center jump spots
	    drawSpots(sizeWidth/2-unitGrid, 150+(6*unitGrid), 5, graph2d, "Vertical"); // bottom left jump spots
	    drawSpots(sizeWidth/2+unitGrid,150+(6*unitGrid),5,graph2d,"Vertical"); //bottom right jump spots
	    
	    drawSpots(150, sizeHeight/2, 5, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150, sizeHeight/2-unitGrid, 5, graph2d, "Horizontal"); // left top jump spots
	    drawSpots(150, sizeHeight/2+unitGrid, 5, graph2d, "Horizontal"); //left bottom jump spots
	    
	    drawSpots(150+(6*unitGrid), sizeHeight/2, 5, graph2d, "Horizontal"); // right center jump spots
	    drawSpots(150+(6*unitGrid), sizeHeight/2-unitGrid, 5, graph2d, "Horizontal"); // right top jump spots
	    drawSpots(150+(6*unitGrid), sizeHeight/2+unitGrid, 5, graph2d, "Horizontal"); //right bottom jump spots
	    
	    //redrawing colored jump spots
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2, 150+unitGrid, 4, graph2d, "Vertical"); // top center colored jump spots
	    drawSpots(sizeWidth/2+unitGrid, 150, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(150+(6*unitGrid), sizeHeight/2, 4, graph2d, "Horizontal"); // right center colored jump spots
	    drawSpots(sizeWidth/2-unitGrid+(6*unitGrid), sizeHeight/2-unitGrid+(2*unitGrid), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(sizeWidth/2, 150+(6*unitGrid), 4, graph2d, "Vertical"); // bottom center colored jump spots
	    drawSpots(sizeWidth/2-unitGrid,150+(10*unitGrid), 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_RED);
	    drawSpots(150+unitGrid, sizeHeight/2, 4, graph2d, "Horizontal"); // left center jump spots
	    drawSpots(150,sizeHeight/2-unitGrid,1, graph2d, "");
	    
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
	    drawSpots(150+unitGrid, 150+unitGrid, 1, graph2d, "");
	    drawSpots(150+unitGrid, 150+unitGrid+unitGrid, 1, graph2d, "");
	    drawSpots(150+unitGrid+unitGrid, 150+unitGrid, 1, graph2d, "");
	    drawSpots(150+unitGrid+unitGrid, 150+unitGrid+unitGrid, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_BLUE);
	    drawSpots(150+unitGrid, sizeHeight/2 +(3*unitGrid), 1, graph2d, "");
	    drawSpots(150+unitGrid+unitGrid, sizeHeight/2 +(3*unitGrid), 1, graph2d, "");
	    drawSpots(150+unitGrid, sizeHeight/2 +(3*unitGrid)+unitGrid, 1, graph2d, "");
	    drawSpots(150+unitGrid+unitGrid, sizeHeight/2 +(3*unitGrid)+unitGrid, 1, graph2d, "");
	    
	    graph2d.setColor(LUDO_GREEN);
	    drawSpots(sizeWidth/2 +(3*unitGrid), 150+unitGrid, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*unitGrid)+unitGrid, 150+unitGrid, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*unitGrid), 150+unitGrid+unitGrid, 1, graph2d, "");
	    drawSpots(sizeWidth/2 +(3*unitGrid)+unitGrid, 150+unitGrid+unitGrid,1, graph2d, "");
	    
	    graph2d.setColor(LUDO_YELLOW);
	    drawSpots(sizeWidth/2+(3*unitGrid), sizeHeight/2 +(3*unitGrid), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*unitGrid)+unitGrid, sizeHeight/2 +(3*unitGrid), 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*unitGrid), sizeHeight/2 +(3*unitGrid)+unitGrid, 1, graph2d, "");
	    drawSpots(sizeWidth/2+(3*unitGrid)+unitGrid, sizeHeight/2 +(3*unitGrid)+unitGrid, 1, graph2d, "");
	    
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
			graph2d.fillOval(startX-20+(i*unitGrid*horz), startY-20+(i*unitGrid*vert), jumpSpotWidth,jumpSpotHeight);
		}
	}
}
