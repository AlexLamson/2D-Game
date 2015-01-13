package world.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Main;
import math.RandomColor;
import mobs.Thing;

public abstract class Block extends Thing
{
	public int[] id;
	public Animation anim;
	
	public Block(){ super(); } //constructor for placeholder blocks
	
	public Block(int[] id, int maxFrames, int xPos, int yPos)
	{
		super(xPos*BlockManager.getScale(), yPos*BlockManager.getScale(), 
				BlockManager.getScale(), BlockManager.getScale());
		
		this.id = id;
		anim = new Animation(this, maxFrames);
		
		isStatic = true;
		isAffectedByGravity = false;
		collides = true;
	}
	
	public void tick()
	{
		super.tick();
		anim.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
//		g.setColor(RandomColor.nextColor());
//		g.fillRect((int)pos.x, (int)pos.y, (int)width, (int)height);
//		g.drawRect((int)pos.x, (int)pos.y, (int)width, (int)height);
		
		anim.render(g);
	}
	
	public String toString()
	{
		return "Block("+toPosString()+")";
	}
}
