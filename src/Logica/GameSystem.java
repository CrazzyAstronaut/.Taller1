package Logica;

import java.io.FileWriter;

public interface GameSystem {
	/**
	 * This function enters a character with his data that is delivered by
	 * parameters
	 * 
	 * @param nombre
	 * @param rol
	 * @return
	 */
	public boolean agregarCampeon(String nombre, String rol);

	/**
	 * This function enters a skin with his data and character that is delivered by
	 * parameters
	 * 
	 * @param nombreSkin
	 * @param nombreChamp
	 * @param calidad
	 * @return
	 */
	public boolean agregarSkin(String nombreSkin, String nombreChamp, String calidad);

	/**
	 * This function enters a user account with its data that are delivered by
	 * parameters
	 * 
	 * @param nombreCuenta
	 * @param contraseña
	 * @param nick
	 * @param region
	 * @param nivel
	 * @param RP
	 * @return
	 */
	public boolean agregarCuenta(String nombreCuenta, String contraseña, String nick, String region, int nivel, int RP);

	/**
	 * This function adds the characters to the account inventory
	 * 
	 * @param nombreChamp
	 * @param nombreCuenta
	 * @return
	 */
	public boolean asociarCampeonCuenta(String nombreChamp, String nombreCuenta);

	/**
	 * This function adds the skin to the inventory of the possessed character of
	 * the account
	 * 
	 * @param nombreSkin
	 * @param nombreCuenta
	 * @param nombreCamp
	 * @return
	 */
	public boolean asociarSkinCuenta(String nombreSkin, String nombreCuenta, String nombreCamp);

	/**
	 * This function sets the rp collected by the character
	 * 
	 * @param nombreChamp
	 * @param recaudacion
	 */
	public void setRecaudacion(String nombreChamp, int recaudacion);

	/**
	 * This function adds a skin to the inventory of the character owned by the
	 * account, subtracting the balance and adding the proceeds to the champion
	 * 
	 * @param nombreChamp
	 * @param nombreSkin
	 * @param nombreCuenta
	 * @return
	 */
	public boolean comprarSkin(String nombreChamp, String nombreSkin, String nombreCuenta);

	/**
	 * This function adds a character to the inventory of the account, subtracting
	 * the balance, adding the amount collected by the character and leveling up the
	 * account
	 * 
	 * @param nombreChamp
	 * @param nombreCuenta
	 * @return
	 */
	public boolean comprarPersonaje(String nombreChamp, String nombreCuenta);

	/**
	 * This function displays the skins that the user does not have yet
	 * 
	 * @param nombreCuenta
	 */
	public void desplegarSkinsDisponibles(String nombreCuenta);

	/**
	 * This function displays the champions and skins that the user owns
	 * 
	 * @param nombreCuenta
	 */
	public void desplegarInventario(String nombreCuenta);

	/**
	 * This function adds the balance entered by parameter to the user's account
	 * 
	 * @param nombreCuenta
	 * @param cantRP
	 * @return
	 */
	public boolean recargarRP(String nombreCuenta, int cantRP);

	/**
	 * This function displays the account name, nickname, and the censored password
	 * of the user
	 * 
	 * @param nombreCuenta
	 */
	public void desplegarDatosCuenta(String nombreCuenta);

	/**
	 * This function changes the user's password to the one entered by parameter
	 * 
	 * @param nombreCuenta
	 * @param newcontraseña
	 * @return
	 */
	public boolean cambiarContraseña(String nombreCuenta, String newcontraseña);

	/**
	 * This function displays the total sales by character role
	 */
	public void desplegarVentasRol();

	/**
	 * This function displays the total sales by region
	 */
	public void desplegarVentasRegion();

	/**
	 * This function displays the total sales per character
	 */
	public void desplegarVentasPersonajes();

	/**
	 * This function displays the total number of characters per role
	 */
	public void desplegarCantPersonajesRol();

	/**
	 * This function blocks the account of the user entered by parameter
	 * 
	 * @param nombreCuenta
	 * @return
	 */
	public boolean bloquearJugador(String nombreCuenta);

	/**
	 * This function displays all the accounts in an orderly manner
	 */
	public void desplegarCuentas();

	/**
	 * This function verifies if the data is in the system or if it is correctly
	 * entered
	 * 
	 * @param nombreCuenta
	 * @param contraseña
	 * @return
	 */
	public int iniciarSesion(String nombreCuenta, String contraseña);

	/**
	 * This function saves the characters in a writing file
	 * 
	 * @param file1
	 * @return
	 */
	public FileWriter guardarPersonajes(FileWriter file1);

	/**
	 * This function saves the collected by the characters in a writing file
	 * 
	 * @param file2
	 * @return
	 */
	public FileWriter guardarEstadisticas(FileWriter file2);

	/**
	 * This function saves the accounts in a write file
	 * 
	 * @param file3
	 * @return
	 */
	public FileWriter guardarCuentas(FileWriter file3);

}
