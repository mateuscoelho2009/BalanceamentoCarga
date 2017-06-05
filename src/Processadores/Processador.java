package Processadores;

import java.util.LinkedList;
import java.util.Queue;

import util.Processo;

public class Processador {
	static float LIMIT_MAX = .4f, LIMIT_MIN = .1f; // Métrica será de tempo de processamento necessário
	
	LinkedList<Processo> processos;
	
	public Processador () {
		processos = new LinkedList<Processo>();
	}
	
	public int getTempoProcessamento () {
		int sum = 0;
		
		for (Processo processo : processos) {
			sum += processo.tempoNecessario_ - processo.tempoDeProcessamento_;
		}
		
		return sum;
	}
	
	public void addProcess (Processo p) {
		processos.add(p);
	}
	
	public void update (Gerenciador g) {
		if (processos.isEmpty()) return;
		
		if (processos.peek().finished()) processos.poll();
		
		if (processos.isEmpty()) return;
		processos.peek().update();
	}
	
	public void printStatus () {
		for (Processo processo : processos) {
			processo.printStatus();
		}
	}
	
	public int getNumProcessos() {
		return processos.size();
	}
}
