
package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import util.ImageLoader;
import util.MinimHelper;

public class Restart {
	private double xPos;
	private double yPos;
	private double sca;
	private BufferedImage img;

	private Minim minim;
	private AudioPlayer open, close;
	public Restart(double x, double y, double s) {

		xPos = x;
		yPos = y;
		sca = s;
		img = ImageLoader.loadImage("assets/re.png");

		minim = new Minim(new MinimHelper());

		open = minim.loadFile("open.mp3");
		close = minim.loadFile("close.mp3");
	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);

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

