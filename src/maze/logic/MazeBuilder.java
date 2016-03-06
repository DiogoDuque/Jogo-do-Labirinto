package maze.logic;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

public class MazeBuilder {
	
	char[][] maze;
	char[][] visitedCells;
	ArrayDeque<Point> pathHistory;
	
	public MazeBuilder(int height, int width)
	{
		if ((height & 1) == 0 || (width & 1) == 0)
			return; //throw
		
		//inicializar array
		maze=new char[height][width];
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				maze[i][j]='X';
		
		//fazer profundidade para permitir corredores
		for(int i=1; i<height; i+=2)
			for(int j=1; j<width; j+=2)
				maze[i][j]=' ';
		
		//fazer a saida e a inicializacao da guidingCell
		int x=-1, y=-1;
		int rand = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		switch (rand)
		{
		case 1:
			x=0;
			y=2*ThreadLocalRandom.current().nextInt(1, (height-1)/2 +1)-1;
			maze[y][x]='S';
			x++; //reutilizado para criar a guidingCell
			break;
			
		case 2:
			x=width-1;
			y=2*ThreadLocalRandom.current().nextInt(1, (height-1)/2 +1)-1;
			maze[y][x]='S';
			x--; //reutilizado para criar a guidingCell
			break;
			
		case 3:
			y=0;
			x=2*ThreadLocalRandom.current().nextInt(1, (width-1)/2 +1)-1;
			maze[y][x]='S';
			y++; //reutilizado para criar a guidingCell
			break;
			
		case 4:
			y=height-1;
			x=2*ThreadLocalRandom.current().nextInt(1, (width-1)/2 +1)-1;
			maze[y][x]='S';
			y--; //reutilizado para criar a guidingCell
			break;
		}
		Point guidingCell = new Point((y-1)/2,(x-1)/2);
		maze[y][x]='+';
		
		//criar visitedCells[][]
		visitedCells = new char[(height-1)/2][(width-1)/2];
		for(int i=0; i<(height-1)/2; i++)
			for(int j=0; j<(width-1)/2; j++)
				visitedCells[i][j]='.';
		visitedCells[(y-1)/2][(x-1)/2]='+';
		
		//criar pathHistory
		pathHistory = new ArrayDeque<Point>((height-1)*(width-1)/4);
		pathHistory.push(guidingCell);
	}
	
	public void display() //so para debugging
	{
		System.out.println("Maze");
		for(int i=0; i<maze.length; i++)
		{
			for(int j=0; j<maze[0].length; j++)
			{
				System.out.print(maze[i][j]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("VisitedCells");
		for(int i=0; i<visitedCells.length; i++)
		{
			for(int j=0; j<visitedCells[0].length; j++)
			{
				System.out.print(visitedCells[i][j]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("pathHistory");
		while(!pathHistory.isEmpty())
		{
			System.out.println(pathHistory.pop());
		}
	}
}
