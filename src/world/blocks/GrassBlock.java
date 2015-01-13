package world.blocks;

import java.awt.Graphics;

public class GrassBlock extends Block
{
	public GrassBlock(int xPos, int yPos)
	{
		super(Texture.grass, 1, xPos, yPos);
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
