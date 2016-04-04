/**
 * Esta classe é usada para representar a Saida presente no labirinto.
 */

package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Exit extends General {
	
	public Exit(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'S',maze);
	}
	
}
