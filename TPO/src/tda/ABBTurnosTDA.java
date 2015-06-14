package tda;

public interface ABBTurnosTDA {

	public void inicializar();
	
	/**
	 * inicializado
	 * @param turno ES LA HORA QUE SE LE ASIGNA A UN PACIENTE
	 * @param paciente ES NOMBRE DE UN PACIENTE
	 * */
	public void agregar(String turno, String paciente);
	
	/**
	 * inicializado
	 * @param turno ES LA HORA QUE SE LE ASIGNA A UN PACIENTE
	 * @param paciente ES NOMBRE DE UN PACIENTE
	 * */
	public void eliminar(String turno, String paciente);
	
	/**
	 * inicializado y no vacio
	 * 
	 * ES LA HORA DE UN PACIENTE
	 * */
	public String turno();
	
	/**
	 * inicializado y no vacio
	 * */
	public String paciente();
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBTurnosTDA hijoIzq();
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBTurnosTDA hijoDer();
	
	/**
	 * inicializado
	 * */
	public boolean arbolVacio();
}
