package mobs;

import java.awt.Color;
import java.awt.Graphics;

import main.Main;
import math.PointD;

public abstract class CircleThing extends Thing
{
	public double radius;
	protected Color color;
	
	public CircleThing(double x, double y)
	{
		super(x, y, 20, 20);
		radius = 20;
		color = Color.red; 
	}
	
	@Override
	public boolean isVisible()
	{
		int screenX = Main.world.worldToScreenX((int)pos.x);
		int screenY = Main.world.worldToScreenY((int)pos.y);
		
		if(screenX-radius > Main.pixel.width || screenX+radius < 0)
			return false;
		
		if(screenY-radius > Main.pixel.height|| screenY+radius < 0)
			return false;
		
		return true;
	}

	@Override
	public boolean isCollidingWith(Thing t)
	{
		if(t instanceof CircleThing)
		{
			CircleThing c = (CircleThing)t;
			double dist = t.getDistanceFrom(this);
			return dist <= c.radius+this.radius;
		}
		
		// TODO add more else if's for new classes
		return false;
	}

	@Override
	public boolean isCollidingWith(PointD p)
	{
		return this.getDistanceFrom(p.x, p.y) <= radius;
	}

	@Override
	public void constrainToWorldBounds()
	{
		if(pos.y+radius > Main.world.getHeight())
			pos.y = Main.world.getHeight()-radius;
		
		if(pos.y-radius < 0)
			pos.y = 0+radius;
		
		if(pos.x+radius > Main.world.getWidth())
			pos.x = Main.world.getWidth()-radius;
		
		if(pos.x-radius < 0)
			pos.x = 0+radius;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		double x = pos.x;
		double y = pos.y;
		
//		g.setColor(new Color(240, 20, 50));
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
		
		renderSpeed(g);
		
		g.setColor(Color.black);
		g.drawOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
		
		g.setColor(Color.black);
		g.drawLine((int)(x), (int)(y), (int)(x+speed.getX()), (int)(y+speed.getY()));
		
//		g.setColor(Color.black);
//		g.drawRect((int)getX()-radius, (int)getY()-radius, 2*radius, 2*radius);
	}
}
