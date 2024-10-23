package com.david.model;

/**
 * La clase {@code Nomina} se encarga de calcular el sueldo de los empleados en
 * función de su categoría y los años de servicio.
 */
public class Nomina {

	/**
	 * Sueldos base correspondientes a cada categoría de empleado. Las categorías
	 * van de 1 a 10, y el sueldo base aumenta con cada categoría.
	 */
	private static final int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Calcula el sueldo de un empleado basándose en su categoría y años trabajados.
	 *
	 * @param empleado el empleado del cual se va a calcular el sueldo.
	 * @return el sueldo total del empleado.
	 * @throws IllegalArgumentException si la categoría del empleado no es válida
	 *                                  (fuera del rango 1-10).
	 */
	public static double calcularSueldo(Empleado empleado) {
		int categoria = empleado.getCategoria();
		int añosTrabajados = empleado.getAnios();

		// Validación de categoría
		if (categoria < 1 || categoria > 10) {
			throw new IllegalArgumentException("Categoría no válida");
		}

		// Cálculo del sueldo
		return SUELDO_BASE[categoria - 1] + (5000 * añosTrabajados);
	}
}
