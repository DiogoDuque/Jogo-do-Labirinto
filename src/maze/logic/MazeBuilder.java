package maze.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;

public class MazeBuilder {

	char[][] maze;
	char[][] visitedCells;
	ArrayDeque<Point> pathHistory;
	Point guidingCell;

	public MazeBuilder(int height, int width, int numDragons) {
		if ((height & 1) == 0 || (width & 1) == 0)
			throw new IllegalArgumentException("ERRO: Altura e largura do labirinto devem ser impares!");
		if (height>17 || width>19)
			throw new IllegalArgumentException("ERRO: Dimensoes do labirinto são demasiado grandes!");
		if(numDragons>(height/5)*(width/5))
			throw new IllegalArgumentException("Existem demasiados dragoes para o tamanho do labirinto!");

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
		guidingCell = new Point((x - 1) / 2, (y - 1) / 2);

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
		generateCharacters(numDragons, height, width);
	}

	/**
	 * Gera um labirinto vazio, somente com paredes e saida.
	 */
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
				
				guidingCell = new Point(x,y);
				pathHistory.push(guidingCell);
			}
		}
	}

	/**
	 * @param x possivel coordenada x da guidingCell.
	 * @param y possivel coordenada y da guidingCell.
	 * @return true se a guidingCell se puder mover para estas coordenadas, falso em caso contrário.
	 */
	private boolean canMove(int x, int y) {
		if (x < 0 || y < 0 || x >= visitedCells[0].length || y >= visitedCells.length)
			return false;
		if (visitedCells[y][x] == '+')
			return false;
		return true;
	}

	/**
	 * Gerar os dragões, o heroi e a espada.
	 * @param numDragons número de dragoes para inserir no labirinto.
	 * @param height altura do labirinto.
	 * @param width largura do labirinto.
	 */
	private void generateCharacters(int numDragons, int height, int width)
	{
		//por dragoes no labirinto
		while(numDragons>0)
		{
			int h = ThreadLocalRandom.current().nextInt(1,height-1);
			int w = ThreadLocalRandom.current().nextInt(1,width-1);
			
			if(h<=0 || w<=0 || h>=height-1 || w>=width-1) //se fora dos limites
				continue;
			
			if(maze[h][w]==' ')
			{
				maze[h][w]='D';
				numDragons--;
			}
		}
		
		//por heroi e espada
		while(true)
		{
			Point hero= new Point(ThreadLocalRandom.current().nextInt(1,height-1),ThreadLocalRandom.current().nextInt(1,width-1));
			Point sword= new Point(ThreadLocalRandom.current().nextInt(1,height-1),ThreadLocalRandom.current().nextInt(1,width-1));
			
			if(hero.equals(sword) || maze[hero.x][hero.y] != ' ' || maze[sword.x][sword.y] != ' ')
				continue;
			
			char[][] clonedMaze = new char[height][width];
			for(int i=0; i<height; i++)
				for(int j=0; j<width; j++)
					clonedMaze[i][j]=maze[i][j];
			if(heroCanReachSword(clonedMaze, hero, sword))
			{
				maze[hero.x][hero.y]='H';
				maze[sword.x][sword.y]='E';
				break;
			}
		}
	}
	
	/**
	 * Chamada recursivamente, permite perceber se o heroi tem um caminho livre de dragões até à espada.
	 * @param mazeWithDragons labirinto já com dragoes inseridos.
	 * @param hero coordenadas do heroi no labirinto.
	 * @param sword coordenadas da espada no labirinto.
	 * @return true se o heroi tiver um caminho sem dragões até à espada, false em caso contrário.
	 */
	public boolean heroCanReachSword(char[][] mazeWithDragons, Point hero, Point sword)
	{
		if(hero.x<=0 || hero.y<=0 || hero.x>=mazeWithDragons.length-1 || hero.y>=mazeWithDragons[0].length-1)
			return false;
		
		if(hero.equals(sword))
			return true;
		
		if(mazeWithDragons[hero.x][hero.y] != ' ')
			return false;
		
		mazeWithDragons[hero.x][hero.y]='H';
		if (heroCanReachSword(mazeWithDragons,new Point(hero.x,hero.y-1),sword))
			return true;
		else if(heroCanReachSword(mazeWithDragons,new Point(hero.x,hero.y+1),sword))
			return true;
		else if(heroCanReachSword(mazeWithDragons,new Point(hero.x+1,hero.y),sword))
			return true;
		else return(heroCanReachSword(mazeWithDragons,new Point(hero.x-1,hero.y),sword));
		
	}
	
	public char[][] getMaze()
	{
		return maze;
	}
	
	/**
	 * FUNCAO USADA APENAS PARA DEBUGGING!
	 * Mostra na consola o estado atual das estruturas maze, visitedCells e pathHistory.
	 */
//	public void displayFull()
//	{
//		System.out.println("");
//		System.out.println("");
//		System.out.println("Maze");
//		for (int i = 0; i < maze.length; i++) {
//			for (int j = 0; j < maze[0].length; j++) {
//				System.out.print(maze[i][j] + " ");
//			}
//			System.out.println("");
//		}
//		
//		System.out.println("");
//		
//		System.out.println("VisitedCells");
//		for (int i = 0; i < visitedCells.length; i++) {
//			for (int j = 0; j < visitedCells[0].length; j++) {
//				System.out.print(visitedCells[i][j] + " ");
//			}
//			System.out.println("");
//		}
//
//		System.out.println("");
//		
//		System.out.println("pathHistory");
//		ArrayDeque<Point> temp = pathHistory.clone();
//		while (!pathHistory.isEmpty()) {
//			System.out.println(pathHistory.pop());
//		}
//		pathHistory=temp;
//	}
	
	/**
	 * FUNCAO USADA APENAS PARA DEBUGGING!
	 * Mostra na consola o estado atual da estrutura maze.
	 */
//	public void display() // so para debugging
//	{
//		for (int i = 0; i < maze.length; i++) {
//			for (int j = 0; j < maze[0].length; j++) {
//				System.out.print(maze[i][j] + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("");
//	}
}
