package Logica;

import Dominio.Campeon;
import Dominio.Cuenta;
import Dominio.Skin;

public class GameSystemImplements implements GameSystem{

	@Override
	public boolean agregarCampeon(String nombre, String rol, int recaudado, int cantSkins) {
		
		return false;
	}

	@Override
	public boolean agregarSkin(String nombreSkin, String calidad) {
		
		return false;
	}

	@Override
	public boolean asociarCampeonSkin(Campeon Champ, Skin skin) {
		
		return false;
	}

	@Override
	public boolean agregarCuenta(String nombreCuenta, String contraseña, String nick, int nivel, int RP,
			int cantChamps) {
		
		return false;
	}

	@Override
	public boolean asociarCampeonCuenta(Campeon champ, Cuenta cuenta) {
		
		return false;
	}

	@Override
	public boolean asociarSkinCuenta(String nombreSkin, Cuenta cuenta, String nombreCamp) {
		
		return false;
	}

	@Override
	public boolean setRecaudacion(String nombreChamp, int recaudacion) {
		
		return false;
	}

	@Override
	public boolean comprarSkin(String nombreSkin, String nombreCuenta) {
		
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
		
		
	}

	@Override
	public boolean recargarRP(String nombreCuenta, int cantRP) {
		
		return false;
	}

	@Override
	public void desplegarDatosCuenta(String nombreCuenta) {
		
		
	}

	@Override
	public boolean cambiarContraseña(String nombreCuenta, String newcontraseña) {
		
		return false;
	}

	@Override
	public void desplegarVentasRol() {
		
		
	}

	@Override
	public void desplegarVentasRegion() {
		
		
	}

	@Override
	public void desplegarVentasPersonajes() {
		
		
	}

	@Override
	public void desplegarCantPersonajesRol() {
		
		
	}

	@Override
	public boolean bloquearJugador(String nombreCuenta) {
		
		return false;
	}

	@Override
	public void desplegarCuentas() {
		
		
	}

}
