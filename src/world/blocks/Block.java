package world.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Main;
import math.RandomColor;
import mobs.TextureThing;
import mobs.Thing;

public abstract class Block extends TextureThing
{
	public int[] id;
	public Animation anim;
	
	public Block(){ super(); } //constructor for placeholder blocks
	
	public Block(int xPos, int yPos, int[] id, int totalFrames)
	{
		super((xPos+0.5)*BlockManager.getScale(), 
				(yPos+0.5)*BlockManager.getScale(), 
				BlockManager.getScale(), 
				BlockManager.getScale(),
				id, 
				totalFrames);
		
		isStatic = true;
		isAffectedByGravity = false;
		collides = true;
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
//		g.setColor(RandomColor.nextColor());
//		g.fillRect((int)pos.x, (int)pos.y, (int)width, (int)height);
//		g.setColor(Color.blue);
//		g.drawRect((int)(pos.x-width/2), (int)(pos.y-height/2), (int)width, (int)height);
	}
	
	public String toString()
	{
		return "Block("+toPosString()+")";
	}
}
