package Logica;

import Dominio.Campeon;
import Dominio.Cuenta;
import Dominio.Skin;

public interface GameSystem {
	public boolean agregarCampeon (String nombre, String rol, int recaudado, int cantSkins);
	public boolean agregarSkin (String nombreSkin, String calidad);
	public boolean asociarCampeonSkin (Campeon Champ, Skin skin);
	public boolean agregarCuenta (String nombreCuenta, String contraseña, String nick, int nivel, int RP, int cantChamps);
	public boolean asociarCampeonCuenta (Campeon champ, Cuenta cuenta);
	public boolean asociarSkinCuenta (String nombreSkin, Cuenta cuenta, String nombreCamp);
	public boolean setRecaudacion (String nombreChamp, int recaudacion);
	public boolean comprarSkin (String nombreSkin, String nombreCuenta);
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
