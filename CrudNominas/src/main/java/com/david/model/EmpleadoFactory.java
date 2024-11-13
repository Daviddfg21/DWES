package com.david.model;

/**
 * La clase {@code EmpleadoFactory} es responsable de la creación de objetos
 * {@code Empleado}. Utiliza el patrón Factory para encapsular la lógica de
 * creación de empleados.
 */
public class EmpleadoFactory {

	/**
	 * Crea un nuevo objeto {@code Empleado}.
	 * 
	 * @param dni       el DNI del empleado.
	 * @param nombre    el nombre del empleado.
	 * @param sexo      el sexo del empleado.
	 * @param categoria la categoría del empleado.
	 * @param anios     los años de servicio del empleado.
	 * @return un nuevo objeto {@code Empleado}.
	 */
	public static Empleado crearEmpleado(String dni, String nombre, char sexo, int categoria, int anios) {
		return new Empleado(dni, nombre, sexo, categoria, anios);
	}
}
