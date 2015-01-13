package input;

import mobs.Thing;

public class GravController extends AbsController
{
	public double jumpingSpeed = -30;
	public double biggerJumpingSpeed = -60;
	public double walkingSpeed = 3;
	public double runningSpeed = 8;
	public boolean isJumping = false;
	
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
		
//		if(Listening.w && (t.collidingBottom() || t.collidingLeft() || t.collidingRight()))
//			ySpeed += 1;
//		if(Listening.s)
//			ySpeed += -1;
//		if(Listening.a)
//			xSpeed += -1;
//		if(Listening.d)
//			xSpeed += 1;
//		
//		double xMoveSpeed = 0;
//		if(xSpeed != 0)
//		{
//			xMoveSpeed = walkingSpeed*xSpeed;
//			if(Listening.shift)
//				xMoveSpeed = runningSpeed*xSpeed;
//		}
//		
//		double yMoveSpeed = 0;
//		if(ySpeed != 0)
//		{
//			yMoveSpeed = jumpingSpeed*ySpeed;
//			if(Listening.shift)
//				yMoveSpeed = biggerJumpingSpeed*ySpeed;
//		}
//		
//		t.speed.addXY(t.friction*t.speed.getX(), 0);
//		
//		Main.mq.addMessage("xSpeed: "+xSpeed+" ySpeed: "+ySpeed);
//		
//		t.speed.addXY(xMoveSpeed, yMoveSpeed);
	}
}
