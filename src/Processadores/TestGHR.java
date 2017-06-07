package Processadores;

import java.util.Random;

import org.junit.Test;

import util.Processo;

public class TestGHR {
	GerenciadorHReceptor ghr_ = new GerenciadorHReceptor(3);

	@Test
	public void test() {
		int NUMITER = 100;
		Random rand = new Random();
		
		for (int i = 0; i < NUMITER; i++) {
			System.out.println("Iteração " + i);
			//ghe_.printStatus();
			ghr_.printSimpleStatus();
			
			if (i%2 == 0)
				ghr_.addProcess(new Processo(rand.nextInt(ghr_.nProc_),
											 i,
											 rand.nextInt(10) + 1));
			
			ghr_.update();
		}
	}

}
