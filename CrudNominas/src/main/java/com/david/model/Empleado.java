package com.david.model;

public class Empleado {
	private String dni;
	private String nombre;
	private char sexo;
	private int categoria;
	private int anios;
	private double sueldo;

	// Constructor
	public Empleado(String dni, String nombre, char sexo, int categoria, int anios) {
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.categoria = categoria;
		this.anios = anios;
		calcularSueldo();
	}

	// Getters y setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getAnios() {
		return anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

	public double getSueldo() {
		return sueldo;
	}

	// Método para calcular el sueldo basado en la categoría y años trabajados
	public void calcularSueldo() {
		this.sueldo = (categoria * 1000) + (anios * 100);
	}
}