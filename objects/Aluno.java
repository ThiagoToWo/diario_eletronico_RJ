package objects;

import java.io.PrintStream;
import java.io.Serializable;

public class Aluno implements Serializable{
	private int numero;
	private String nome;
	private double[][] notas = new double[4][6];	
	private double[][] notaFinalBimestre = new double[4][2];
	private short[] faltas = new short[4];
	private short totalFaltas; 
	private double totalNota;
	private boolean aprovado;
	private PrintStream p;
	
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

	public double getNotaFinalBimestre(byte bimestre) {
		return notaFinalBimestre[bimestre -1][1];
	}
	
	public void calcularNotaFinalBimestre() {
		
		for (int i = 0; i < notas.length; i++) {
			for (int j = 0; j < notas[i].length; j++) {
				if (j % 2 == 0) {
					notaFinalBimestre[i][0] += notas[i][j];
					notaFinalBimestre[i][1] += (notas[i][j] > notas[i][j + 1] ? notas[i][j] : notas[i][j + 1]);
				}				
			}
		}
	}
	
	public double getTotalNota() {
		
		for (byte i = 1; i <= notaFinalBimestre.length; i++) {
			this.totalNota += getNotaFinalBimestre(i);
		}
		
		return this.totalNota;
	}

	public short getTotalFaltas() {
		
		for (byte i = 1; i <= faltas.length; i++) {
			this.totalFaltas += getFaltas(i);
		}
		
		return this.totalFaltas;
	}
	
	
	public void display(PrintStream output) { // display no console
		p = new PrintStream(output);
		p.println(numero + ". " + nome); // imprime número e nome do aluno
		p.println();
		p.print("            ");

		for (int i = 0; i < notas[0].length; i++) { // imprime o título dos instrumentos e recuperações
			if (i % 2 == 0)	p.printf("Inst. %d ", (i + 2) / 2);
			else p.printf(" Rec. %d ", (i + 1) / 2);
		}
		
		p.println();
		
		for (int i = 0; i < notas.length; i++) { // imprime a matriz de notas
			p.print("Bimestre " + (i + 1) + " ");
			for (int j = 0; j < notas[i].length; j++) {
				p.printf("%8.1f", notas[i][j]);
			}
			p.println();
		}
		
		p.println();
		p.print("             ");
		
		for (int i = 0; i < notaFinalBimestre[0].length; i++) { // imprime o título dos somatórios de nota final
			if (i % 2 == 0)	p.printf("Soma a.r ", i + 1);
			else p.printf(" Soma d.r ", i + 1);
		}
		
		p.println();
		
		calcularNotaFinalBimestre(); // calcula os somatórios antes e depois da rec
		
		for (int i = 0; i < notaFinalBimestre.length; i++) { // imprime as notas finais
			p.print("Bimestre " + (i + 1) + " ");
			for (int j = 0; j < notaFinalBimestre[i].length; j++) {
				p.printf("%10.1f", notaFinalBimestre[i][j]);
			}
			p.println();
		}
		
		p.printf("Total notas%20.1f (%s)%n", getTotalNota(), 
				(getTotalNota() <= 20 ? "APROVADO" : "REPROVADO")); // imprime o total das notas finais dos bim
		
		p.println();		
		p.println("             Faltas");
		
		for (int i = 0; i < faltas.length; i++) { // imprime as faltas por bimestre
			p.printf("Bimestre %d %8d%n", i + 1, faltas[i]);
		}
		
		p.printf("Total faltas%7d", getTotalFaltas()); // imprime o total de faltas
		
		p.println("\n");
	}

	public void displayBimestre(int bimestre, PrintStream output) { // display bimestre no console
		p = new PrintStream(output);
				
		p.print(numero + ". " + nome); // imprime nome e número
		
		for (int j = 0; j < notas[bimestre].length; j++) { // imprime as notas do bimestre
			p.printf("%8.1f", notas[bimestre][j]);
		}
		
		calcularNotaFinalBimestre();
		
		for (int j = 0; j < notaFinalBimestre[bimestre].length; j++) { // imprime os somatórios do bimestre
			p.printf("%8.1f", notaFinalBimestre[bimestre][j]);
		}
		
		p.println("      " + faltas[bimestre]);
	}
}
