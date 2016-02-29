package maze.cli;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
	

public class main {
	
	public static void main(String[] args) {
		maze.logic.Labirinto jogo = new maze.logic.Labirinto();
		Scanner s = new Scanner(System.in);
		while(!jogo.gameWon && !jogo.gameLost)
		{
			int x = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
			System.out.println(x);
			jogo.display();
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroiSimbolo());
			if(!jogo.dragonKilled)
				jogo.updateDragao(x);
			
			jogo.proximidadeHeroiDragao();
		}
		
		if(jogo.gameWon)
			System.out.println("O HEROI CONSEGUIU ESCAPAR!");
		else System.out.println("O HEROI MORREU...");
		
	}

}