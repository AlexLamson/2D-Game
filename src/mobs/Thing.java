package mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import world.World;
import main.Main;
import math.RectangleD;
import math.Mod;
import math.PointD;
import math.Rotation;
import math.Sensor;
import math.Vector;

public abstract class Thing
{
	public boolean isPlaceholder = false;
	public boolean mustDie = false; //will be removed from World's ArrayList if true
	public boolean isStatic = true; //won't move if true
	
	public boolean isAffectedByGravity = true;
	public static double gravity = 2.0;
	public double mass;
	
	public PointD pos; //position
	public double width, height;
	public Vector speed;
	public Rotation rot; //orientation
	public double friction = 0; //speed += -friction*speed
	
	public boolean collides = false; //won't intersect other Things in the World
	public boolean isColliding = false; //is touching another Thing
	
	public Sensor left, right, top, bottom;
	public boolean usesSensors = false;
	
	public Thing()
	{
		isPlaceholder = true;
		isStatic = true;
		setMass(1.0);
		pos = new PointD(0, 0);
		speed = new Vector(0, 0);
		rot = new Rotation(0);
		width = 1;
		height = 1;
		usesSensors = false;
	}
	
	public Thing(double x, double y, double width, double height)
	{
		this();
		isPlaceholder = false;
		isStatic = false;
		pos = new PointD(x, y);
		this.width = width;
		this.height = height;
		
		left = new Sensor(this, -width/2-10, -height/2, 10, height);
		right = new Sensor(this, width/2, -height/2, 10, height);
		top = new Sensor(this, -width/2, -height/2-10, width, 10);
		bottom = new Sensor(this, -width/2, height/2, width, 10);
		usesSensors = false;
	}
	
	public boolean isVisible()
	{
		int screenMinX = Main.world.worldToScreenX(pos.x-width/2);
		int screenMaxX = Main.world.worldToScreenX(pos.x+width/2);
		int screenMinY = Main.world.worldToScreenY(pos.y-height/2);
		int screenMaxY = Main.world.worldToScreenY(pos.y+height/2);
		
		if(screenMinX > Main.pixel.width || screenMaxX < 0)
			return false;
		
		if(screenMinY > Main.pixel.height|| screenMaxY < 0)
			return false;
		
		return true;
	}
	
	public boolean isCollidingWith(Thing t)
	{
		return this.getBounds().isIntersecting(t.getBounds());
	}
	
	public boolean isCollidingWith(PointD p)
	{
		double left = pos.x-width/2;
		double right = pos.x+width/2;
		double top = pos.y-height/2;
		double bottom = pos.y+height/2;
		
		return !(p.x >= left && p.x <= right && p.y >= top && p.y <= bottom);
	}
	
	public RectangleD getBounds()
	{
//		return new RectangleD(pos, width, height);
		double x = pos.x-width/2;
		double y = pos.y-height/2;
		return new RectangleD(x, y, width, height);
	}
	
	public double getMass()
	{
		return mass;
	}

	public void setMass(double mass)
	{
		this.mass = mass;
	}
	
	public void addXY(double x, double y)
	{
		setXY(pos.x + x, pos.y + y);
	}
	
	public void setXY(double x, double y)
	{
		pos.x = x;
		pos.y = y;
	}
	
	public double getGravityOn(int i)
	{
		return getGravityOn(Main.world.things.get(i));
	}
	
	public double getGravityOn(Thing t)
	{
		double dist = getDistanceFrom(t);
		double G = Main.world.G;
		double m1 = t.mass;
		double m2 = this.mass;
		
		if(dist < 1)
			dist = 1;
		
		double gForce = G*m1*m2/(dist*dist);
		
		if(gForce > 100000)
			gForce = 100000;
		
		return gForce;
	}
	
	public double getDistanceFrom(double x, double y)
	{
		double xDist = x-pos.x;
		
		if(Main.world.loopingEdges && xDist > Main.world.width/2)
			xDist = Main.world.width - xDist;
		
		double yDist = y-pos.y;
		if(Main.world.loopingEdges && yDist > Main.world.height/2)
			yDist = Main.world.height - yDist;
		
		double distance = Math.sqrt(xDist*xDist + yDist*yDist);
		return distance;
	}
	
	public double getDistanceFrom(Thing t)
	{
		return getDistanceFrom((int)t.pos.x, (int)t.pos.y);
	}
	
	public void constrainToWorldBounds()
	{
//		Main.mq.addMessage(0+" < "+(pos.x)+" < "+(Main.world.width));
//		Main.mq.addMessage(0+" < "+(pos.y)+" < "+(Main.world.height));
		
		double newX = speed.getX();
		double newY = speed.getY();
		
		if(pos.y+height/2 > Main.world.height)
		{
			pos.y = Main.world.height-height/2;
			newY = 0;
		}
		
		if(pos.y-height/2 < 0)
		{
			pos.y = 0+height/2;
			newY = 0;
		}
		
		if(pos.x+width/2 > Main.world.width)
		{
			pos.x = Main.world.width-width/2;
			newX = 0;
		}
		
		if(pos.x-width/2 < 0)
		{
			pos.x = 0+width/2;
			newX = 0;
		}
		
//		speed.setXY(newX, newY);
	}
	
	public void constrainSpeedToWorldBounds()
	{
		double newX = speed.getX();
		double newY = speed.getY();
		
		if(pos.y+height/2+speed.getY() == Main.world.height)
			newY = 0;
		
		if(pos.y-height/2-speed.getY() == 0)
			newY = 0;
		
		if(pos.x+width/2+speed.getX() == Main.world.width)
			newX = 0;
		
		if(pos.x-width/2-speed.getX() == 0)
			newX = 0;
		
		speed.setXY(newX, newY);
	}
	
	public PointD getNextPos()
	{
		return new PointD(pos.x+speed.getX(), pos.y+speed.getY());
	}
	
	public void updateSensors()
	{
		left.tick();
		right.tick();
		top.tick();
		bottom.tick();
	}
	
	public void tick()
	{
		isColliding = false;
		
		//don't move if static
		if(isStatic && speed.getMag() != 0)
			speed = new Vector(0, 0);
		
		//add gravity vector to all objects
		if(!isStatic && Main.world.useGlobalGravity)
			speed.addXY( Main.world.globalGravity.getX(), Main.world.globalGravity.getY() );
		
		//give objects gravitation pull
		if(!isStatic && Main.world.useMassGravity && isAffectedByGravity)
		{
			double netX = 0, netY = 0;
			for(int i = 0; i < Main.world.things.size(); i++)
			{
				if(Main.world.things.get(i) == this)
					continue;
				
				PointD tPos = Main.world.things.get(i).pos;
				
				double mag = getGravityOn(Main.world.things.get(i));
				
				double accel = 0;
				if(this.mass != 0)
					accel = mag/this.mass;
				
				int rot = new Vector(tPos.x-this.pos.x, tPos.y-this.pos.y, true).getRot();
				
				Vector v = new Vector(rot, accel);
				netX += v.getX();
				netY += v.getY();
			}
			
			speed.addXY(netX, netY);
		}
		
		//check if sensors are colliding
		if(usesSensors && collides)
			updateSensors();
		
		//prevent this Thing from moving out of  the world's bounds
		if(!Main.world.loopingEdges)
			constrainToWorldBounds();
		
		//stop movement after hitting world edge
		if(!Main.world.loopingEdges)
			constrainSpeedToWorldBounds();
		
		//enforce a global speed limit
		if(speed.getMag() > World.globalSpeedLimit)
			speed.setRotMag(speed.getRot(), World.globalSpeedLimit);
		
		//apply friction
		if(!isStatic)
			speed.addRotMag(0, -friction*speed.getMag());
		
		//actually move the Thing
		if(!isStatic)
		{
			pos.x += speed.getX();
			pos.y += speed.getY();
		}
		
		//make the edges of the window loop into each other
		if(Main.world.loopingEdges)
		{
			pos.x = Mod.mod(pos.x, Main.world.width);
			pos.y = Mod.mod(pos.y, Main.world.height);
		}
		else //or constrain position to within world bounds
		{
			//TODO the code in this if statement seems to be doing 
			//more work constraining that the constrainToWorldBounds method
			
			if(pos.x > Main.world.width)
				pos.x = Main.world.width;
			else if(pos.x < 0)
				pos.x = 0;
			
			if(pos.y > Main.world.height)
				pos.y = Main.world.height;
			if(pos.y < 0)
				pos.y = 0;
		}
		
		//prevent Things from intersecting with each other
		if(collides)
		{
			if(!Main.world.loopingEdges)
			{
				//bounce off walls
			}
			
			//check for collision with another other objects (excluding world edges)
			ArrayList<Thing> collidingThings = Main.world.getCollidingThings(this);
			
			if(collidingThings.size() > 0)
				isColliding = true;
			
			for(int i = 0; i < collidingThings.size(); i++)
			{
				Thing t = collidingThings.get(i);
				if(!t.collides)
					continue;
				
				if(t.isStatic)
				{
					
				}
				else
				{
					//check if they are moving away from each other
//					double oldDist = this.getDistanceFrom(t);
//					double newDist = this.getNextPos().getDistance(t.getNextPos());
//					
//					if(newDist <= oldDist)
					{
						
						//for now, assume that each collision is separate
						double speedX1 = (this.speed.getX() * (this.mass - t.mass) + (2 * t.mass * t.speed.getX())) / (this.mass + t.mass);
						double speedY1 = (this.speed.getY() * (this.mass - t.mass) + (2 * t.mass * t.speed.getY())) / (this.mass + t.mass);
						double speedX2 = (t.speed.getX() * (t.mass - this.mass) + (2 * this.mass * this.speed.getX())) / (this.mass + t.mass);
						double speedY2 = (t.speed.getY() * (t.mass - this.mass) + (2 * this.mass * this.speed.getY())) / (this.mass + t.mass);
						
						this.speed.setXY(speedX1, speedY1);
						t.speed.setXY(speedX2, speedY2);
					}
				}
			}
		}
		
		
	}
	
	//TODO add arguments for color and a boolean for a normalized vector
	public void renderSpeed(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine((int)(pos.x), (int)(pos.y), (int)(pos.x+speed.getX()), (int)(pos.y+speed.getY()));
	}
	
	public void renderSensors(Graphics g)
	{
		left.render(g);
		right.render(g);
		top.render(g);
		bottom.render(g);
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public String toPosString()
	{
		return ((int)(pos.x*10)/10) + ", " + (int)(pos.y*10)/10;
	}
	
	public String toString()
	{
		return "Thing("+toPosString()+")";
	}
}
