package maze.gui;

import maze.logic.Maze.*;

public class GameHandler {
	
	public static maze.logic.Maze objMaze;
	public static DragonType dragonType;
	
	public static void play(MainWindow window, Direction direcao)
	{
		if(objMaze.getStatus()!=MazeStatus.HeroDied && objMaze.getStatus()!=MazeStatus.Victory)
		{
			objMaze.updateAnimado(direcao, objMaze.getHeroi());
			if(objMaze.getStatus()!= MazeStatus.DragonDied && //se o dragao nao estiver morto e
				dragonType != DragonType.Static) 			//se tiver movimento ativo
					for(int i=0; i<objMaze.getDragoesSize(); i++)
					{
						objMaze.getDragaoIndex(i).mudarEstado(objMaze, dragonType);
					}
		}
		else {		
		if(objMaze.getStatus()==MazeStatus.Victory)
			window.lblMessageBox.setText(("O HEROI CONSEGUIU ESCAPAR!"));
		else window.lblMessageBox.setText(("O HEROI MORREU..."));
		//disable dos butoes
		window.btnBaixo.setEnabled(false);
		window.btnCima.setEnabled(false);
		window.btnEsquerda.setEnabled(false);
		window.btnDireita.setEnabled(false);
		}
		MainWindow.mazeWindow.setText(maze.cli.Main.getDisplay(objMaze.getMaze()));
	}
}
