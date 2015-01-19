package image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageGenerator
{
	public static BufferedImage randomImage(int width, int height)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				int rgb = Color.HSBtoRGB((float)Math.random(), 0.7f, 1.0f);
				img.setRGB(x, y, rgb);
				
//				int r = (int)(256*Math.random());
//				int g = (int)(256*Math.random());
//				int b = (int)(256*Math.random());
//				img.setRGB(x, y, new Color(r, g, b).getRGB());
			}
		}
		
		return img;
	}
}
