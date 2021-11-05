package Logica;

import Dominio.CampeonPoseido;
import Dominio.Skin;
import Dominio.SkinPoseida;

public class ListaSkinsPoseidas {
	private int max;
	private int cant;
	private SkinPoseida [] lista;
	public ListaSkinsPoseidas(int max) {
		this.max = max;
		cant = 0;
		this.lista = new SkinPoseida[999];
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
	public SkinPoseida[] getLista() {
		return lista;
	}
	public void setLista(SkinPoseida[] lista) {
		this.lista = lista;
	}
	public SkinPoseida getSkinPoseida(int index) {
		return lista[index];
	}
	public SkinPoseida getSkinPoseida(String nombre) {
		for(int i = 0; i < cant; i++) {
			if(lista[i].getSkin().getNombre().equals(nombre)) {
				return lista[i];
			}
		}
		return null;
	}
	public SkinPoseida getSkinPoseida(Skin skin) {
		for(int i = 0; i < cant; i++) {
			if(lista[i].getSkin()==skin) {
				return lista[i];
			}
		}
		return null;		
	}
	public boolean agregarSkin(Skin skin, CampeonPoseido champPoseido) {
		SkinPoseida newskin = new SkinPoseida(skin,champPoseido);
		if(cant<max) {
			lista[cant] = newskin;
			cant++;
			champPoseido.getCuentaDueño().agregarPrecioCuenta(skin.getPrecio());
			return true;
		}
		return false;
	}
	public void desplegarSkins() {
		for(int i = 0 ; i < cant; i++) {
			System.out.println("		- "+lista[i].getSkin().getNombre());
		}
	}
}
