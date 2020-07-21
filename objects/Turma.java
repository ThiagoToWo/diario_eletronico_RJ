package objects;

import java.io.PrintStream;
import java.io.Serializable;
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
	
	public void display() {
		p = new PrintStream(System.out);
		for (Aluno aluno : listaDeAlunos) {
			p.printf("%d %s", aluno.getNumero(), aluno.getNome());
		}
	}
}
