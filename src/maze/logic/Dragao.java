package maze.logic;

import java.util.concurrent.ThreadLocalRandom;

import maze.cli.main.DragonType;

public class Dragao extends Animado {

	boolean acordado;
	
	Dragao(int x, int y)
	{
		super.setSimbolo('D');
		super.point(x,y);
		acordado = true;
	}
	
	public void mudarEstado(Maze jogo, DragonType dragonType)
	{	if(dragonType == DragonType.RandomMovement)
		{
			int y = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
			jogo.updateAnimado(y,this);
			return;
		}
			
		
		if(acordado)
		{
			int x = ThreadLocalRandom.current().nextInt(0, 2+1);
			switch(x)
			{
			case 0: //manter
				break;
			case 1: //adormece
				acordado = false;
				super.setSimbolo('d');
				break;
			case 2: //move
				int y = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
				jogo.updateAnimado(y,this);
				break;
				
			}
		}
		else { //se estiver adormecido
			int x = ThreadLocalRandom.current().nextInt(0, 1+1);
			switch(x)
			{
			case 0: //manter
				break;
			case 1: //acorda
				acordado=true;
				super.setSimbolo('D');
				break;
			}
		}
	}
}