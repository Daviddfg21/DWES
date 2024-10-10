package com.david.model;

import java.sql.Timestamp;

/**
 * Clase que representa un producto en el sistema. Contiene información sobre el
 * ID, nombre, cantidad, precio, y las fechas de creación y actualización del
 * producto.
 */
public class Producto {

	private int id;
	private String nombre;
	private double cantidad;
	private double precio;
	private Timestamp fechaCrear;
	private Timestamp fechaActualizar;

	/**
	 * Constructor que inicializa un nuevo objeto Producto con los parámetros
	 * especificados.
	 *
	 * @param id              El ID del producto.
	 * @param nombre          El nombre del producto.
	 * @param cantidad        La cantidad del producto.
	 * @param precio          El precio del producto.
	 * @param fechaCrear      La fecha de creación del producto.
	 * @param fechaActualizar La fecha de actualización del producto.
	 */
	public Producto(int id, String nombre, double cantidad, double precio, Timestamp fechaCrear,
			Timestamp fechaActualizar) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fechaCrear = fechaCrear;
		this.fechaActualizar = fechaActualizar;
	}

	/**
	 * Constructor por defecto que crea un objeto Producto vacío.
	 */
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene el ID del producto.
	 *
	 * @return El ID del producto.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el ID del producto.
	 *
	 * @param id El ID a establecer para el producto.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del producto.
	 *
	 * @return El nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del producto.
	 *
	 * @param nombre El nombre a establecer para el producto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la cantidad del producto.
	 *
	 * @return La cantidad del producto.
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Establece la cantidad del producto.
	 *
	 * @param cantidad La cantidad a establecer para el producto.
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Obtiene el precio del producto.
	 *
	 * @return El precio del producto.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto.
	 *
	 * @param precio El precio a establecer para el producto.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Obtiene la fecha de creación del producto.
	 *
	 * @return La fecha de creación del producto.
	 */
	public Timestamp getFechaCrear() {
		return fechaCrear;
	}

	/**
	 * Establece la fecha de creación del producto.
	 *
	 * @param fechaCrear La fecha de creación a establecer para el producto.
	 */
	public void setFechaCrear(Timestamp fechaCrear) {
		this.fechaCrear = fechaCrear;
	}

	/**
	 * Obtiene la fecha de actualización del producto.
	 *
	 * @return La fecha de actualización del producto.
	 */
	public Timestamp getFechaActualizar() {
		return fechaActualizar;
	}

	/**
	 * Establece la fecha de actualización del producto.
	 *
	 * @param fechaActualizar La fecha de actualización a establecer para el
	 *                        producto.
	 */
	public void setFechaActualizar(Timestamp fechaActualizar) {
		this.fechaActualizar = fechaActualizar;
	}

	/**
	 * Devuelve una representación en cadena del objeto Producto.
	 *
	 * @return Una cadena que representa el producto.
	 */
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", fechaCrear=" + fechaCrear + ", fechaActualizar=" + fechaActualizar + "]";
	}

}
