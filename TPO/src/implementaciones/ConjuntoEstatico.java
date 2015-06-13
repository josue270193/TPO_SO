package implementaciones;

import tda.ConjuntoTDA;

public class ConjuntoEstatico implements ConjuntoTDA {
	public static final int ELEMENTOS = 100;
	
	String[] valores;
	int cant;
	
	@Override
	public void inicializar() {
		valores = new String [ELEMENTOS];
		cant = 0;
	}

	@Override
	public boolean conjuntoVacio() {
		return cant == 0;
	}	

	@Override
	public void agregar(String valor) {
		if (!this.pertenece(valor)){
			valores[cant] = valor;
			cant++;
		}
			
	}

	@Override
	public String elegir() {
		return valores[cant - 1];
	}

	@Override
	public void sacar(String valor) {
		int i = 0;
		
		while (i < cant && valores[i].compareToIgnoreCase(valor) != 0)
			i++;
		
		if (i < cant ){
			valores[i] = valores[cant -1];
			cant--;
		}

	}

	@Override
	public boolean pertenece(String valor) {
		int i = 0;
		
		while (i < cant && valores[i].compareToIgnoreCase(valor) != 0)
			i++;
		
		return ( i < cant );
	}

}
