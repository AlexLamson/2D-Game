package world.blocks;

import java.awt.Graphics;

public class AirBlock extends Block
{
	public AirBlock()
	{
		super();
	}
	
	public AirBlock(int xPos, int yPos)
	{
		super(Texture.air, 1, xPos, yPos);
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
