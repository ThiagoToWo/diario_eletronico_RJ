package objects;

import java.io.Serializable;

public class Aluno implements Serializable{
	private int numero;
	private String nome;
	private double[][] notas = new double[4][6];
	
	private double[][] notaFinal = new double[4][2];
	private short[] faltas = new short[4];
	private short totalFaltas; 
	private double totalNota;
	private boolean aprovado = false;
	
	public Aluno(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(byte numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNotas(byte bimestre, byte avaliacao) {
		return notas[bimestre - 1][avaliacao - 1];
	}

	public void setNotas(byte bimestre, byte avaliacao, float nota) {
		this.notas[bimestre - 1][avaliacao - 1] = nota;
	}

	public short getFaltas(byte bimestre) {
		return faltas[bimestre - 1];
	}

	public void setFaltas(byte bimestre, short faltas) {
		this.faltas[bimestre - 1] = faltas;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public double getNotaFinal(byte bimestre) {
		return notaFinal[bimestre -1][1];
	}
	
	public void calcularNotaFinal() {
		
		for (int i = 0; i < notas.length; i++) {
			for (int j = 0; j < notas[i].length; j++) {
				if (j % 2 == 0) {
					notaFinal[i][0] += notas[i][j];
					notaFinal[i][1] += (notas[i][j] > notas[i][j + 1] ? notas[i][j]: notas[i][j + 1]);
				}				
			}
		}
	}
	
	public double getTotalNota() {
		
		for (byte i = 1; i <= notaFinal.length; i++) {
			this.totalNota += getNotaFinal(i);
		}
		
		return this.totalNota;
	}

	public short getTotalFaltas() {
		
		for (byte i = 1; i <= faltas.length; i++) {
			this.totalFaltas += getFaltas(i);
		}
		
		return this.totalFaltas;
	}
	
	
	public void display() {
		System.out.println(numero + ". " + nome); // imprime n�mero e nome do aluno
		System.out.println();
		System.out.print("            ");

		for (int i = 0; i < notas[0].length; i++) { // imprime o t�tulo dos instrumentos e recupera��es
			if (i % 2 == 0)	System.out.printf("Inst. %d ", (i + 2) / 2);
			else System.out.printf(" Rec. %d ", (i + 1) / 2);
		}
		
		System.out.println();
		
		for (int i = 0; i < notas.length; i++) { // imprime a matriz de notas
			System.out.print("Bimestre " + (i + 1) + " ");
			for (int j = 0; j < notas[i].length; j++) {
				System.out.printf("%8.1f", notas[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.print("             ");
		
		for (int i = 0; i < notaFinal[0].length; i++) { // imprime o t�tulo dos somat�rios de nota final
			if (i % 2 == 0)	System.out.printf("Soma a.r ", i + 1);
			else System.out.printf(" Soma d.r ", i + 1);
		}
		
		System.out.println();
		
		calcularNotaFinal();
		
		for (int i = 0; i < notaFinal.length; i++) { // imprime as notas finais
			System.out.print("Bimestre " + (i + 1) + " ");
			for (int j = 0; j < notaFinal[i].length; j++) {
				System.out.printf("%10.1f", notaFinal[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();		
		System.out.println("             Faltas");
		
		for (int i = 0; i < faltas.length; i++) {
			System.out.printf("Bimestre %d %8d%n", i + 1, faltas[i]);
		}
	}
}