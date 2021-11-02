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
}
