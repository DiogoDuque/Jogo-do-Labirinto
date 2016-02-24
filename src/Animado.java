
public class Animado extends Geral {

	private int x, y; //permite saber diretamente onde estao os objetos animados
					//no labirinto (para sabermos se os podemos mover, ou nao)
	
	int getX() {return x;}
	
	int getY() {return y;}
	
	void setX(int x) {this.x=x;}
	
	void setY(int y) {this.y=y;}
	
}
