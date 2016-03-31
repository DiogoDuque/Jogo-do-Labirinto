package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Exit extends General {
	
	Exit(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'S',maze);
	}
	
}
