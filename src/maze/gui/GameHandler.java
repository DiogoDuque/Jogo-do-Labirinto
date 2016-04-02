package maze.gui;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JPanel;

import maze.logic.Maze.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import maze.logic.*;

public class GameHandler extends JPanel {
	
	public maze.logic.Maze objMaze;
	public DragonType dragonType;
	private MainWindow window;
	public BufferedImage won;
	public BufferedImage lost;
	public BufferedImage play;
	public boolean gWon=false;
	public boolean gLost=false;
	
	GameHandler(final MainWindow window)
	{
		this.window=window;
		try {
			won =  ImageIO.read(new File("res/youWin.png"));
			lost =  ImageIO.read(new File("res/gameover.png"));
			play =  ImageIO.read(new File("res/photo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setStatus(Maze objMaze){
		if(objMaze.getStatus()==MazeStatus.Victory)
			gWon=true;
		if(objMaze.getStatus()==MazeStatus.HeroDied)
			gLost=true;
	}
	
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr); // clears the background ...		
		if(gWon)
			gr.drawImage(won,0,0, null);
		else if(gLost)
			gr.drawImage(lost,0,0, null);
		else gr.drawImage(play,0,0, null);
		
	}
	
	
	
	public void play(Direction direcao)
	{
		if(objMaze.getStatus()!=MazeStatus.HeroDied && objMaze.getStatus()!=MazeStatus.Victory)
		{
			objMaze.moveGeral(direcao, objMaze.getHero());
			if(objMaze.getStatus()!= MazeStatus.DragonDied && //se o dragao nao estiver morto e
				dragonType != DragonType.Static) 			//se tiver movimento ativo
					for(int i=0; i<objMaze.getDragonsSize(); i++)
					{
						objMaze.getDragonByIndex(i).mudarEstado(objMaze, dragonType);
					}
		}
		objMaze.proximityHeroDragon();		
		if(objMaze.getStatus()==MazeStatus.Victory)
		{
			window.lblMessageBox.setText(("O HEROI CONSEGUIU ESCAPAR!"));
			
			//disable dos butoes
			disableButtons();
		}
		
		if(objMaze.getStatus()==MazeStatus.HeroDied)
		{
			window.lblMessageBox.setText(("O HEROI MORREU..."));
		//disable dos butoes
			disableButtons();
		}
		MainWindow.mazeWindow.setText(getDisplay());
		setStatus(objMaze);
	}
	
	public String getDisplay() {
		HashMap<Point,General> maze = objMaze.getMaze();
		Point dims = objMaze.getDimensions();
		int height=dims.x, width=dims.y;
		String disp="";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (maze.get(new Point(i,j)) == null)
					disp += " ";
				else
				{
					if(maze.get(new Point(i,j)) instanceof Exit)
					{
						if(objMaze.getStatus()==MazeStatus.DragonDied)
							disp += 'S';
						else disp += 'X';
					}
					else disp += maze.get(new Point(i,j)).getSymbol();
				}
				disp += " ";
			}
			disp += "\n";
		}
		return disp;
	}
	
	private void disableButtons()
	{
		window.btnBaixo.setEnabled(false);
		window.btnCima.setEnabled(false);
		window.btnEsquerda.setEnabled(false);
		window.btnDireita.setEnabled(false);
	}
}
