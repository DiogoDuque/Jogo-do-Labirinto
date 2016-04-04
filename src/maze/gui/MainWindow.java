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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import maze.logic.Maze.Direction;
import maze.logic.Maze.DragonType;



public class MainWindow {

	private GameHandler handler;
	private ShowStatus showStatus;
	private JFrame frame;
	private JTextField altura;
	private JTextField largura;
	private JTextField numDragoes;
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
		
		final MainWindow tempRef = this; //para uso na inicializacao do GameHandler
		
		frame = new JFrame();
		frame.setBounds(20, 20, 1031, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		showStatus = new ShowStatus(this);
		showStatus.setVisible(true);
		showStatus.setBounds(750, 390, 100, 100);
		frame.getContentPane().add(showStatus);
		
		
		//Info para gerar o labirinto
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do labirinto");
		lblDimensoDoLabirinto.setBounds(30, 30, 124, 14);
		frame.getContentPane().add(lblDimensoDoLabirinto);
		
		JLabel lblNumeroDeDragoes = new JLabel("N\u00FAmero de drag\u00F5es");
		lblNumeroDeDragoes.setBounds(30, 60, 124, 14);
		frame.getContentPane().add(lblNumeroDeDragoes);
		
		JLabel lblTipoDeDragoes = new JLabel("Tipo de drag\u00F5es");
		lblTipoDeDragoes.setBounds(30, 90, 124, 14);
		frame.getContentPane().add(lblTipoDeDragoes);
		
		JLabel lblX = new JLabel("x");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(185, 30, 10, 14);
		frame.getContentPane().add(lblX);
		
		altura = new JTextField();
		altura.setText("11");
		altura.setBounds(160, 27, 20, 20);
		frame.getContentPane().add(altura);
		altura.setColumns(10);
		
		largura = new JTextField();
		largura.setText("11");
		largura.setBounds(200, 27, 20, 20);
		frame.getContentPane().add(largura);
		largura.setColumns(10);
		
		numDragoes = new JTextField();
		numDragoes.setText("1");
		numDragoes.setBounds(160, 55, 20, 20);
		frame.getContentPane().add(numDragoes);
		numDragoes.setColumns(10);
		
		final JComboBox tipoDragao = new JComboBox();
		tipoDragao.setModel(new DefaultComboBoxModel(DragonType.values()));
		tipoDragao.setToolTipText("");
		tipoDragao.setBounds(160, 85, 173, 20);
		frame.getContentPane().add(tipoDragao);
		
		//botoes para as direcoes
		
		btnCima = new JButton("CIMA");
		btnCima.setEnabled(false);
		receiveInputs(btnCima);
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.UP);
				showStatus.repaint();
			}
		});
		btnCima.setBounds(750, 225, 100, 35);
		frame.getContentPane().add(btnCima);
		
		btnEsquerda = new JButton("ESQUERDA");
		btnEsquerda.setEnabled(false);
		receiveInputs(btnEsquerda);
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.LEFT);
				showStatus.repaint();
			}
		});
		btnEsquerda.setBounds(690, 270, 100, 35);
		frame.getContentPane().add(btnEsquerda);
		
		btnDireita = new JButton("DIREITA");
		btnDireita.setEnabled(false);
		receiveInputs(btnDireita);
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.RIGHT);
				showStatus.repaint();
			}
		});
		btnDireita.setBounds(810, 270, 100, 35);
		frame.getContentPane().add(btnDireita);
		
		btnBaixo = new JButton("BAIXO");
		btnBaixo.setEnabled(false);
		receiveInputs(btnBaixo);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.play(Direction.DOWN);
				showStatus.repaint();
			}
		});
		btnBaixo.setBounds(750, 315, 100, 35);
		frame.getContentPane().add(btnBaixo);
		
		lblMessageBox = new JLabel("Pode gerar um novo labirinto!");
		lblMessageBox.setVerticalAlignment(SwingConstants.TOP);
		lblMessageBox.setBounds(30, 694, 580, 20);
		frame.getContentPane().add(lblMessageBox);
		
		
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		receiveInputs(btnGerarNovoLabirinto);
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alt,larg,numD;
				
				
				alt=Integer.parseInt(altura.getText());
				larg=Integer.parseInt(largura.getText());
				numD=Integer.parseInt(numDragoes.getText());
				maze.logic.MazeBuilder charMaze;
				if(alt > 17)
					alt=17;
				if(larg > 19)
					larg=19;
				try {
				charMaze = new maze.logic.MazeBuilder(alt,larg,numD);
				}
				catch(IllegalArgumentException exception)
				{
					lblMessageBox.setText(exception.getMessage());
					return;
				}
				lblMessageBox.setText("Pode jogar!");
				btnBaixo.setEnabled(true);
				btnCima.setEnabled(true);
				btnEsquerda.setEnabled(true);
				btnDireita.setEnabled(true);
				
				handler = new GameHandler(tempRef);
				handler.objMaze= new maze.logic.Maze(charMaze.getMaze());
				handler.dragonType=(DragonType) tipoDragao.getSelectedItem();
				handler.setVisible(true);
				handler.setBounds(30, 115, 638, 723);
				frame.getContentPane().add(handler);
				
				ShowStatus.setStatus(handler.objMaze);
				handler.repaint();
				showStatus.repaint();
				
			}
		});
		
		btnGerarNovoLabirinto.setBounds(722, 18, 148, 38);
		frame.getContentPane().add(btnGerarNovoLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(722, 78, 148, 38);
		frame.getContentPane().add(btnTerminarPrograma);
		
	}
	
	public ShowStatus getStatus(){
		return showStatus;
	}
	
	
	/**
	 * 
	 */
	void receiveInputs(JButton button)
	{
		button.addKeyListener(new KeyAdapter() {
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
				
			}
		});
	}
}
