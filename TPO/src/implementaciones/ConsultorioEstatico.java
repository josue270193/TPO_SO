package implementaciones;

import java.util.Arrays;
import java.util.Comparator;

import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class ConsultorioEstatico implements ConsultorioTDA {
	
	public static final int ELEMENTOS = 100;
	
	NodoConsultorio[] nodoConsultorio;
	int cantidad;
	
	@Override
	public void inicializar() {
		nodoConsultorio = new NodoConsultorio[ELEMENTOS];
		for (int i = 0; i < ELEMENTOS; i++){
			nodoConsultorio[i] = new NodoConsultorio();
			nodoConsultorio[i].fechas = new NodoFecha[ELEMENTOS];
			nodoConsultorio[i].cantidadFechas = 0;
		}
				
		cantidad = 0;
	}

	@Override
	public void agregar(String medico, String fecha, String hora, String paciente) {
		
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			
			NodoConsultorio nodoNuevo = new NodoConsultorio();
			nodoNuevo.medico = medico;
			nodoNuevo.fechas = new NodoFecha[ELEMENTOS];
			nodoNuevo.cantidadFechas = 0;
			
			NodoFecha nodoFecha = new NodoFecha();
			nodoFecha.fecha = fecha;
			// TODO AGREGAR ARBOL
			nodoNuevo.fechas[nodoNuevo.cantidadFechas++] = nodoFecha;
			
			nodoConsultorio[cantidad++] = nodoNuevo;
			
			System.out.println("AGREGADO NUEVO MEDICO: " + medico);
		}else{
			
			int posicionFecha = existeFecha(posicionMedico, fecha);
			if (posicionFecha == -1){	// NO EXISTE LA FECHA
				NodoFecha nodoFecha = new NodoFecha();
				nodoFecha.fecha = fecha;
				// TODO AGREGAR ARBOL
				nodoConsultorio[posicionMedico].fechas[nodoConsultorio[posicionMedico].cantidadFechas++] = nodoFecha;
				
				System.out.println("AGREGADO NUEVA FECHA: " + medico);
			}else{
				
				System.out.println("AGREGADO: " + medico);
			}
		}
			
		
		
		
		
	}

	private int existeFecha(int posicionMedico, String fecha) {		
		for (int i = 0; i < nodoConsultorio[posicionMedico].cantidadFechas; i++){				
			if(nodoConsultorio[posicionMedico].fechas[i].fecha == fecha){						
				return i;
			}			
		}		
		return -1;
	}

	private int existeMedico(String medico) {		
		for (int i = 0; i < cantidad; i++){					
			if(nodoConsultorio[i].medico == medico){						
				return i;
			}			
		}		
		return -1;
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
		
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			return conjunto;
		}else{
			
			for (int i = 0; i < nodoConsultorio[posicionMedico].cantidadFechas; i++){
				conjunto.agregar(nodoConsultorio[posicionMedico].fechas[i].fecha);
			}
			
			//return ordernarConjuntoMayorMenor(conjunto);
			return ordernarConjuntoMenorMayor(conjunto);
			
		}
	}
	
	private ConjuntoTDA ordernarConjuntoMenorMayor(ConjuntoTDA c) {
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
				
		String valores[] = new String[((ConjuntoEstatico)c).cant];
		int i = 0;
		while (!c.conjuntoVacio()){
			String elementoAux = c.elegir();			
			valores[i++] = elementoAux;
			
			c.sacar(elementoAux);
		}		
		
		Arrays.sort(valores, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {				
				return o1.compareTo(o2);
			}
		});
		
		while(i != 0){			
			conjunto.agregar(valores[--i]);
		}
		
		return conjunto;
	}
	
	private ConjuntoTDA ordernarConjuntoMayorMenor(ConjuntoTDA c) {
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
				
		String valores[] = new String[((ConjuntoEstatico)c).cant];
		int i = 0;
		while (!c.conjuntoVacio()){
			String elementoAux = c.elegir();			
			valores[i++] = elementoAux;
			
			c.sacar(elementoAux);
		}		
		
		Arrays.sort(valores, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {				
				return o2.compareTo(o1);
			}
		});
		
		while(i != 0){			
			conjunto.agregar(valores[--i]);
		}
		
		return conjunto;
	}

	@Override
	public ColaPrioridadTDA turnos(String medico, String fecha) {
		return null;
	}

	
	
}
