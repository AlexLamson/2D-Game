package input;

import math.Vector;
import mobs.Thing;

public class Controller extends AbsController
{
	public double slowForwardSpeed = 4; // speed added to the car every tick
	public double fastForwardSpeed = 8; // speed added to the car every tick
	public int slowTurningSpeed = 8; // degrees that the car can turn in one tick
	public int fastTurningSpeed = 16; // degrees that the car can turn in one tick
	
	public Controller()
	{
		polarMoveMode = false;
	}
	
	public void applyInput(Thing t)
	{
		double xSpeed = 0;
		double ySpeed = 0;
		
		if(Listening.get('w'))
			ySpeed += 1;
		if(Listening.get('s'))
			ySpeed += -1;
		if(Listening.get('a'))
			xSpeed += -1;
		if(Listening.get('d'))
			xSpeed += 1;
		
		double forwardSpeed = 0;
		if(ySpeed != 0)
		{
			forwardSpeed = slowForwardSpeed*ySpeed;
			if(Listening.get("shift"))
				forwardSpeed = fastForwardSpeed*ySpeed;
		}
		
		double turningSpeed = 0;
		if(xSpeed != 0)
		{
			turningSpeed = slowTurningSpeed*xSpeed;
			if(Listening.get("shift"))
				turningSpeed = fastTurningSpeed*xSpeed;
		}
		
		double moveSpeed = 0;
		if(xSpeed != 0 || ySpeed != 0)
		{
			moveSpeed = slowForwardSpeed;
			if(Listening.get("shift"))
				moveSpeed = fastForwardSpeed;
		}
		
//		Main.mq.addMessage("moveSpeed: "+moveSpeed);
		
		if(moveSpeed != 0)
		{
			if(polarMoveMode)
			{
				Vector rotVec = new Vector(t.rot.getRot(), forwardSpeed);
				t.speed.addXY(rotVec.getX(), rotVec.getY());
				
				t.rot.addRot((int)(turningSpeed));
			}
			else
			{
				Vector moveVec = new Vector(xSpeed, -ySpeed, true);
				t.rot.setRot(moveVec.getRot());
				
				moveVec.setRotMag(t.rot.getRot(), moveSpeed);
				t.speed.addXY(moveVec.getX(), moveVec.getY());
			}
		}
	}
}
