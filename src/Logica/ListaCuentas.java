package Logica;

import Dominio.Cuenta;

public class ListaCuentas {

	private int max;
	private int cant;
	private Cuenta [] lista;
	
	public ListaCuentas(int max) {
		this.max = max;
		cant = 0;
		this.lista = new Cuenta[max];
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

	public Cuenta[] getLista() {
		return lista;
	}

	public void setLista(Cuenta[] lista) {
		this.lista = lista;
	}
	public Cuenta getCuenta(String nombre) {
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getNombre().equals(nombre)){
				return lista[i];
			}
		}
		return null;
	}
	public Cuenta getCuenta(int index) {
		return lista[index];
	}
	public Cuenta getCuentaNick(String nick) {
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getNick().equals(nick)){
				return lista[i];
			}
		}
		return null;
	}
	public double getRecaudadoRegion(String Region) {
		int Recaudado = 0;
		for(int i = 0; i < cant ; i++ ) {
			if(lista[i].getRegion().equals(Region)) {
				Recaudado += lista[i].getPrecioCuenta();
			}
		}
		return Recaudado*6.15;//aaaaaaaaaaaaaaaa
	}
	public boolean agregarCuenta(Cuenta newcuenta) {
		if(cant<max) {
			lista[cant] = newcuenta;
			cant++;
			return true;
		}
		return false;
	}
	public void ordenar() {
		for(int i = 0; i < cant; i++) {
			for(int j = 0; j < cant-1; j++) {
				if(lista[j].getNivel()<lista[j+1].getNivel()) {
					Cuenta aux = lista[j];
					lista[j]=lista[j+1];
					lista[j+1]=aux;
				}
			}
		}
	}
	public void desplegarCuentas() {
		for(int i = 0; i < cant; i++) {
			System.out.println(lista[i].getNick()+" Nivel: "+lista[i].getNivel());
		}
	}
}
