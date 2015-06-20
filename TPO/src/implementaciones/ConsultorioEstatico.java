package implementaciones;

import java.util.Arrays;
import java.util.Comparator;

import tda.ABBTurnosTDA;
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
						
			ArbolTurnos arbolTurnos = new ArbolTurnos();
			arbolTurnos.inicializar();
			arbolTurnos.agregar(hora, paciente);
			
			NodoFecha nodoFecha = new NodoFecha();
			nodoFecha.fecha = fecha;
			nodoFecha.turnos = arbolTurnos;
			
			nodoNuevo.fechas[nodoNuevo.cantidadFechas++] = nodoFecha;			
					
			nodoConsultorio[cantidad++] = nodoNuevo;
			
			System.out.println("1 - MEDICO: " + medico + " FECHA: " + fecha + " HORA: " + hora + " PACIENTE: " + paciente );
		}else{
			
			int posicionFecha = existeFecha(posicionMedico, fecha);
			
			if (posicionFecha == -1){	// NO EXISTE LA FECHA
				
				ArbolTurnos arbolTurnos = new ArbolTurnos();
				arbolTurnos.inicializar();
				arbolTurnos.agregar(hora, paciente);
				
				NodoFecha nodoFecha = new NodoFecha();
				nodoFecha.fecha = fecha;
				nodoFecha.turnos = arbolTurnos;
				
				nodoConsultorio[posicionMedico].fechas[nodoConsultorio[posicionMedico].cantidadFechas++] = nodoFecha;
				
				System.out.println("2 - MEDICO: " + medico + " FECHA: " + fecha + " HORA: " + hora + " PACIENTE: " + paciente);
			}else{				
				nodoConsultorio[posicionMedico].fechas[posicionFecha].turnos.agregar(hora, paciente);								
				System.out.println("3 - MEDICO: " + medico + " FECHA: " + fecha + " HORA: " + hora + " PACIENTE: " + paciente);
			}
		}	
	}

	@Override
	public void eliminarTurno(String medico, String fecha, String turno, String paciente) {
		
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			
			System.out.println("ELIMINANDO TURNO - NO EXISTE MEDICO: " + medico);
			//System.out.println("ELIMINANDO TURNO - NO EXISTE MEDICO: " + medico + " NI FECHA: " + fecha + " HORA: " + turno + " PACIENTE: " + paciente);
			
		}else{
			int posicionFecha = existeFecha(posicionMedico, fecha);
			if (posicionFecha == -1){	// NO EXISTE LA FECHA
				
				System.out.println("ELIMINANDO TURNO - MEDICO: " + medico + " NO EXISTE FECHA: " + fecha);
					
			}else{
				
				if (existeTurno(nodoConsultorio[posicionMedico].fechas[posicionFecha].turnos, turno, paciente)){					
					nodoConsultorio[posicionMedico].fechas[posicionFecha].turnos.eliminar(turno, paciente);
										
					if (nodoConsultorio[posicionMedico].fechas[posicionFecha].turnos.arbolVacio()){
						eliminarFecha(medico, fecha);
					}
				}
				
				/*
				for (int j = posicionFecha; j < nodoConsultorio[posicionMedico].cantidadFechas-1; j++){
					nodoConsultorio[posicionMedico].fechas[j] = nodoConsultorio[posicionMedico].fechas[j+1];
				}
				nodoConsultorio[posicionMedico].cantidadFechas--;
				
				System.out.println("ELIMINANDO FECHA - MEDICO: " + medico + " FECHA: " + fecha);
				
				*/
			}
		}			
	}

	@Override
	public void eliminarFecha(String medico, String fecha) {
				
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			
			System.out.println("ELIMINANDO FECHA NO EXISTE - MEDICO: " + medico);
			
		}else{
			int posicionFecha = existeFecha(posicionMedico, fecha);
			if (posicionFecha == -1){	// NO EXISTE LA FECHA
				
				System.out.println("ELIMINANDO FECHA - MEDICO: " + medico + " NO EXISTE FECHA: " + fecha);
				
			}else{
				for (int j = posicionFecha; j < nodoConsultorio[posicionMedico].cantidadFechas-1; j++){
					nodoConsultorio[posicionMedico].fechas[j] = nodoConsultorio[posicionMedico].fechas[j+1];
				}
				nodoConsultorio[posicionMedico].cantidadFechas--;
				
				System.out.println("ELIMINANDO FECHA - MEDICO: " + medico + " FECHA: " + fecha);
				
				if (nodoConsultorio[posicionMedico].cantidadFechas == 0){
					eliminarMedico(medico);
				}
			}
		}		
	}

	@Override
	public void eliminarMedico(String medico) {
				
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			
			System.out.println("ELIMINANDO MEDICO NO EXISTE - MEDICO: " + medico);
			
		}else{			
			for (int j = posicionMedico; j < cantidad-1; j++){
				nodoConsultorio[j] = nodoConsultorio[j+1];
			}
			cantidad--;
			
			System.out.println("ELIMINANDO MEDICO - MEDICO: " + medico);
		}
		
	}

	@Override
	public ConjuntoTDA medicos() {
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
		
		for (int i = 0; i < cantidad; i++){					
			conjunto.agregar(nodoConsultorio[i].medico);			
		}
		
		//return conjunto;
		return ordernarConjuntoMenorMayor(conjunto);
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

	@Override
	public ColaPrioridadTDA turnos(String medico, String fecha) {
		ColaPrioridadEstatica cola = new ColaPrioridadEstatica();
		cola.inicializar();
		
		int posicionMedico = existeMedico(medico);
		
		if (posicionMedico == -1){ // NO EXISTE EL MEDICO
			
		}else{
			
			int posicionFecha = existeFecha(posicionMedico, fecha);
			
			if (posicionFecha == -1){	// NO EXISTE LA FECHA
								
			}else{
				crearColaPrioridad(cola, nodoConsultorio[posicionMedico].fechas[posicionFecha].turnos);				
			}
		}			
		return cola;
	}
	
	private void crearColaPrioridad(ColaPrioridadTDA cola, ABBTurnosTDA nodo){
		
		if (! nodo.arbolVacio()){			
			crearColaPrioridad(cola, nodo.hijoIzq());
			cola.acolar(nodo.paciente(), nodo.turno());
			crearColaPrioridad(cola, nodo.hijoDer());
		}
		
	}
	
	/**
	 * BUSCA SI EL TURNO Y EL PACIENTE EXISTE EN EL ARBOL
	 * @param arbol 
	 * @param turno
	 * @param paciente
	 * @return
	 */
	private boolean existeTurno(ABBTurnosTDA arbol, String turno, String paciente) {
	
		if (arbol.arbolVacio())
			return false;
		else{
			if (arbol.turno().compareTo(turno) == 0 && arbol.paciente().compareTo(paciente) == 0){
				return true;
			}else{
				return (existeTurno(arbol.hijoIzq(), turno, paciente) || existeTurno(arbol.hijoDer(), turno, paciente)); 
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

	private ConjuntoTDA ordernarConjuntoMenorMayor(ConjuntoTDA c) {
		ConjuntoEstatico conjunto = new ConjuntoEstatico();
		conjunto.inicializar();
		
		String valores[] = new String[((ConjuntoEstatico)c).cantidad];
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
		
		String valores[] = new String[((ConjuntoEstatico)c).cantidad];
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
}
