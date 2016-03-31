package maze.gui;

import java.awt.Point;
import java.util.HashMap;

import maze.logic.General;
import maze.logic.Maze.*;
import maze.logic.Exit;

public class GameHandler {
	
	public static maze.logic.Maze objMaze;
	public static DragonType dragonType;
	
	public static void play(MainWindow window, Direction direcao)
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
		{		
		if(objMaze.getStatus()==MazeStatus.Victory){
			window.lblMessageBox.setText(("O HEROI CONSEGUIU ESCAPAR!"));
		//disable dos butoes
				window.btnBaixo.setEnabled(false);
				window.btnCima.setEnabled(false);
				window.btnEsquerda.setEnabled(false);
				window.btnDireita.setEnabled(false);}
		
		if(objMaze.getStatus()==MazeStatus.HeroDied){
			window.lblMessageBox.setText(("O HEROI MORREU..."));
		//disable dos butoes
		window.btnBaixo.setEnabled(false);
		window.btnCima.setEnabled(false);
		window.btnEsquerda.setEnabled(false);
		window.btnDireita.setEnabled(false);}
		}
		MainWindow.mazeWindow.setText(getDisplay());
	}
	
	public static String getDisplay() {
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
}
