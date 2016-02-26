
public class Labirinto {

	private int altura;
	private int comprimento;
	private Geral[][] labirinto;
	private Heroi heroi;
	private Dragao dragao;
	private Espada espada;

	public Labirinto() {
		this.altura = 10;
		this.comprimento = 10;
		this.labirinto = new Geral[altura][comprimento];

		// paredes exteriores
		for (int i = 0; i < altura; i++) {
			if (i == 0 || i == altura - 1) {
				for (int j = 0; j < comprimento; j++)
					labirinto[i][j] = new Parede();
			} else {
				labirinto[i][0] = new Parede();
				labirinto[i][comprimento - 1] = new Parede();
			}
		}

		// paredes interiores
		criarParedes(2, 2, 4, 3);
		criarParedes(6, 2, 8, 3);
		criarParedes(2, 5, 4, 5);
		criarParedes(6, 5, 7, 5);
		criarParedes(2, 7, 7, 7);

		// criar heroi, dragao e espada
		this.heroi = new Heroi(1, 1);
		this.dragao = new Dragao(1, 3);
		this.espada = new Espada(1, 8);
		labirinto[1][1] = this.heroi;
		labirinto[3][1] = this.dragao;
		labirinto[8][1] = this.espada;
	}

	public char getHeroiSimbolo() {return heroi.getSimbolo();}
	
	/**
	 * Cria varios objetos Parede entre as alturas alt1 e alt2 e os comprimentos
	 * comp1 e comp2
	 */
	public void criarParedes(int alt1, int comp1, int alt2, int comp2) {
		for (int i = alt1; i <= alt2; i++)
			for (int j = comp1; j <= comp2; j++)
				labirinto[i][j] = new Parede();
	}

	/**
	 * Mostra o labirinto e o seu conteudo
	 */
	public void display() {
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < comprimento; j++) {
				if (labirinto[i][j] == null)
					System.out.print(" ");
				else
					System.out.print(labirinto[i][j].getSimbolo());
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/**
	 * Atualiza a posicao do Animado com o simbolo recebido de
	 * acordo com a direcao recebida, caso seja possivel (e nao vá contra uma
	 * parede).
	 */
	public void updateAnimado(int direcao, char simbolo) {
		Animado anim;
		if (simbolo == heroi.getSimbolo())
			anim = heroi;
		else if (simbolo == dragao.getSimbolo())
			anim = dragao;
		else
			return; // Possivelmente dar erro??
		
		int x = anim.getX(), y = anim.getY(); //recolher coordenadas do Animado

		switch (direcao) {
		case 2: // UP
			if (detecaoColisao(anim, x, y - 1))
				break;
			anim.setY(y - 1);
			labirinto[y - 1][x] = anim;
			labirinto[y][x] = null;
			break;

		case 6: // RIGHT
			if (detecaoColisao(anim, x + 1, y))
				break;
			anim.setX(x + 1);
			labirinto[y][x + 1] = anim;
			labirinto[y][x] = null;
			break;

		case 8: // DOWN
			if (detecaoColisao(anim, x, y + 1))
				break;
			anim.setY(y + 1);
			labirinto[y + 1][x] = anim;
			labirinto[y][x] = null;
			break;

		case 4: // LEFT
			if (detecaoColisao(anim, x - 1, y))
				break;
			anim.setX(x - 1);
			labirinto[y][x - 1] = anim;
			labirinto[y][x] = null;
			break;
		default:
			break;
		}
	}

	/**
	 * Deteta colisoes. Retorna true se houver colisao, falso se nao houver.
	 */
	private boolean detecaoColisao(Animado anim, int x, int y) {
		if (x < 0 || y < 0 || x >= comprimento || y >= altura) // limites do labirinto
			return true;
		
		if (labirinto[y][x] == null) // se é um espaco vazio, pode avancar
			return false;

		// se o heroi vai apanhar a espada
		if (labirinto[y][x].getSimbolo() == 'E' && anim.getSimbolo() == 'H') {
			anim.setSimbolo('A');
			return false;
		}

		/*/ se for uma parede
		if (labirinto[y][x].getSimbolo() == 'X')
			return true;*/
		
		//por enquanto, qualquer outra situacao é colisao
		return true;
	}

}
