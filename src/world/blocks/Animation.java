package world.blocks;

import java.awt.Graphics;

import main.Timer;

public class Animation
{
	private Block b;
	private Timer timer;
	
	private int currFrame = 0;
	private int maxFrame = 2;
	
	public Animation(Block b, int maxFrames)
	{
		this.b = b;
		timer = new Timer(5);
	}
	
	public void tick()
	{
		timer.tick();
		if(timer.atMax())
			incrFrame();
	}
	
	public void incrFrame()
	{
		currFrame++;
		if(currFrame > maxFrame)
			currFrame = 0;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Texture.tiles, 
				(int)(b.pos.x), 
				(int)(b.pos.y), 
				(int)(b.pos.x + b.width), 
				(int)(b.pos.y + b.height),
				(b.id[0] + currFrame)*Texture.tileSize, 
				(b.id[1])*Texture.tileSize,
				(b.id[0] + currFrame + 1)*Texture.tileSize, 
				(b.id[1] + 1)*Texture.tileSize, 
				null);
	}
}
