package food;

import java.awt.Graphics2D;

//This is the class of lettuce which extends the veggies class. 
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Lettuce extends Veggies{
	

	protected double xPos;
	protected double yPos;
	protected double sca;
	protected BufferedImage img;

	public Lettuce(double xPos, double yPos, double sca) {
		super(xPos, yPos, sca);
		this.xPos = xPos;
		this.yPos = yPos;
		this.sca = sca;

		img = ImageLoader.loadImage("assets/lettuce.png");

	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		super.drawButton(g2);

		g2.setTransform(transform);
	}

	public boolean hit (BurgerBun bun) {

		if(xPos - (bun.getXPos()) < 1 && yPos - (bun.getYPos()) < 1) {

			return true;

		}
		return false;

	}
	public void setXPos(double mouseX) {
		xPos = mouseX;


	}

	public void setYPos(double mouseY) {

		yPos = mouseY;

	}
	public boolean clicked(double x, double y){
		boolean clicked = false;

		if(x > (xPos - ((double) img.getWidth())/2*sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
			clicked = true;

		return clicked;
	}
}