package main;

//ECO POINTS
//night and day: open the shop when it is closed
//Finite state machine, 10 states used.
//decorator patterns used with the help of buttons for pickle and tomato
//objects created using factory pattern.
//Perlin noise in the toaster
//Human like avatar (CHEF), when you drag on it, it moves with you to help you with everything, also a visual element. 
///////Images took from the internet but they all were edited and cropped in the photoshop. //////////
//using frcators to design the shops names on the top right side. 
//animations involved for the toaster, dra and drop. 
//different types of music for differnt hits and interactions. 

import static util.ImageLoader.loadImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import food.BurgerBun;
import food.Lettuce;
import food.Patty;
import food.Pickle;
import food.Tomato;
import food.Veggies;
import kitchen.Welcome;
import kitchen.Van;
import kitchen.OpenSign;
import kitchen.Restart;
import kitchen.Toaster;
import kitchen.ChefHand;
import kitchen.Design;
import kitchen.Fog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;

public class BurgerPanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1050;
	public static int W_HEIGHT = 750;

	private double mouseX;
	private double mouseY;

	private double mouseX1;
	private double mouseY1;

	private JFrame anotherFrame;

	private int state = -1;
	private Lettuce lettuce;
	private Tomato tomato;
	private Pickle pickle;
	private Patty patty;
	private Van van;
	private OpenSign lampBtn;
	private Toaster toaster;
	private BurgerBun bun;
	private ChefHand hand;

	private Fog fog;
	private Restart r;
	private Welcome s;
	private Design d;

	public int price = 0;

	private int toastTimer = 0; 
	private boolean halfD = false;

	private int totalToastTime= 150;


	private Timer timer;

	private Minim minim;
	private AudioPlayer bkmusic, click, drag, ting, welcome, end;

	BurgerPanel(JFrame frame) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		anotherFrame = frame;


		van = new Van("assets/kitchen.png");
		lampBtn = new OpenSign(W_WIDTH / 2, 20, 0.2);
		toaster = new Toaster (W_WIDTH-740, W_HEIGHT/2 + 100, 0.4);
		hand = new ChefHand(850, 400, 1);

		lettuce = new Lettuce (60, 100, 1);
		tomato = new Tomato (340, 195, 1);


		fog = new Fog ((float) toaster.getXPos() - 90, (float) toaster.getYPos() - 100, 100, 30 );

		bun = new BurgerBun(W_WIDTH - 280, W_HEIGHT/2 + 80, 1);

		r = new Restart (W_WIDTH/2, W_HEIGHT/2+W_HEIGHT/3.5, 1);
		s = new Welcome (W_WIDTH/2, W_HEIGHT/2+W_HEIGHT/3.5, 1);
		d = new Design();
//		patty = new Patty (870, 190, 1);
//
//		pickle = new Pickle (580, 190, 1);


		minim = new Minim(new MinimHelper());

		bkmusic = minim.loadFile("lala.mp3");
		click = minim.loadFile("tap.mp3");
		drag = minim.loadFile("drag.mp3");
		ting = minim.loadFile("ting.mp3");
		welcome = minim.loadFile("welcome.mp3");
		end = minim.loadFile("end.mp3");

		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);

		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);


		JButton addPatty = new JButton("TAKE OUT PATTY FROM FRIDGE");


		Dimension size2 = addPatty.getPreferredSize();
		addPatty.setBounds(830, 59, size2.width, size2.height);
		addPatty.setBackground(Color.cyan);
		add(addPatty);
		setLayout(null);
		addPatty.addActionListener(this);		

		JButton pickle = new JButton("TAKE OUT PICKLE FROM FRIDGE");
		Dimension size1 = pickle.getPreferredSize();
		pickle.setBounds(830, 100, size1.width, size1.height);
		pickle.setBackground(Color.cyan);
		add(pickle);

		pickle.addActionListener(this);		



		timer = new Timer(30, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "TAKE OUT PATTY FROM FRIDGE") {
			patty = new Patty (870, 190, 1);
		}

		if (e.getActionCommand() == "TAKE OUT PICKLE FROM FRIDGE") {
			pickle = new Pickle (580, 190, 1);
		}

		if(state == 3) {
			fog.setWidth(toastTimer/2);
			fog.setHeight(toastTimer/2);
			if(!halfD) {
				toastTimer ++;
				if(toastTimer > totalToastTime) {
					toastTimer = totalToastTime;
					halfD = true; 
				}
			} else {
				toastTimer --;
				if(toastTimer <= 0) {
					toastTimer = 0;
					bun.setXPos(800);
					bun.setYPos(460);
					state = 4;
					bun.setBunState(2);
					toaster.setOvenImg(1);
					price = 2;
				}
			}
		}



		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		if (state == -1) {
			lampBtn.setLightOn(false);
			s.drawButton(g2);
			welcome.play();
			hand.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 40));
			g2.setColor(Color.red);
			g2.drawString("WELCOME TO TASTY BURGERZZZZZ", W_WIDTH/2 - 320, W_HEIGHT/2 - 320);

			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.setColor(Color.black);
			g2.drawString("Let's Open our Burger Shop", W_WIDTH/2 - 400, W_HEIGHT/2 - 70);
			g2.drawString("Toast the Bun and Add the Veggies", W_WIDTH/2 - 400, W_HEIGHT/2);
			g2.drawString("Keep an eye on the price on the top left side", W_WIDTH/2 - 400, W_HEIGHT/2 + 80);

		} else if (state == 0) {
			welcome.pause();
			hand.drawButton(g2);
			lampBtn.setLightOn(false);
			van.drawKitchen(g2);
			lampBtn.drawButton(g2);

			g2.setColor(new Color(0, 0, 0, 150));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));

			String st1 = "It's dark, turn on the light by clicking on Open Button!";

			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.setColor(Color.black);
			g2.drawString(st1,  W_WIDTH/2 - 470, W_HEIGHT/2 - 250);		

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if (state == 1) {
			end.pause();
			hand.drawButton(g2);
			lampBtn.setLightOn(true);
			van.drawKitchen(g2);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);


			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.setColor(Color.black);
			g2.drawString("CHEF, OPEN THE OVEN",  W_WIDTH/2 - 170, W_HEIGHT/2 - 250);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state ==2) {
			hand.drawButton(g2);
			lampBtn.setLightOn(true);
			van.drawKitchen(g2);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);

			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);
			//pickle.drawButton(g2);
			//patty.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.setColor(Color.black);
			g2.drawString("DRAG THE BURGER IN THE OVEN",  W_WIDTH/2 - 240, W_HEIGHT/2 - 250);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if (state ==3) {
			hand.drawButton(g2);
			lampBtn.setLightOn(true);
			van.drawKitchen(g2);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			fog.drawFog(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);

			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);
			//pickle.drawButton(g2);
			//patty.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.setColor(Color.black);
			g2.drawString("LET IT TOAST PROPERLY",  W_WIDTH/2 - 180, W_HEIGHT/2 - 250);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state == 4) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			ting.play();

			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString("ADD LETTUCE ON THE BURGER BY CLICKING ON IT",  W_WIDTH/2 - 250, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);


		} else if (state == 5) {
			end.pause();
			hand.drawButton(g2);
			van.drawKitchen(g2);
			r.drawButton(g2);


			g2.setFont(new Font("SansSerif", Font.BOLD, 40));
			g2.setColor(Color.black);
			g2.drawString("Want to make another Burger? Press Restart Button!", W_WIDTH/2 - 500, W_HEIGHT/2 - 120);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state == 6) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);


			g2.setFont(new Font("SansSerif", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString("ADD TOMATO ON THE BURGER BY CLICKING ON IT",  W_WIDTH/2 - 250, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);


		} else if(state == 7) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);
			//pickle.drawButton(g2);
			//patty.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString("ADD PICKLES, BUT FIRST TAKE THEM OUT OF THE FREEZER",  W_WIDTH/2 - 300, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state == 8) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);
			//pickle.drawButton(g2);
			//patty.drawButton(g2);

			g2.setFont(new Font("SansSerif", Font.BOLD, 15));
			g2.setColor(Color.black);
			g2.drawString("GREAT JOB SO FAR, FINALLY ADD THE PATTY, TAKE THE PATTY OUT OF THE FREEZER",  W_WIDTH/2 - 350, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state == 9) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);


			g2.setFont(new Font("SansSerif", Font.BOLD, 17));
			g2.setColor(Color.black);
			g2.drawString("WOW, THE BURGER LOOKS NICEEE. CLICK ON THE BURGER TO PACK IT AND READY TO EAT. :)",  W_WIDTH/2 - 490, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		} else if(state == 10) {
			hand.drawButton(g2);
			van.drawKitchen(g2);
			lampBtn.setLightOn(true);
			lampBtn.drawButton(g2);
			toaster.drawButton(g2);
			bun.drawButton(g2);
			lettuce.drawButton(g2);
			tomato.drawButton(g2);
			if(patty != null) patty.drawButton(g2);
			if(pickle != null) pickle.drawButton(g2);
			end.play();

			g2.setFont(new Font("SansSerif", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString("THIS LOOKS PERFECT, CLICK THE ABOVE SIGN TO CLOSE THE SHOP AGAIN.",  W_WIDTH/2 - 440, W_HEIGHT/2 - 270);

			g2.setColor(Color.black);
			g2.setFont(new Font("SansSerif", Font.BOLD, 30));
			g2.drawString("Price: $" + price, 10 , 40);

			d.drawFrac(g2, 765, 50, 9);

		}


	}


	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == 0 && lampBtn.clicked(mouseX, mouseY)) {
				click.play(0);
				bkmusic.loop();
				state = 1;
			} else if(state == 1 && lampBtn.clicked(mouseX, mouseY)) {
				click.play(0);
				bkmusic.pause();
				state = 0;
			} 

			if(state == -1 && s.clicked(mouseX, mouseY)) {
				//toaster.setOvenImg(1);
				state = 0;
			}

			if(state == 1 && toaster.clicked(mouseX, mouseY)) {
				drag.play();
				toaster.setOvenImg(1);				
				state = 2;
			}

			if(state == 4 && lettuce.clicked(mouseX + 30,  mouseY - 60)) {
				ting.loop(0);
				price = price + 1;
				bun.setBunState(3);
				state = 6; 
			}
			if(state == 6 && tomato.clicked(mouseX,  mouseY)) {
				ting.loop(0);
				price = price + 1;
				bun.setBunState(4);
				state = 7; 
			}

			if(state == 7 && pickle.clicked(mouseX,  mouseY)) {
				ting.loop(0);
				price = price + 1;
				bun.setBunState(5);
				state = 8; 
			}
			if(state == 8 && patty.clicked(mouseX,  mouseY)) {
				ting.loop(0);
				price = price + 1;
				bun.setBunState(6);
				state = 9; 
			}

			if(state == 9 && bun.clicked(mouseX,  mouseY)) {
				bun.setBunState(7);
				state = 10; 
			}


			if(state == 10 && lampBtn.clicked(mouseX, mouseY)) {

				state = 5;
				bkmusic.pause();
			}

			if(state == 5 && r.clicked(mouseX,  mouseY)) {
				anotherFrame.dispose();
				anotherFrame = new BurgerApp("Tasty Burgerzzz");

			}
		}

	}

	public class MyMouseMotionListener extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {

			mouseX = e.getX();
			mouseY = e.getY();



			if (state == 2) {
				bun.setXPos(mouseX);
				bun.setYPos(mouseY);
				if (bun.hit(toaster)) {
					toaster.setOvenImg(2);
					state = 3;
				}
			}

			if(state == 4) {
				bun.setXPos(800);
				bun.setYPos(460);

			}
			if(state == -1) {
				hand.setXPos(850);
				hand.setYPos(400);
			} else {
				hand.setXPos(mouseX);
				hand.setYPos(mouseY);
			}


			repaint();

		}
	}

}