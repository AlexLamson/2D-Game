package world.blocks;

import java.awt.Graphics;

public class AirBlock extends Block
{
	public AirBlock(){ super(); } //constructor for placeholder blocks
	
	public AirBlock(int xPos, int yPos)
	{
		super(Texture.air, 1, xPos, yPos);
		collides = false;
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
		return "AirBlock("+toPosString()+")";
	}
}
