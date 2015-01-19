package world.blocks;

import image.Texture;

import java.awt.Graphics;

public class DirtBlock extends Block
{
	public DirtBlock(int xPos, int yPos)
	{
		super(xPos, yPos, Texture.dirt, 1);
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
		return "DirtBlock("+toPosString()+")";
	}
}
