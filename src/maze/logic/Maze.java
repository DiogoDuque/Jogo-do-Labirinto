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

	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
	
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
		espada = new Espada(5, 8);
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

		for (int i = 0; i < altura; i++)
			for (int j = 0; j < comprimento; j++) {
				switch (novomaze[i][j]) {
				case ' ':
					break;
				case 'X':
					maze[i][j] = new Parede();
					break;
				case 'S':
					saida = new Saida(i,j);
					maze[i][j] = saida;
					break;
				case 'H':
					heroi = new Heroi(i, j);
					maze[i][j] = heroi;
					break;
				case 'D':
					dragao = new Dragao(i, j);
					maze[i][j] = dragao;
					break;
				case 'E':
					espada = new Espada(i, j);
					maze[i][j] = espada;
					break;
				
				}
			}
	}

	public Geral[][] getMaze() {
		return maze;
	}

	public char getHeroiSimbolo() {
		return heroi.getSimbolo();
	}

	public char getDragaoSimbolo() {
		return dragao.getSimbolo();
	}

	public Dragao getDragao() {
		return dragao;
	}

	public Heroi getHero() {
		return heroi;
	}
	public Point getHeroPosition() {
		return heroi.p;
	}

	public void moveHeroLeft() {
		updateAnimado(4, heroi.getSimbolo());
	}

	public void moveHeroRight() {
		updateAnimado(6, heroi.getSimbolo());
	}

	public void moveHeroUp() {
		updateAnimado(2, heroi.getSimbolo());
	}

	public void moveHeroDown() {
		updateAnimado(8, heroi.getSimbolo());
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
	 * Atualiza a posicao do Animado com o simbolo recebido de acordo com a
	 * direcao recebida, caso seja possivel (e nao v� contra uma parede).
	 */
	public void updateAnimado(int direcao, char simbolo) {
		Animado anim;
		Animado replacer = null;

		if (simbolo == heroi.getSimbolo())
			anim = heroi;
		else if (simbolo == dragao.getSimbolo())
			anim = dragao;
		else
			return; // Possivelmente dar erro??

		int x = anim.getX(), y = anim.getY(); // recolher coordenadas do Animado

		// se dragao e espada estiverem sobrepostos
		if (simbolo == 'F') {
			espada = new Espada(x, y);
			replacer = espada;
		}
		while (direcao != 0) {
			switch (direcao) {
			case 2: // UP
				if (detecaoColisao(anim, x - 1, y )) {
					if (anim == heroi)
						direcao = 0;
					break;
				}
				direcao = 0;
				if (simbolo == 'F')
					dragao.setSimbolo('D');
				anim.setX(x - 1);

				maze[x-1][y] = anim;
				maze[x][y] = replacer;
				break;

			case 6: // RIGHT
				if (detecaoColisao(anim, x, y+1)) {
					if (anim == heroi)
						direcao = 0;
					break;
				}
				direcao = 0;
				if (simbolo == 'F')
					dragao.setSimbolo('D');
				anim.setY(y + 1);

				maze[x][y+1] = anim;
				maze[x][y] = replacer;
				break;

			case 8: // DOWN
				if (detecaoColisao(anim, x+1, y)) {
					if (anim == heroi)
						direcao = 0;
					break;
				}
				direcao = 0;
				if (simbolo == 'F')
					dragao.setSimbolo('D');
				anim.setX(x + 1);

				maze[x + 1][y] = anim;
				maze[x][y] = replacer;
				break;

			case 4: // LEFT
				if (detecaoColisao(anim, x, y-1)) {
					if (anim == heroi)
						direcao = 0;
					break;
				}
				direcao = 0;
				if (simbolo == 'F')
					dragao.setSimbolo('D');
				anim.setY(y-1);

				maze[x][y-1] = anim;
				maze[x][y] = replacer;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Deteta colisoes. Retorna true se houver colisao, falso se nao houver.
	 */
	private boolean detecaoColisao(Animado anim, int x, int y) {

		char animSimbolo = anim.getSimbolo();
		Geral obj = maze[x][y];

		if (x < 0 || y < 0 || y >= comprimento || x >= altura) // limites do
																// maze
			return true;

		// se � um espaco vazio, pode avancar
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

		// qualquer outra situacao � colisao
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
				dragonKilled = true;
				maze[saida.getY()][saida.getX()] = saida;
			} else if (dragao.getSimbolo() == 'D')
				gameLost = true; // senao, heroi morre e jogo acaba
		}
	}

}