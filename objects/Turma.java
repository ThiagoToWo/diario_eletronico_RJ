package objects;

import java.util.ArrayList;

public class Turma {
	
	private String nome;
	private ArrayList<Aluno> listaDeAlunos = new ArrayList<Aluno>();
	
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
		this.listaDeAlunos.add(aluno.getNumero(), aluno);
	}
}