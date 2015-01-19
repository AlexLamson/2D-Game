package world.blocks;

import image.Texture;

import java.awt.Graphics;

public class AirBlock extends Block
{
	public AirBlock(){ super(); } //constructor for placeholder blocks
	
	public AirBlock(int xPos, int yPos)
	{
		super(xPos, yPos, Texture.air, 1);
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
