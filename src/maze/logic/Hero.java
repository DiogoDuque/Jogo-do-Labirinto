package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Hero extends General {

	Hero(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'H',maze);
	}
}