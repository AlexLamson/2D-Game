package math;

import java.util.ArrayList;

public class Array2D<T>
{
	private ArrayList<ArrayList<T>> arr = new ArrayList<ArrayList<T>>();
	private T placeholder;
	
	public Array2D(T placeholder)
	{
		this.placeholder = placeholder;
		ArrayList<T> b = new ArrayList<T>();
		arr.add(b);
	}
	
	public int getWidth()
	{
		return arr.size();
	}
	
	public int getHeight()
	{
		return arr.get(0).size();
	}
	
	public void setSize(int width, int height)
	{
		int currWidth = getWidth();
		int currHeight = getHeight();
		
		//update the current column heights
		if(height > currHeight)
		{
			for(int x = 0; x < currWidth; x++)
			{
				ArrayList<T> col = arr.get(x);
				for(int y = currHeight; y < height; y++)
					col.add(placeholder);
			}
		}
		else if(height < currHeight)
		{
			for(int x = 0; x < width; x++)
			{
				for(int y = currHeight-1; y >= height; y--)
					arr.get(x).remove(y);
			}
		}
		
		//add or remove columns
		if(width > currWidth)
		{
			for(int x = currWidth; x < width; x++)
			{
				ArrayList<T> newCol = new ArrayList<T>();
				for(int y = 0; y < height; y++)
					newCol.add(placeholder);
				arr.add(newCol);
			}
		}
		else if(width < currWidth)
		{
			for(int x = currWidth-1; x >= width; x--)
				arr.remove(x);
		}
	}
	
	public void set(int x, int y, T t)
	{
		arr.get(x).set(y, t);
	}
	
	public void add(int x, int y, T t)
	{
		int newWidth = getWidth();
		int newHeight = getHeight();
		
		if(x >= getWidth())
			newWidth = x+1;
		if(y >= getHeight())
			newHeight = y+1;
		
		setSize(newWidth, newHeight);
		
		set(x, y, t);
	}
	
	public T get(int x, int y)
	{
		return arr.get(x).get(y);
	}
	
	public void remove(int x, int y)
	{
		arr.get(x).set(y, placeholder);
	}
	
	public boolean isEmpty(int x, int y)
	{
		return get(x, y) == placeholder;
	}
	
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> output = new ArrayList<T>();
		
		for(int y = 0; y < getHeight(); y++)
			for(int x = 0; x < getWidth(); x++)
				output.add(get(x, y));
		
		return output;
	}
	
	public String toString()
	{
		String out = "";
		for(int y = 0; y < getHeight(); y++)
		{
			for(int x = 0; x < getWidth(); x++)
			{
				out += get(x, y).toString() + " ";
			}
			out += "\n";
		}
		
		return out;
	}
	
	public static void main(String[] args)
	{
		Array2D<String> a = new Array2D<String>(new String("A"));
		a.setSize(2, 3);
		
		System.out.println(a.toString());
		
		for(int x = 0; x < a.getWidth(); x++)
		{
			for(int y = 0; y < a.getHeight(); y++)
			{
				a.set(x, y, new String("B"));
			}
		}
		
		System.out.println(a.toString());
		
		a.add(3, 5, new String("C"));
		
		System.out.println(a.toString());
		
		a.setSize(2, 4);
		
		System.out.println(a.toString());
	}
}
