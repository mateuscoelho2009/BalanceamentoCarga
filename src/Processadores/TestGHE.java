package Processadores;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import util.Processo;

public class TestGHE {
	GerenciadorHEmissor ghe_ = new GerenciadorHEmissor(3);

	@Test
	public void test() {
		int NUMITER = 100;
		Random rand = new Random();
		
		for (int i = 0; i < NUMITER; i++) {
			System.out.println("Itera��o " + i);
			//ghe_.printStatus();
			ghe_.printSimpleStatus();
			
			if (i%2 == 0)
				ghe_.addProcess(new Processo(rand.nextInt(ghe_.nProc_),
											 i,
											 rand.nextInt(10) + 1));
			
			ghe_.update();
		}
	}

}
