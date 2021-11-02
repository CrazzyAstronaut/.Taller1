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
		this.InventarioChamps = new ListaCampeonesPoseidos(150);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public void agregarSaldo(int add) {
		this.saldo += add;
	}
	
	public boolean restarSaldo(int rest) {
		if((saldo-rest)<0) {
			return false;
		}
		saldo -= rest;
		return true;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public void subirNivel() {
		this.nivel++;
	}

	public boolean isStatusBloqueado() {
		return statusBloqueado;
	}

	public void bloquear() {
		this.statusBloqueado = true;
	}

	public ListaCampeonesPoseidos getInventarioChamps() {
		return InventarioChamps;
	}

	public void setInventarioChamps(ListaCampeonesPoseidos inventarioChamps) {
		InventarioChamps = inventarioChamps;
	}
	

}
