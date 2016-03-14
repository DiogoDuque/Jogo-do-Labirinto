package maze.logic;

import java.util.concurrent.ThreadLocalRandom;
import maze.cli.Main;

public class Dragao extends Animado {

	public boolean acordado;
	
	Dragao(int x, int y)
	{
		super.setSimbolo('D');
		super.point(x,y);
		acordado = true;
	}
	
	public void mudarEstado(Maze jogo, Maze.DragonType dragonType)
	{	
		int tryCounter=20; //para impedir que as "tentativas de movimento forcadas" nao dessem loop infinito
		
		if(dragonType == Maze.DragonType.RandomMovement)
		{
			int y = 2*ThreadLocalRandom.current().nextInt(1, 4 + 1);
			while(!jogo.updateAnimado(Main.convertIntToDirection(y),this) && tryCounter<0) {tryCounter--;}
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
				int y = 2*ThreadLocalRandom.current().nextInt(1, 4 + 1);
				while(!jogo.updateAnimado(Main.convertIntToDirection(y),this) && tryCounter>0) {tryCounter--;} //obrigar o dragao a mover-se
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