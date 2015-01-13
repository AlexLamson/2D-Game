package mobs;

import input.Listening;

import java.awt.Color;
import java.awt.Graphics;

import main.Main;
import math.Vector;

public class Player extends PolyMob
{
	public Player(int x, int y)
	{
		super(x, y, 3);
		speed = new Vector(0, 0);
		color = new Color(30, 230, 80);
//		friction = 0.1;
		
		mass = 200;
		isAffectedByGravity = false;
		collides = false;
		
		if(!collides)
			color = new Color(30, 230, 80, 100);
	}
	
	public void tick()
	{
		super.tick();
		if(mustDie)
			Main.isGameOver = true;
		
		if(Listening.get("space"))
			mass += 5;
		if(Listening.get("ctrl"))
			mass += -5;
//		Main.mq.addMessage("Mass: "+mass);
		
		if(Listening.get("space"))
			radius += 1;
		if(Listening.get("ctrl"))
			radius += -1;
//		Main.mq.addMessage("Radius: "+radius);
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
//		renderSpeed(g);
		
		g.setColor(Color.red);
		double rRot = Math.toRadians(rot.getRot());
		g.drawLine((int)pos.x, (int)pos.y, (int)(pos.x+radius*Math.cos(rRot)), (int)(pos.y+radius*Math.sin(rRot)));
		
//		g.setColor(Color.black);
//		g.drawRect((int)getX()-radius, (int)getY()-radius, 2*radius, 2*radius);
		
//		int cellX = Main.world.level.getCellX((int)getX());
//		int cellY = Main.world.level.getCellY((int)getY());
//		Main.mq.addMessage("cell: "+cellX+" , "+cellY);
	}
	
	public String toString()
	{
		return "Car"+super.toString();
	}
}
