package Geradores;

import static org.junit.Assert.*;

import org.junit.Test;

import Processadores.Gerenciador;
import Processadores.GerenciadorHibrido;

public class TestGerador {

	@Test
	public void test() {
		GerenciadorHibrido gh = new GerenciadorHibrido(5);
		Gerador g = new Gerador(gh, 30, 3);
		
		g.run();
	}

}
