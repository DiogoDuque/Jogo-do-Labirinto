
public class Labirinto {

	private int altura;
	private int comprimento;
	private Geral[][] labirinto;
	
	public Labirinto()
	{
		this.altura=10;
		this.comprimento=10;
		this.labirinto = new Geral[altura][comprimento];
		
		//paredes exteriores
		for(int i = 0; i < altura; i++){
			if(i==0 || i==altura-1)
			{
				for(int j = 0; j<comprimento; j++)
					labirinto[i][j]=new Parede();
			}
			else {
				labirinto[i][0] = new Parede();
				labirinto[i][comprimento-1] = new Parede();
			}
		}
		
		//paredes interiores
		criarParedes(2,2,4,3);
		criarParedes(6,2,8,3);
		criarParedes(2,5,4,5);
		criarParedes(6,5,7,5);
		criarParedes(2,7,7,7);
		
		//criar heroi, dragao e espada
		labirinto[1][1]=new Heroi();
		labirinto[3][1]=new Dragao();
		labirinto[8][1]=new Espada();
	}
	
	/**
	 * Cria varios objetos Parede entre as alturas alt1 e alt2
	 * e os comprimentos comp1 e comp2
	 */
	public void criarParedes(int alt1, int comp1, int alt2, int comp2)
	{
		for(int i = alt1; i <= alt2; i++)
			for(int j = comp1; j <= comp2; j++)
				labirinto[i][j] = new Parede();
	}
	
	/**
	 * Mostra o labirinto e o seu conteudo
	 */
	public void display()
	{
		for(int i=0; i<altura; i++)
		{
			for(int j=0; j<comprimento; j++)
			{
				if(labirinto[i][j]==null)
					System.out.print(" ");
				else System.out.print(labirinto[i][j].getSimbolo());
			}
			System.out.println();
		}
	}
	
	/**
	 * [Incompleta] Atualiza a posicao de acordo com a direcao recebida,
	 * caso seja possivel (e nao vá contra uma parede)
	*/
	public void updateHeroi(int direcao)
 {
		switch (direcao) {
		case 1: //UP
			//handle
			break;
		case 2: //RIGHT
			//handle
			break;
		case 3: //DOWN
			//handle
			break;
		case 4: //LEFT
			//handle
			break;
		default:
			break;
		}
	}
}
