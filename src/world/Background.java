package world;

import image.ImageGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Main;
import math.Mod;

public class Background
{
	BufferedImage img;
	
	public Background(int width, int height)
	{
		img = ImageGenerator.randomImage(width, height);
	}
	
	public Background()
	{
		this(10, 10);
	}
	
	public Background(String path)
	{
		img = readImage(path);
	}
	
	//return an image as an array of Colors
	public BufferedImage readImage(String path)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read( new File( path ) );
		} catch (IOException e)
		{
//			e.printStackTrace();
			System.err.println("couldn't load background image from '"+path+"'");
			
			img = ImageGenerator.randomImage(10, 10);
		}
		
		return img;
	}
	
	public int getWidth()
	{
		return img.getWidth();
	}
	
	public int getHeight()
	{
		return img.getHeight();
	}
	
	public int getCellWidth()
	{
		return Main.world.getWidth()/getWidth();
	}
	
	public int getCellHeight()
	{
		return Main.world.getHeight()/getHeight();
	}
	
	public int getCellX(int worldX)
	{
		return worldX / getCellWidth();
	}
	
	public int getCellY(int worldY)
	{
		return worldY / getCellHeight();
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		int worldWidth = Main.world.getWidth();
		int worldHeight = Main.world.getHeight();
		int screenWidth = Main.pixel.width;
		int screenHeight = Main.pixel.height;
		double cellWidth = 1.0*worldWidth/getWidth();
		double cellHeight = 1.0*worldHeight/getHeight();
		
		int camX = (int)Main.world.getCamX();
		int camY = (int)Main.world.getCamY();
		
		int xPadding = 0;
		int yPadding = 0;
		if(Main.world.loopingEdges)
		{
			xPadding = (int) (1.0 * screenWidth/cellWidth / 2);
			yPadding = (int) (1.0 * screenHeight/cellHeight / 2);
		}
		
//		g.drawImage(img, -xPadding*cellWidth -camX+screenWidth, -yPadding*cellWidth -camY+screenHeight, 
//				getWidth()*cellWidth, getHeight()*cellHeight, null);
		
		int yi1 = 0;
		int yi2 = 1;
		int xi1 = 0;
		int xi2 = 1;
		
		if(Main.world.loopingEdges)
		{
			int camX1 = (int)(camX-Main.pixel.width/2);
			int camX2 = (int)(camX+Main.pixel.width/2);
			int camY1 = (int)(camY-Main.pixel.height/2);
			int camY2 = (int)(camY+Main.pixel.height/2);
			
			if(camX1 < 0)
				xi1 = (camX1-camX)/Main.world.getWidth()-1;
			if(camX2 > Main.world.getWidth())
				xi2 = (camX2-camX)/Main.world.getWidth()+1+1;
			if(camY1 < 0)
				yi1 = (camY1-camY)/Main.world.getHeight()-1;
			if(camY2 > Main.world.getHeight())
				yi2 = (camY2-camY)/Main.world.getHeight()+1+1;
		}
		
		int oldX = (int) (-xPadding*cellWidth -camX+screenWidth);
		int oldY = (int) (-yPadding*cellHeight -camY+screenHeight);
		
		if(!Main.world.loopingEdges)
		{
			oldX += -Main.pixel.width/2;
			oldY += -Main.pixel.height/2;
		}
		
		for(int yi = yi1; yi < yi2; yi++)
		{
			for(int xi = xi1; xi < xi2; xi++)
			{
				int x = oldX + xi*Main.world.getWidth();
				int y = oldY + yi*Main.world.getHeight();
				
				g.drawImage(img, x, y, (int)(getWidth()*cellWidth), (int)(getHeight()*cellHeight), null);
			}
		}
	}
	
	public Color getColor(int x, int y)
	{
		x = Mod.mod(x, getWidth());
		y = Mod.mod(y, getHeight());
		return new Color(img.getRGB(x, y));
	}
	
	public void setColor(int x, int y, Color color)
	{
		x = Mod.mod(x, getWidth());
		y = Mod.mod(y, getHeight());
		img.setRGB(x, y, color.getRGB());
	}
}
