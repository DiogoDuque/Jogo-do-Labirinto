/**
 * Esta classe é usada para representar as Paredes presente no labirinto.
 */

package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Wall extends General{

	public Wall(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'X',maze);
	}
	
}