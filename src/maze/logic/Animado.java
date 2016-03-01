package maze.logic;
import java.awt.Point;
public class Animado extends Geral {

	public Point p = new Point(0,0); //permite saber diretamente onde estao os objetos animados
					//no labirinto (para sabermos se os podemos mover, ou nao)
	void point(int x, int y)
	{
		p = new Point(x,y);
	}
	
	int getX() {return p.x;}
	
	int getY() {return p.y;}
	
	void setX(int x) {p.x=x;}
	
	void setY(int y) {p.y=y;}
	
}