package world;

import input.AbsController;
import input.Controller;
import input.Listening;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import world.blocks.AirBlock;
import world.blocks.Block;
import world.blocks.BlockManager;
import main.Main;
import math.Array2D;
import math.Mod;
import math.PointD;
import math.Vector;
import mobs.*;

public class World
{
	private int width, height;
	
	public boolean loopingEdges = false;
	public boolean renderGrid = false;
	
	public static double globalSpeedLimit = 100.0;
	
	public double G = 1000.0; //gravitational constant
	public boolean useMassGravity = false; //things with mass have gravity
	public boolean useGlobalGravity = false;
	public Vector globalGravity = new Vector(0, 1, true);
	
	public ArrayList<Thing> things = new ArrayList<Thing>();
	public Thing cam;
	public boolean controllerEnabled = true;
	public AbsController controller = new Controller();
	
	public Background background;
	public BlockManager blocks = new BlockManager();
	
	public World(int width, int height)
	{
		this.width = width;
		this.height = height;
		
//		background = new Background("res/img_rainbow.png");
//		background = new Background("res/img_blue.png");
		background = new Background("res/img_bounds_test.png");
		
		blocks = new BlockManager();
		
		
//		for (int i = 0; i < 10; i++)
//			add(new Mob(Math.random()*width, Math.random()*height));
	}
	
	public int getWidth(){ return width; }
	
	public int getHeight(){ return height; }
	
	public void setWidth(int width){ this.width = width;}
	
	public void setHeight(int height){ this.height = height;}
	
	public double getCamX()
	{
		if(cam == null)
			return 0;
		return cam.pos.x;
	}
	
	public double getCamY()
	{
		if(cam == null)
			return 0;
		return cam.pos.y;
	}
	
	public void add(Thing thing)
	{
		things.add(thing);
	}

	public void remove(Thing thing)
	{
		things.remove(thing);
	}

	public void remove(double x, double y)
	{
		for (int i = things.size() - 1; i >= 0; i--)
			if (things.get(i).getDistanceFrom(x, y) < 20)
				remove(things.get(i));
	}
	
	public void remove(PointD p)
	{
		remove(p.x, p.y);
	}
	
	public ArrayList<Thing> getCollidableThings()
	{
		ArrayList<Thing> output = new ArrayList<Thing>();
		
		for(int i = 0; i < things.size(); i++)
			if(things.get(i).collides)
				output.add(things.get(i));
		
		ArrayList<Block> allBlocks = blocks.blocks.toArrayList();
		for(int i = 0; i < allBlocks.size(); i++)
			if(allBlocks.get(i).collides)
				output.add(allBlocks.get(i));
		
		return output;
	}
	
	public boolean isColliding(Thing t)
	{
//		if(t.isCollidingWithWorldEdge())
//			return true;
		
		ArrayList<Thing> collThings = getCollidableThings();
		
		for(int i = 0; i < collThings.size(); i++)
		{
			if(collThings.get(i) == t)
				continue;
			
			if(t.isCollidingWith(collThings.get(i)))
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Thing> getCollidingThings(Thing t)
	{
		ArrayList<Thing> colliding = new ArrayList<Thing>();
		ArrayList<Thing> collThings = getCollidableThings();
		
		for(int i = 0; i < collThings.size(); i++)
		{
			if(collThings.get(i) == t)
				continue;
			
			if(t.isCollidingWith(collThings.get(i)))
				colliding.add(collThings.get(i));
		}
		return colliding;
	}
	
	public ArrayList<Thing> getCollidingThings(PointD p)
	{
		ArrayList<Thing> colliding = new ArrayList<Thing>();
		ArrayList<Thing> collThings = getCollidableThings();
		
		for(int i = 0; i < collThings.size(); i++)
		{
			if(collThings.get(i).isCollidingWith(p))
				colliding.add(collThings.get(i));
		}
		return colliding;
	}
	
	public ArrayList<Thing> getCollidingThings(double x, double y)
	{
		return getCollidingThings(new PointD(x, y));
	}
	
	public void checkDead()
	{
		for (Thing thing : getThings())
			if (thing.mustDie)
				remove(thing);
	}

	public ArrayList<Thing> getThings()
	{
		ArrayList<Thing> thingsClone = new ArrayList<Thing>();
		for (int i = 0; i < things.size(); i++)
			thingsClone.add(things.get(i));
		return thingsClone;
	}

	public Point worldToScreen(double x, double y)
	{
		return new Point(worldToScreenX(x), worldToScreenY(y));
	}
	
	public int worldToScreenX(double x)
	{
		return (int)(x-getCamX() + Main.pixel.width/2);
	}
	
	public int worldToScreenY(double y)
	{
		return (int)(y-getCamY() + Main.pixel.height/2);
	}
	
	public PointD screenToWorld(int x, int y)
	{
		return new PointD(screenToWorldX(x), screenToWorldY(y));
	}
	
	public double screenToWorldX(int x)
	{
		return x - Main.pixel.width/2 + getCamX();
	}
	
	public double screenToWorldY(int y)
	{
		return y - Main.pixel.height/2 + getCamY();
	}
	
	public void tick()
	{
		if(Listening.get("1"))
			setWidth(getWidth()+10);
		if(Listening.get("2"))
			setWidth(getWidth()-10);
		if(Listening.get("3"))
			setHeight(getHeight()+10);
		if(Listening.get("4"))
			setHeight(getHeight()-10);
		
		background.tick();
		
		blocks.tick();
		
		checkDead();
		
		for (int i = 0; i < things.size(); i++)
			things.get(i).tick();
		
		if(controllerEnabled)
			controller.applyInput(cam);
	}

	public void render(Graphics g)
	{
		background.render(g);
		
		blocks.render(g);
		
		int camX = (int)getCamX();
		int camY = (int)getCamY();
		
		
//		g.setColor(Color.green);
//		g.drawRect(worldToScreenX(0), worldToScreenY(0), Main.world.width, Main.world.height);
//		int rad = 20;
//		g.drawOval(worldToScreenX(0)-rad, worldToScreenY(0)-rad, 2*rad, 2*rad);
//		g.drawOval(worldToScreenX(width)-rad, worldToScreenY(0)-rad, 2*rad, 2*rad);
//		g.drawOval(worldToScreenX(0)-rad, worldToScreenY(height)-rad, 2*rad, 2*rad);
//		g.drawOval(worldToScreenX(width)-rad, worldToScreenY(height)-rad, 2*rad, 2*rad);
		
		//draw the grid
		if (renderGrid && cam != null)
		{
			int cellSize = 100;
			g.setColor(Color.black);
			for(int i = 0; i < Main.pixel.width/cellSize+1; i++)
				g.drawLine((int)(i*cellSize - camX % 100), 0, (int)(i*cellSize - camX % 100), Main.pixel.height);
			
			g.setColor(Color.black);
			for(int i = 0; i < Main.pixel.height/cellSize+2; i++)
				g.drawLine(0, (int)(i*cellSize - camY % 100), Main.pixel.width, (int)(i*cellSize - camY % 100));
		}
		
		//draw the Things
		for (int i = 0; i < things.size(); i++)
			renderThing(g, things.get(i));
	}
	
	public void renderThing(Graphics g, Thing t)
	{
		int camX = (int)getCamX();
		int camY = (int)getCamY();

		if (cam != null)
		{
			t.addXY(-camX + Main.pixel.width/2, -camY + Main.pixel.height/2);
			if(loopingEdges)
			{
				double oldX = t.pos.x;
				double oldY = t.pos.y;
				
				ArrayList<Double> xPositions = Mod.getPostions(oldX, width, 0, Main.pixel.width);
				ArrayList<Double> yPositions = Mod.getPostions(oldY, height, 0, Main.pixel.height);
//				ArrayList<Integer> xPositions = Mod.getPostions((int)oldX, width, 0, Main.pixel.width);
//				ArrayList<Integer> yPositions = Mod.getPostions((int)oldY, height, 0, Main.pixel.height);
				for(int yi = 0; yi < yPositions.size(); yi++)
				{
					for(int xi = 0; xi < xPositions.size(); xi++)
					{
						t.setXY(xPositions.get(xi), yPositions.get(yi));
						t.render(g);
					}
				}
				
				t.setXY(oldX, oldY);
			}
			else
				t.render(g);
			
			
			//draw normalized speed vector for camera
			if(t == cam)
			{
				g.setColor(Color.magenta);
				double sRot = Math.toRadians(cam.speed.getRot());
				g.drawLine((int)cam.pos.x, (int)cam.pos.y, (int)(cam.pos.x+20*Math.cos(sRot)), (int)(cam.pos.y+20*Math.sin(sRot)));
			}
			
			t.addXY( camX - Main.pixel.width/2,  camY - Main.pixel.height/2);
		}
		else
			t.render(g);
	}
}
