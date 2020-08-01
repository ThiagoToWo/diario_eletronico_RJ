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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import objects.Aluno;
import objects.FileManager;
import objects.Turma;

public class DiarioGui extends JFrame {	

	private Turma turma;
	private Aluno aluno;
	private String[] caput = {"Número", "Nome", "Intrumento 1", "Recuperação 1", "Intrumento 2", "Recuperação 2",
			 "Intrumento 3", "Recuperação 3", "Somatória antes da rec.", "Somatório depois da rec.", "Faltas"};
	private JTabbedPane tabBimestres;
	private DefaultTableModel dm1 = new DefaultTableModel(caput, 1); 
	private DefaultTableModel dm2 = new DefaultTableModel(caput, 1); 
	private DefaultTableModel dm3 = new DefaultTableModel(caput, 1); 
	private DefaultTableModel dm4 = new DefaultTableModel(caput, 1); 
	private JTable panelBimestre1;
	private JTable panelBimestre2;
	private JTable panelBimestre3;
	private JTable panelBimestre4;
	private JTable panelFinal;
	private FileManager fmn = new FileManager();
	JTextField numero;
	JTextField nome;
	JTextField nota1Inst;
	JTextField rec1Inst;
	JTextField nota2Inst;
	JTextField rec2Inst;
	JTextField nota3Inst;
	JTextField rec3Inst;
	JTextField faltas;
	private String autor = "Autor: Thiago de Oliveira Alves\ntowo497@gmail.com";
	private String versao = "Versão: 1.0 \n 19-07-2020\n\n";
	
	public void construir() {
		super.setTitle("Diário");
		
		// cria e configura a barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenuItem novaTurma = new JMenuItem("Criar nova turma");
		novaTurma.addActionListener(new CriarListener());
		JMenuItem carregarTurma = new JMenuItem("Carregar");
		carregarTurma.addActionListener(new CarregarListener());
		JMenuItem salvarTurma = new JMenuItem("Salvar");
		salvarTurma.addActionListener(new SalvarListener());
		JMenu menuSobre = new JMenu("Informações");
		JMenuItem autoria = new JMenuItem("Autor");
		autoria.addActionListener(new AutorListener());
		JMenuItem versao = new JMenuItem("Sobre o aplicativo");
		versao.addActionListener(new VersaoListener());
		menuArquivo.add(novaTurma);
		menuArquivo.add(carregarTurma);
		menuArquivo.add(salvarTurma);
		menuSobre.add(autoria);
		menuSobre.add(versao);
		barraDeMenu.add(menuArquivo);
		barraDeMenu.add(menuSobre);
		setJMenuBar(barraDeMenu);
		
		// cria um painel norte 
		JPanel painelNorte = new JPanel();
		
		// cria as entradas de texto que capturarão as informações dos alunos
		numero = new JTextField(2);
		nome = new JTextField(20);
		nota1Inst = new JTextField(3);
		rec1Inst = new JTextField(3);
		nota2Inst = new JTextField(3);
		rec2Inst = new JTextField(3);
		nota3Inst = new JTextField(3);
		rec3Inst = new JTextField(3);
		faltas = new JTextField(3);
		
		IncluirListener il = new IncluirListener();
		numero.addActionListener(il);
		nome.addActionListener(il);
		nota1Inst.addActionListener(il);
		rec1Inst.addActionListener(il);
		nota2Inst.addActionListener(il);
		rec2Inst.addActionListener(il);
		nota3Inst.addActionListener(il);
		rec3Inst.addActionListener(il);
		faltas.addActionListener(il);
		
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
		panelBimestre1 = new JTable(dm1);
		panelBimestre2 = new JTable(dm2);
		panelBimestre3 = new JTable(dm3);
		panelBimestre4 = new JTable(dm4);
		panelFinal = new JTable();
		
		// cria as tabs e adiciona os painéis dos bimestres
		tabBimestres = new JTabbedPane();
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
	
	private void editarAluno() {
		if (turma != null) { // se existe turma
			int num = getNumero();
			double[] not = getNotas();
			short fal = getFaltas();
			if (turma.getListaDeAlunos(num) == null) {
				aluno = new Aluno(num, nome.getText());				
				aluno.setNotas(tabBimestres.getSelectedIndex() + 1, not);
				aluno.setFaltas(tabBimestres.getSelectedIndex() + 1, fal);
				turma.addListaDeAlunos(aluno);
			} else {
				aluno = turma.getListaDeAlunos(num);
				if (!nome.getText().isEmpty()) aluno.setNome(nome.getText());
				aluno.setNotas(tabBimestres.getSelectedIndex() + 1, not);
				aluno.setFaltas(tabBimestres.getSelectedIndex() + 1, fal);
				//turma.addListaDeAlunos(aluno);
			}			
		} else {
			JOptionPane.showMessageDialog(getParent(), "Crie ou carregue uma turma.",
					null, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void preencherTabela() {
		switch (tabBimestres.getSelectedIndex()) {
		case 0:				
			dm1.insertRow(aluno.getNumero() - 1, aluno.getInfoGeralBimestre(1));
			dm1.removeRow(aluno.getNumero());
			break;
		case 1:
			dm2.insertRow(aluno.getNumero() - 1, aluno.getInfoGeralBimestre(2));
			dm2.removeRow(aluno.getNumero());
			break;
		case 2:
			dm3.insertRow(aluno.getNumero() - 1, aluno.getInfoGeralBimestre(3));
			dm3.removeRow(aluno.getNumero());
			break;
		case 3:
			dm4.insertRow(aluno.getNumero() - 1, aluno.getInfoGeralBimestre(4));
			dm4.removeRow(aluno.getNumero());
			break;
		}
		
			
	}	
	
	private int getNumero() {
		int num = 0;
		try {
			num = new Integer(numero.getText());
		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Insira um número válido para o aluno",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}		
		return num;
	}
	
	private double[] getNotas() {
		double[] notas = new double[6];
		try {
			if (!nota1Inst.getText().isEmpty()) notas[0] = new Double(nota1Inst.getText());
			if (!rec1Inst.getText().isEmpty()) notas[1] = new Double(rec1Inst.getText());
			if (!nota2Inst.getText().isEmpty()) notas[2] = new Double(nota2Inst.getText());
			if (!rec2Inst.getText().isEmpty()) notas[3] = new Double(rec2Inst.getText());
			if (!nota3Inst.getText().isEmpty()) notas[4] = new Double(nota3Inst.getText());
			if (!rec3Inst.getText().isEmpty()) notas[5] = new Double(rec3Inst.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Insira qualquer valor de nota separando o décimos"
					+ "com \".\" (ponto)",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		return notas;
	}
	
	private short getFaltas() {
		short fal = 0;
		try {
			if (!faltas.getText().isEmpty())
				fal = new Short(faltas.getText());		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Insira um número válido para a falta",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}		
		return fal;
	}
	
	public class CriarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomeDaTurma = JOptionPane.showInputDialog(getParent(), "Nome da turma", "Nova turma",
					JOptionPane.QUESTION_MESSAGE);
			turma = new Turma(nomeDaTurma);
			setTitle(nomeDaTurma);
		}

	}

	public class CarregarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			turma = (Turma) fmn.desserializarComFileChooser();
			setTitle(turma.getNome());
			
		}

	}

	public class SalvarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			fmn.serializarComFileChooser(turma);
		}

	}
	
	public class IncluirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {			
			editarAluno();
			preencherTabela();
		}

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
