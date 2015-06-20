package algoritmos;

import implementaciones.ConsultorioEstatico;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class Algoritmos {

	/* No modificar las siguientes lineas sin una indicaci�n expresa por parte de la c�tedra*/
	private ConsultorioTDA consultorio;
	public Algoritmos()
	{
		consultorio = new ConsultorioEstatico();
		cargoDatos();
	}
	/* Fin de la no modificacion de lineas sin una indicaci�n expresa por parte de la c�tedra*/
	
	
	/**	
	 * Agregarle un turno a un m�dico determinado en una fecha determinada en un horario determinado 
	 * para un paciente determinado.
	 * 
	 */
	public void agregarTurno(String medico, String fecha, String hora, String paciente) // JOSUE
	{
		consultorio.agregar(medico, fecha, hora, paciente);
	}
	
	/**
	 * 	Eliminar un turno determinado de un paciente determinado a un m�dico determinado 
	 *  en una fecha determinada.
	 *  
	 */
	public void eliminarTurno(String medico, String fecha, String hora, String paciente) // LEANDRO
	{
		
	}
	
	/**
	 * 	Eliminar una fecha determinada a un medico determinado. 
	 *  
	 */
	public void eliminarFechaMedico(String medico, String fecha) // LEANDRO
	{
		
	}
	
	/**
	 * Obtener los turnos de un m�dico en una fecha determinada pasada como par�metro, 
	 * los turnos deben estar ordenados de menor a mayor.
	 * 
	 */
	
	public String[][] turnosEnFecha(String medico, String fecha) // LEANDRO
	{
		return null;
	}
	
	/**
	 * Obtener las fechas en las que un m�dico determinado posee turnos en un rango de  
	 * fechas pasadas como par�metro, los fechas deben estar ordenados de menor a mayor.
	 * 
	 */
	
	public String[] fechasOcupadas(String medico, String fechaDesde, String fechaHasta) // JAVIER
	{
		return null;
	}
	
	/**
	 * Obtener la lista de turnos que posee un paciente entre dos fechas pasadas como par�metro, devuelve 
	 * la fecha, el horario y el m�dico con el cual el paciente tiene turno. El resultado debe estar ordenados 
	 * por fecha y dentro de la fecha por horario de menor a mayor.
	 * 
	 */
	public String[][] turnosDePaciente(String paciente, String fechaDesde, String fechaHasta) // JAVIER
	{
		return null;
	}

	/**
	 * Obtener la agenda del consultorio ordenada por m�dico, fecha, turno y paciente
	 * */
	public String[][] agendaConsultorio() // JAVIER
	{
		return null;
	}

	/**
	 * Obtiene los m�dicos ordenados alfabeticamente;
	 * 
	 */
	public String[] obtenerMedicos() // JOSUE
	{
		ConjuntoTDA medicos = consultorio.medicos();		
		
		int i = 0;
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();
			i++;
			medicos.sacar(medicoAux);
		}
		
		medicos = consultorio.medicos();
		String resultado[] = new String[i];
		i = 0;
		
		while (!medicos.conjuntoVacio()){
			String medicoAux = medicos.elegir();
			resultado[i++] = medicoAux;
			medicos.sacar(medicoAux);
		}
		
		return resultado;
	}
	
	/**
	 * Obtiene las fechas de un medico pasado como par�metro ordenadas cronologicamente;
	 * 
	 */
	public String[] obtenerFechas(String medico) // JOSUE
	{
		ConjuntoTDA fechas = consultorio.fechas(medico);
		int i = 0;
		
		while (!fechas.conjuntoVacio()){
			String fechaAux = fechas.elegir();
			i++;
			fechas.sacar(fechaAux);
		}
		
		fechas = consultorio.fechas(medico);
		String resultado[] = new String[i];
		i = 0;
		
		while (!fechas.conjuntoVacio()){
			String fechaAux = fechas.elegir();
			resultado[i++] = fechaAux;
			fechas.sacar(fechaAux);
		}
		
		return resultado;		
	}
	
	private void cargoDatos()
	{
		consultorio.inicializar();
		
		/* Agregue aqui algunos datos a la estructura para prueba */
		consultorio.agregar("ZAPATA", "20150515", "15:00", "MARIA");
		
	}
}


/*
	-	PROBAR CADA TDA POR SEPARADO
	-	UNA INTERFAZ QUE SE USA PARA LA PRACTICA 
	- 	HORAS (TURNOS) Y FECHAS SON VALIDAS
	- 	FORMATO HORA XX:XX
	- 	FORMATO FECHA YYYYMMDD	- SIENDO YYYY A�O MM MES DD DIA
	
*/