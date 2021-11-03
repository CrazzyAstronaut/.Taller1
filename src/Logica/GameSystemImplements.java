package Logica;

import Dominio.Campeon;
import Dominio.CampeonPoseido;
import Dominio.Cuenta;
import Dominio.Skin;

public class GameSystemImplements implements GameSystem{

	private ListaCuentas listaCuentas;
	private ListaCampeones listaCampeones;
	private ListaSkins listaSkins;
	
	public GameSystemImplements() {
		listaCuentas = new ListaCuentas(9999);
		listaCampeones = new ListaCampeones(9999);
	}
	
	@Override
	public boolean agregarCampeon(String nombre, String rol) {
		Campeon champ = new Campeon(nombre, rol);
		if(listaCampeones.getCampeon(nombre)!= null) {
			System.out.println("Ya existe");
			return false;
		}
		return listaCampeones.agregarCampeon(champ);
	}

	@Override
	public boolean agregarSkin(String nombreSkin, String nombreChamp, String calidad) {
		Skin skin = new Skin(calidad, listaCampeones.getCampeon(nombreChamp), calidad);
		if(listaCampeones.getCampeon(nombreChamp).getInventarioSkins().getSkin(nombreSkin)!= null) {
			System.out.println("Ya existe");
			return false;
		}
		return 	listaCampeones.getCampeon(nombreChamp).getInventarioSkins().agregarSkin(skin);

	}

	@Override
	public boolean asociarSkinCampeon(String nombreSkin, String nombreChamp) {
		
		return false;
	}	
	
	@Override
	public boolean agregarCuenta(String nombreCuenta, String contraseña, String nick, String region,  int RP, int nivel) {
		Cuenta cuenta = new Cuenta(nombreCuenta, contraseña, nick, region, RP, nivel);
		return listaCuentas.agregarCuenta(cuenta);
	}

	@Override
	public boolean asociarCampeonCuenta(String nombreChamp, String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		Campeon champ = listaCampeones.getCampeon(nombreChamp);
		return cuenta.getInventarioChamps().agregarCampeon(champ, cuenta);
	}

	@Override
	public boolean asociarSkinCuenta(String nombreSkin, String nombreCuenta, String nombreChamp) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		Skin skin = listaCampeones.getCampeon(nombreChamp).getInventarioSkins().getSkin(nombreSkin);
		CampeonPoseido champ = cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp);
		return champ.getSkinsPoseidas().agregarSkin(skin, champ);
	}

	@Override
	public void setRecaudacion(String nombreChamp, int recaudacion) {
		listaCampeones.getCampeon(nombreChamp).setRecaudado(recaudacion);
	}

	@Override
	public boolean comprarSkin(String nombreChamp, String nombreSkin, String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		if(cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp)==null) {
			System.out.println("No posee el campeón");
			return false;
		}
		
		return false;
	}

	@Override
	public boolean comprarPersonaje(String nombreChamp, String nombreCuenta) {
		
		return false;
	}

	@Override
	public void desplegarSkinsDisponibles(String nombreCuenta) {
		
		
	}

	@Override
	public void desplegarInventario(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		int cant = cuenta.getInventarioChamps().getCant();
		for(int i = 0; i < cant; i++) {
			
		}
		
	}

	@Override
	public boolean recargarRP(String nombreCuenta, int cantRP) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		if(cantRP < 0) {
			return false;
		}
		cuenta.agregarSaldo(cantRP);
		return true;
	}

	@Override
	public void desplegarDatosCuenta(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		System.out.println(" - Nombre de cuenta: " + nombreCuenta);
		System.out.println(" - Nickname: "+cuenta.getNick());
		System.out.println(" - Contraseña: ");
	}

	@Override
	public boolean cambiarContraseña(String nombreCuenta, String newcontraseña) {
		if(newcontraseña.equalsIgnoreCase("SALIR")) {
			System.out.println("La contraseña no puede ser SALIR ");
			return false;
		}
		listaCuentas.getCuenta(nombreCuenta).setContraseña(newcontraseña);
		return true;
	}

	@Override
	public void desplegarVentasRol() {
		System.out.println(" - El rol de SUP ha recaudado: "+listaCampeones.getRecaudadoRol("SUP")+" CLP");
		System.out.println(" - El rol de ADC ha recaudado: "+listaCampeones.getRecaudadoRol("ADC")+" CLP");
		System.out.println(" - El rol de TOP ha recaudado: "+listaCampeones.getRecaudadoRol("TOP")+" CLP");
		System.out.println(" - El rol de MID ha recaudado: "+listaCampeones.getRecaudadoRol("MID")+" CLP");
		System.out.println(" - El rol de JG ha recaudado: "+listaCampeones.getRecaudadoRol("JG")+" CLP");
	}

	@Override
	public void desplegarVentasRegion() {
		
		
	}

	@Override
	public void desplegarVentasPersonajes() {
		int cant = listaCampeones.getCant();
		for(int i = 0; i < cant; i++) {
			System.out.println(" - El personaje "+listaCampeones.getLista()[i].getNombre()+" ha recaudado: "
		+(listaCampeones.getLista()[i].getRecaudado()*6.15)+" CLP");
		}
	}

	@Override
	public void desplegarCantPersonajesRol() {
		
		
	}

	@Override
	public boolean bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		if (cuenta.isStatusBloqueado()) {
			System.out.println("Cuenta ya bloqueada");
			return false;
		}
		cuenta.bloquear();
		return true;
	}

	@Override
	public void desplegarCuentas() {
		listaCuentas.ordenar();
		listaCuentas.desplegarCuentas();
	}

}
