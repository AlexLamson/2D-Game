package main;


import input.Listening;

import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.*;

import world.World;
import mobs.*;
import mobs.grav.SquarePlayer;

//TO DO
//make width & height private in the World class
//fix background rendering for when camera is near edge and world size is big

public class Main extends Applet implements Runnable
{
	private static final long serialVersionUID = 8864158495101925325L;				//because stupid warnings
	
	public static int pixelSize = 1;												//change the scale the pixels are multiplied by when drawn to
	
	public static int tickTime = 50;
	public static int ticksPerSecond = (1000/Main.tickTime);
	public static boolean isRunning = false, isPaused = false, isGameOver = false;
	
	public static String windowName = "Shapes are awesome";

	public static boolean debugMode = false;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenWidth = (int)screenSize.getWidth();
	public static int screenHeight = (int)screenSize.getHeight();
	public static Dimension realSize;															//size of whole window
	public static Dimension size = new Dimension(screenWidth*3/4,screenHeight*3/4);				//drawable area
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);	//"pixels" in drawable area
	
	public static Point mse = new Point(0, 0);

	public static boolean isMouseLeft = false;
	public static boolean isMouseMiddle = false;
	public static boolean isMouseRight = false;

	public Image screen;
	public static JFrame frame;
	public static MessageQueue mq;
	public static World world;
	
	public Main()
	{
		Dimension fixedSize = new Dimension(size.width-10,size.height-10);
		setPreferredSize(fixedSize);
		requestFocus();
	}

	public static void restart()
	{
		Main viewer = new Main();
		viewer.start();
	}

	public void start()
	{
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());
//		addKeyListener(new Listening2());
//		addMouseListener(new Listening2());
//		addMouseMotionListener(new Listening2());
//		addMouseWheelListener(new Listening2());
		
		mq = new MessageQueue(10, 10);
		
//		Sounds.loadSounds();
//		Sounds.groovy.loop();
		
		//defining objects
		double worldSize = 1.0;
		
//		world = new World((int)(worldSize*pixel.width), (int)(worldSize*pixel.height));
		world = new World(1000, 1000);

		double centerX = (world.width/2);
		double centerY = (world.height/2);
		
		SquarePlayer player = new SquarePlayer((int)centerX, (int)centerY);
		world.add(player);
		world.cam = player;
		
		
		//start the main loop
		isRunning = true;
		new Thread(this).start();
		requestFocus();
	}

	public void stop()
	{
		isRunning = false;
	}

	public void tick()
	{
//		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height)
//			frame.pack();
		
		world.tick();
	}

	public void render()
	{
		Graphics g = screen.getGraphics();

		g.setColor(new Color(200, 200, 200));
		g.fillRect(0, 0, pixel.width, pixel.height);
		
		world.render(g);
		
		if(isPaused)
			drawTextScreen(g, "Game Paused (esc to unpause)", 3);
		else if(isGameOver)
			drawTextScreen(g, "Game Over", 2);
		
		if(!isPaused)
		{
			mq.addMessage("There are "+world.things.size()+" things");
			mq.addMessage("Movemode: "+world.controller.getMoveMode());
			mq.addMessage(Listening.mouseStates[Listening.currMouseState]);
			mq.addMessage("Controller: "+Main.world.controllerEnabled);
//			mq.addMessage("Keys: "+Listening.keysToString());
			mq.render(g);
		}
		mq.clear();
		
		g = getGraphics();
		
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();	//throw it away to avoid lag from too many graphics objects
	}
	
	public void drawTextScreen(Graphics g, String message, int scale)
	{
		int width = pixel.width;
		int height = pixel.height;
		
		g.setColor(new Color(100, 100, 100, 200));
		g.fillRect(0, 0, width, height);
		
		// g.setColor(Color.white);
		// g.fillRect(width/scale, (height/scale)+(height/(scale*4)), width/scale, height/(scale*2));
		
		Font fontSave = g.getFont();
		
		g.setFont(new Font("Verdana", 6, 18));
		g.setColor(Color.black);
		g.drawString(message, width/scale, height/2);
		
		g.setFont(fontSave);
	}
	
	public void run()
	{
		screen = createVolatileImage(pixel.width, pixel.height);	//actually use the graphics card (less lag)
		
		render();
//		if(!debugMode)
//			JOptionPane.showMessageDialog(null, "Hexagon thing\n\nControls:\nleft click - brighter\nmiddle click - random color\nright click - darker\nspace - toggle random colors mode (seizure warning!)");
		
		while(isRunning)
		{
			if(!isPaused && !isGameOver)
				tick();			//do math and any calculations
			render();
			
			try
			{
				Thread.sleep(tickTime);
			}catch(Exception e){ }
		}
	}

	public static void main(String[] args) {
		Main main = new Main();

		frame = new JFrame();
		frame.add(main);
		frame.pack();
		
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		frame.setTitle(windowName);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		//null makes it go to the center
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		main.start();
	}
}