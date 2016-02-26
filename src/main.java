import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		Labirinto jogo = new Labirinto();
		Scanner s = new Scanner(System.in);
		while(true)
		{
			jogo.display();
			int direcao = s.nextInt();
			jogo.updateAnimado(direcao, jogo.getHeroiSimbolo());
		}
	}


}
