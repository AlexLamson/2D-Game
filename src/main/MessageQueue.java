package main;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MessageQueue
{
	public int x, y;
	
	private static int maxTextWidth = 0;
	
	private ArrayList<String> strings;
	
	public MessageQueue(int x, int y)
	{
		this.x = x;
		this.y = y;
		strings = new ArrayList<String>();
	}
	
	public void addMessage(String s)
	{
		strings.add(s);
		
		Graphics g = Main.frame.getGraphics();
		g.getFont();
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(s, g);
		int textWidth = (int)rect.getWidth();
		
		if(textWidth > maxTextWidth)
			maxTextWidth = textWidth;
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(255, 255, 255, 255*3/4));
		g.fillRect(x-5, y, maxTextWidth+10, (strings.size())*20);
		
		g.setColor(Color.black);
		for(int i = 0; i < strings.size(); i++)
			g.drawString(strings.get(i), x, y+(i+1)*20-5);
	}
	
	public void clear()
	{
		strings.clear();
		maxTextWidth = 0;
	}
}
