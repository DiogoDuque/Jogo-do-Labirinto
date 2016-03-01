package maze.cli;
import java.util.Scanner;
public class main {
	
	public static void main(String[] args) {
		maze.logic.Maze jogo = new maze.logic.Maze();
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
			jogo.display();
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroiSimbolo());
			if(!jogo.dragonKilled)
				if(i!=1) //se o dragao tiver movimento ativo
					jogo.getDragao().mudarEstado(jogo, i);
				jogo.proximidadeHeroiDragao();
		}
		
		if(jogo.gameWon)
			System.out.println("O HEROI CONSEGUIU ESCAPAR!");
		else System.out.println("O HEROI MORREU...");
		
	}

}