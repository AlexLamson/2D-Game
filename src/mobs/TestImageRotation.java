package mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import math.Rotation;

public class TestImageRotation extends ImageThing
{
	public Rotation rot;
	
	public TestImageRotation(double x, double y, BufferedImage img)
	{
		super(x, y, img);
		rot = new Rotation(0);
	}
	
	public TestImageRotation(double x, double y, String path) throws IOException
	{
		super(x, y, path);
		rot = new Rotation(0);
	}
	
	public void tick()
	{
		super.tick();
		
		rot.addRot(1); //TODO DEBUG
	}
	
	public void render(Graphics g)
	{
		double radians = Math.toRadians(rot.getRot());
		AffineTransform tx = AffineTransform.getRotateInstance(radians, width/2, height/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(getImage(), 
				op, 
				(int)(pos.x-width/2), 
				(int)(pos.y-height/2));
	}
}
