package Logica;

import Dominio.Campeon;
import Dominio.CampeonPoseido;
import Dominio.Cuenta;

public class ListaCampeonesPoseidos {
	private int max;
	private int cant;
	private CampeonPoseido [] lista;
	public ListaCampeonesPoseidos(int max) {
		this.max = max;
		cant = 0;
		this.lista = new CampeonPoseido[max];
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
	public CampeonPoseido[] getLista() {
		return lista;
	}
	public void setLista(CampeonPoseido[] lista) {
		this.lista = lista;
	}
	public boolean comprarCampeon(Campeon champ, Cuenta cuenta) {
		CampeonPoseido newChamp = new CampeonPoseido(champ,cuenta);
		if(cant<max) {
			lista[cant] = newChamp;
			cant++;
			champ.addRecaudado(950);
			return true;
		}
		return false;
	}
}