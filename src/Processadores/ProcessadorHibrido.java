package Processadores;

import util.Processo;

public class ProcessadorHibrido extends Processador {
	public ProcessadorHibrido (int numProcessador) {
		// TODO Auto-generated constructor stub
		super(numProcessador);
	}
	
	@Override
	public Processo receiveLastProcess () {
		if (processos.size() == 0) return null;
		else {
			Processo p = processos.get(processos.size()-1);
			processos.remove(processos.size()-1);
			
			return p;
		}
	}
	
	@Override
	public void update (Gerenciador g) {
		int tProc = g.getTempoDeProcessamentoTotal();
		
		if (tProc > 0 && (float) (getTempoProcessamento() / tProc) < LIMIT_MIN) {
			g.tryReceiveProcess (nProcessador);
		}
		
		if (tProc > 0 && (float) (getTempoProcessamento() / tProc) > LIMIT_MAX) {
			if (processos.size() > 1) {
				if (g.tryPassProcess (processos.get(processos.size()-1))) {
					processos.remove(processos.size()-1);
				}
			}
		}
		
		if (processos.isEmpty()) return;
		
		if (processos.peek().finished()) processos.poll();
		
		if (processos.isEmpty()) return;
		processos.peek().update();
	}
}
