package math;

public class Rotation
{
	private int degree;
	public static final int maxDegrees = 360;
	
	public Rotation(Rotation rot)
	{
		degree = rot.degree;
	}
	
	public Rotation(double x, double y)
	{
		setRot(getDegreeFromXY(x, y));
	}
	
	public Rotation(int degree)
	{
		this.degree = degree;
		constraintRot();
	}
	
	public Rotation()
	{
		this(0);
	}
	
	public int getRot()
	{
		constraintRot();
		return degree;
	}
	
	public void setRot(int degree)
	{
		this.degree = degree;
		constraintRot();
	}
	
	public void addRot(int degree)
	{
		setRot(this.degree + degree);
	}
	
	// make sure that rot is never over 360 degrees
	private void constraintRot()
	{
		degree = degree % maxDegrees;
		if(degree < 0)
			degree += maxDegrees;
	}
	
	public static int getDegreeFromXY(double x, double y)
	{
		return (int)(maxDegrees * Math.atan2(y,x) / (2.0*Math.PI));
	}
	
	public String toString()
	{
		return degree+"/"+maxDegrees;
	}
}
