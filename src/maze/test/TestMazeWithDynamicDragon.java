package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Maze.Direction;
import maze.logic.Maze.DragonType;

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
	public void isDragonWithSword(){
		boolean outcome1 = false;	
		boolean outcome2 = false;
		
		while (! outcome1 && !outcome2) {
			Maze maze = new Maze(m2);
			
			
			maze.moveGeral(Direction.DOWN, maze.getDragonByIndex(0));
			maze.moveGeral(Direction.LEFT, maze.getDragonByIndex(0));
		if(maze.getDragonByIndex(0).getSymbol()=='F'){
			outcome1 = true;
		}
			else
				fail("some error message");
		maze.moveGeral(Direction.RIGHT, maze.getDragonByIndex(0));
		if(maze.getDragonByIndex(0).getSymbol()=='D'){
			outcome2 = true;
		}
		else
			fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void isDragonSleeping(){
		boolean outcome1 = false;		
		
		while (! outcome1) {
			Maze maze = new Maze(m1);
			
			
			maze.getDragonByIndex(0).asleep();
			
		if(!maze.getDragonByIndex(0).acordado){
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
			
			maze.getDragonByIndex(0).asleep();
			maze.getDragonByIndex(0).awake();
			
		if(maze.getDragonByIndex(0).acordado){
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
			Point p= new Point(2,2);
			Maze maze = new Maze(m2);
			DragonType dragonType = DragonType.RandomMovement;
			maze.getDragonByIndex(0).mudarEstado(maze, dragonType);
			
		if(maze.getDragonByIndex(0).getX() == p.x && maze.getDragonByIndex(0).getY() != p.y){
			outcome1 = true;
		} else if(maze.getDragonByIndex(0).getX() != p.x && maze.getDragonByIndex(0).getY() == p.y){
			outcome1 = true;
		} else if(maze.getDragonByIndex(0).getX() != p.x && maze.getDragonByIndex(0).getY() != p.y){
			outcome1 = true;
		} else
		
				fail("some error message");
		}
		
	}
		
	}
	
	
	
