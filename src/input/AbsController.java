package input;

import main.Main;
import mobs.Thing;

public abstract class AbsController
{
	public boolean polarMoveMode = true;
	
	public abstract void applyInput(Thing t);
	
	public String getMoveMode()
	{
		if(Main.world.controller.polarMoveMode)
			return "polar";
		return "arcade";
	}
}
