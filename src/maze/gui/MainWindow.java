package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
//import java.awt.GridBagLayout;
//import javax.swing.BoxLayout;
//import java.awt.CardLayout;
//import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import maze.logic.Maze.Direction;
import maze.logic.Maze.DragonType;


public class MainWindow {

	private GameHandler handler;
	private JFrame frame;
	private JTextField altura;
	private JTextField largura;
	private JTextField numDragoes;
	public static JTextArea mazeWindow;
	public JLabel lblMessageBox;
	public JButton btnCima;
	public JButton btnBaixo;
	public JButton btnEsquerda;
	public JButton btnDireita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		handler = new GameHandler(this);
		frame.getContentPane().add(handler);
		
		
		//Info para gerar o labirinto
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do labirinto");
		lblDimensoDoLabirinto.setBounds(30, 30, 110, 14);
		frame.getContentPane().add(lblDimensoDoLabirinto);
		
		JLabel lblNumeroDeDragoes = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNumeroDeDragoes.setBounds(30, 60, 110, 14);
		frame.getContentPane().add(lblNumeroDeDragoes);
		
		JLabel lblTipoDeDragoes = new JLabel("Tipo de drag\u00F5es");
		lblTipoDeDragoes.setBounds(30, 90, 110, 14);
		frame.getContentPane().add(lblTipoDeDragoes);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(175, 30, 12, 14);
		frame.getContentPane().add(lblX);
		
		altura = new JTextField();
		altura.setText("11");
		altura.setBounds(150, 27, 20, 20);
		frame.getContentPane().add(altura);
		altura.setColumns(10);
		
		largura = new JTextField();
		largura.setText("11");
		largura.setBounds(185, 27, 20, 20);
		frame.getContentPane().add(largura);
		largura.setColumns(10);
		
		numDragoes = new JTextField();
		numDragoes.setText("1");
		numDragoes.setBounds(150, 57, 20, 20);
		frame.getContentPane().add(numDragoes);
		numDragoes.setColumns(10);
		
		final JComboBox tipoDragao = new JComboBox();
		tipoDragao.setModel(new DefaultComboBoxModel(DragonType.values()));
		tipoDragao.setToolTipText("");
		tipoDragao.setBounds(150, 87, 173, 20);
		frame.getContentPane().add(tipoDragao);
		
		//botoes para as direcoes
		
		btnCima = new JButton("CIMA");
		btnCima.setEnabled(false);
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.UP);
			}
		});
		btnCima.setBounds(450, 225, 100, 35);
		frame.getContentPane().add(btnCima);
		
		btnEsquerda = new JButton("ESQUERDA");
		btnEsquerda.setEnabled(false);
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.LEFT);
			}
		});
		btnEsquerda.setBounds(390, 270, 100, 35);
		frame.getContentPane().add(btnEsquerda);
		
		btnDireita = new JButton("DIREITA");
		btnDireita.setEnabled(false);
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.RIGHT);
			}
		});
		btnDireita.setBounds(510, 270, 100, 35);
		frame.getContentPane().add(btnDireita);
		
		btnBaixo = new JButton("BAIXO");
		btnBaixo.setEnabled(false);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.DOWN);
			}
		});
		btnBaixo.setBounds(450, 315, 100, 35);
		frame.getContentPane().add(btnBaixo);
		
		//Acoes para o labirinto
		
		mazeWindow = new JTextArea();
		mazeWindow.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeWindow.setEditable(false);
		mazeWindow.setBounds(30, 143, 330, 345);
		frame.getContentPane().add(mazeWindow);
		
		lblMessageBox = new JLabel("Pode gerar um novo labirinto!");
		lblMessageBox.setVerticalAlignment(SwingConstants.TOP);
		lblMessageBox.setBounds(30, 499, 580, 20);
		frame.getContentPane().add(lblMessageBox);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alt,larg,numD;
				alt=Integer.parseInt(altura.getText());
				larg=Integer.parseInt(largura.getText());
				numD=Integer.parseInt(numDragoes.getText());
				maze.logic.MazeBuilder charMaze;
				try {
				charMaze = new maze.logic.MazeBuilder(alt,larg,numD);
				}
				catch(IllegalArgumentException exception)
				{
					lblMessageBox.setText(exception.getMessage());
					return;
				}
				handler.objMaze= new maze.logic.Maze(charMaze.getMaze());
				handler.dragonType=(DragonType) tipoDragao.getSelectedItem();
				mazeWindow.setText(handler.getDisplay());
				lblMessageBox.setText("Pode jogar!");
				btnBaixo.setEnabled(true);
				btnCima.setEnabled(true);
				btnEsquerda.setEnabled(true);
				btnDireita.setEnabled(true);
			}
		});
		btnGerarNovoLabirinto.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(!btnBaixo.isEnabled()) //basta fazer a verificacao para um botao, visto que estao todos no mesmo estado
					return; //nao recebe inputs enquanto nao estiver um jogo a decorrer
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					handler.play(Direction.LEFT);
					break;
					
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					handler.play(Direction.RIGHT);
					break;

				case KeyEvent.VK_UP:
				case KeyEvent.VK_W: 
					handler.play(Direction.UP);
					break;

				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S: 
					handler.play(Direction.DOWN);
					break;
				}
				//repaint();

				
			}
		});
		btnGerarNovoLabirinto.setBounds(422, 18, 148, 38);
		frame.getContentPane().add(btnGerarNovoLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(422, 78, 148, 38);
		frame.getContentPane().add(btnTerminarPrograma);
		
	}
}
