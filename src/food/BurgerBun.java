package food;

//This is the class for the burger bun and it draws the different types of buns in simulation, with lettuce and with all the veggies. 

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import kitchen.Toaster;
import util.ImageLoader;
import util.MinimHelper;

public class BurgerBun extends Veggies{
	protected double xPos;
	protected double yPos;
	protected double scale;

	protected BufferedImage img;


	public BurgerBun(double x, double y, double sca) {
		super(x, y, sca);
		xPos = x;
		yPos = y;
		scale = sca;
		img = loadImage("assets/bun.png");
	}

	public boolean clicked(double x, double y){
		boolean clicked = false;

		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
//				drag.play();
//				drag.loop();

		return clicked;
	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		//g2.drawImage(img, -img.getWidth() / 2 + 300, -img.getHeight() / 2 + 220, 150, 150, null);
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);


		g2.setTransform(transform);
	}

	public void setPos(double mouseX, double mouseY) {
		xPos = mouseX;
		yPos = mouseY ;

	}

	public void setXPos(double X) {
		xPos = X;

	}

	public void setYPos(double Y) {

		yPos = Y;

	}

	public boolean hit (Toaster toaster) {

		if(xPos - (toaster.getXPos()) < 50 && yPos - (toaster.getYPos()) < 30) {

			return true;

		}
		return false;

	}

	public void setBunState(int bunState) {

		if(bunState == 1) {
			img = loadImage("assets/bun.png");
		} else if(bunState == 2) {
			img = loadImage("assets/openbun.png");
		} else if (bunState == 3) {
			img = loadImage("assets/withlettuce.png");
		} else if (bunState == 4) {
			img = loadImage("assets/withtomato.png");
		} else if (bunState == 5) {
			img = loadImage("assets/withpickle.png");
		} else if (bunState == 6) {
			img = loadImage("assets/withpatty.png");
		} else if (bunState == 7) {
			img = loadImage("assets/complete.png");
		}
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {

		return yPos;
	}
}