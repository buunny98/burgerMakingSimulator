package kitchen;

// This is a toaster class which extends the veggies abstract class. :)
// I also have four packages Food, Kitchen, Main and Util.
// This class will draw the shelf, toaster Images and the chopping board. 

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import food.Veggies;
import main.BurgerPanel;
import util.ImageLoader;
import util.MinimHelper;

public class Toaster extends Veggies {
	private double xPos;
	private double yPos;
	private double sca;
	private BufferedImage img, img1, img2, img3, img4;

	private Minim minim;
	private AudioPlayer open, close;

	
	// constructor
	public Toaster(double x, double y, double s) {
		super(x, y, s);
		xPos = x;
		yPos = y;
		sca = s;
		img = ImageLoader.loadImage("assets/microclosedwithout.png");
		//img1 = ImageLoader.loadImage("assets/board.png");
		img2 = ImageLoader.loadImage("assets/kitchen.png");
		img3 = ImageLoader.loadImage("assets/shelf.png");
		img4 = ImageLoader.loadImage("assets/chop.png");

		minim = new Minim(new MinimHelper());

		open = minim.loadFile("open.mp3");
		close = minim.loadFile("close.mp3");
	}

	public void drawButton(Graphics2D g2) {
		
		g2.drawImage(img2, -15, 310, 1100, 500, null);
		g2.drawImage(img3, -15, -360, 1100, 800, null);
		g2.drawImage(img4, 510, 330, 500, 250, null);
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);

		
		g2.drawImage(img1, -img.getWidth() / 2 - 600, -img.getHeight() / 2 + 700, 2950, 500, null);
		g2.drawImage(img, -img.getWidth() / 2 - 200, -img.getHeight() / 2 - 200, null);
		


		g2.setTransform(transform);

	}

	public boolean clicked(double x, double y){
		boolean clicked = false;

		if (x > (xPos - ((double) img.getWidth()) / 2 * sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
			clicked = true;


		return clicked;
	}

	public void setOvenImg (int ovenState) {
		if(ovenState == 0) {
			img = ImageLoader.loadImage("assets/microclosedwithout.png");
		} else if(ovenState == 1) {
			img = ImageLoader.loadImage("assets/microopen.png");
			open.play();
		} else if(ovenState == 2) {
			img = ImageLoader.loadImage("assets/microclosedwith.png");
			close.play();
		} 
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {

		return yPos;
	}

}

