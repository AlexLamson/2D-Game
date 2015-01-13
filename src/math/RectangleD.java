package math;

public class RectangleD
{
	private PointD pos;
	private double width, height;
	
	public RectangleD(double x, double y, double width, double height)
	{
		pos = new PointD(x, y);
		this.width = width;
		this.height = height;
	}
	
	public RectangleD(PointD pos, double width, double height)
	{
		this.pos = pos;
		this.width = width;
		this.height = height;
	}
	
	public RectangleD(RectangleD rect)
	{
		this.pos = rect.pos;
		this.width = rect.width;
		this.height = rect.height;
	}
	
	public PointD getPos()
	{
		return pos;
	}
	
	public void setPos(PointD pos)
	{
		this.pos = pos;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public boolean contains(PointD p)
	{
		if(
			p.x >= pos.x &&
			p.y >= pos.y && 
			p.x <= pos.x+width &&
			p.y <= pos.y+height
		)
			return true;
		
		return false;
	}
	
	public boolean isIntersecting(RectangleD rect)
	{
		if(
			pos.x+width < rect.pos.x ||
			pos.x > rect.pos.x+rect.width ||
			pos.y+height < rect.pos.y ||
			pos.y > rect.pos.y+rect.height
		)
			return false;
		
		return true;
	}
}
