package kitchen;

//For points: This is a class which creates a design using the refractor function on the screen. It is very subtle I know but this fits the best for my simulation. :(

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Design {

	public BufferedImage img;

	public Design () {
		img = ImageLoader.loadImage("assets/StartBurger.png");
	}


	public void drawFrac (Graphics2D g2, int x, int y, float d) { //d - diameter
		AffineTransform at = g2.getTransform();
		g2.translate(x, y);

		g2.setFont(new Font("SansSerif", Font.ITALIC, 30));
		g2.setColor(Color.darkGray);
		g2.drawString("TASTY BURGERZZ!",  -d/2, -d/2);
		g2.setTransform(at);
		if (d  > 2) {
			d *= 0.5;
			drawFrac (g2, (int) (x + 0.3), (int) (y + 0.9), d );
		}
	}
}
