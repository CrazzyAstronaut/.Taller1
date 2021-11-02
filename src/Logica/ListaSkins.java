package Logica;

import Dominio.Skin;

public class ListaSkins {
	private int max;
	private int cant;
	private Skin [] lista;
	
	public ListaSkins(int max) {
		this.max = max;
		this.cant = 0;
		this.lista = new Skin[max];
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

	public Skin[] getLista() {
		return lista;
	}

	public void setLista(Skin[] lista) {
		this.lista = lista;
	}
	public Skin getSkin(int index) {
		return lista[index];
		}
	public Skin getSkin(String nombre) {
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getNombre().equalsIgnoreCase(nombre)){
				return lista[i];
			}
		}
		return null;
	}
	public boolean agregarSkin(Skin newskin) {
		if(cant<max) {
			lista[cant] = newskin;
			cant++;
			return true;
		}
		return false;
	}
	
}
