package mobs;

import java.awt.Color;
import java.awt.Graphics;

import math.Rotation;

public class Mob extends Thing
{
	public Color mobColor = new Color(255, 0, 0);
	
	public Mob(double x, double y)
	{
		super(x, y, 40, 40);
		
		mass = 100;
		speed.setRotMag((int)(Math.random()*Rotation.maxDegrees), 5 + Math.random()*10);
		collides = true;
	}
	
	public void tick()
	{
		super.tick();
		
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
//		renderSensors(g);
		
		Color color;
		
		//turn green if colliding
		if(isColliding)
			color = Color.green;
		else
			color = mobColor;
		
		double x = pos.x;
		double y = pos.y;
		
//		g.setColor(new Color(240, 20, 50));
		g.setColor(color);
		g.fillOval((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
		
		renderSpeed(g);
		
		g.setColor(Color.black);
		g.drawOval((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
		
		g.setColor(Color.black);
		g.drawLine((int)(x), (int)(y), (int)(x+speed.getX()), (int)(y+speed.getY()));
		
//		g.setColor(Color.black);
//		g.drawRect((int)getX()-radius, (int)getY()-radius, 2*radius, 2*radius);
	}
}
