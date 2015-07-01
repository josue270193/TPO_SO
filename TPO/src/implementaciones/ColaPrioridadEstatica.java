package implementaciones;

import tda.ColaPrioridadTDA;

public class ColaPrioridadEstatica implements ColaPrioridadTDA {

	public final static int ELEMENTOS = 100;
	
	ElementosColaPrioridad elementos[];
	int cantidad = 0;
	
	@Override
	public void inicializar(){
		elementos = new ElementosColaPrioridad[ELEMENTOS];
	}

	@Override
	public void acolar(String paciente, String turno) {
		int x = cantidad;
		
		for(; x > 0 && elementos[x-1].hora.compareTo(turno) <= 0; x--){
			elementos[x] = elementos[x-1];
		}
		
		elementos[x] = new ElementosColaPrioridad();
		elementos[x].paciente = paciente;
		elementos[x].hora = turno;
		cantidad++;
	}

	@Override
	public void dasacolar() {
		elementos[cantidad - 1] = null;
		cantidad--;
	}

	@Override
	public String paciente() {
		return elementos[cantidad - 1].paciente;
	}

	@Override
	public String turno() {
		return elementos[cantidad - 1].hora;
	}

	@Override
	public boolean colaVacia() {
		return (cantidad == 0);
	}
	
	public int cantidad(){
		return cantidad;
	}

}
