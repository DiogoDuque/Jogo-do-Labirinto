package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;
import maze.logic.Maze.MazeStatus;

@SuppressWarnings("serial")
public class ShowStatus extends JPanel {
	
	public BufferedImage won;
	public BufferedImage lost;
	public BufferedImage play;
	public static boolean gWon=false;
	public static boolean gLost=false;
	

	public ShowStatus(MainWindow window)
	{
		try {
		won = ImageIO.read(new File("res/youWin.png"));
		lost = ImageIO.read(new File("res/gameover.png"));
		play = ImageIO.read(new File("res/photo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setStatus(Maze objMaze){
		if(objMaze.getStatus()==MazeStatus.Victory)
			gWon=true;
		else if(objMaze.getStatus()==MazeStatus.HeroDied)
			gLost=true;
		else 
			{gWon=false;
			gLost=false;}
			
	}
	
	public  void paintComponent(Graphics gr) {
		//estado de vitoria/derrota
		super.paintComponent(gr); // clears the background ...		
		if(gWon)
			gr.drawImage(won,0,0, null);
		else if(gLost)
			gr.drawImage(lost,0,0, null);
		else gr.drawImage(play,0,0, null);
	}
}
