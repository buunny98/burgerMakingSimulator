
package kitchen;

//This is a class to create the starting screen at the very beginning of the simulation, it guides the viwer what to do. 

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//import ddf.minim.AudioPlayer;
//import ddf.minim.Minim;
import util.ImageLoader;
//import util.MinimHelper;

public class Welcome {
	private double xPos;
	private double yPos;
	private double sca;
	private BufferedImage img, img1, img2;

	public Welcome(double x, double y, double s) {

		xPos = x;
		yPos = y;
		sca = s;
		img = ImageLoader.loadImage("assets/start.png");
		img1 = ImageLoader.loadImage("assets/StartBurger.png");
		img2 = ImageLoader.loadImage("assets/ChefStart.png");

	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		g2.drawImage(img2, -img2.getWidth() / 2 - 300, -img2.getHeight() / 2 - 400, 400, 400, null);
		g2.drawImage(img1, -img1.getWidth() / 2 - 320, -img1.getHeight() / 2 + 130, 360, 360, null);
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}

	public boolean clicked(double x, double y){
		boolean clicked = false;

		if (x > (xPos - ((double) img.getWidth()) / 2 * sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
			clicked = true;


		return clicked;
	}
}

