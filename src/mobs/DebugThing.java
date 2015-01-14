package mobs;

import java.awt.Color;
import java.awt.Graphics;

public class DebugThing extends Thing
{
	public DebugThing(double x, double y)
	{
		super(x, y, 20, 20);
		collides = true;
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		int x = (int)pos.x;
		int y = (int)pos.y;
		
		g.setColor(Color.red);
		g.drawOval((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
		g.drawLine(-1000, y, 1000, y);
		g.drawLine(x, -1000, x, 1000);
	}
}
