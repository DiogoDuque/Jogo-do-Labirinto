package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.cli.main.DragonType;

import maze.logic.*;

public class TestMazeWithDynamicDragon {
	
	
	
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' },
			        { 'X', ' ', 'H', ' ', 'S' }, 
			        { 'X', ' ', 'X', ' ', 'X' },
			        { 'X', 'E', ' ', 'D', 'X' }, 
			        { 'X', 'X', 'X', 'X', 'X' } };
	
	char[][] m2 = { { 'X', 'X', 'X', 'X', 'X' },
					{ 'X', 'H', ' ', ' ', 'S' }, 
					{ 'X', ' ', 'D', ' ', 'X' },
					{ 'X', 'E', ' ', ' ', 'X' }, 
					{ 'X', 'X', 'X', 'X', 'X' } };
	
	@Test(timeout=1000)
	public void isDragonSleeping(){
		boolean outcome1 = false;		
		
		while (! outcome1) {
			Maze maze = new Maze(m1);
			
			
			maze.getDragaoIndex(0).asleep();
			
		if(!maze.getDragaoIndex(0).acordado){
			outcome1 = true;
		}
			else
				fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void isDragonAwaking(){
		boolean outcome1 = false;		
		
		while (! outcome1) {
			Maze maze = new Maze(m1);
			
			maze.getDragaoIndex(0).asleep();
			maze.getDragaoIndex(0).awake();
			
		if(maze.getDragaoIndex(0).acordado){
			outcome1 = true;
		}
			else
				fail("some error message");
		}
	}
	
	
	
	@Test(timeout=1000)
	public void isDragonMoveRandomly(){
		boolean outcome1 = false;		
		
		while (! outcome1) {
			Point P= new Point(2,2);
			Maze maze = new Maze(m2);
			DragonType dragonType = DragonType.RandomMovement;
			maze.getDragaoIndex(0).mudarEstado(maze, dragonType);
			
		if(maze.getDragaoIndex(0).p.x == P.x && maze.getDragaoIndex(0).p.y != P.y){
			outcome1 = true;
		} else if(maze.getDragaoIndex(0).p.x != P.x && maze.getDragaoIndex(0).p.y == P.y){
			outcome1 = true;
		} else if(maze.getDragaoIndex(0).p.x != P.x && maze.getDragaoIndex(0).p.y != P.y){
			outcome1 = true;
		} else
		
				fail("some error message");
		}
		
	}
		
	}
	
	
	

