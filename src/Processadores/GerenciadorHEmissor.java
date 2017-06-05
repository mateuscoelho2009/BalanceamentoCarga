package Processadores;

import java.util.Random;
import java.util.Vector;

import util.Processo;

public class GerenciadorHEmissor extends Gerenciador {
	public GerenciadorHEmissor() {
		proc_ = new Vector<Processador>();
		for (int i = 0; i < NUM_PROCESSORS; i++) {
			proc_.add(new ProcessadorEmissor());
		}
	}
	
	public void addProcess (Processo p) {
		proc_.elementAt(p.cpu_).addProcess(p);
	}
	
	public void update() {
		for (int i = 0; i < NUM_PROCESSORS; i++)
			proc_.elementAt(i).update(this);
	}
	
	@Override
	public boolean tryPassProcess (Processo p) {
		Random rand = new Random();
		for (int i = 1; i <= RETRY; i++) {
			int r = rand.nextInt(NUM_PROCESSORS);
			
			while (r == p.cpu_) r = rand.nextInt(NUM_PROCESSORS);
			
			float coef = proc_.elementAt(r).getTempoProcessamento() / getTempoDeProcessamentoTotal();
			if (coef < Processador.LIMIT_MIN) {
				// Mudou a cpu
				printCPUChange(p.cpu_, r);
				p.cpu_ = r;
				proc_.elementAt(r).addProcess(p);
				return true;
			}
		}
		
		return false;
	}
}
