package Processadores;

import java.util.Vector;

import util.Processo;

public class Gerenciador {
	final int RETRY = 2;
	public static int NUM_PROCESSORS = 3;
	
	Vector<Processador> proc_;
	
	int getTempoDeProcessamentoTotal () {
		int sum = 0;
		
		for (Processador processador : proc_) {
			sum += processador.getTempoProcessamento();
		}
		
		return sum;
	}

	public boolean tryPassProcess(Processo p) {
		return false;
	}
	
	public void printStatus() {
		System.out.println("===============================================");
		for (int i = 0; i < NUM_PROCESSORS; i++) {
			System.out.println("Processador " + (i + 1));
			proc_.get(i).printStatus();
			System.out.println("");
		}
		System.out.println("===============================================");
	}
	
	public void printSimpleStatus () {
		System.out.println("===============================================");
		System.out.println("Tempo de processamento total = " + getTempoDeProcessamentoTotal());
		int numProc = 0;
		for (int i = 0; i < NUM_PROCESSORS; i++) {
			numProc += proc_.get(i).getNumProcessos();
		}
		System.out.println("Número total de processos = " + numProc);
		System.out.println("===============================================");
	}
	
	void printCPUChange(int antCPU, int novaCPU) {
		System.out.println("Mudança de CPU:");
		System.out.println(antCPU + " para " + novaCPU);
		System.out.println("Tempo de Processamento CPU " + antCPU + " = "
							+ proc_.elementAt(antCPU).getTempoProcessamento());
		System.out.println("Tempo de Processamento CPU " + novaCPU + " = "
				+ proc_.elementAt(novaCPU).getTempoProcessamento());
	}
}
