package algoritmos;

import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConjuntoEstatico;
import implementaciones.ConsultorioEstatico;

import java.util.ArrayList;
import java.util.List;

import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class Algoritmos {

	/*
	 * No modificar las siguientes lineas sin una indicaci�n expresa por parte
	 * de la c�tedra
	 */
	private ConsultorioTDA consultorio;

	public Algoritmos() {
		consultorio = new ConsultorioEstatico();
		cargoDatos();
	}

	/*
	 * Fin de la no modificacion de lineas sin una indicaci�n expresa por parte
	 * de la c�tedra
	 */

	/**
	 * Agregarle un turno a un m�dico determinado en una fecha determinada en un
	 * horario determinado para un paciente determinado.
	 * 
	 */
	public void agregarTurno(String medico, String fecha, String hora, String paciente) // JOSUE
	{
		consultorio.agregar(medico, fecha, hora, paciente);
	}

	/**
	 * Eliminar un turno determinado de un paciente determinado a un m�dico
	 * determinado en una fecha determinada.
	 * 
	 */
	public void eliminarTurno(String medico, String fecha, String hora, String paciente) // LEANDRO
	{

	}

	/**
	 * Eliminar una fecha determinada a un medico determinado.
	 * 
	 */
	public void eliminarFechaMedico(String medico, String fecha) // LEANDRO
	{

	}

	/**
	 * Obtener los turnos de un m�dico en una fecha determinada pasada como
	 * par�metro, los turnos deben estar ordenados de menor a mayor.
	 * 
	 */

	public String[][] turnosEnFecha(String medico, String fecha) //SCULLI
	{
		ColaPrioridadEstatica cola = new ColaPrioridadEstatica();
		cola.inicializar();
		
		ConjuntoTDA fechas = this.consultorio.fechas(medico);
		
		while (!fechas.conjuntoVacio()) {
			String f = fechas.elegir();
			
			if( !f.equals(fecha) ){
				fechas.sacar(f);
				continue;
			}
			
			ColaPrioridadTDA turnos = this.consultorio.turnos(medico, fecha);
			
			while( !turnos.colaVacia()  ){
				
				cola.acolar(turnos.paciente(), turnos.turno());
				
				turnos.dasacolar();
			}
			
			fechas.sacar(fecha);
		}

		String[][] turnosEnFecha = new String[cola.cantidad()][2];

		for (int i = 0; i < turnosEnFecha.length; i++) {
			
			turnosEnFecha[i][0] = cola.turno();
			turnosEnFecha[i][1] = cola.paciente();
			
			cola.dasacolar();
		}
		
		return turnosEnFecha;

	}

	/**
	 * Obtener las fechas en las que un m�dico determinado posee turnos en un
	 * rango de fechas pasadas como par�metro, los fechas deben estar ordenados
	 * de menor a mayor.
	 * 
	 */
	public String[] fechasOcupadas(String medico, String fechaDesde, String fechaHasta) //SCULLI
	{
		
		ColaPrioridadEstatica cola = new ColaPrioridadEstatica();
		cola.inicializar();
		
		ConjuntoTDA fechas = this.consultorio.fechas(medico);
		
		while (!fechas.conjuntoVacio()) {
			String fecha = fechas.elegir();
			
			if( fecha.compareTo(fechaDesde) < 0 && fecha.compareTo(fechaHasta) > 0 ){
				continue;
			}
			
			ColaPrioridadTDA turnos = this.consultorio.turnos(medico, fecha);
			
			if( !turnos.colaVacia()  ){
				
				cola.acolar(fecha, fecha);
				
			}
			
			fechas.sacar(fecha);
		}

		String[] fechasOcupadas = new String[cola.cantidad()];

		for (int i = 0; i < fechasOcupadas.length; i++) {
			
			String r = cola.paciente();
			
			fechasOcupadas[i] = r;
			cola.dasacolar();
		}
		
		return fechasOcupadas;
	}

	/**
	 * Obtener la lista de turnos que posee un paciente entre dos fechas pasadas
	 * como par�metro, devuelve la fecha, el horario y el m�dico con el cual el
	 * paciente tiene turno. El resultado debe estar ordenados por fecha y
	 * dentro de la fecha por horario de menor a mayor.
	 * 
	 */
	public String[][] turnosDePaciente(String paciente, String fechaDesde, String fechaHasta) //SCULLI
	{

		List<List<String>> respuesta = new ArrayList<List<String>>();

		ConjuntoTDA medicos = consultorio.medicos();

		while (!medicos.conjuntoVacio()) {

			String medico = medicos.elegir();

			ConjuntoTDA fechasMedico = consultorio.fechas(medico);

			while (!fechasMedico.conjuntoVacio()) {

				String fecha = fechasMedico.elegir();

				if (fechaDesde.compareTo(fecha) > 0 || fechaHasta.compareTo(fecha) < 0) {

					fechasMedico.sacar(fecha);
					continue;

				}

				ColaPrioridadTDA turnos = consultorio.turnos(medico, fecha);
				ColaPrioridadTDA turnosCantidad = consultorio.turnos(medico, fecha);

				int cantidad = 0;

				while (!turnosCantidad.colaVacia()) {

					if (turnosCantidad.paciente().equals(paciente)) {
						cantidad++;
						turnosCantidad.dasacolar();
					}

				}

				while (!turnos.colaVacia()) {

					if (turnos.paciente().equals(paciente)) {

						ArrayList<String> aux = new ArrayList<String>();

						aux.add(medico);
						aux.add(fecha);
						aux.add(turnos.turno());

						respuesta.add(aux);

					}

					turnos.dasacolar();

				}

				fechasMedico.sacar(fecha);

			}

			medicos.sacar(medico);

		}

		String[][] res = new String[respuesta.size()][3];

		int i = 0;
		for (List<String> list : respuesta) {

			int j = 0;
			for (String value : list) {
				res[i][j] = value;
				j++;
			}

			i++;
		}

		return res;

	}

	/**
	 * Obtener la agenda del consultorio ordenada por m�dico, fecha, turno y
	 * paciente
	 * */
	public String[][] agendaConsultorio() //SCULLI
	{

		String[][] result = new String[100][4];
		int i = 0;

		ConjuntoTDA medicos = this.consultorio.medicos();

		while (!medicos.conjuntoVacio()){

			String medico = medicos.elegir();

			ConjuntoTDA fechas = this.consultorio.fechas(medico);

			while (!fechas.conjuntoVacio()){

				String fecha = fechas.elegir();

				ColaPrioridadTDA turnos = this.consultorio.turnos(medico, fecha);

				while(!turnos.colaVacia()){

					result[i][0] = medico;
					result[i][1] = fecha;
					result[i][2] = turnos.turno();
					result[i][3] = turnos.paciente();

					turnos.dasacolar();
					i++;
				}

				fechas.sacar(fecha);

			}

			medicos.sacar(medico);
		}

		return result;
	}

	/**
	 * Obtiene los m�dicos ordenados alfabeticamente;
	 * 
	 */
	public String[] obtenerMedicos() // JOSUE
	{
		ConjuntoTDA medicos = consultorio.medicos();

		int i = 0;
		while (!medicos.conjuntoVacio()) {
			String medicoAux = medicos.elegir();
			i++;
			medicos.sacar(medicoAux);
		}

		medicos = consultorio.medicos();
		String resultado[] = new String[i];
		i = 0;

		while (!medicos.conjuntoVacio()) {
			String medicoAux = medicos.elegir();
			resultado[i++] = medicoAux;
			medicos.sacar(medicoAux);
		}

		return resultado;
	}

	/**
	 * Obtiene las fechas de un medico pasado como par�metro ordenadas
	 * cronologicamente;
	 * 
	 */
	public String[] obtenerFechas(String medico) // JOSUE
	{
		ConjuntoTDA fechas = consultorio.fechas(medico);
		int i = 0;

		while (!fechas.conjuntoVacio()) {
			String fechaAux = fechas.elegir();
			i++;
			fechas.sacar(fechaAux);
		}

		fechas = consultorio.fechas(medico);
		String resultado[] = new String[i];
		i = 0;

		while (!fechas.conjuntoVacio()) {
			String fechaAux = fechas.elegir();
			resultado[i++] = fechaAux;
			fechas.sacar(fechaAux);
		}

		return resultado;
	}

	private void cargoDatos() {
		consultorio.inicializar();

		/* Agregue aqui algunos datos a la estructura para prueba */
		consultorio.agregar("ZAPATA", "20150515", "15:00", "MARIO");
		consultorio.agregar("ZAPATA", "20150516", "15:00", "MARIA");
		consultorio.agregar("ZAPATA", "20150515", "10:00", "MARIO");
		consultorio.agregar("LOPEZ", "20150516", "11:00", "PEPE");

	}
}

/*
 * - PROBAR CADA TDA POR SEPARADO - UNA INTERFAZ QUE SE USA PARA LA PRACTICA -
 * HORAS (TURNOS) Y FECHAS SON VALIDAS - FORMATO HORA XX:XX - FORMATO FECHA
 * YYYYMMDD - SIENDO YYYY A�O MM MES DD DIA
 */
