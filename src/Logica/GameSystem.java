package Logica;

import Dominio.Campeon;
import Dominio.Cuenta;
import Dominio.Skin;

public interface GameSystem {
	public boolean agregarCampeon (String nombre, String rol);
	public boolean agregarSkin (String nombreSkin, String nombreChamp, String calidad);
	public boolean asociarSkinCampeon(String nombreSkin, String nombreChamp);
	public boolean agregarCuenta (String nombreCuenta, String contraseña, String nick, String region, int nivel, int RP);
	public boolean asociarCampeonCuenta(String nombreChamp, String nombreCuenta);
	public boolean asociarSkinCuenta(String nombreSkin, String nombreCuenta, String nombreCamp);
	public void setRecaudacion(String nombreChamp, int recaudacion);
	public boolean comprarSkin(String nombreChamp, String nombreSkin, String nombreCuenta);
	public boolean comprarPersonaje (String nombreChamp, String nombreCuenta);
	public void desplegarSkinsDisponibles (String nombreCuenta);
	public void desplegarInventario (String nombreCuenta);
	public boolean recargarRP (String nombreCuenta,int cantRP);
	public void desplegarDatosCuenta (String nombreCuenta);
	public boolean cambiarContraseña (String nombreCuenta, String newcontraseña);
	public void desplegarVentasRol ();
	public void desplegarVentasRegion ();
	public void desplegarVentasPersonajes ();
	public void desplegarCantPersonajesRol ();
	public boolean bloquearJugador (String nombreCuenta);
	public void desplegarCuentas ();
}
