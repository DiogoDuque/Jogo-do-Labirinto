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
import maze.cli.main.DragonType;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class MainWindow {

	private JFrame frame;
	private JTextField altura;
	private JTextField largura;
	private JTextField numDragoes;

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
		frame.setBounds(100, 100, 650, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		JComboBox tipoDragao = new JComboBox();
		tipoDragao.setModel(new DefaultComboBoxModel(DragonType.values()));
		tipoDragao.setToolTipText("");
		tipoDragao.setBounds(150, 87, 173, 20);
		frame.getContentPane().add(tipoDragao);
		
		//Acoes para o labirinto
		
		final JTextArea mazeWindow = new JTextArea();
		mazeWindow.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeWindow.setEditable(false);
		mazeWindow.setBounds(30, 143, 330, 345);
		frame.getContentPane().add(mazeWindow);
		
		final JLabel lblMessageBox = new JLabel("Pode gerar um novo labirinto!");
		lblMessageBox.setVerticalAlignment(SwingConstants.TOP);
		lblMessageBox.setBounds(400, 409, 189, 69);
		frame.getContentPane().add(lblMessageBox);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alt,larg,numD;
				alt=Integer.parseInt(altura.getText());
				larg=Integer.parseInt(largura.getText());
				numD=Integer.parseInt(numDragoes.getText());
				maze.logic.MazeBuilder charMaze = new maze.logic.MazeBuilder(alt,larg,numD);
				maze.logic.Maze objMaze= new maze.logic.Maze(charMaze.getMaze());
				mazeWindow.setText(maze.cli.main.getDisplay(objMaze.getMaze()));
				lblMessageBox.setText("Pode jogar!");
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
		
		JButton btnCima = new JButton("CIMA");
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCima.setBounds(450, 225, 100, 35);
		frame.getContentPane().add(btnCima);
		
		JButton btnEsquerda = new JButton("ESQUERDA");
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEsquerda.setBounds(390, 270, 100, 35);
		frame.getContentPane().add(btnEsquerda);
		
		JButton btnDireita = new JButton("DIREITA");
		btnDireita.setBounds(510, 270, 100, 35);
		frame.getContentPane().add(btnDireita);
		
		JButton btnBaixo = new JButton("BAIXO");
		btnBaixo.setBounds(450, 315, 100, 35);
		frame.getContentPane().add(btnBaixo);
	}
}
