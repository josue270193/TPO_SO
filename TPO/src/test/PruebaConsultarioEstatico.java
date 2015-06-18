package test;

import algoritmos.Algoritmos;

public class PruebaConsultarioEstatico {

	public static void main(String[] args) {

		Algoritmos algoritmos = new Algoritmos();

		String[][] turnosDePaciente = algoritmos.turnosDePaciente("PEPE", "20150614", "20150617");

		for (int i = 0; i < turnosDePaciente.length; i++) {
			System.out.println(turnosDePaciente[i][0] + " " + turnosDePaciente[i][1] + " " + turnosDePaciente[i][2]);
		}

	}

}
