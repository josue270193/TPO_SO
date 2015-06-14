package implementaciones;

import tda.ConjuntoTDA;

public class ConjuntoEstatico implements ConjuntoTDA {
	public static final int ELEMENTOS = 100;
	
	String elementos[];
	int cantidad;
	
	@Override
	public void inicializar() {
		elementos = new String [ELEMENTOS];
		cantidad = 0;
	}

	@Override
	public boolean conjuntoVacio() {
		return cantidad == 0;
	}	

	@Override
	public void agregar(String valor) {
		if (!this.pertenece(valor)){
			elementos[cantidad] = valor;
			cantidad++;
		}			
	}

	@Override
	public String elegir() {
		return elementos[cantidad - 1];
	}

	@Override
	public void sacar(String valor) {
		int i = 0;
		
		while (i < cantidad && elementos[i].compareToIgnoreCase(valor) != 0){
			i++;
		}
		
		if (i < cantidad ){
			elementos[i] = elementos[cantidad -1];
			cantidad--;
		}
	}

	@Override
	public boolean pertenece(String valor) {
		int i = 0;
		
		while (i < cantidad && elementos[i].compareToIgnoreCase(valor) != 0){
			i++;
		}
		
		return ( i < cantidad );
	}

}
