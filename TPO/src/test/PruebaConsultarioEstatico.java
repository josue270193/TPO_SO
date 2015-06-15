package test;

import tda.ConjuntoTDA;
import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConsultorioEstatico;

public class PruebaConsultarioEstatico {

	
	public static void main(String[] args) {
		ConsultorioEstatico consultorio = new ConsultorioEstatico();
		consultorio.inicializar();
		
		System.out.println("DATOS AGREGADOS\n----------------------------------------------------------------------------------------");
		
		consultorio.agregar("LOPEZ", "15/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ", "15/15/16", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 2", "15/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 2", "15/15/15", "10:30", "ANA");
		consultorio.agregar("LOPEZ 2", "15/15/15", "11:00", "MARIA");
		consultorio.agregar("LOPEZ 2", "15/15/15", "11:00", "JOSE");
		consultorio.agregar("LOPEZ 3", "15/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "15/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "01/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "02/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "03/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "04/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "05/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "05/15/15", "10:00", "PEPE");
		consultorio.agregar("LOPEZ 4", "05/15/15", "20:00", "ANA");
		consultorio.agregar("LOPEZ 4", "05/15/15", "15:00", "MARIA");
		consultorio.agregar("LOPEZ 4", "05/15/15", "13:00", "JOSE");
		
		//consultorio.agregar("LOPEZ", "15/15/15", "10:10", "PEPE");
		
		System.out.println("\nMEDICOS CON SUS FECHAS\n---------------------------------------------------------------------------------------- ");
		ConjuntoTDA medicos = consultorio.medicos();		
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();			
			System.out.println("\nMEDICO: " + medicoAux);
			
			ConjuntoTDA fechasMedico = consultorio.fechas(medicoAux);
			while (!fechasMedico.conjuntoVacio()){
				String fechaAux = fechasMedico.elegir();
				System.out.println("	FECHA: " + fechaAux);
				
				fechasMedico.sacar(fechaAux);
			}			
			medicos.sacar(medicoAux);
		}
		
		System.out.println("\nPRUEBA DE ELIMINAR MEDICOS - FECHAS\n----------------------------------------------------------------------------------------");
		
		/*
		consultorio.eliminarFecha("LOPEZ 3", "15/15/15");
		consultorio.eliminarFecha("LOPEZ 3", "15/15/15");
		consultorio.eliminarMedico("LOPEZ");
		consultorio.eliminarFecha("LOPEZ 4", "15/15/15");
		consultorio.eliminarFecha("LOPEZ 4", "15/15/15");
		*/
		
		System.out.println("\nMEDICOS CON SUS FECHAS\n----------------------------------------------------------------------------------------");
		medicos = consultorio.medicos();
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();			
			System.out.println("\nMEDICO: " + medicoAux);
			
			ConjuntoTDA fechasMedico = consultorio.fechas(medicoAux);
			while (!fechasMedico.conjuntoVacio()){
				String fechaAux = fechasMedico.elegir();
				System.out.println("	FECHA: " + fechaAux);
				
				fechasMedico.sacar(fechaAux);
			}			
			medicos.sacar(medicoAux);
		}
		
		System.out.println("\nMEDICOS CON SUS FECHAS, TURNOS Y PACIENTES\n----------------------------------------------------------------------------------------");
		medicos = consultorio.medicos();
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();			
			System.out.println("\nMEDICO: " + medicoAux);
			
			ConjuntoTDA fechasMedico = consultorio.fechas(medicoAux);			
			while (!fechasMedico.conjuntoVacio()){
				String fechaAux = fechasMedico.elegir();
				System.out.println("	FECHA: " + fechaAux);
				
				ColaPrioridadEstatica colaTurnos = (ColaPrioridadEstatica) consultorio.turnos(medicoAux, fechaAux);
				while (! colaTurnos.colaVacia()){
					System.out.println("		HORA: " + colaTurnos.turno() + " - PACIENTE: " + colaTurnos.paciente());
					colaTurnos.dasacolar();
				}				
				
				fechasMedico.sacar(fechaAux);
			}			
			medicos.sacar(medicoAux);
		}
		
		System.out.println("\nPRUEBA DE ELIMINAR MEDICOS - FECHAS - TURNOS\n----------------------------------------------------------------------------------------");
		
		consultorio.eliminarTurno("LOPEZ 4", "05/15/15", "10:00", "PEPE");
		
		System.out.println("\nMEDICOS CON SUS FECHAS, TURNOS Y PACIENTES\n----------------------------------------------------------------------------------------");
		medicos = consultorio.medicos();
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();			
			System.out.println("\nMEDICO: " + medicoAux);
			
			ConjuntoTDA fechasMedico = consultorio.fechas(medicoAux);			
			while (!fechasMedico.conjuntoVacio()){
				String fechaAux = fechasMedico.elegir();
				System.out.println("	FECHA: " + fechaAux);
				
				ColaPrioridadEstatica colaTurnos = (ColaPrioridadEstatica) consultorio.turnos(medicoAux, fechaAux);
				while (! colaTurnos.colaVacia()){
					System.out.println("		HORA: " + colaTurnos.turno() + " - PACIENTE: " + colaTurnos.paciente());
					colaTurnos.dasacolar();
				}				
				
				fechasMedico.sacar(fechaAux);
			}			
			medicos.sacar(medicoAux);
		}
	}

}
