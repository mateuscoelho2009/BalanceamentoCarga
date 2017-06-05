package Processadores;

import java.util.Iterator;

import util.Processo;

public class ProcessadorEmissor extends Processador {
	@Override
	public void update (Gerenciador g) {
		int tProc = g.getTempoDeProcessamentoTotal();
		
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
