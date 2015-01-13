package math;

public class PointD
{
	public double x, y;
	
	public PointD(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public PointD(PointD p)
	{
		this(p.x, p.y);
	}
	
	public double getDistance(PointD p)
	{
		return Math.sqrt((x-p.x)*(x-p.x) + (y-p.y)*(y-p.y));
	}
	
	public String toString()
	{
		return x+", "+y;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof PointD)
		{
			PointD p = ((PointD)obj);
			return p.x == x && p.y == y;
		}
		return false;
	}
}
