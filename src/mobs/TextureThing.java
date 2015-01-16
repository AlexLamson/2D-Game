package mobs;

import java.awt.Color;
import java.awt.Graphics;

import world.blocks.Animation;
import world.blocks.BlockManager;
import math.Rotation;

public class TextureThing extends Thing
{
	public int[] id;
	public Animation anim;
	
	public TextureThing(){ super(); } //constructor for placeholder things
	
	public TextureThing(double x, double y, double width, double height, int[] id, int totalFrames)
	{
		super(x, y, width, height);
		
		this.id = id;
		anim = new Animation(this, totalFrames);
	}
	
	public void tick()
	{
		super.tick();
		
		anim.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		anim.render(g);
	}
	
	public String toString()
	{
		return "TextureThing("+toPosString()+")";
	}
}
