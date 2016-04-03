package maze.gui;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JPanel;

import maze.logic.Exit;
import maze.logic.General;
import maze.logic.Maze.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameHandler extends JPanel {
	
	public maze.logic.Maze objMaze;
	
	public DragonType dragonType;
	private MainWindow window;
	public BufferedImage dragon, dragonAndSword, asleepDragonAndSword, asleepDragon, hero, armedHero, sword, wall, exit, none;
	
	GameHandler(final MainWindow window)
	{
		this.window=window;
		try {
			hero = ImageIO.read(new File("res/hero.png"));
			armedHero = ImageIO.read(new File("res/armedHero.png"));
			dragon = ImageIO.read(new File("res/dragon.png"));
			asleepDragon = ImageIO.read(new File("res/asleepDragon.png"));
			asleepDragonAndSword = ImageIO.read(new File("res/asleepDragonAndSword.png"));
			dragonAndSword = ImageIO.read(new File("res/dragonAndSword.png"));
			sword = ImageIO.read(new File("res/sword.png"));
			wall = ImageIO.read(new File("res/wall.png"));
			exit = ImageIO.read(new File("res/exit.png"));
			none = ImageIO.read(new File("res/none.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void paintComponent(Graphics gr) {
		//labirinto
		super.paintComponent(gr); // clears the background ...
		HashMap<Point,General> maze = objMaze.getMaze();
		int height=objMaze.getDimensions().x, width=objMaze.getDimensions().y, imgDim=32;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(maze.get(new Point(i,j))==null)
					gr.drawImage(none, j*imgDim, i*imgDim, null);
				else switch(maze.get(new Point(i,j)).getSymbol())
				{
				case 'H':
					gr.drawImage(hero, j*imgDim, i*imgDim, null);
					break;
				case 'A':
					gr.drawImage(armedHero, j*imgDim, i*imgDim, null);
					break;
					
				case 'D':
					gr.drawImage(dragon, j*imgDim, i*imgDim, null);
					break;
				case'd':
					gr.drawImage(asleepDragon, j*imgDim, i*imgDim, null);
					break;
				case'F':
					gr.drawImage(dragonAndSword, j*imgDim, i*imgDim, null);
					break;
				case 'f':
					gr.drawImage(asleepDragonAndSword, j*imgDim, i*imgDim, null);
					break;
					
				case 'E':
					gr.drawImage(sword, j*imgDim, i*imgDim, null);
					break;
					
				case 'X':
					gr.drawImage(wall, j*imgDim, i*imgDim, null);
					break;
				case 'S':
					gr.drawImage(exit, j*imgDim, i*imgDim, null);
					break;
					
				default:
					break;
				}
			}
		}//*/
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
		repaint();
		ShowStatus.setStatus(objMaze);
		repaint();
	}
	
	/*public String getDisplay() {
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
	}*/
	
	private void disableButtons()
	{
		window.btnBaixo.setEnabled(false);
		window.btnCima.setEnabled(false);
		window.btnEsquerda.setEnabled(false);
		window.btnDireita.setEnabled(false);
	}
}
