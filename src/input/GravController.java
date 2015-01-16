package input;

import main.Main;
import mobs.Thing;

public class GravController extends AbsController
{
	public double jumpingSpeed = -5;
	public double biggerJumpingSpeed = -10;
	public double walkingSpeed = 3;
	public double runningSpeed = 8;
	
	public GravController()
	{
		polarMoveMode = false;
	}

	public void applyInput(Thing t)
	{
		t.rot.setRot(270); //?
		
		double xSpeed = 0;
		double ySpeed = 0;
		
		//TODO make all this code work
		
		if(Listening.get('w') && t.bottom.isColliding())
			ySpeed += 1;
//		if(Listening.get('s'))
//			ySpeed += -1;
		if(Listening.get('a'))
			xSpeed += -1;
		if(Listening.get('d'))
			xSpeed += 1;
		
		double xMoveSpeed = 0;
		if(xSpeed != 0)
		{
			xMoveSpeed = walkingSpeed*xSpeed;
			if(Listening.get("shift"))
				xMoveSpeed = runningSpeed*xSpeed;
		}
		
		double yMoveSpeed = 0;
		if(ySpeed != 0)
		{
			yMoveSpeed = jumpingSpeed*ySpeed;
			if(Listening.get("shift"))
				yMoveSpeed = biggerJumpingSpeed*ySpeed;
		}
		
		t.speed.addXY(t.friction*t.speed.getX(), 0);
		
		Main.mq.addMessage("xSpeed: "+xSpeed+" ySpeed: "+ySpeed);
		
		t.speed.addXY(xMoveSpeed, yMoveSpeed);
	}
}
