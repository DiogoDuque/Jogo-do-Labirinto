import java.awt.event.KeyEvent;

public class main {
	
	public static void main(String[] args) {
		Labirinto jogo = new Labirinto();
		jogo.display();
	}
	
	public int keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			return 1;
		case KeyEvent.VK_RIGHT:
			return 2;
		case KeyEvent.VK_DOWN:
			return 3;
		case KeyEvent.VK_LEFT:
			return 4;
		default:
			return 0;
		}
	}

}
