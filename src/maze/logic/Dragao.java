package maze.logic;

import java.awt.Point;

import java.util.concurrent.ThreadLocalRandom;

public class Dragao extends Animado {

	boolean acordado;
	
	Dragao(int x, int y)
	{
		super.setSimbolo('D');
		super.point(x,y);
		acordado = true;
	}
	
	public void mudarEstado(Maze jogo, int dragonFlag)
	{	if(dragonFlag == 2)
		{
			int y = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
			jogo.updateDragao(y);
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
				super.setSimbolo('d');;
				break;
			case 2: //move
				int y = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
				jogo.updateDragao(y);
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