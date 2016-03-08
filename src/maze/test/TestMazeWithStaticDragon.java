package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import java.awt.Point;

public class TestMazeWithStaticDragon {
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
			        { 'X', ' ', ' ', 'H', 'S' }, 
		         	{ 'X', ' ', 'X', ' ', 'X' },
		         	{ 'X', 'E', ' ', 'D', 'X' },
		        	{ 'X', 'X', 'X', 'X', 'X' } };
	@Test //a
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		maze.moveHeroLeft();
		assertEquals(new Point(1, 2), maze.getHeroPosition());
	}
	
	@Test //b
	public void testMoveHeroAgainstWall()
	{
		Maze maze = new Maze(m1);
		maze.moveHeroUp();
		assertEquals(new Point(1, 3), maze.getHeroPosition());
	}

	@Test //c
	public void testHeroCatchSword()
	{
		Maze maze = new Maze(m1);
		for(int i=0; i<=2; i++)
			maze.moveHeroLeft();
		for(int i=0; i<=2; i++)
			maze.moveHeroDown();
		assertEquals('A',maze.getHeroiSimbolo());
	}
	
	/*@Test //d
	 public void testHeroDies() {
		Maze maze = new Maze(m1);
		assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
		maze.moveHeroDown();
		assertEquals(MazeStatus.HeroDied, maze.getStatus());
	}//*/
	
	/*@Test //e
	public void testKillDragon()
	{
		Maze maze = new Maze(m1);
		for(int i=0; i<=2; i++)
			maze.moveHeroLeft();
		for(int i=0; i<=2; i++)
			maze.moveHeroDown();
		maze.moveHeroRight();
		assertEquals(MazeStatus.DragonDied, maze.getStatus());
	}//*/
	
	/*@Test //f
	public void testVictory()
	{
		Maze maze = new Maze(m1);
		for(int i=0; i<=2; i++)
			maze.moveHeroLeft();
		for(int i=0; i<=2; i++)
			maze.moveHeroDown();
		for(int i=0; i<=2; i++)
			maze.moveHeroRight();
		for(int i=0; i<=2; i++)
			maze.moveHeroUp();
		maze.moveHeroRight();
		assertEquals(MazeStatus.Victory, maze.getStatus());
	}//*/
	
	@Test //g
	public void testHeroMoveToExitUnarmed()
	{
		Maze maze = new Maze(m1);
		maze.moveHeroRight();
		assertEquals(new Point(1, 3), maze.getHeroPosition());		
	}
	
	@Test //h
	public void testHeroMoveToExitArmedWithoutDragonKilled()
	{
		Maze maze = new Maze(m1);
		for(int i=0; i<=2; i++)
			maze.moveHeroLeft();
		for(int i=0; i<=2; i++)
			maze.moveHeroDown();
		for(int i=0; i<=2; i++)
			maze.moveHeroUp();
		for(int i=0; i<=3; i++)
			maze.moveHeroRight();
		assertEquals(new Point(1, 3), maze.getHeroPosition());
	}
}