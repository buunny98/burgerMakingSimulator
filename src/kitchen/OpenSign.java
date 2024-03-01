package kitchen;

//This is the class for the open sign to shift from the night light to day light and to open the shop. 

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class OpenSign  {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img, img1, img2;
	private boolean lightOn = false;

	// constructor
	public OpenSign(double x, double y,  double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/open.png");
		img2 = ImageLoader.loadImage("assets/hand.png");
		img1 = ImageLoader.loadImage("assets/burgershop.png");
		

	}

	public void drawButton(Graphics2D g2) {

		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2 + 90, null);


		if (!lightOn){ 
			g2.drawImage(img1, -2000, 600, 4000, 3000, null);
		}


		g2.setTransform(transform);

	}

	public void setLightOn(boolean on){
		lightOn = on;
	}

	public boolean clicked(double x, double y){
		boolean clicked = false;

		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;

		return clicked;
	}
}
