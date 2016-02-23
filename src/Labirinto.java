
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
					labirinto[i][j]=new Parede(i,j);
			}
			else {
				labirinto[i][0] = new Parede(i,0);
				labirinto[i][comprimento-1] = new Parede(i,comprimento-1);
			}
		}
		
		//paredes interiores
		criarParedes(2,2,4,3);
		criarParedes(6,2,8,3);
		criarParedes(2,5,4,5);
		criarParedes(6,5,7,5);
		criarParedes(2,7,7,7);
		
	}
	
	public void criarParedes(int alt1, int comp1, int alt2, int comp2)
	{
		for(int i = alt1; i <= alt2; i++)
			for(int j = comp1; j <= comp2; j++)
				labirinto[i][j] = new Parede(i,j);
	}
	
	public void display()
	{
		for(int i=0; i<altura; i++)
		{
			for(int j=0; j<comprimento; j++)
			{
				if(labirinto[i][j]==null)
					System.out.print(" ");
				else System.out.print(labirinto[i][j].simbolo);
			}
			System.out.println();
		}
	}
}
