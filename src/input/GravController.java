package input;

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
		t.rot.setRot(270); //makes triangle always point up
		
		double xSpeed = 0;
		double ySpeed = 0;
		
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
		
		t.speed.addXY(xMoveSpeed, yMoveSpeed);
	}
}
