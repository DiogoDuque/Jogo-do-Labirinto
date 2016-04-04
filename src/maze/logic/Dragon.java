/**
 * Esta classe é usada para representar os Dragões presente no labirinto.
 */

package maze.logic;

import java.awt.Point;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import maze.cli.Main;

public class Dragon extends General {

	public boolean acordado;
	
	public Dragon(int x, int y, HashMap<Point,General> maze)
	{
		super(x,y,'D',maze);
		acordado = true;
	}
	
	/**
	 * Possibilita que o dragão acorde(se estiver adormecido), adormeça(se estiver acordado), ou se mantenha no mesmo estado.
	 * Caso este esteja (e permaneça) acordado, poderá mover-se.
	 * @param jogo objeto que contém o labirinto e as funções associadas à sua lógica, neste caso para permitir o uso de moveGeral().
	 * @param dragonType modo de jogo associado ao movimento dos dragões.
	 */
	public void mudarEstado(Maze jogo, Maze.DragonType dragonType)
	{	
		int tryCounter=20; //para impedir que as "tentativas de movimento forcadas" nao dessem loop infinito
		
		if(dragonType == Maze.DragonType.RandomMovement)
		{
			int y = 2*ThreadLocalRandom.current().nextInt(1, 4 + 1);
			while(!jogo.moveGeral(Main.convertIntToDirection(y),this) && tryCounter<0) {tryCounter--;}
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
				while(!jogo.moveGeral(Main.convertIntToDirection(y),this) && tryCounter>0) {tryCounter--;} //obrigar o dragao a mover-se
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
	
	/**
	 * adormece o dragao.
	 */
	public void asleep(){
		acordado = false;
		if(super.getSymbol()=='F')
			super.setSymbol('f');
		else super.setSymbol('d');
	}
	
	/**
	 * acorda o dragão.
	 */
	public void awake(){
		acordado = true;
		if(super.getSymbol()=='f')
			super.setSymbol('F');
		else super.setSymbol('D');
		
	}

	//OVERRIDES DE GENERAL
	
	/**
	 * override da funcao da superclasse, com uma alteracao para permitir que o dragao esteja "em cima" da espada.
	 * @see General
	 */
	public void setX(int x)
	{
		maze.remove(p); //remover dragao do hashmap para depois o reposicionar
		
		if(symbol=='F') //se estivesse a pisar a espada
		{
			symbol='D';
			General temp = maze.remove(new Point(-1,-1));
			maze.put(p,temp); //repor a espada
		}
		
		p = new Point (x,p.y);
		General temp = maze.put(p, this);
		
		if(temp instanceof Sword) //se o dragao esta a pisar a espada
		{
			symbol='F';
			maze.put(new Point(-1,-1), temp); //espada fica 'fora' do maze, na posicao (-1,-1)
		}
	}
	
	/**
	 * override da funcao da superclasse, com uma alteracao para permitir que o dragao esteja "em cima" da espada.
	 * @see General
	 */
	public void setY(int y)
	{
		maze.remove(p); //remover dragao do hashmap para depois o reposicionar
		
		if(symbol=='F') //se estivesse a pisar a espada
		{
			symbol='D';
			maze.put(p,maze.remove(new Point(-1,-1))); //repor a espada
		}
		
		p = new Point (p.x,y);
		General temp = maze.put(p, this);
		
		if(temp instanceof Sword) //se o dragao esta a pisar a espada
		{
			symbol='F';
			maze.put(new Point(-1,-1), temp); //espada fica 'fora' do maze, na posicao (-1,-1)
		}
	}

}