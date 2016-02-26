import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		Labirinto jogo = new Labirinto();
		Scanner s = new Scanner(System.in);
		while(!jogo.gameWon && !jogo.gameLost)
		{
			jogo.display();
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroiSimbolo());
			jogo.proximidadeHeroiDragao();
		}
		if(jogo.gameWon)
			System.out.println("O HEROI CONSEGUIU ESCAPAR!");
		else System.out.println("O HEROI MORREU...");
	}

}
