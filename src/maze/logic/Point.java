package maze.logic;

public class Point {

	public int x,y;
	
	public Point(int y, int x) {
		this.x=x;
		this.y=y;
	};

	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(obj instanceof Point)
		{
			Point p = (Point) obj;
			if(this.x == p.x && this.y == p.y)
			return true;
			else return false;
		}
		else return false;
	}

	public String toString()
	{
		String s = "(" + x + ", " + y + ")" ;
		return s;
	}
}
