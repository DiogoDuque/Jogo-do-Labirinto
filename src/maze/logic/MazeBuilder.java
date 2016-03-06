package maze.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MazeBuilder {

	char[][] maze;
	char[][] visitedCells;
	ArrayDeque<Point> pathHistory;
	Point guidingCell;

	public MazeBuilder(int height, int width) {
		if ((height & 1) == 0 || (width & 1) == 0)
			return; // throw

		// inicializar array
		maze = new char[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				maze[i][j] = 'X';

		// fazer profundidade para permitir corredores
		for (int i = 1; i < height; i += 2)
			for (int j = 1; j < width; j += 2)
				maze[i][j] = ' ';

		// fazer a saida e a inicializacao da guidingCell
		int x = -1, y = -1;
		int rand = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		switch (rand) {
		case 1:
			x = 0;
			y = 2 * ThreadLocalRandom.current().nextInt(1, (height - 1) / 2 + 1) - 1;
			maze[y][x] = 'S';
			x++; // reutilizado para criar a guidingCell
			break;

		case 2:
			x = width - 1;
			y = 2 * ThreadLocalRandom.current().nextInt(1, (height - 1) / 2 + 1) - 1;
			maze[y][x] = 'S';
			x--; // reutilizado para criar a guidingCell
			break;

		case 3:
			y = 0;
			x = 2 * ThreadLocalRandom.current().nextInt(1, (width - 1) / 2 + 1) - 1;
			maze[y][x] = 'S';
			y++; // reutilizado para criar a guidingCell
			break;

		case 4:
			y = height - 1;
			x = 2 * ThreadLocalRandom.current().nextInt(1, (width - 1) / 2 + 1) - 1;
			maze[y][x] = 'S';
			y--; // reutilizado para criar a guidingCell
			break;
		}
		guidingCell = new Point((y - 1) / 2, (x - 1) / 2);

		// criar visitedCells[][]
		visitedCells = new char[(height - 1) / 2][(width - 1) / 2];
		for (int i = 0; i < (height - 1) / 2; i++)
			for (int j = 0; j < (width - 1) / 2; j++)
				visitedCells[i][j] = '.';
		visitedCells[(y - 1) / 2][(x - 1) / 2] = '+';

		// criar pathHistory
		pathHistory = new ArrayDeque<Point>((height - 1) * (width - 1) / 4);
		pathHistory.push(guidingCell);

		// gerar labirinto
		generate();
		
		//colocar outros objetos/personagens
		ArrayList objs = new ArrayList();
		objs.add('H');
		objs.add('D');
		objs.add('E');
		
		while(!objs.isEmpty())
		{
			int h = ThreadLocalRandom.current().nextInt(0,height);
			int w = ThreadLocalRandom.current().nextInt(0,width);
			
			if(h<=0 || w<=0 || h>=height-1 || w>=width-1) //se fora dos limites
				continue;
			
			if(objs.size()==1) //se so sobra o heroi
			{
				if(maze[h+1][w]=='D'||maze[h-1][w]=='D'||
						maze[h][w+1]=='D'||maze[h][w-1]=='D') //se dragao perto
					continue;
			}
			
			if(maze[h][w]==' ')
			{
				maze[h][w]=(char)objs.get(objs.size()-1);
				objs.remove(objs.size()-1);
			}
		}
	}

	// o construtor chama este metodo
	private void generate() {
		while (!pathHistory.isEmpty()) {			
			int x = guidingCell.x, y = guidingCell.y;
			if(!(canMove(x+1,y)||canMove(x-1,y)||canMove(x,y+1)||canMove(x,y-1)))
			{ //se nao puder mover para nenhum sitio
				pathHistory.pop();
				guidingCell=pathHistory.peek();
				continue;
			}

			//gerar direcao aleatoria e tentar mover
			int rand = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			switch (rand) {
			case 1:
				x++;
				break;
			case 2:
				x--;
				break;
			case 3:
				y++;
				break;
			case 4:
				y--;
				break;
			}

			if (canMove(x, y)) //se puder mover, move
			{
				visitedCells[y][x]='+'; //marcar como visitada
				maze[guidingCell.y+y+1][guidingCell.x+x+1]=' '; //abrir caminho no maze
				
				guidingCell = new Point(y,x);
				pathHistory.push(guidingCell);
			}
		}
	}

	private boolean canMove(int x, int y) {
		if (x < 0 || y < 0 || x >= visitedCells[0].length || y >= visitedCells.length)
			return false;
		if (visitedCells[y][x] == '+')
			return false;
		return true;
	}

	public void displayFull() // so para debugging
	{
		System.out.println("");
		System.out.println("");
		System.out.println("Maze");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		System.out.println("VisitedCells");
		for (int i = 0; i < visitedCells.length; i++) {
			for (int j = 0; j < visitedCells[0].length; j++) {
				System.out.print(visitedCells[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("");
		
		System.out.println("pathHistory");
		ArrayDeque<Point> temp = pathHistory.clone();
		while (!pathHistory.isEmpty()) {
			System.out.println(pathHistory.pop());
		}
		pathHistory=temp;
	}

	
	public void display() // so para debugging
	{
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
