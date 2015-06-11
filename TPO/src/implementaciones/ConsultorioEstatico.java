package implementaciones;

import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class ConsultorioEstatico implements ConsultorioTDA {
	
	NodoConsultorio[] nodoConsultorio;
	int cantidad;
	
	@Override
	public void inicializar() {
		nodoConsultorio = new NodoConsultorio[100];
		cantidad = 0;
	}

	@Override
	public void agregar(String medico, String fecha, String hora, String paciente) {
		NodoConsultorio nodoNuevo = new NodoConsultorio();
		nodoNuevo.medico = medico;
		
		// TODO AGREGAR FECHA CON HORA Y PACIENTE
		
		nodoConsultorio[cantidad++] = nodoNuevo;
		
		System.out.println("AGREGADO MEDICO: " + nodoNuevo.medico);
	}

	@Override
	public void eliminarTurno(String medico, String fecha, String turno, String paciente) {
		// TODO 				
	}

	@Override
	public void eliminarFecha(String medico, String fecha) {

	}

	@Override
	public void eliminarMedico(String medico) {
		
		for (int i = 0; i < cantidad; i++){		
			
			if(nodoConsultorio[i].medico == medico){
				
				for (int j = i; j < cantidad-1; j++){
					nodoConsultorio[j] = nodoConsultorio[j+1];
				}
				cantidad--;
				break;
			}
			
		}
	}

	@Override
	public ConjuntoTDA medicos() {
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
		
		for (int i = 0; i < cantidad; i++){		
			
			conjunto.agregar(nodoConsultorio[i].medico);
			
		}
		
		return conjunto;
	}

	@Override
	public ConjuntoTDA fechas(String medico) {
		
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
		
		ConjuntoTDA conjuntoAux = medicos();
		while (!conjuntoAux.conjuntoVacio()){
			String medicoAux = conjuntoAux.elegir();
			if (medico.compareTo(medicoAux) == 0){
				// IMPRIMIR FECHAS
				System.out.println("MEDICO ENCONTRADO");
				conjunto.agregar(medicoAux);
				
				break;
			}
			
			conjuntoAux.sacar(medicoAux);
		}
		
		/*
		for(NodoConsultorio nodo : nodoConsultorio){
			conjunto.agregar(nodo.medico);
		}		
		*/
		return conjunto;
	}

	@Override
	public ColaPrioridadTDA turnos(String medico, String fecha) {
		return null;
	}

	
	
}
