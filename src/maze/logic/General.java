/**
 * Esta classe tem como subclasses todos os objetos representados no labirinto, tendo tambem os métodos
 * e variáveis que estes têm em comum.
 */

package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class General {

	protected Point p;
	
	protected char symbol ;
	
	protected HashMap<Point,General> maze;
	
	public General(int x, int y, char symbol, HashMap<Point,General> maze)
	{
		p = new Point(x,y);
		this.symbol=symbol;
		this.maze=maze;
	}
	
	//GETS
	
	/**
	 * 
	 * @return HashMap com os objetos e suas posições no labirinto.
	 */
	public HashMap<Point,General> getMaze() {return maze;}

	/**
	 * 
	 * @return posição do objeto.
	 */
	public Point getPoint() {return p;}
	
	/**
	 * 
	 * @return coordenada X da posição do objeto.
	 */
	public int getX() {return p.x;}
	
	/**
	 * 
	 * @return coordenada Y da posição do objeto.
	 */
	public int getY() {return p.y;}
	
	/**
	 * 
	 * @return simbolo correspondente ao objeto.
	 */
	public char getSymbol() {return symbol;}
	
	//SETS
	
	/**
	 * Move o objeto para a nova coordenada (x,this.y).
	 * @param x nova coordenada X deste objeto.
	 */
	public void setX(int x)
	{
		maze.remove(p);
		p = new Point (x,p.y);
		maze.put(p, this);
	}
	
	/**
	 * Move o objeto para a nova coordenada (this.x,y).
	 * @param y nova coordenada Y deste objeto.
	 */
	public void setY(int y)
	{
		maze.remove(p);
		p = new Point (p.x,y);
		maze.put(p, this);
	}

	/**
	 * Redefine o simbolo do objeto.
	 * @param symbol novo simbolo do objeto.
	 */
	void setSymbol(char symbol) {this.symbol=symbol;}
	
}
