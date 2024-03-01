package kitchen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.BurgerPanel;
import util.ImageLoader;

public class Van{
	private BufferedImage img;



	public Van(String file) {

		img = ImageLoader.loadImage(file);
	}

	public void drawKitchen(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(-90, 0);
		g2.scale(1.25, 1.25);

		g2.setColor(Color.gray);

		g2.setTransform(at);
	}

}
