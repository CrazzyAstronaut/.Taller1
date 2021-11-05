package Logica;

import Dominio.Campeon;

public class ListaCampeones {
	private int max;
	private int cant;
	private Campeon [] lista;
	
	public ListaCampeones(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Campeon[999];
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public Campeon[] getLista() {
		return lista;
	}

	public void setLista(Campeon[] lista) {
		this.lista = lista;
	}
	
	public Campeon getCampeon(int index) {
		return lista[index];
	}
		
	public Campeon getCampeon(String nombre) {
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getNombre().equalsIgnoreCase(nombre)){
				return lista[i];
			}
		}
		return null;
	}

	public double getRecaudadoRol(String rol) {
		int Recaudado = 0;
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getRol().equals(rol)){
				Recaudado += lista[i].getRecaudado();
			}
		}
		return Recaudado*6.15;
	}
	
 	public boolean agregarCampeon(Campeon newchamp) {
		if(cant<max) {
			lista[cant] = newchamp;
			cant++;
			return true;
		}
		return false;
	}
 	
 	public int cantCampeonesRol(String rol) {
 		int cantidad = 0;
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getRol().equals(rol)){
				cantidad++;
			}
		}
		return cantidad;
 	}
}
