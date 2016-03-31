package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Sword extends General{

	public Sword(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'E',maze);
	}
	
}
