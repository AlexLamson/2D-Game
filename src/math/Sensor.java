package math;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import main.Main;
import mobs.Thing;
import mobs.grav.SquarePlayer;

public class Sensor
{
	private RectangleD rect;
	private Thing parent;
	private boolean colliding;
	
	public Sensor(Thing parent, double x, double y, double width, double height)
	{
		this.parent = parent;
		rect = new RectangleD(x, y, width, height);
		colliding = false;
	}
	
	public RectangleD getRect()
	{
		PointD newPos = new PointD(rect.getPos());
		newPos.x += parent.pos.x;
		newPos.y += parent.pos.y;
		
		RectangleD newRect = new RectangleD(rect);
		newRect.setPos(newPos);
		return newRect;
	}
	
	public boolean isIntersecting(Thing t)
	{
		if(t == parent)
			return false;
		return t.collides && this.getRect().isIntersecting(t.getBounds());
	}
	
	public boolean isIntersecting()
	{
		RectangleD newRect = getRect();
		ArrayList<Thing> collThings = Main.world.getCollidableThings();
		for(int i = 0; i < collThings.size(); i++)
			if(collThings.get(i).collides && 
					collThings.get(i) != parent && 
					newRect.isIntersecting( collThings.get(i).getBounds() ))
				return true;
		return false;
	}
	
	public Set<Thing> getIntersecting()
	{
		RectangleD newRect = getRect();
		ArrayList<Thing> collThings = Main.world.getCollidableThings();
		Set<Thing> things = new HashSet<Thing>();
		for(int i = 0; i < collThings.size(); i++)
			if(collThings.get(i).collides && 
					collThings.get(i) != parent && 
					newRect.isIntersecting( collThings.get(i).getBounds() ))
				things.add(collThings.get(i));
		return things;
	}
	
	public void reset()
	{
		colliding = false;
	}
	
	public void tick()
	{
//		reset();
		colliding = isIntersecting();
	}
	
	public void render(Graphics g)
	{
		Color color = new Color(0, 255, 0, 100);
//		if(isIntersecting()) //TODO DEBUG
		if(colliding)
			color = new Color(255, 0, 0, 100);
		g.setColor(color);
		
		RectangleD newRect = getRect();
		g.fillRect((int)newRect.getPos().x, (int)newRect.getPos().y, 
				(int)newRect.getWidth(), (int)newRect.getHeight());
	}
}
