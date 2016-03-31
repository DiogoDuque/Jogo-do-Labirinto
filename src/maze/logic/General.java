package maze.logic;

import java.awt.Point;
import java.util.HashMap;

public class General {

	protected Point p;
	
	protected char simbolo;
	
	protected HashMap<Point,General> maze;
	
	public General(int x, int y, char simbolo, HashMap<Point,General> maze)
	{
		p = new Point(x,y);
		this.simbolo=simbolo;
		this.maze=maze;
	}
	
	//GETS
	
	public HashMap<Point,General> getMaze() {return maze;}

	public Point getPoint() {return p;}
	
	public int getX() {return p.x;}
	
	public int getY() {return p.y;}
	
	public char getSymbol() {return simbolo;}
	
	//SETS
	
	public void setX(int x)
	{
		maze.remove(p);
		p = new Point (x,p.y);
		maze.put(p, this);
	}
	
	public void setY(int y)
	{
		maze.remove(p);
		p = new Point (p.x,y);
		maze.put(p, this);
	}

	void setSimbolo(char simbolo) {this.simbolo=simbolo;}
	
}
