package Geradores;

import java.util.Random;

import util.Processo;
import Processadores.Gerenciador;

public class Gerador {
	Gerenciador gerenciador_;
	
	int amount_, tmt_;
	int ipsec_; // Iterations per second
	
	public Gerador (Gerenciador g, int amount, int tmt, int ipsec) {
		gerenciador_ = g;
		amount_ = amount;
		tmt_ = tmt;
		ipsec_ = ipsec;
	}
	
	public Gerador (Gerenciador g, int amount, int tmt) {
		gerenciador_ = g;
		amount_ = amount;
		tmt_ = tmt;
		ipsec_ = 1000;
	}
	
	public void run() {
		int numCreatedProcess = 0;
		Random rand = new Random();
		
		// Supor 700 processos para simular.
		for (int i = 0; numCreatedProcess < 700; i++) {
			// Pode-se printar também por segundo, basta pegar i%ipsec_. A iteração nesse caso será i/ipsec_.
			System.out.println("Iteração " + i);
			//gerenciador_.printStatus();
			gerenciador_.printSimpleStatus();
			
			if (generateProcess(i)) {
				numCreatedProcess++;
				gerenciador_.addProcess(new Processo(rand.nextInt(gerenciador_.nProc_),
											 		 i,
											 		 (rand.nextInt(tmt_ * 2) + 1) * ipsec_)); // processos que duram em media ipsec_
			}
			
			gerenciador_.update();
		}
	}
	
	boolean generateProcess (int iter) {
		int numProcPerSec = amount_ / tmt_;
		Random rand = new Random();
		
		if (rand.nextInt(ipsec_) <= numProcPerSec)
			return true;
		
		return false;
	}
}
