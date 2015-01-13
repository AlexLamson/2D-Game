package mobs;

import java.awt.Color;
import java.awt.Graphics;

import math.Rotation;

public class Mob extends CircleThing
{
	public Color mobColor = new Color(255, 0, 0);
	
	public Mob(double x, double y)
	{
		super(x, y);
		
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
		//turn green if colliding
		if(isColliding)
			color = Color.green;
		else
			color = mobColor;
		
		super.render(g);
	}
}
