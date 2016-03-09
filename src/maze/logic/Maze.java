package maze.logic;

import java.awt.Point;
import java.util.ArrayList;

public class Maze {
	
	private int altura;
	private int comprimento;
	private Geral[][] maze;
	private Heroi heroi;
	private ArrayList<Dragao> dragoes;
	private Espada espada;
	private Saida saida;
	private MazeStatus status;

	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
	
	public enum MazeStatus
	{
		HeroDied, HeroUnarmed, HeroArmed, DragonDied, Victory
	}
	
	public Maze() {
		status=MazeStatus.HeroUnarmed;
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
		Dragao dragao = new Dragao(3, 1);
		espada = new Espada(8, 1);
		saida = new Saida(5, 9);
		maze[heroi.getX()][heroi.getY()] = heroi;
		maze[dragao.getX()][dragao.getY()] = dragao;
		maze[espada.getX()][espada.getY()] = espada;
		
		dragoes=new ArrayList<Dragao>();
		dragoes.add(dragao);
	}

	public Maze(char[][] novomaze) {
		status=MazeStatus.HeroUnarmed;

		altura = novomaze.length;
		comprimento = novomaze[0].length;
		maze = new Geral[altura][comprimento];
		dragoes = new ArrayList<Dragao>();
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
					Dragao dragao = new Dragao(i, j);
					maze[i][j] = dragao;
					dragoes.add(dragao);
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

	public MazeStatus getStatus()
	{
		return status;
	}
	
	public Heroi getHeroi() {
		return heroi;
	}

//	public char getDragaoSimbolo() {
//		return dragao.getSimbolo();
//	}

	public Dragao getDragaoIndex(int index) {
		return dragoes.get(index);
	}
	
	public int getDragoesSize()
	{
		return dragoes.size();
	}

	public Heroi getHero() {
		return heroi;
	}
	public Point getHeroPosition() {
		return heroi.p;
	}

	public void moveHeroLeft() {
		updateAnimado(4, heroi);
	}

	public void moveHeroRight() {
		updateAnimado(6, heroi);
	}

	public void moveHeroUp() {
		updateAnimado(2, heroi);
	}

	public void moveHeroDown() {
		updateAnimado(8, heroi);
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
	 * direcao recebida, caso seja possivel (e nao vá contra uma parede).
	 */
	public void updateAnimado(int direcao, Animado anim) {

		Animado replacer = null;

		int x = anim.getX(), y = anim.getY(); // recolher coordenadas do Animado

		// se dragao e espada estiverem sobrepostos
		if (anim.getSimbolo() == 'F') {
			espada = new Espada(x, y);
			replacer = espada;
		}
		
			switch (direcao) {
			case 2: // UP
				if (detecaoColisao(anim, x - 1, y )) 
					break;
				if (anim.getSimbolo() == 'F')
					anim.setSimbolo('D');
				anim.setX(x - 1);

				maze[x-1][y] = anim;
				maze[x][y] = replacer;
				break;

			case 6: // RIGHT
				if (detecaoColisao(anim, x, y+1)) 
					break;
				if (anim.getSimbolo() == 'F')
					anim.setSimbolo('D');
				anim.setY(y + 1);

				maze[x][y+1] = anim;
				maze[x][y] = replacer;
				break;

			case 8: // DOWN
				if (detecaoColisao(anim, x+1, y)) 
				
					break;
				if (anim.getSimbolo() == 'F')
					anim.setSimbolo('D');
				anim.setX(x + 1);

				maze[x + 1][y] = anim;
				maze[x][y] = replacer;
				break;

			case 4: // LEFT
				if (detecaoColisao(anim, x, y-1))
					break;
				
				if (anim.getSimbolo() == 'F')
					anim.setSimbolo('D');
				anim.setY(y-1);

				maze[x][y-1] = anim;
				maze[x][y] = replacer;
				break;
			default:
				break;
			}
		proximidadeHeroiDragao();
	}

	/**
	 * Deteta colisoes. Retorna true se houver colisao, falso se nao houver.
	 */
	private boolean detecaoColisao(Animado anim, int x, int y) {

		char animSimbolo = anim.getSimbolo();
		Geral obj = maze[x][y]; //objeto na posicao pretendida do maze

		if (x < 0 || y < 0 || y >= comprimento || x >= altura) // limites do maze
			return true;

		// se é um espaco vazio, pode avancar
		if (obj == null)
			return false;

		// se o heroi vai apanhar a espada
		if (obj.getSimbolo() == 'E' && animSimbolo == 'H') {
			status=MazeStatus.HeroArmed;
			anim.setSimbolo('A');
			return false;
		}

		if (obj.getSimbolo() == 'E' && animSimbolo == 'D') {
			anim.setSimbolo('F');
			return false;
		}

		// se estiver na saida
		if (animSimbolo == 'A' && obj.getSimbolo() == 'S') {
			status=MazeStatus.Victory;
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
		for(int i=0; i<dragoes.size(); i++)
		{
			Dragao dragao = dragoes.get(i);
			int hX = heroi.getX(), hY = heroi.getY();
			int dX = dragao.getX(), dY = dragao.getY();
			int difX = Math.abs(hX - dX), difY = Math.abs(hY - dY);

			if (difX + difY == 1) // se estiverem adjacentes
			{
				if (getStatus() == MazeStatus.HeroArmed) { // se estiver armado
					maze[dX][dY] = null; // dragao desaparece
					status=MazeStatus.DragonDied;
					dragao.p = new Point(-1,-1);
					maze[saida.getX()][saida.getY()] = saida;
				} else if (dragao.getSimbolo() == 'D')
					status=MazeStatus.HeroDied; // heroi morre e jogo acaba
			}
		}
	}

}