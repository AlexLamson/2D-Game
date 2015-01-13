package input;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import world.Background;
import main.Main;
import mobs.*;
import mobs.grav.GravityMob;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	public static Set<Integer> keys = new HashSet<Integer>();
	
	public static int currMouseState = 0;
	public static String[] mouseStates = {"Placing mobs", "Possessing mobs", "Altering map", "Debugging"}; //TODO DEBUG
	
	public static boolean get(String str)
	{
		int keyCode = getKeyEvent(str);
		return keys.contains(keyCode);
	}
	
	public static boolean get(char ch)
	{
		int keyCode = getKeyEvent(ch);
		return keys.contains(keyCode);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		keys.add(key);
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		keys.remove(key);
		
		
		switch(key)
		{
		case KeyEvent.VK_ESCAPE: case KeyEvent.VK_P:
			Main.isPaused = !Main.isPaused;
			break;
		case KeyEvent.VK_Q:
			Main.world.controller.polarMoveMode = !Main.world.controller.polarMoveMode;
			break;
		case KeyEvent.VK_L:
			Main.world.controllerEnabled = !Main.world.controllerEnabled;
			break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public static int getKeyEvent(char ch)
	{
		if(ch >= '0' && ch <= '9') //if number
			return KeyEvent.VK_0+ch-'0';
		else if(ch >= 'a' && ch <= 'z') //if lowercase letter
			return KeyEvent.VK_A+ch-'a';
		else if(ch >= 'A' && ch <= 'Z') //if uppercase letter
			return KeyEvent.VK_A+ch-'A';
		else if(ch == ' ') //if space
			return KeyEvent.VK_SPACE;
		else
		{
			System.err.println("cannot find KeyEvent for: "+ch);
			throw new IllegalStateException();
		}
	}
	
	public static int getKeyEvent(String str)
	{
		if(str.length() == 1)
			return getKeyEvent(str.charAt(0));
		
		switch(str.toLowerCase())
		{
		case "ctrl": case "control":
			return KeyEvent.VK_CONTROL;
		case "shift":
			return KeyEvent.VK_SHIFT;
		case "space":
			return KeyEvent.VK_SPACE;
		case "up":
			return KeyEvent.VK_UP;
		case "down":
			return KeyEvent.VK_DOWN;
		case "left":
			return KeyEvent.VK_LEFT;
		case "right":
			return KeyEvent.VK_RIGHT;
		default:
			System.err.println("cannot find KeyEvent for: "+str);
			throw new IllegalStateException();
		}
	}
	
	public void mouseClicked(MouseEvent e)
	{
		Main.mse.setLocation(e.getX()/Main.pixelSize, e.getY()/Main.pixelSize);
//		System.out.println(save);
		mouseToggle(e, true);
		mouseToggle(e, false);
	}

	public void mouseDragged(MouseEvent e)
	{
		Main.mse.setLocation(e.getX()/Main.pixelSize, e.getY()/Main.pixelSize);
//		System.out.println(save);
	}

	public void mousePressed(MouseEvent e)
	{
		mouseToggle(e, true);
		mouseChanged();
		
		boolean left = e.getButton() == MouseEvent.BUTTON1;
		boolean middle = e.getButton() == MouseEvent.BUTTON2;
		boolean right = e.getButton() == MouseEvent.BUTTON3;
		
		double worldX = Main.world.screenToWorldX(Main.mse.x);
		double worldY = Main.world.screenToWorldY(Main.mse.y);
		
		switch(currMouseState)
		{
		case 0:
			if(left)
				for(int i = 0; i < 1; i++)
					Main.world.add(new GravityMob(worldX, worldY));
			else if(right)
				Main.world.remove(worldX, worldY);
			break;
		case 1:
			if(left)
			{
				ArrayList<Thing> things = Main.world.getCollidingThings(worldX, worldY);
				if(!things.isEmpty())
					Main.world.cam = things.get(0);
			}
			break;
		case 2:
			if(left)
			{
				Background bg = Main.world.background;
				//get the pixel location
				//change pixel to black
//				Main.world.background.setColor(worldX, worldY, Color.black);
				bg.setColor(bg.getWidth()/2, bg.getHeight()/2, Color.magenta);
				bg.setColor(0, 0, Color.magenta);
			}
			break;
		case 3:
			if(left)
				Main.world.add(new DebugThing(worldX, worldY));
			break;
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		mouseToggle(e, false);
		mouseChanged();
	}

	public static void mouseToggle(MouseEvent e, boolean toggle)
	{
		if(e.getButton() == MouseEvent.BUTTON1)			//left click
			Main.isMouseLeft = toggle;
		else if(e.getButton() == MouseEvent.BUTTON2)	//middle click
			Main.isMouseMiddle = toggle;
		else if(e.getButton() == MouseEvent.BUTTON3)	//right click
			Main.isMouseRight = toggle;
	}
	
	public static void mouseChanged()
	{
		
	}
	
	public void mouseMoved(MouseEvent e)
	{
		Main.mse.setLocation(e.getX(), e.getY());
//		Viewer.car.setRot(Viewer.mse);
	}

	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if(e.getWheelRotation() < 0)			//scrolled up
		{
			currMouseState++;
			if(currMouseState > mouseStates.length-1)
				currMouseState = 0;
		}
		else if(e.getWheelRotation() > 0)		//scrolled down
		{
			currMouseState--;
			if(currMouseState < 0)
				currMouseState = mouseStates.length-1;
		}
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}
	
	public static String keysToString()
	{
		String out = "";
		for(int i : keys)
			out += i+" ";
		return out;
	}
}
