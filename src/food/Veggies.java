package food;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public abstract class Veggies {
	protected double xPos;
	protected double yPos;
	protected double sca;
	protected BufferedImage img;

	public Veggies(double xPos, double yPos, double sca) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.sca = sca;
		
		img = ImageLoader.loadImage("assets/lettuce.png");

	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);
		
		
		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);



		g2.setTransform(transform);
	}
}
