package world.blocks;

import java.awt.Graphics;

import main.Main;
import main.Timer;
import mobs.TextureThing;
import mobs.Thing;

public class Animation
{
	private TextureThing t;
	private Timer timer;
	
	private int currFrame;
	private int maxFrame;
	
	public Animation(TextureThing t, int totalFrames)
	{
		currFrame = 0;
		this.maxFrame = totalFrames-1;
		this.t = t;
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
				(int)(t.pos.x - t.width/2), 
				(int)(t.pos.y - t.height/2), 
				(int)(t.pos.x + t.width/2), 
				(int)(t.pos.y + t.height/2), 
				(t.id[0] + currFrame)*Texture.tileSize, 
				(t.id[1])*Texture.tileSize, 
				(t.id[0] + currFrame + 1)*Texture.tileSize, 
				(t.id[1] + 1)*Texture.tileSize, 
				null);
	}
}
