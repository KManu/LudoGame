package mainSrc;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class ActiveHalo extends JComponent implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pane = new JPanel();
	int x;
	int y;
	int w;
	int h;
	Color myColour;
	int angle = 0;
	private Timer timer; //to animate highlight rotation
	//int count = 0;
	
	public ActiveHalo(int x, int y, int w, int h, Color myColour)
	{	
		timer = new Timer((30), this);
		timer.start();
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.myColour = myColour;
	}
	
	public ActiveHalo(int x, int y, int w, int h)
	{		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.myColour = Color.RED; //default highlight colour
		//timer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		drawCircle(gr, x, y, w, h);
	}
	
	public void drawCircle(final Graphics2D g2, final int x, final int y, final int w, final int h)
	{
		g2.setColor(myColour);
		final Shape circle = new Ellipse2D.Float(x, y, w, h);
		g2.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {7.0f, 15.0f}, 0.0f));
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//rotate the circle
		if (angle<360)
		{
			angle = angle + 5;
		}
		
		else
		{
			angle = 0;
		}
				
		//count++;
		g2.rotate(Math.toRadians(angle), x+w/2, y+h/2);		
		g2.draw(circle);
		
		/*if(count == 60){
			timer.stop();
		}*/
		
	}
	

	@Override
	public void actionPerformed(ActionEvent ev) {
		repaint();
		
	}

	

}
