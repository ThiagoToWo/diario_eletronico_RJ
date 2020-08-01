package objects;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Turma implements Serializable{
	
	private String nome;
	private TreeMap<Integer, Aluno> listaDeAlunos = new TreeMap<Integer, Aluno>();
	private PrintStream p;
	
	public Turma(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public Aluno getListaDeAlunos(int numero) {
		return listaDeAlunos.get(numero);
	}

	public void addListaDeAlunos(Aluno aluno) {
		listaDeAlunos.put(aluno.getNumero(), aluno);
	}
	
	public Object[][] getInfoDosAlunosPorBimestre(int bimestre) {		
		Object[][] infoBi = new Object[listaDeAlunos.size()][11];
		
		for (int i = 0; i < infoBi.length; i++) {
			infoBi[i][0] = new Integer(getListaDeAlunos(i + 1).getNumero());
		}
		
		for (int i = 0; i < infoBi.length; i++) {
			infoBi[i][1] = getListaDeAlunos(i + 1).getNome();
		}
		
		for (int i = 0; i < infoBi.length; i++) {
			Aluno a = getListaDeAlunos(i + 1);
			for (int j = 2; j <= 7; j++) {				
				infoBi[i][j] = new Double(a.getNotasDaAvaliacao(bimestre, j - 1));
			}
		}		
		
		for (int i = 0; i < infoBi.length; i++) {
			infoBi[i][10] = new Integer(getListaDeAlunos(i + 1).getFaltas(bimestre));
		}
		
		return infoBi;
	}
	
	public void display() {
		p = new PrintStream(System.out);
		for (Map.Entry<Integer, Aluno> me : listaDeAlunos.entrySet()) {
			int numeroDoAluno = me.getValue().getNumero();
			String nomeDoAluno = me.getValue().getNome();
			p.printf("%d %s", numeroDoAluno, nomeDoAluno);
		}
	}
}
