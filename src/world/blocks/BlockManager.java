package world.blocks;

import java.awt.Graphics;

import main.Main;
import math.Array2D;

public class BlockManager
{
	public Array2D<Block> blocks = new Array2D<Block>(new AirBlock());
	
	public BlockManager()
	{
		Texture.loadTextures();
		
//		for(int y = 0; y < 20; y++)
//		{
//			for(int x = 0; x < 20; x++)
//			{
//				blocks.add(x, y, makeBlock(x, y)); //TODO DEBUG
//			}
//		}
	}
	
	public static Block makeBlock(int x, int y)
	{
//		x += getScale()/2;
//		y += getScale()/2;
		
		int groundLevel = 11;
		
		if(y < groundLevel)
			return new AirBlock(x, y);
		else if(y == groundLevel)
			return new GrassBlock(x, y);
		else
			return new DirtBlock(x, y);
	}
	
	public static double getScale()
	{
		return 50;
	}
	
	public void tick()
	{
		for(int y = 0; y < blocks.getHeight(); y++)
		{
			for(int x = 0; x < blocks.getWidth(); x++)
			{
				blocks.get(x, y).tick();
			}
		}
	}
	
	public void render(Graphics g)
	{
		for(int y = 0; y < blocks.getHeight(); y++)
		{
			for(int x = 0; x < blocks.getWidth(); x++)
			{
//				blocks.get(x, y).render(g, x, y);
				
				Main.world.renderThing(g, blocks.get(x, y));
			}
		}
	}
}
