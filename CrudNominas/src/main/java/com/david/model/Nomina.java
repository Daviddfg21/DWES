package com.david.model;

public class Nomina {
	private static final int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	public static double calcularSueldo(Empleado empleado) {
		int categoria = empleado.getCategoria();
		int añosTrabajados = empleado.getAnios();
		if (categoria < 1 || categoria > 10) {
			throw new IllegalArgumentException("Categoría no válida");
		}
		return SUELDO_BASE[categoria - 1] + (5000 * añosTrabajados);
	}
}
