package image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Texture
{
	public static int tileSize = 20;
	
	public static final int[] air = {3, 0};
	public static final int[] dirt = {0, 0};
	public static final int[] grass = {1, 0};
	
	public static BufferedImage tiles;
	
	public static void loadTextures()
	{
		try
		{
			Texture.tiles = ImageIO.read(new File("res/tileset_terrian.png"));
		}catch(Exception e){ System.err.println("Terrian image load failed!"); }
	}
}
