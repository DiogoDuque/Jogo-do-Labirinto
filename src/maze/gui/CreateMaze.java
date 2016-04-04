package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;

import maze.logic.Dragon;
import maze.logic.Exit;
import maze.logic.General;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Wall;
import maze.logic.Maze.*;
import maze.logic.Sword;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateMaze extends JFrame implements MouseListener {

	private JFrame frame;
	private JTextField altura;
	private JTextField largura;
	private JTextField numDragoes;
	private static maze.logic.Maze objMaze;
	private static GameHandler handler;
	private Type type;
	private boolean isMazeValidated;

	private static JFrame f;	
	public enum Type
	{
		Dragon, Wall, Hero, Sword, Exit
	} 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMaze window = new CreateMaze();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateMaze() {
		initialize();
		
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final CreateMaze tempRef = this;
		frame = new JFrame();
		frame.setTitle("Cria o teu labirinto!");
		frame.setBounds(20, 10, 1031, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(tempRef);
		handler = new GameHandler(tempRef);
		frame.getContentPane().add(handler);
		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSelectOne = new JLabel("Escolhe um!");
		lblSelectOne.setBounds(907, 11, 74, 23);
		panel.add(lblSelectOne);
		
		altura = new JTextField();
		altura.setBounds(95, 21, 21, 20);
		altura.setText("11");
		panel.add(altura);
		altura.setColumns(10);
		
		largura = new JTextField();
		largura.setBounds(132, 21, 21, 20);
		largura.setText("11");
		panel.add(largura);
		largura.setColumns(10);
		
		JLabel lblX = new JLabel("  x");
		lblX.setBounds(115, 22, 21, 19);
		panel.add(lblX);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setBounds(27, 24, 58, 14);
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblTamanho);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(164, 20, 58, 23);
		panel.add(btnOk);
		
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de Drag\u00F5es");
		lblNmeroDeDrages.setBounds(27, 58, 109, 20);
		lblNmeroDeDrages.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNmeroDeDrages);
		
		numDragoes = new JTextField();
		numDragoes.setBounds(153, 59, 21, 20);
		numDragoes.setText("1");
		panel.add(numDragoes);
		numDragoes.setColumns(10);
		
		
		JButton btnCheckLabirinto = new JButton("Testar Labirinto!");
		btnCheckLabirinto.setBounds(859, 272, 145, 74);
		panel.add(btnCheckLabirinto);
		btnCheckLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateMaze(handler.objMaze))
					isMazeValidated =true;
					
					
			}});
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(859, 45, 145, 22);
		comboBox.setMaximumRowCount(5);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Parede", "Her\u00F3i", "Drag\u00E3o", "Sa\u00EDda", "Espada"}));
		panel.add(comboBox);
		type = Type.Wall;
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 0)
					type = Type.Wall;
				if (comboBox.getSelectedIndex() == 1)
					type = Type.Hero;
				if (comboBox.getSelectedIndex() == 2)
					type = Type.Dragon;
				if (comboBox.getSelectedIndex() == 3)
					type = Type.Exit;
				if (comboBox.getSelectedIndex() == 4)
					type = Type.Sword;
				
			}
		});
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 88, 779, 545);
		panel.add(panel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(DragonType.values()));
		comboBox_1.setMaximumRowCount(3);
		comboBox_1.setBounds(859, 133, 146, 23);
		panel.add(comboBox_1);
		
		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(859, 88, 145, 34);
		panel.add(lblTipoDeDrages);
		
		JButton btnPlay = new JButton("Jogar!");
		btnPlay.setBounds(859, 515, 145, 74);
		panel.add(btnPlay);
		panel_1.addMouseListener(tempRef);
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isMazeValidated){
					
				
				frame.setVisible(false);}
					
			}});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alt,larg;
				
				alt=Integer.parseInt(altura.getText());
				larg=Integer.parseInt(largura.getText());
				
				if(alt > 17){
					altura.setText("17");
					alt=17;}
				if(larg > 19){
					altura.setText("19");
					larg=19;}
				
				handler.objMaze= new maze.logic.Maze(larg,alt);
				handler.setVisible(true);
				handler.setBounds(30, 115, 638, 723);
				
				
				handler.repaint();	
				
			}});

}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//handler.setBounds(30, 115, 638, 723)
		{
			int x = (int) ((e.getX()-20)/32 );
			int y = (int)((e.getY())/32 -1);
		if(type == Type.Wall){
		Wall wall = new Wall(y,x,handler.objMaze.getMaze());
		handler.objMaze.getMaze().put(new Point(y,x),wall);}
		if(type == Type.Hero){
			Hero hero = new Hero(y,x,handler.objMaze.getMaze());
			handler.objMaze.setHero(hero);
			handler.objMaze.getMaze().put(new Point(y,x),hero);}
		if(type == Type.Sword){
			Sword sword = new Sword(y,x,handler.objMaze.getMaze());
			handler.objMaze.getMaze().put(new Point(y,x),sword);}
		if(type == Type.Exit){
			Exit exit = new Exit(y,x,handler.objMaze.getMaze());
			handler.objMaze.getMaze().put(new Point(y,x),exit);}
		if(type == Type.Dragon){
			Dragon dragon = new Dragon(y,x,handler.objMaze.getMaze());
			handler.objMaze.getMaze().put(new Point(y,x),dragon);
			handler.objMaze.getDragons().add(dragon);}
		}
		
		handler.repaint();				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean validateMaze(Maze maze){
		int height=maze.getHeight(), width=maze.getWidth();
		char[][] clonedMaze = new char[height][width];
		Boolean foundExit=null, foundSword=null;
		Integer numDragons=0;
		Point hero=null;
		
		//clone maze
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
			{
				if(maze.getMaze().get(new Point(i,j)) != null)
				clonedMaze[i][j]=maze.getMaze().get(new Point(i,j)).getSymbol();
				switch(clonedMaze[i][j])
				{
				case 'D':
					numDragons++;
					break;
				case 'H':
					if(hero != null)
						return false; //mais de 1 hero
					hero=new Point(i,j);
					break;
				case 'E':
					if(foundSword != null) //mais de 1 espada
						return false;
					foundSword=false;
					break;
				case 'S':
					if(foundExit != null) //mais de 1 espada
						return false;
					foundExit=false;
					break;
				default:
					break;
				}
			}
		
		if(numDragons==0) //se ainda nao tem dragoes
			return false;
		
		if(hero==null || foundSword==null || foundExit==null) //falta espada, saida e/ou heroi
			return false;
		
		return  auxValidateMaze(clonedMaze, hero, numDragons, foundSword, foundExit);
	}
	
	private boolean auxValidateMaze(char[][] clonedMaze, Point hero, Integer numDragons, Boolean foundSword, Boolean foundExit)
	{
		if(numDragons==0 && foundSword==true && foundExit==true) //se todos os objetos estiverem alcancaveis
			return true;
		
		if(hero.x<=0 || hero.y<=0 || hero.x>=clonedMaze.length || hero.y>=clonedMaze[0].length) //se estiver out of bounds
			return false;
		
		switch(clonedMaze[hero.x][hero.y])
		{
		case 'H':
		case 'X':
			return false;
			
		case 'D':
			numDragons--;
			break;
			
		case 'E':
			foundSword=true;
			break;
			
		case 'S':
			foundExit=true;
			break;
			
		default:
			break;
		}
		
		clonedMaze[hero.x][hero.y]='H'; //marcar como visitado
		
		//tentar à volta
		if (auxValidateMaze(clonedMaze,new Point(hero.x,hero.y-1), numDragons, foundSword, foundExit))
			return true;
		else if(auxValidateMaze(clonedMaze,new Point(hero.x,hero.y+1), numDragons, foundSword, foundExit))
			return true;
		else if(auxValidateMaze(clonedMaze,new Point(hero.x-1,hero.y), numDragons, foundSword, foundExit))
			return true;
		else return(auxValidateMaze(clonedMaze,new Point(hero.x+1,hero.y), numDragons, foundSword, foundExit));
	}
	
	public Maze getCreatedMaze(){
		return objMaze;
	}
	public static GameHandler getHandler(){
		return handler;
	}

//	public static void construct(int size) {
//		f = new JFrame("Constroi o teu labirinto");
//		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		f.setPreferredSize(new Dimension(620, 670));
//		f.getContentPane().setLayout(new BorderLayout());
//		
//		
//		f.getContentPane().add(panel, BorderLayout.CENTER);
//		f.getContentPane().add(panel2, BorderLayout.SOUTH);
//		f.pack();
//		f.setResizable(true);
//		f.setLocationRelativeTo(null);
//		f.setVisible(true);
//		f.setFocusable(true);
//		f.requestFocus();
//	}
}
