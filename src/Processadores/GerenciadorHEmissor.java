package Processadores;

import java.util.Random;
import java.util.Vector;

import util.Processo;

public class GerenciadorHEmissor extends Gerenciador {
	public GerenciadorHEmissor(int numProc) {
		super(numProc);
		proc_ = new Vector<Processador>();
		for (int i = 0; i < nProc_; i++) {
			proc_.add(new ProcessadorEmissor(i));
		}
	}
	
	@Override
	public boolean tryPassProcess (Processo p) {
		Random rand = new Random();
		for (int i = 1; i <= RETRY; i++) {
			int r = rand.nextInt(nProc_);
			
			while (r == p.cpu_) r = rand.nextInt(nProc_);
			
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
