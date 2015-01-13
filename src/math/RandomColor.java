package math;

import java.awt.Color;

public class RandomColor
{
	public static int numer = 1;
	public static int denom = 2;
	public static boolean subtracting = false;
	
	public static double getNextBinNum()
	{
		double out = 1.0*numer/denom;
		numer += 2;
		if(numer >= denom)
		{
			numer = 1;
			denom *= 2;
		}
		return out;
	}
	
	public static Color nextColor()
	{
		double binNum = getNextBinNum();
		if(subtracting)
			binNum = 1.0-binNum;
		
		float hue = (float)(binNum);
//		float hue = (float)((int)(20*Math.random())/20.0f);
		float sat = 1f;
		float val = 0.7f;
		return new Color(Color.HSBtoRGB(hue, sat, val));
	}
	
	public static Color nextTransparentColor()
	{
		Color c = nextColor();
		c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 256/2);
		return c;
	}
	
	public static void reset()
	{
		numer = 1;
		denom = 2;
	}
	
	public static Color avgColor(Color c1, Color c2)
	{
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();
		
		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();
		
		int rAvg = (r1+r2)/2;
		int gAvg = (g1+g2)/2;
		int bAvg = (b1+b2)/2;
		
		Color avgColor = new Color(rAvg, gAvg, bAvg);
		
		return avgColor;
	}
}
