package mobs.grav;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import math.Rotation;
import math.Vector;
import mobs.Thing;

public class PolyMob extends Thing
{
	private Polygon poly;
	public Color color;
	
	public PolyMob(double x, double y, double width, double height, int nPoints)
	{
		super(x, y, width, height);
		setPoly(makePolygon(nPoints));
		
		speed.setRotMag((int)(Math.random()*Rotation.maxDegrees), 5 + Math.random()*10);
//		speed.setRotMag(0, 0);
		color = new Color(140, 0, 200);
	}
	
	public Polygon makePolygon(int nPoints)
	{
		int[] xPoints = new int[nPoints];
		int[] yPoints = new int[nPoints];
		for(int i = 0; i < nPoints; i++)
		{
			Vector v = new Vector(i*Rotation.maxDegrees/nPoints + rot.getRot(), width/2);
			xPoints[i] = (int)v.getX();
			yPoints[i] = (int)v.getY();
		}
		return new Polygon(xPoints, yPoints, nPoints);
	}
	
	public Polygon getPolygon()
	{
		setPoly(makePolygon(getPoly().npoints)); //get rotated polygon
		
		int[] newXPoints = new int[getPoly().xpoints.length];
		for(int i = 0; i < getPoly().xpoints.length; i++)
			newXPoints[i] = (int)(pos.x+getPoly().xpoints[i]);
		
		int[] newYPoints = new int[getPoly().ypoints.length];
		for(int i = 0; i < getPoly().ypoints.length; i++)
			newYPoints[i] = (int)(pos.y+getPoly().ypoints[i]);
		
		return new Polygon(newXPoints, newYPoints, getPoly().npoints);
	}
	
	public Polygon getPoly()
	{
		return poly;
	}

	public void setPoly(Polygon poly)
	{
		this.poly = poly;
	}

	public void tick()
	{
		super.tick();
//		rot.addRot(5);
	}
	
	public void render(Graphics g)
	{
		super.render(g);

		Polygon p = getPolygon();
		
		g.setColor(color);
		g.fillPolygon(p);
		
		g.setColor(Color.black);
		g.drawPolygon(p);
	}
}
