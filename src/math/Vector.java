package math;

public class Vector
{
//	private static final double minMag = 0.2; //if |mag| < minMag, set mag to 0
	private static final double minMag = 2; //if |mag| < minMag, set mag to 0
	private double x, y;
	private Rotation rot;
	private double mag;
	
	public Vector()
	{
		this(0, 0);
	}
	
	public Vector(Vector vec)
	{
		x = vec.x;
		y = vec.y;
		rot = new Rotation(vec.rot);
		mag = vec.mag;
	}
	
	public Vector(int rot, double mag)
	{
		this.rot = new Rotation(rot);
		this.mag = mag;
		calcCartesian();
	}
	
	public Vector(double x, double y, boolean placeholder)
	{
		this.x = x;
		this.y = y;
		calcPolar();
	}
	
	public void setXY(double x, double y)
	{
		this.x = x;
		this.y = y;
		calcPolar();
	}
	
	public void addXY(double x, double y)
	{
		if(x == 0 && y == 0)
			return;
		setXY(this.x + x, this.y + y);
	}
	
	public void setRotMag(int degree, double mag)
	{
		rot.setRot(degree);
		this.mag = mag;
		calcCartesian();
	}
	
	public void addRotMag(int degree, double mag)
	{
		if(degree == 0 && mag == 0)
			return;
		
		setRotMag(rot.getRot() + degree, this.mag + mag);
	}
	
	public void calcMagnitude()
	{
		mag = Math.sqrt(x*x + y*y);
	}
	
	public void calcPolar()
	{
		rot = new Rotation(x, y);
		calcMagnitude();
	}
	
	public void calcCartesian()
	{
		if(mag < minMag && mag > -minMag)
			mag = 0;
		x = mag * Math.cos(Math.toRadians(rot.getRot()));
		y = mag * Math.sin(Math.toRadians(rot.getRot()));
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public int getRot()
	{
		return rot.getRot();
	}
	
	public double getMag()
	{
		return mag;
	}
	
	public static Vector randomVector()
	{
		int rot = (int)(Math.random() * 360);
		double mag = Math.random() * 1.0;
		return new Vector(rot, mag);
	}
	
	public String toString()
	{
		double shortX = (int)(x*1000)/1000.0;
		double shortY = (int)(y*1000)/1000.0;
		
		return "("+shortX+" , "+shortY+")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Vector)
		{
			Vector v = (Vector)obj;
			return (v.rot == this.rot && (v.mag > this.mag-minMag && v.mag < this.mag+minMag));
		}
		return false;
	}
}
