package maze.logic;

import java.util.concurrent.ThreadLocalRandom;

import maze.cli.main.DragonType;

public class Dragao extends Animado {

	public boolean acordado;
	int tryCounter=0; //para impedir que as "tentativas de movimento forcadas" nao dessem loop infinito
	
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
			while(!jogo.updateAnimado(y,this) && tryCounter<20) {tryCounter++;}
			//pode mover-se 
			
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
				asleep();
				break;
			case 2: //move
				int y = 2*ThreadLocalRandom.current().nextInt(1, 5 + 1);
				while(!jogo.updateAnimado(y,this) && tryCounter<8) {tryCounter++;} //obrigar o dragao a mover-se
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
				awake();
				break;
			}
		}
	}
	
	public boolean asleep(){
		acordado = false;
		super.setSimbolo('d');
		return true;
		
	}
	public boolean awake(){
		acordado = true;
		super.setSimbolo('D');
		return true;
		
	}
}