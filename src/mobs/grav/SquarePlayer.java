package mobs.grav;

import input.Listening;

import java.awt.Color;
import java.awt.Graphics;

import main.Main;
import math.Vector;

public class SquarePlayer extends PolyMob
{
	public SquarePlayer(int x, int y)
	{
		super(x, y, 40, 40, 3);
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
		
		updateSensors();
		
		if(Listening.get("space"))
			mass += 5;
		if(Listening.get("ctrl"))
			mass += -5;
//		Main.mq.addMessage("Mass: "+mass);
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		renderSpeed(g);
		renderSensors(g);
		
		g.setColor(Color.red);
		double rRot = Math.toRadians(rot.getRot());
		g.drawLine((int)pos.x, (int)pos.y, (int)(pos.x+width/2*Math.cos(rRot)), (int)(pos.y+height/2*Math.sin(rRot)));
		
		g.setColor(Color.black);
		g.drawRect((int)(pos.x-width/2), (int)(pos.y-height/2), (int)width, (int)height);
		
//		int cellX = Main.world.level.getCellX((int)getX());
//		int cellY = Main.world.level.getCellY((int)getY());
//		Main.mq.addMessage("cell: "+cellX+" , "+cellY);
	}
	
	public String toString()
	{
		return "Car"+super.toString();
	}
}
