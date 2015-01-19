package world.blocks;

import image.Texture;

import java.awt.Graphics;

public class GrassBlock extends Block
{
	public GrassBlock(int xPos, int yPos)
	{
		super(xPos, yPos, Texture.grass, 1);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	public String toString()
	{
		return "GrassBlock("+toPosString()+")";
	}
}
