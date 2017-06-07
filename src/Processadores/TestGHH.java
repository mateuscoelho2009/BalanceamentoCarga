package Processadores;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import util.Processo;

public class TestGHH {
	GerenciadorHibrido ghh_ = new GerenciadorHibrido(3);

	@Test
	public void test() {
		int NUMITER = 100;
		Random rand = new Random();
		
		for (int i = 0; i < NUMITER; i++) {
			System.out.println("Iteração " + i);
			//ghe_.printStatus();
			ghh_.printSimpleStatus();
			
			if (i%2 == 0)
				ghh_.addProcess(new Processo(rand.nextInt(ghh_.nProc_),
											 i,
											 rand.nextInt(10) + 1));
			
			ghh_.update();
		}
	}
	
}
