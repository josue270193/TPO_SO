package implementaciones;

import tda.ABBTurnosTDA;

public class ArbolTurnos implements ABBTurnosTDA {

	NodoABBTurno raiz;
	
	@Override
	public void inicializar() {
		raiz = null;
	}

	@Override
	public void agregar(String turno, String paciente) {
		
		if(raiz == null){
			raiz = new NodoABBTurno();
			raiz.paciente = paciente;
			raiz.hora = turno;
			raiz.hijoDer = new ArbolTurnos();
			raiz.hijoDer.inicializar();
			raiz.hijoIzq = new ArbolTurnos();
			raiz.hijoIzq.inicializar();
		}
		else if(raiz.hora.compareTo(turno) > 0)	// SI EL TURNO A AGREGAR ES MENOR SE AGREGA A LA IZQUIERDA 
			raiz.hijoIzq.agregar(turno,paciente);
		else if(raiz.hora.compareTo(turno) < 0)  // SI EL TURNO A AGREGAR ES MAYOR SE AGREGA A LA DERECHA  
			raiz.hijoDer.agregar(turno, paciente);		
	}

	@Override
	public void eliminar(String turno, String paciente) {
		
		if(raiz != null){			
			if(raiz.hora == turno && raiz.paciente == paciente && raiz.hijoDer.arbolVacio() && raiz.hijoIzq.arbolVacio())
				raiz = null;
			else if(raiz.hora == turno && raiz.paciente == paciente && !raiz.hijoIzq.arbolVacio()){
				raiz.hora = mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminar(raiz.hora, raiz.paciente);
			}
			else if(raiz.hora == turno && raiz.paciente == paciente && raiz.hijoIzq.arbolVacio()){
				raiz.hora = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminar(raiz.hora, raiz.paciente);
			}
			else if(raiz.hora.compareTo(turno) > 0) // BUSCA POR EL NODO IZQUIERDO EL TURNO
				raiz.hijoIzq.eliminar(turno, paciente);
			else{									// BUSCA POR EL NODO DERECHO EL TURNO
				raiz.hijoDer.eliminar(turno, paciente);
			}
		}
	}

	@Override
	public String turno() {
		return raiz.hora;
	}

	@Override
	public String paciente() {
		return raiz.paciente;
	}

	@Override
	public ABBTurnosTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTurnosTDA hijoDer() {
		return raiz.hijoDer;
	}

	@Override
	public boolean arbolVacio() {
		return (raiz == null);
	}
	
	/**
	 * BUSCA LA HORA MAYOR
	 * @param a UN ARBOL ABBTurnosTDA	
	 * */
	private String mayor(ABBTurnosTDA a){		
		if(a.hijoDer().arbolVacio())
			return a.turno();
		else
			return mayor(a.hijoDer());
	}
	
	/**
	 * BUSCA LA HORA MENOR
	 * @param a UN ARBOL ABBTurnosTDA	
	 * */
	private String menor(ABBTurnosTDA a){		
		if(a.hijoIzq().arbolVacio())
			return a.turno();
		else
			return menor(a.hijoIzq());
	}

}
