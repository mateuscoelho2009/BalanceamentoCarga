package util;

import java.util.Vector;

public class Processo {
	public enum proc_State {ESPERA, PROCESSANDO, TERMINO};
	static String[] _states_ = {"Espera", "Processando", "Termino"};
	
	public int horaCriacao_;
	public int tempoNecessario_; // "Clocks"
	public int tempoDeProcessamento_;
	public int cpu_;
	proc_State estado_;
	
	public Processo(int cpu, int tempoAtual, int tProcessamento) {
		cpu_ = cpu;
		horaCriacao_ = tempoAtual;
		tempoNecessario_ = tProcessamento;
		tempoDeProcessamento_ = 0;
		estado_ = proc_State.ESPERA;
	}
	
	public void update () {
		if (estado_ == proc_State.ESPERA) estado_ = proc_State.PROCESSANDO;
		tempoDeProcessamento_++;
		
		if (tempoDeProcessamento_ == tempoNecessario_) estado_ = proc_State.TERMINO;
	}
	
	public boolean finished () {
		return (estado_ == proc_State.TERMINO);
	}

	public void printStatus() {
		System.out.println("Processador responsável: " + cpu_);
		System.out.println("Hora da Criação = " + horaCriacao_);
		System.out.println("Tempo de Processamento atual/Tempo necessário de execução = "
							+ tempoDeProcessamento_ + "/" + tempoNecessario_);
		System.out.print("Estado = ");
		switch (estado_) {
		case PROCESSANDO: System.out.println("Processando"); break;
		case ESPERA: System.out.println("Espera"); break;
		case TERMINO: System.out.println("Termino");
		}
	}
}
