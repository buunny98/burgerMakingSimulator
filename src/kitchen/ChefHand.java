package kitchen;

//This is the class for the chef hand which follows your mouse when you click and drag on it. This chef hand is too help the person who is doing the simulating and also for a visual experience. 

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class ChefHand {

	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img;


	// constructor
	public ChefHand(double x, double y,  double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/hand.png");


	}

	public void drawButton(Graphics2D g2) {

		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2 + 90, null);

		g2.setTransform(transform);

	}

	public void setXPos(double X) {
		xPos = X;

	}

	public void setYPos(double Y) {

		yPos = Y;
	}
}

