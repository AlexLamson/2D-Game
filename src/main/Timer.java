package main;


public class Timer
{
	private int currTick, maxTick;
	
	public Timer(int maxTick)
	{
		reset();
		this.maxTick = maxTick;
	}
	
	public void tick()
	{
		currTick++;
		if(currTick > maxTick)
			reset();
	}
	
	public boolean atMax()
	{
		return currTick == maxTick;
	}
	
	public void reset()
	{
		currTick = 0;
	}
}
