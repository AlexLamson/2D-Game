package math;

import java.util.ArrayList;

public class Mod
{
	//usage: mod(-25, 1024) = 999
	public static int mod(int num1, int num2)
	{
		num1 = num1 % num2;
		if(num1 < 0)
			num1 += num2;
		return num1;
	}
	
	public static double mod(double num1, double num2)
	{
		num1 = num1 % num2;
		if(num1 < 0)
			num1 += num2;
		return num1;
	}
	
	//returns all positions of Thing given world width & screen bounds
	public static ArrayList<Integer> getPostions(int pos, int width, int lowerBound, int upperBound)
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		int modVal = mod(pos, width);
		int modLower = mod(lowerBound, width);
		
		if(modLower <= modVal)
			lowerBound = (int)(width*Math.floor(1.0*lowerBound/width));
		else if(modLower > modVal)
			lowerBound = (int)(width*Math.ceil(1.0*lowerBound/width));
		
		for(int i = lowerBound; i+modVal <= upperBound; i+=width)
			arr.add( i + modVal );
		
		return arr;
	}
	
	//returns all positions of Thing given world width & screen bounds
	public static ArrayList<Double> getPostions(double pos, double width, double lowerBound, double upperBound)
	{
		ArrayList<Double> arr = new ArrayList<Double>();
		
		double modVal = mod(pos, width);
		
		lowerBound = width*1.0*lowerBound/width;
		
		for(double i = lowerBound; i+modVal <= upperBound; i+=width)
			arr.add( i + modVal );
		
		return arr;
	}
}
