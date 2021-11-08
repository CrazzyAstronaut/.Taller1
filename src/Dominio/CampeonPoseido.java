package Dominio;

import Logica.ListaSkinsPoseidas;

public class CampeonPoseido {
	private Campeon champ;
	private Cuenta cuentaDueño;
	private ListaSkinsPoseidas skinsPoseidas;

	public CampeonPoseido(Campeon champ, Cuenta cuentaDueño) {
		this.champ = champ;
		this.cuentaDueño = cuentaDueño;
		this.skinsPoseidas = new ListaSkinsPoseidas(999);
	}

	public Campeon getChamp() {
		return champ;
	}

	public void setChamp(Campeon champ) {
		this.champ = champ;
	}

	public Cuenta getCuentaDueño() {
		return cuentaDueño;
	}

	public void setCuentaDueño(Cuenta cuentaDueño) {
		this.cuentaDueño = cuentaDueño;
	}

	public ListaSkinsPoseidas getSkinsPoseidas() {
		return skinsPoseidas;
	}

	public void setSkinsPoseidas(ListaSkinsPoseidas skinsPoseidas) {
		this.skinsPoseidas = skinsPoseidas;
	}

}
