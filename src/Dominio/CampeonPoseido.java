package Dominio;

import Logica.ListaSkinsPoseidas;

public class CampeonPoseido {
	private Campeon champ;
	private Cuenta cuentaDue�o;
	private ListaSkinsPoseidas skinsPoseidas;

	public CampeonPoseido(Campeon champ, Cuenta cuentaDue�o) {
		this.champ = champ;
		this.cuentaDue�o = cuentaDue�o;
		this.skinsPoseidas = new ListaSkinsPoseidas(999);
	}

	public Campeon getChamp() {
		return champ;
	}

	public void setChamp(Campeon champ) {
		this.champ = champ;
	}

	public Cuenta getCuentaDue�o() {
		return cuentaDue�o;
	}

	public void setCuentaDue�o(Cuenta cuentaDue�o) {
		this.cuentaDue�o = cuentaDue�o;
	}

	public ListaSkinsPoseidas getSkinsPoseidas() {
		return skinsPoseidas;
	}

	public void setSkinsPoseidas(ListaSkinsPoseidas skinsPoseidas) {
		this.skinsPoseidas = skinsPoseidas;
	}

}
