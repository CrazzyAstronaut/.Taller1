package Dominio;

import Logica.ListaSkins;

public class Campeon {
	private String nombre;
	private String rol;
	private int recaudado;
	private ListaSkins InventarioSkins;

	public Campeon(String nombre, String rol) {
		this.nombre = nombre;
		this.rol = rol;
		this.recaudado = 0;
		InventarioSkins = new ListaSkins(999);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getRecaudado() {
		return recaudado;
	}

	public void setRecaudado(int recaudado) {
		this.recaudado = recaudado;
	}

	public void addRecaudado(int add) {
		this.recaudado += add;
	}

	public ListaSkins getInventarioSkins() {
		return InventarioSkins;
	}

	public void setInventarioSkins(ListaSkins inventarioSkins) {
		InventarioSkins = inventarioSkins;
	}

}
