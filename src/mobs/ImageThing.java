package mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Main;
import math.Vector;

public class ImageThing extends Thing
{
	private BufferedImage img;
	
	public ImageThing(double x, double y, BufferedImage img)
	{
		super(x, y, img.getWidth(), img.getHeight());
		this.img = img;
		
		speed = new Vector(10, 5, true);
//		speed = Vector.randomVector();
	}
	
	public ImageThing(double x, double y, String path) throws IOException
	{
		this(x, y, ImageIO.read( new File( path ) ));
	}
	
	public void tick()
	{
		super.tick();
		
		speed.addRotMag(3, 0);
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		g.drawImage(img, (int)(pos.x-width/2), (int)(pos.y-height/2), 
				(int)width, (int)height, null);
		
	}
}
