package maze.logic;
import java.awt.Point;

public class Maze {
	public boolean gameLost;
	public boolean gameWon;
	public boolean dragonKilled;

	private int altura;
	private int comprimento;
	private Geral[][] maze;
	private Heroi heroi;
	private Dragao dragao;
	private Espada espada;
	private Saida saida;

	public Maze() {
		gameLost = false;
		gameWon = false;
		dragonKilled = false;
		altura = 10;
		comprimento = 10;
		maze = new Geral[altura][comprimento];

		// paredes exteriores
		for (int i = 0; i < altura; i++) {
			if (i == 0 || i == altura - 1) {
				for (int j = 0; j < comprimento; j++)
					maze[i][j] = new Parede();
			} else {
				maze[i][0] = new Parede();
				maze[i][comprimento - 1] = new Parede();
			}
		}

		// paredes interiores
		criarParedes(2, 2, 4, 3);
		criarParedes(6, 2, 8, 3);
		criarParedes(2, 5, 4, 5);
		criarParedes(6, 5, 7, 5);
		criarParedes(2, 7, 7, 7);

		// criar heroi, dragao, espada e saida (a saida nao fica desenhada
		// ainda)
		heroi = new Heroi(1, 1);
		dragao = new Dragao(1, 3);
		espada = new Espada(1, 8);
		saida = new Saida(9, 5);
		maze[heroi.getY()][heroi.getX()] = heroi;
		maze[dragao.getY()][dragao.getX()] = dragao;
		maze[espada.getY()][espada.getX()] = espada;
	}

	public Maze(char[][] novomaze) {
		gameLost = false;
		gameWon = false;
		dragonKilled = false;
		
		
		altura = novomaze.length;
		comprimento = novomaze[0].length;
		maze = new Geral[altura][comprimento];

		for(int i=0; i<altura; i++)
			for(int j=0; j<comprimento; j++)
			{
				switch(novomaze[i][j])
				{
				case ' ':
					break;
				case 'X':
					maze[i][j] = new Parede();
					break;
				case 'H':
					heroi = new Heroi(j,i);
					maze[i][j] = heroi;
					break;
				case 'D':
					dragao = new Dragao(j,i);
					maze[i][j] = dragao;
					break;
				case 'E':
					espada = new Espada(j,i);
					maze[i][j] = espada;
					break;
				}
			}
	}

	
	public char getHeroiSimbolo() {
		return heroi.getSimbolo();
	}

	public char getDragaoSimbolo() {
		return dragao.getSimbolo();
	}

	public Dragao getDragao() {return dragao;}
	
	public Point getHeroPosition() {
		return heroi.p;
	}
	
	public void moveHeroLeft()
	{
		int x = heroi.p.x, y = heroi.p.y;
		if (detecaoColisao(heroi, x - 1, y))
			return;

		maze[y][x - 1] = heroi;
		maze[y][x] = null;
		heroi.p.x--;
	}
	
	/**
	 * Cria varios objetos Parede entre as alturas alt1 e alt2 e os comprimentos
	 * comp1 e comp2
	 */
	public void criarParedes(int alt1, int comp1, int alt2, int comp2) {
		for (int i = alt1; i <= alt2; i++)
			for (int j = comp1; j <= comp2; j++)
				maze[i][j] = new Parede();
	}

	/**
	 * Mostra o maze e o seu conteudo
	 */
	public void display() {
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < comprimento; j++) {
				if (maze[i][j] == null)
					System.out.print(" ");
				else
					System.out.print(maze[i][j].getSimbolo());
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/**
	 * Atualiza a posicao do Animado com o simbolo recebido de acordo com a
	 * direcao recebida, caso seja possivel (e nao vá contra uma parede).
	 */
	public void updateAnimado(int direcao, char simbolo) {
		Animado anim;

		if (simbolo == heroi.getSimbolo())
			anim = heroi;
		else if (simbolo == dragao.getSimbolo())
			anim = dragao;
		else
			return; // Possivelmente dar erro??

		int x = anim.getX(), y = anim.getY(); // recolher coordenadas do Animado

		switch (direcao) {
		case 2: // UP
			if (detecaoColisao(anim, x, y - 1))
				break;
			anim.setY(y - 1);

			maze[y - 1][x] = anim;
			maze[y][x] = null;
			break;

		case 6: // RIGHT
			if (detecaoColisao(anim, x + 1, y))
				break;
			anim.setX(x + 1);

			maze[y][x + 1] = anim;
			maze[y][x] = null;
			break;

		case 8: // DOWN
			if (detecaoColisao(anim, x, y + 1))
				break;
			anim.setY(y + 1);

			maze[y + 1][x] = anim;
			maze[y][x] = null;
			break;

		case 4: // LEFT
//			if (detecaoColisao(anim, x - 1, y))
//				break;
//			anim.setX(x - 1);
//
//			maze[y][x - 1] = anim;
//			maze[y][x] = null;
			break;
		default:
			break;
		}
	}

	/**
	 * Deteta colisoes. Retorna true se houver colisao, falso se nao houver.
	 */
	private boolean detecaoColisao(Animado anim, int x, int y) {

		char animSimbolo = anim.getSimbolo();
		Geral obj = maze[y][x];

		if (x < 0 || y < 0 || x >= comprimento || y >= altura) // limites do
																// maze
			return true;

		// se é um espaco vazio, pode avancar
		if (obj == null)
			return false;

		// se o heroi vai apanhar a espada
		if (obj.getSimbolo() == 'E' && animSimbolo == 'H') {
			anim.setSimbolo('A');
			return false;
		}

		if (obj.getSimbolo() == 'E' && animSimbolo == 'D') {
			anim.setSimbolo('F');
			return false;
		}

		// se estiver na saida
		if (animSimbolo == 'A' && obj.getSimbolo() == 'S') {
			gameWon = true;
			return true;
		}

		// qualquer outra situacao é colisao
		return true;
	}

	/**
	 * Deteta se o dragao e o heroi estao adjacentes. Caso estejam, aplicam-se
	 * as regras do jogo e um deles morre, de acordo com se o heroi possui a
	 * espada ou nao.
	 */
	public void proximidadeHeroiDragao() {
		int hX = heroi.getX(), hY = heroi.getY();
		int dX = dragao.getX(), dY = dragao.getY();
		int difX = Math.abs(hX - dX), difY = Math.abs(hY - dY);

		if (difX + difY == 1) // se estiverem adjacentes
		{
			if (heroi.getSimbolo() == 'A') { // se estiver armado
				maze[dY][dX] = null; // dragao desaparece
				dragonKilled=true;
				maze[saida.getY()][saida.getX()] = saida;
			} else if (dragao.getSimbolo() == 'D')
				gameLost = true; // senao, heroi morre e jogo acaba
		}
	}

	public void updateDragao(int direcao) {
		Animado anim;
		Animado anim2;
		if (dragao.getSimbolo() == 'F') {
			anim = dragao;
			int x = anim.getX(), y = anim.getY();
			espada = new Espada(x, y);
			anim2 = espada;

			switch (direcao) {
			case 2: // UP
				if (detecaoColisao(anim, x, y - 1))
					break;
				dragao.setSimbolo('D');

				anim.setY(y - 1);

				maze[y - 1][x] = anim;
				maze[y][x] = anim2;
				break;

			case 6: // RIGHT
				if (detecaoColisao(anim, x + 1, y))
					break;
				dragao.setSimbolo('D');
				anim.setX(x + 1);

				maze[y][x + 1] = anim;
				maze[y][x] = anim2;
				break;

			case 8: // DOWN
				if (detecaoColisao(anim, x, y + 1))
					break;
				dragao.setSimbolo('D');
				anim.setY(y + 1);

				maze[y + 1][x] = anim;
				maze[y][x] = anim2;
				break;

			case 4: // LEFT
				if (detecaoColisao(anim, x - 1, y))
					break;
				dragao.setSimbolo('D');
				anim.setX(x - 1);

				maze[y][x - 1] = anim;
				maze[y][x] = anim2;
				break;
			default:
				break;
			}
		} else
			updateAnimado(direcao, dragao.getSimbolo());

	}
	
}