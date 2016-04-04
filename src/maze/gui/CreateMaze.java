package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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

import maze.logic.Exit;
import maze.logic.General;
import maze.logic.Wall;
import maze.logic.Maze.*;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateMaze implements MouseListener {

	private JFrame frame;
	private JTextField altura;
	private JTextField largura;
	private JTextField numDragoes;
	public maze.logic.Maze objMaze;
	private GameHandler handler;
	
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
		frame.setBounds(20, 20, 1031, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		handler = new GameHandler(tempRef);
		frame.getContentPane().add(handler);
		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSelectOne = new JLabel("Escolhe um!");
		lblSelectOne.setBounds(907, 11, 74, 23);
		panel.add(lblSelectOne);
		
		altura = new JTextField();
		altura.setText("11");
		altura.setBounds(95, 21, 21, 20);
		panel.add(altura);
		altura.setColumns(10);
		
		largura = new JTextField();
		largura.setText("11");
		largura.setBounds(132, 21, 21, 20);
		panel.add(largura);
		largura.setColumns(10);
		
		JLabel lblX = new JLabel("  x");
		lblX.setBounds(115, 22, 21, 19);
		panel.add(lblX);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTamanho.setBounds(27, 24, 58, 14);
		panel.add(lblTamanho);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(164, 20, 58, 23);
		panel.add(btnOk);
		
		JLabel lblNmeroDeDrages = new JLabel("N\u00FAmero de Drag\u00F5es");
		lblNmeroDeDrages.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmeroDeDrages.setBounds(27, 58, 109, 20);
		panel.add(lblNmeroDeDrages);
		
		numDragoes = new JTextField();
		numDragoes.setText("1");
		numDragoes.setBounds(153, 59, 21, 20);
		panel.add(numDragoes);
		numDragoes.setColumns(10);
		
		
		JButton btnCheckLabirinto = new JButton("Testar Labirinto!");
		btnCheckLabirinto.setBounds(883, 272, 121, 23);
		panel.add(btnCheckLabirinto);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(5);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Parede", "Her\u00F3i", "Drag\u00E3o", "Sa\u00EDda", "Espada"}));
		comboBox.setBounds(859, 45, 145, 22);
		panel.add(comboBox);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 88, 779, 545);
		panel.add(panel_1);
		panel_1.addMouseListener(tempRef);
		
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alt,larg;
				
				alt=Integer.parseInt(altura.getText());
				larg=Integer.parseInt(largura.getText());
				
				if(alt > 17)
					alt=17;
				if(larg > 19)
					larg=19;
				
				handler.objMaze= new maze.logic.Maze(alt,larg);
				handler.setVisible(true);
				handler.setBounds(30, 115, 638, 723);
				
				
				handler.repaint();	
				
			}});
//		panel_1.addMouseListener(new MouseAdapter(){
//			
//				@Override
//				public void mouseClicked(MouseEvent e) {
////					
//					}
//				
//
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					HashMap<Point,General> maze = objMaze.getMaze();
//				
//												
//										
//					Wall wall = new Wall(5,5,maze);
//         			maze.put(new Point(5,5),wall);
//         			numDragoes.setText("11");
//							
//					handler.repaint();}
//						
//				
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}	
//		});
		
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
		
//		
		Wall wall = new Wall(5,5,handler.objMaze.getMaze());
		handler.objMaze.getMaze().put(new Point(5,5),wall);
//		handler.setVisible(true);
//		handler.setBounds(30, 115, 638, 723);
//		
		
		handler.repaint();			
							
			
 			numDragoes.setText("11");
					
			
				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
