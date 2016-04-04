/**
 * Centro da logica do jogo. É onde está guardado o labirinto e os seus objetos.
 */

package maze.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
	
	private int height;
	private int width;
	private HashMap<Point,General> maze;
	private Hero hero;
	private ArrayList<Dragon> dragons;
	private Sword sword;
	private Exit exit;
	private MazeStatus status;
	
	public enum DragonType
	{
		Static, RandomMovement, SleepingAndRandomMovement
	}
	
	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
	
	public enum MazeStatus
	{
		HeroDied, HeroUnarmed, HeroArmed, DragonDied, Victory
	}
	
	public Maze(int x, int y){
		status=MazeStatus.HeroUnarmed;
		height = y+1;
		width = x+1;
		
		maze = new HashMap<Point,General>();
		dragons = new ArrayList<Dragon>();
		for (int i = 0; i < y ; i++){
				Wall wall = new Wall(i,0,maze);
				maze.put(new Point(i,0),wall);
				Wall wall1 = new Wall(i,x,maze);
				maze.put(new Point(i,x),wall1);
			}
		for (int j = 0; j <= x; j++){
		Wall wall2 = new Wall(0,y,maze);
		maze.put(new Point(0,j),wall2);
		Wall wall3 = new Wall(y,j,maze);
		maze.put(new Point(y,j),wall3);
	}
		
	}
	
	public Maze(char[][] novomaze) {
		status=MazeStatus.HeroUnarmed;

		height = novomaze.length;
		width = novomaze[0].length;
		maze = new HashMap<Point,General>();
		dragons = new ArrayList<Dragon>();
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				switch (novomaze[i][j]) {
				case ' ':
					break;
				case 'X':
					Wall wall = new Wall(i,j,maze);
					maze.put(new Point(i,j),wall);
					break;
				case 'S':
					exit = new Exit(i,j,maze);
					maze.put(new Point(i,j),exit);
					break;
				case 'H':
					hero = new Hero(i,j,maze);
					maze.put(new Point(i,j),hero);
					break;
				case 'D':
					Dragon dragon = new Dragon(i,j,maze);
					maze.put(new Point(i,j),dragon);
					dragons.add(dragon);
					break;
				case 'E':
					sword = new Sword(i,j,maze);
					maze.put(new Point(i,j),sword);
					break;
				
				}
			}
	}
	
	/**
	 * 
	 * @return altura do labirinto.
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * 
	 * @return largura do labirinto
	 */
	public int getWidth(){
		return width;
	}

	//GETS GERAIS
	
	/**
	 * 
	 * @return HashMap com os objetos do labirinto.
	 */
	public HashMap<Point,General> getMaze() {return maze;}

	/**
	 * 
	 * @return estado do labirinto.
	 */
	public MazeStatus getStatus() {return status;}
	
	/**
	 * 
	 * @return dimensoes do labirinto num Point.
	 */
	public Point getDimensions() {return new Point(height,width);}
	
	//GETS DRAGON

	/**
	 * 
	 * @param index indice do dragao no ArrayList dragons.
	 * @return dragao no indice index do ArrayList dragons.
	 */
	public Dragon getDragonByIndex(int index) {return dragons.get(index);}
	
	/**
	 * 
	 * @return numero de dragoes existentes.
	 */
	public int getDragonsSize() {return dragons.size();}
	
	/**
	 * 
	 * @return ArrayList com todos os dragoes do labirinto.
	 */
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
	//GETS HERO
	
	/**
	 * (Re)define o Hero.
	 * @param hero
	 */
	public void setHero(Hero hero){
		this.hero=hero;
	}
	
	/**
	 * 
	 * @return heroi do labirinto.
	 */
	public Hero getHero() {return hero;}
	
	/**
	 * 
	 * @return posicao do heroi no labirinto.
	 */
	public Point getHeroPosition() {return hero.getPoint();}
	
	//MOVES HERO

	/**
	 * Move o heroi para a esquerda.
	 */
	public void moveHeroLeft() {moveGeral(Direction.LEFT, hero);}

	/**
	 * Move o heroi para a direita.
	 */
	public void moveHeroRight() {moveGeral(Direction.RIGHT, hero);}

	/**
	 * Move o heroi para cima.
	 */
	public void moveHeroUp() {moveGeral(Direction.UP, hero);}

	/**
	 * Move o heroi para a baixo.
	 */
	public void moveHeroDown() {moveGeral(Direction.DOWN, hero);}


	/**
	 * 
	 * @param direction direção em que anim se tentará mover.
	 * @param anim General que se tentará mover.
	 * @return true se anim conseguiu mover-se, falso em caso contrário. (Particularmente util para verificar se o dragão se moveu ou não com sucesso)
	 */
	public boolean moveGeral(Direction direction, General anim) {

		boolean moved = true;
		int x = anim.getX(), y = anim.getY(); // recolher coordenadas do Geral

		// se dragao e espada estiverem sobrepostos
		
			switch (direction) {
			case UP: // UP
				if (detecaoColisao(anim,x-1,y))
				{
					moved=false;
					break;
				}
				anim.setX(x-1);
				break;

			case RIGHT: // RIGHT
				if (detecaoColisao(anim,x,y+1)) 
				{
					moved=false;
					break;
				}
				anim.setY(y+1);
				break;

			case DOWN: // DOWN
				if (detecaoColisao(anim,x+1,y)) 
				{
					moved=false;
					break;
				}
				if (anim.getSymbol() == 'F')
					anim.setSymbol('D');
				anim.setX(x+1);
				break;

			case LEFT: // LEFT
				if (detecaoColisao(anim, x, y-1))
				{
					moved=false;
					break;
				}
				if (anim.getSymbol() == 'F')
					anim.setSymbol('D');
				anim.setY(y-1);
				break;
				
			default:
				break;
			}
	
			
		return moved;
	}

	/**
	 * Deteta colisões.
	 * @return true se ocorreu colisão, false em caso contrário.
	 */
	private boolean detecaoColisao(General anim, int x, int y) {

		char animSimbolo = anim.getSymbol();
		General obj = maze.get(new Point(x,y)); //objeto na posicao pretendida do maze

		if (x < 0 || y < 0 || y >= width || x >= height) // limites do maze
			return true;

		// se é um espaco vazio, pode avancar
		if (obj == null)
			return false;

		// se o hero vai apanhar a espada
		if (obj.getSymbol() == 'E' && animSimbolo == 'H') {
			status=MazeStatus.HeroArmed;
			anim.setSymbol('A');
			return false;
		}

		if (obj.getSymbol() == 'E' && animSimbolo == 'D') { //se dragao vai pisar a espada
			return false; //as mudancas dos simbolos sao efetuadas na class Dragon, no override dos metodos setX() e setY()
		}

		// se estiver na saida
		if (status == MazeStatus.DragonDied && obj.getSymbol() == 'S') {
			
			status=MazeStatus.Victory;
			return false;
		}

		// qualquer outra situacao é colisao
		return true;
	}

	/**
	 * Deteta se um dragon e o hero estao adjacentes. Caso estejam, aplicam-se
	 * as regras do jogo e um deles morre, de acordo com se o hero possui a
	 * espada ou nao.
	 */
	public void proximityHeroDragon() {
		for(int i=0; i<dragons.size(); i++)
		{
			Dragon dragao = dragons.get(i);
			int hX = hero.getX(), hY = hero.getY();
			int dX = dragao.getX(), dY = dragao.getY();
			int difX = Math.abs(hX - dX), difY = Math.abs(hY - dY);

			if (difX + difY == 1) // se estiverem adjacentes
			{
				if (getStatus() == MazeStatus.HeroArmed) { // se estiver armado
					maze.remove(new Point(dX,dY)); // dragao desaparece
					dragons.remove(i);
					if(dragons.isEmpty())
					{
						status=MazeStatus.DragonDied;
					}
				} else if (dragao.getSymbol() == 'D' || dragao.getSymbol() == 'F')
					status=MazeStatus.HeroDied; // hero morre e jogo acaba
			}
		}
	}

}