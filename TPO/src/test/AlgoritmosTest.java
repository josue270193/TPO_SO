package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algoritmos.Algoritmos;

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
			Assert.assertEquals(expected[i][0], agendaConsultorio[i][0]);
			Assert.assertEquals(expected[i][1], agendaConsultorio[i][1]);
			Assert.assertEquals(expected[i][2], agendaConsultorio[i][2]);
			Assert.assertEquals(expected[i][3], agendaConsultorio[i][3]);
		}
		
	
	}
	
	@Test
	public void test_fechas_ocupadas() {

		String[] fechas = this.algoritmos.fechasOcupadas("ZAPATA", "20150515", "20150516");
		String[] expected = { "20150515", "20150516" };
		
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i], fechas[i]);
		}
		
	
	}
	
	@Test
	public void test_turnos_en_fecha() {

		String[][] fechas = this.algoritmos.turnosEnFecha("ZAPATA", "20150515");
		String[][] expected = { {"10:00","MARIO"}, {"15:00","MARIO"} };
		
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i][0], fechas[i][0]);
			Assert.assertEquals(expected[i][1], fechas[i][1]);
		}
		
	
	}

}
