package com.david.model;

/**
 * La clase {@code Empleado} representa a un empleado en la empresa, incluyendo
 * su información personal, categoría, años de servicio y sueldo.
 */
public class Empleado {
	private String dni;
	private String nombre;
	private char sexo;
	private int categoria;
	private int anios;
	private double sueldo;

	/**
	 * Constructor de la clase {@code Empleado}.
	 * 
	 * @param dni       el DNI del empleado.
	 * @param nombre    el nombre del empleado.
	 * @param sexo      el sexo del empleado (carácter).
	 * @param categoria la categoría del empleado.
	 * @param anios     los años de servicio del empleado.
	 */
	public Empleado(String dni, String nombre, char sexo, int categoria, int anios) {
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.categoria = categoria;
		this.anios = anios;
		calcularSueldo();
	}

	/**
	 * Obtiene el DNI del empleado.
	 * 
	 * @return el DNI del empleado.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del empleado.
	 * 
	 * @param dni el nuevo DNI del empleado.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el nombre del empleado.
	 * 
	 * @return el nombre del empleado.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del empleado.
	 * 
	 * @param nombre el nuevo nombre del empleado.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el sexo del empleado.
	 * 
	 * @return el sexo del empleado (carácter).
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * Establece el sexo del empleado.
	 * 
	 * @param sexo el nuevo sexo del empleado (carácter).
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene la categoría del empleado.
	 * 
	 * @return la categoría del empleado.
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Establece la categoría del empleado.
	 * 
	 * @param categoria la nueva categoría del empleado.
	 */
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	/**
	 * Obtiene los años de servicio del empleado.
	 * 
	 * @return los años de servicio del empleado.
	 */
	public int getAnios() {
		return anios;
	}

	/**
	 * Establece los años de servicio del empleado.
	 * 
	 * @param anios los nuevos años de servicio del empleado.
	 */
	public void setAnios(int anios) {
		this.anios = anios;
	}

	/**
	 * Obtiene el sueldo del empleado.
	 * 
	 * @return el sueldo del empleado.
	 */
	public double getSueldo() {
		return sueldo;
	}

	/**
	 * Método para calcular el sueldo del empleado basado en la categoría y los años
	 * trabajados. Llama a la clase {@code Nomina} para realizar el cálculo.
	 */
	public void calcularSueldo() {
		this.sueldo = Nomina.calcularSueldo(this);
	}
}
