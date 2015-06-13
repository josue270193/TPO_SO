package test;

import tda.ConjuntoTDA;
import implementaciones.ConsultorioEstatico;

public class PruebaConsultarioEstatico {

	
	public static void main(String[] args) {
		ConsultorioEstatico consultorio = new ConsultorioEstatico();
		consultorio.inicializar();
		
		System.out.println("AGREGAR ----- ");
		
		consultorio.agregar("LOPEZ", "15/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ", "15/15/16", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 2", "15/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "15/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 3", "15/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "01/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "02/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "03/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "04/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "05/15/15", "10:10", "PEPE");
		consultorio.agregar("LOPEZ 4", "05/15/15", "10:10", "PEPE");
		
		//consultorio.agregar("LOPEZ", "15/15/15", "10:10", "PEPE");
		
		System.out.println("MEDICOS Y FECHAS ----- ");
		ConjuntoTDA medicos = consultorio.medicos();		
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();
			System.out.println("FECHAS ----- ");
			System.out.println("MEDICO: " + medicoAux);
			
			ConjuntoTDA fechasMedico = consultorio.fechas(medicoAux);
			while (!fechasMedico.conjuntoVacio()){
				String fechaAux = fechasMedico.elegir();
				System.out.println("FECHA: " + fechaAux);
				
				fechasMedico.sacar(fechaAux);
			}
			
			
			medicos.sacar(medicoAux);
		}
		/*
		System.out.println("FECHAS ----- ");
		
		ConjuntoTDA fechasMedico = consultorio.fechas("LOPEZ");
		if (fechasMedico.conjuntoVacio())
			System.out.println("ES VACIO 1");
		
		fechasMedico = consultorio.fechas("LOPEZ  4");
		if (fechasMedico.conjuntoVacio())
			System.out.println("ES VACIO 4");
		
		fechasMedico = consultorio.fechas("LOPEZ 4");
		if (fechasMedico.conjuntoVacio())
			System.out.println("ES VACIO 5");
		
		System.out.println("ELIMINANDO ----- ");
		
		
		consultorio.eliminarMedico("LOPEZ 4");
		consultorio.eliminarMedico("LOPEZ 2");
				
		
		medicos = consultorio.medicos();		
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();
			System.out.println("MEDICO: " + medicoAux);
			medicos.sacar(medicoAux);
		}
		*/
		
	}

}
