package maze.cli;
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		/*maze.logic.Maze jogo = new maze.logic.Maze();
		Scanner s = new Scanner(System.in);
		int i =-1;
		while(i<1 || i>3)
		{
			System.out.println("Escolha uma das seguintes opcoes acerca do dragao:");
			System.out.println("1. Dragao parado");
			System.out.println("2. Dragao com movimento aleatorio");
			System.out.println("3. Movimento aleatorio + possibilidade de adormecer");
			i=s.nextInt();
		}
		
		while(!jogo.gameWon && !jogo.gameLost)
		{
			display(jogo.getMaze());
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroiSimbolo());
			if(!jogo.dragonKilled)
				if(i!=1) //se o dragao tiver movimento ativo
					jogo.getDragao().mudarEstado(jogo, i);
				jogo.proximidadeHeroiDragao();
		}
		
		if(jogo.gameWon)
			System.out.println("O HEROI CONSEGUIU ESCAPAR!");
		else System.out.println("O HEROI MORREU...");*/
		maze.logic.MazeBuilder gen = new maze.logic.MazeBuilder(9,9);
		gen.display();

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
	
}