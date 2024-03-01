package food;

import java.awt.Graphics2D;

//A class to add the patty on top of the burger bun. 
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Patty {

	protected double xPos;
	protected double yPos;
	protected double sca;
	protected BufferedImage img;
	
	public Patty(double xPos, double yPos, double sca) {

		this.xPos = xPos;
		this.yPos = yPos;
		this.sca = sca;
		
		img = ImageLoader.loadImage("assets/burgerpatty.png");

	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		g2.drawImage(img,-img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public boolean hit (BurgerBun bun) {

		if(xPos - (bun.getXPos()) < 50 && yPos - (bun.getYPos()) < 30) {

			return true;

		}
		return false;

	}
	public void setXPos(double X) {
		xPos = X;


	}

	public void setYPos(double Y) {

		yPos = Y;

	}
	public boolean clicked(double x, double y){
		boolean clicked = false;

		if(x > (xPos - ((double) img.getWidth())/2*sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
			clicked = true;

		return clicked;
	}
}
