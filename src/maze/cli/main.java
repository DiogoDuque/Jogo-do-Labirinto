package maze.cli;
import java.util.Scanner;

import maze.logic.Maze.MazeStatus;
public class main {
	
	public enum DragonType
	{
		Static, RandomMovement, SleepingAndRandomMovement
	}
	
	public static void main(String[] args) {
		maze.logic.MazeBuilder charMaze = new maze.logic.MazeBuilder(9,11,2);
		maze.logic.Maze jogo = new maze.logic.Maze(charMaze.getMaze());
		Scanner s = new Scanner(System.in);
		int choice =-1;
		while(choice<1 || choice>3)
		{
			System.out.println("Escolha uma das seguintes opcoes acerca do dragao:");
			System.out.println("1. Dragao parado");
			System.out.println("2. Dragao com movimento aleatorio");
			System.out.println("3. Movimento aleatorio + possibilidade de adormecer");
			choice=s.nextInt();
		}
		DragonType dragonType;
		if (choice==1)
			dragonType=DragonType.Static;
		else if(choice==2)
			dragonType=DragonType.RandomMovement;
		else if(choice==3)
			dragonType=DragonType.SleepingAndRandomMovement;
		else return; //ERROR
		
		while(jogo.getStatus()!=MazeStatus.HeroDied && jogo.getStatus()!=MazeStatus.Victory)
		{
			display(jogo.getMaze());
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroi());
			if(jogo.getStatus()!= MazeStatus.DragonDied && //se o dragao nao estiver morto e
				dragonType != DragonType.Static) 			//se tiver movimento ativo
					for(int i=0; i<jogo.getDragoesSize(); i++)
					{
						jogo.getDragaoIndex(i).mudarEstado(jogo, dragonType);
					}
			
		}
		
		if(jogo.getStatus()==MazeStatus.Victory)
			System.out.println("O HEROI CONSEGUIU ESCAPAR!");
		else System.out.println("O HEROI MORREU...");
		s.close();
	}

	/**
	 * Mostra o maze e o seu conteudo
	 */
	public static void display(maze.logic.Geral[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == null)
					System.out.print(" ");
				else
					System.out.print(maze[i][j].getSimbolo());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static String getDisplay(maze.logic.Geral[][] maze) {
		String disp="";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == null)
					disp += " ";
				else
					disp += maze[i][j].getSimbolo();
				disp += " ";
			}
			disp += "\n";
		}
		return disp;
	}
	
}