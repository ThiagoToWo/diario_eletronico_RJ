package Visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import objects.Turma;

public class DiarioGui extends JFrame {
	private Turma turma;
	private String[] caput = {"Número", "Nome", "Intrumento 1", "Recuperação 1", "Intrumento 2", "Recuperação 2",
			 "Intrumento 3", "Recuperação 3", "Somatória antes da rec.", "Somatório depois da rec.", "Faltas"};
	private JPanel panelBimestre1;
	private JPanel panelBimestre2;
	private JPanel panelBimestre3;
	private JPanel panelBimestre4;
	private JPanel panelFinal;
	
	private String autor = "Autor: Thiago de Oliveira Alves\ntowo497@gmail.com";
	private String versao = "Versão: 1.0 \n 19-07-2020\n\n";
	
	public void construir() {
		super.setTitle("Diário");
		
		// cria e configura a barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuSobre = new JMenu("Informações");
		JMenuItem autoria = new JMenuItem("Autor");
		autoria.addActionListener(new AutorListener());
		JMenuItem versao = new JMenuItem("Sobre o aplicativo");
		versao.addActionListener(new VersaoListener());
		menuSobre.add(autoria);
		menuSobre.add(versao);
		barraDeMenu.add(menuSobre);
		setJMenuBar(barraDeMenu);
		
		// cria um painel norte 
		JPanel painelNorte = new JPanel();
		
		// cria as entradas de texto que capturarão as informações dos alunos
		JTextField numero = new JTextField(2);
		JTextField nome = new JTextField(20);
		JTextField nota1Inst = new JTextField(3);
		JTextField rec1Inst = new JTextField(3);
		JTextField nota2Inst = new JTextField(3);
		JTextField rec2Inst = new JTextField(3);
		JTextField nota3Inst = new JTextField(3);
		JTextField rec3Inst = new JTextField(3);
		JTextField faltas = new JTextField(3);
		
		// cria os rótulos das entradas de texto
		JLabel rotuloNumero = new JLabel("Número ");	
		JLabel rotuloNome = new JLabel(" Nome ");			
		JLabel rotuloNot1Inst = new JLabel(" Inst. 1 ");	
		JLabel rotuloRec1Inst = new JLabel(" Rec. 1 ");	
		JLabel rotuloNot2Inst = new JLabel(" Inst. 2 ");	
		JLabel rotuloRec2Inst = new JLabel(" Rec. 2 ");
		JLabel rotuloNot3Inst = new JLabel(" Inst. 3 ");	
		JLabel rotuloRec3Inst = new JLabel(" Rec. 3 ");
		JLabel rotuloFaltas = new JLabel("Faltas");
		
		// adiciona os rótulos e entrads de texto no painel norte
		painelNorte.add(rotuloNumero);
		painelNorte.add(numero);
		painelNorte.add(rotuloNome);
		painelNorte.add(nome);
		painelNorte.add(rotuloNot1Inst);
		painelNorte.add(nota1Inst);
		painelNorte.add(rotuloRec1Inst);
		painelNorte.add(rec1Inst);
		painelNorte.add(rotuloNot2Inst);
		painelNorte.add(nota2Inst);
		painelNorte.add(rotuloRec2Inst);
		painelNorte.add(rec2Inst);
		painelNorte.add(rotuloNot3Inst);
		painelNorte.add(nota3Inst);
		painelNorte.add(rotuloRec3Inst);
		painelNorte.add(rec3Inst);
		painelNorte.add(rotuloFaltas);
		painelNorte.add(faltas);
		
		getContentPane().add(BorderLayout.NORTH, painelNorte);
		
		// cria os painéis dos bimestres
		JPanel panelBimestre1 = new JPanel();
		JPanel panelBimestre2 = new JPanel();
		JPanel panelBimestre3 = new JPanel();
		JPanel panelBimestre4 = new JPanel();
		JPanel panelFinal = new JPanel();
		
		// cria as tabs e adiciona os painéis dos bimestres
		JTabbedPane tabBimestres = new JTabbedPane();
		tabBimestres.addTab("Bimestre 1", new JScrollPane(panelBimestre1));
		tabBimestres.addTab("Bimestre 2", new JScrollPane(panelBimestre2));
		tabBimestres.addTab("Bimestre 3", new JScrollPane(panelBimestre3));
		tabBimestres.addTab("Bimestre 4", new JScrollPane(panelBimestre4));
		tabBimestres.addTab("Final", new JScrollPane(panelFinal));
		
		// adiciona as tabs no frame
		getContentPane().add(BorderLayout.CENTER, tabBimestres);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLocation(400, 100);
		setVisible(true);
	}
	
	private class AutorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
			JOptionPane.showMessageDialog(null, autor, "Sobre mim", JOptionPane.INFORMATION_MESSAGE);

		}

	}
	
	private class VersaoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, versao, "Sobre este", JOptionPane.INFORMATION_MESSAGE);

		}

	}	

}
