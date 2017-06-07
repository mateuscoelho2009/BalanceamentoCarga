package Processadores;

import java.util.Random;
import java.util.Vector;

import util.Processo;

public class GerenciadorHibrido extends Gerenciador {
	public GerenciadorHibrido(int numProc) {
		super(numProc);
		proc_ = new Vector<Processador>();
		for (int i = 0; i < nProc_; i++) {
			proc_.add(new ProcessadorHibrido(i));
		}
	}
	
	@Override
	public boolean tryReceiveProcess (int numProcessador) {
		Random rand = new Random();
		for (int i = 1; i <= RETRY; i++) {
			int r = rand.nextInt(nProc_);
			
			while (r == numProcessador) r = rand.nextInt(nProc_);
			
			if (getTempoDeProcessamentoTotal() == 0) return false;
			
			float coef = proc_.elementAt(r).getTempoProcessamento() / getTempoDeProcessamentoTotal();
			if (coef > Processador.LIMIT_MAX && proc_.elementAt(r).getNumProcessos() > 1) {
				Processo p = proc_.elementAt(r).receiveLastProcess();
				// Mudou a cpu
				printCPUChange(r, numProcessador);
				p.cpu_ = numProcessador;
				proc_.elementAt(numProcessador).addProcess(p);
				return true;
			}
		}
		
		return false;
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
