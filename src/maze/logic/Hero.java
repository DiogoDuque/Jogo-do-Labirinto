/**
 * Esta classe é usada para representar o Heroi presente no labirinto.
 */

package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class Hero extends General {

	public Hero(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'H',maze);
	}
}