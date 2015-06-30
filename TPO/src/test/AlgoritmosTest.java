package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algoritmos.Algoritmos;
import implementaciones.ConsultorioEstatico;
import tda.ConjuntoTDA;

public class AlgoritmosTest {

	private Algoritmos algoritmos;
	
	@Before
	public void before(){
		
		algoritmos = new Algoritmos();
		
	}
	
	@Test
	public void test_agenda_consultorio() {

		String[][] agendaConsultorio = this.algoritmos.agendaConsultorio();
		String[][] expected = { { "LOPEZ", "20150516", "11:00", "PEPE" }, { "ZAPATA", "20150515", "10:00", "MARIO" }, { "ZAPATA", "20150515", "15:00", "MARIO" }, { "ZAPATA", "20150516", "15:00", "MARIA" } };
		
		for (int i = 0; i < agendaConsultorio.length; i++) {
			if(agendaConsultorio[i][0]==null) return;

			Assert.assertEquals(expected[i][0], agendaConsultorio[i][0]);
			Assert.assertEquals(expected[i][1], agendaConsultorio[i][1]);
			Assert.assertEquals(expected[i][2], agendaConsultorio[i][2]);
			Assert.assertEquals(expected[i][3], agendaConsultorio[i][3]);
		}
		
	
	}

}
