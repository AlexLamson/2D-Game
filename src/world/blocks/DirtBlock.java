package world.blocks;

import java.awt.Graphics;

public class DirtBlock extends Block
{
	public DirtBlock(int xPos, int yPos)
	{
		super(Texture.dirt, 1, xPos, yPos);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
}
