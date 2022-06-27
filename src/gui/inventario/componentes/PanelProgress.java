package gui.inventario.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class PanelProgress extends JPanel {

	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);
	private int progress =0 ;
	
	/**
	 * Create the panel.
	 */
	public PanelProgress() {
		
	}
	
	public void updateProgress(int valor){
		progress= valor;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(this.getWidth()/2, this.getHeight()/2);
		g2.rotate(Math.toRadians(270));
		
		Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
		Ellipse2D circle = new Ellipse2D.Float(0,0,50,50);
		arc.setFrameFromCenter(new Point(0,0), new Point(60,60));
		circle.setFrameFromCenter(new Point(0,0), new Point(50,50));
		arc.setAngleStart(1);
		arc.setAngleExtent(-progress*3.6);
		g2.setColor(COLOR_ROJO);
		g2.draw(arc);
		g2.fill(arc);
		g2.setColor(Color.WHITE);
		g2.draw(circle);
		g2.fill(circle);
		g2.setColor(COLOR_BORDO);
		g2.rotate(Math.toRadians(90));
		g.setFont(new Font("Verdana",Font.PLAIN,30));
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(progress+"%", g);
		int x = (0-(int)rect.getWidth())/2;
		int y = (0-(int)rect.getHeight())/2+fm.getAscent();
		g2.drawString(progress+"%", x, y);
	}

}
