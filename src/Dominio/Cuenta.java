package Dominio;

import Logica.ListaCampeonesPoseidos;

public class Cuenta {
	private String nombre;
	private String contrase�a;
	private String nick;
	private String region;
	private int saldo;
	private int nivel;
	private boolean statusBloqueado;
	private ListaCampeonesPoseidos InventarioChamps;
	public Cuenta(String nombre, String contrase�a, String nick, String region, int saldo, int nivel) {
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.nick = nick;
		this.region = region;
		this.saldo = saldo;
		this.nivel = nivel;
		this.statusBloqueado = false;
		//this.InventarioChampos = new ListaCampeonesPoseidos();
	}

}
