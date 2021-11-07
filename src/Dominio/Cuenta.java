package Dominio;

import Logica.ListaCampeonesPoseidos;

public class Cuenta {
	private String nombre;
	private String contraseña;
	private String nick;
	private String region;
	private int saldo;
	private int nivel;
	private boolean statusBloqueado;
	private ListaCampeonesPoseidos InventarioChamps;
	private int precioCuenta;
	
	public Cuenta(String nombre, String contraseña, String nick, String region, int saldo, int nivel) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.nick = nick;
		this.region = region;
		this.saldo = saldo;
		this.nivel = nivel;
		this.statusBloqueado = false;
		this.InventarioChamps = new ListaCampeonesPoseidos(150);
		this.precioCuenta = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getContraseñaCensurada() {
		String contraseñaCensored = "";
		String partes [] = contraseña.split("");
		String uno = partes[partes.length-1];
		String dos = partes[partes.length-2];
		String tre = partes[partes.length-3];
		for(int i = 0 ; i < contraseña.length()-3 ; i++) {
			contraseñaCensored = contraseñaCensored+"*";
		}
		contraseñaCensored = contraseñaCensored+tre+dos+uno;
		return contraseñaCensored;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
		nivel++;
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

	public int getPrecioCuenta() {
		return precioCuenta;
	}

	public void setPrecioCuenta(int precioCuenta) {
		this.precioCuenta = precioCuenta;
	}

	public void agregarPrecioCuenta(int cant) {
		this.precioCuenta += cant;		
	}

}
