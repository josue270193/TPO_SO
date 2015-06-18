package algoritmos;

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
	public void agregarTurno(String medico, String fecha, String hora, String paciente) {

	}

	/**
	 * Eliminar un turno determinado de un paciente determinado a un m�dico
	 * determinado en una fecha determinada.
	 * 
	 */
	public void eliminarTurno(String medico, String fecha, String hora, String paciente) {

	}

	/**
	 * Eliminar una fecha determinada a un medico determinado.
	 * 
	 */
	public void eliminarFechaMedico(String medico, String fecha) {

	}

	/**
	 * Obtener los turnos de un m�dico en una fecha determinada pasada como
	 * par�metro, los turnos deben estar ordenados de menor a mayor.
	 * 
	 */

	public String[][] turnosEnFecha(String medico, String fecha) {
		return null;
	}

	/**
	 * Obtener las fechas en las que un m�dico determinado posee turnos en un
	 * rango de fechas pasadas como par�metro, los fechas deben estar ordenados
	 * de menor a mayor.
	 * 
	 */

	public String[] fechasOcupadas(String medico, String fechaDesde, String fechaHasta) {
		return null;
	}

	/**
	 * Obtener la lista de turnos que posee un paciente entre dos fechas pasadas
	 * como par�metro, devuelve la fecha, el horario y el m�dico con el cual el
	 * paciente tiene turno. El resultado debe estar ordenados por fecha y
	 * dentro de la fecha por horario de menor a mayor.
	 * 
	 */
	public String[][] turnosDePaciente(String paciente, String fechaDesde, String fechaHasta) {

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
	public String[][] agendaConsultorio() {
		return null;
	}

	/**
	 * Obtiene los m�dicos ordenados alfabeticamente;
	 * 
	 */
	public String[] obtenerMedicos() {
		return null;
	}

	/**
	 * Obtiene las fechas de un medico pasado como par�metro ordenadas
	 * cronologicamente;
	 * 
	 */
	public String[] obtenerFechas(String medico) {
		return null;
	}

	private void cargoDatos() {
		/* Agregue aqui algunos datos a la estructura para prueba */

		consultorio.inicializar();

		consultorio.agregar("LOPEZ", "20150615", "10:00", "PEPE");
		consultorio.agregar("LOPEZ", "20150616", "10:00", "PEPE");

	}
}

/*
 * - PROBAR CADA TDA POR SEPARADO - UNA INTERFAZ QUE SE USA PARA LA PRACTICA -
 * HORAS (TURNOS) Y FECHAS SON VALIDAS
 */