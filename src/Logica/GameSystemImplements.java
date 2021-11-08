package Logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import Dominio.Campeon;
import Dominio.CampeonPoseido;
import Dominio.Cuenta;
import Dominio.Skin;
/**
 * @author Vicente Rojas
 * @author Claudio Cortes 
 */
public class GameSystemImplements implements GameSystem {

	private ListaCuentas listaCuentas;
	private ListaCampeones listaCampeones;
	private ListaSkins listaSkins;

	public GameSystemImplements() {
		listaCuentas = new ListaCuentas(9999);
		listaCampeones = new ListaCampeones(9999);
		listaSkins = new ListaSkins(99999);
	}

	@Override
	public boolean agregarCampeon(String nombre, String rol) {
		Campeon champ = new Campeon(nombre, rol);
		if (listaCampeones.getCampeon(nombre) != null) {
			System.out.println("Ya existe");
			return false;
		}
		return listaCampeones.agregarCampeon(champ);
	}

	@Override
	public boolean agregarSkin(String nombreSkin, String nombreChamp, String calidad) {
		Skin skin = new Skin(nombreSkin, listaCampeones.getCampeon(nombreChamp), calidad);
		if (listaCampeones.getCampeon(nombreChamp).getInventarioSkins().getSkin(nombreSkin) != null) {
			System.out.println("Ya existe");
			return false;
		}
		listaSkins.agregarSkin(skin);
		return listaCampeones.getCampeon(nombreChamp).getInventarioSkins().agregarSkin(skin);

	}

	@Override
	public boolean agregarCuenta(String nombreCuenta, String contraseña, String nick, String region, int RP,
			int nivel) {
		Cuenta cuenta = new Cuenta(nombreCuenta, contraseña, nick, region, nivel, RP);
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
		Campeon champ = listaCampeones.getCampeon(nombreChamp);
		if (champ == null) {
			System.out.println("El campeón no existe");
			return false;
		}
		if (champ.getInventarioSkins().getSkin(nombreSkin) == null) {
			System.out.println("La skin no existe");
			return false;
		}
		if (cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp) == null) {
			System.out.println("No poseé el campeón");
			return false;
		}
		if (cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp).getSkinsPoseidas()
				.getSkinPoseida(nombreSkin) != null) {
			System.out.println("Ya poseé la skin");
			return false;
		}
		if (cuenta.getSaldo() < champ.getInventarioSkins().getSkin(nombreSkin).getPrecio()) {
			System.out.println("El saldo no es suficiente");
			return false;
		}
		if (cuenta.getInventarioChamps().getCant() >= cuenta.getInventarioChamps().getMax()) {
			System.out.println("No puede poseér mas campeones");
			return false;
		}
		Skin skin = listaCampeones.getCampeon(nombreChamp).getInventarioSkins().getSkin(nombreSkin);
		cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp).getSkinsPoseidas().agregarSkin(skin,
				cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp));
		champ.addRecaudado(skin.getPrecio());
		cuenta.restarSaldo(skin.getPrecio());
		System.out.println("Compra exitosa");
		return true;
	}

	@Override
	public boolean comprarPersonaje(String nombreChamp, String nombreCuenta) {
		if (nombreChamp.equals("0")) {
			return true;
		}
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		Campeon champ = listaCampeones.getCampeon(nombreChamp);
		if (cuenta.getInventarioChamps().getCampeonPoseido(nombreChamp) != null) {
			System.out.println("Ya poseé el campeón");
			return false;
		}
		if (cuenta.getSaldo() < 975) {
			System.out.println("El saldo no es suficiente");
			return false;
		}
		cuenta.getInventarioChamps().agregarCampeon(champ, cuenta);
		champ.addRecaudado(975);
		cuenta.restarSaldo(975);
		cuenta.subirNivel();
		System.out.println("Compra exitosa");
		return true;
	}

	@Override
	public void desplegarSkinsDisponibles(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		int cant = listaSkins.getCant();
		for (int i = 0; i < cant; i++) {
			if (cuenta.getInventarioChamps().tieneSkin(listaSkins.getSkin(i)) == false) {
				System.out.println(listaSkins.getSkin(i).getChamp().getNombre() + " "
						+ listaSkins.getSkin(i).getNombre() + "	(" + listaSkins.getSkin(i).getCalidad() + ")	"
						+ listaSkins.getSkin(i).getPrecio() + " RP");
			}
		}
		if (cuenta.getInventarioChamps().getCant() == 0) {
			for (int i = 0; i < cant; i++) {
				System.out.println(listaSkins.getSkin(i).getChamp().getNombre() + " "
						+ listaSkins.getSkin(i).getNombre() + "	(" + listaSkins.getSkin(i).getCalidad() + ")	"
						+ listaSkins.getSkin(i).getPrecio() + " RP");
			}
		}
	}

	@Override
	public void desplegarInventario(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		cuenta.getInventarioChamps().desplegarCampeonSkins();
	}

	@Override
	public boolean recargarRP(String nombreCuenta, int cantRP) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		if (cantRP < 0) {
			return false;
		}
		cuenta.agregarSaldo(cantRP);
		return true;
	}

	@Override
	public void desplegarDatosCuenta(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		System.out.println(" - Nombre de cuenta: " + nombreCuenta);
		System.out.println(" - Nickname: " + cuenta.getNick());
		System.out.println(" - Saldo: " + cuenta.getSaldo());
		System.out.println(" - Contraseña: " + cuenta.getContraseñaCensurada());
	}

	@Override
	public boolean cambiarContraseña(String nombreCuenta, String newcontraseña) {
		if (newcontraseña.equals("0")) {
			System.out.println("La contraseña no puede ser (0) ");
			return false;
		}
		listaCuentas.getCuenta(nombreCuenta).setContraseña(newcontraseña);
		return true;
	}

	@Override
	public void desplegarVentasRol() {
		System.out.println(" - El rol de SUP ha recaudado: " + listaCampeones.getRecaudadoRol("SUP") + " CLP");
		System.out.println(" - El rol de ADC ha recaudado: " + listaCampeones.getRecaudadoRol("ADC") + " CLP");
		System.out.println(" - El rol de TOP ha recaudado: " + listaCampeones.getRecaudadoRol("TOP") + " CLP");
		System.out.println(" - El rol de MID ha recaudado: " + listaCampeones.getRecaudadoRol("MID") + " CLP");
		System.out.println(" - El rol de JG ha recaudado: " + listaCampeones.getRecaudadoRol("JG") + " CLP");
	}

	@Override
	public void desplegarVentasRegion() {
		System.out.println(" - La región de LAS ha recaudado: " + listaCuentas.getRecaudadoRegion("LAS") + " CLP");
		System.out.println(" - La región de LAN ha recaudado: " + listaCuentas.getRecaudadoRegion("LAN") + " CLP");
		System.out.println(" - La región de EUW ha recaudado: " + listaCuentas.getRecaudadoRegion("EUW") + " CLP");
		System.out.println(" - La región de KR ha recaudado: " + listaCuentas.getRecaudadoRegion("KR") + " CLP");
		System.out.println(" - La región de NA ha recaudado: " + listaCuentas.getRecaudadoRegion("NA") + " CLP");
		System.out.println(" - La región de RU ha recaudado: " + listaCuentas.getRecaudadoRegion("RU") + " CLP");
	}

	@Override
	public void desplegarVentasPersonajes() {
		int cant = listaCampeones.getCant();
		for (int i = 0; i < cant; i++) {
			System.out.println(" - El personaje " + listaCampeones.getLista()[i].getNombre() + " ha recaudado: "
					+ (listaCampeones.getLista()[i].getRecaudado() * 6.15) + " CLP");
		}
	}

	@Override
	public void desplegarCantPersonajesRol() {
		System.out.println(" - Personajes con el rol de SUP: " + listaCampeones.cantCampeonesRol("SUP"));
		System.out.println(" - Personajes con el rol de ADC: " + listaCampeones.cantCampeonesRol("ADC"));
		System.out.println(" - Personajes con el rol de TOP: " + listaCampeones.cantCampeonesRol("TOP"));
		System.out.println(" - Personajes con el rol de MID: " + listaCampeones.cantCampeonesRol("MID"));
		System.out.println(" - Personajes con el rol de JG: " + listaCampeones.cantCampeonesRol("JG"));
	}

	@Override
	public boolean bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = listaCuentas.getCuenta(nombreCuenta);
		if (cuenta == null) {
			System.out.println("No se encontró la cuenta");
			return false;
		}
		if (cuenta.isStatusBloqueado()) {
			System.out.println("Cuenta ya bloqueada");
			return false;
		}
		System.out.println("Cuenta bloqueada exitosamente");
		cuenta.bloquear();
		return true;
	}

	@Override
	public void desplegarCuentas() {
		listaCuentas.ordenar();
		listaCuentas.desplegarCuentas();
	}

	@Override
	public int iniciarSesion(String nombreCuenta, String contraseña) {
		if (listaCuentas.getCuenta(nombreCuenta) == null) {
			return 1;
		}
		if (listaCuentas.getCuenta(nombreCuenta).getContraseña().equals(contraseña) == false) {
			return 2;
		}
		if (listaCuentas.getCuenta(nombreCuenta).isStatusBloqueado() == true) {
			return 3;
		}
		return 0;
	}

	@Override
	public FileWriter guardarPersonajes(FileWriter file1) {
		PrintWriter escritura = new PrintWriter(file1);
		for (int i = 0; i < listaCampeones.getCant(); i++) {
			escritura.print(listaCampeones.getCampeon(i).getNombre() + "," + listaCampeones.getCampeon(i).getRol() + ","
					+ listaCampeones.getCampeon(i).getInventarioSkins().getCant());
			for (int j = 0; j < listaCampeones.getCampeon(i).getInventarioSkins().getCant(); j++) {
				escritura.print("," + listaCampeones.getCampeon(i).getInventarioSkins().getSkin(j).getNombre() + ","
						+ listaCampeones.getCampeon(i).getInventarioSkins().getSkin(j).getCalidad());
			}
			escritura.println();
		}
		escritura.close();
		return file1;
	}

	@Override
	public FileWriter guardarEstadisticas(FileWriter file2) {
		PrintWriter escritura = new PrintWriter(file2);
		for (int i = 0; i < listaCampeones.getCant(); i++) {
			escritura.print(
					listaCampeones.getCampeon(i).getNombre() + "," + listaCampeones.getCampeon(i).getRecaudado());
			escritura.println();
		}
		escritura.close();
		return file2;
	}

	@Override
	public FileWriter guardarCuentas(FileWriter file3) {
		PrintWriter escritura = new PrintWriter(file3);
		for (int i = 0; i < listaCuentas.getCant(); i++) {
			Cuenta cuenta = listaCuentas.getCuenta(i);
			escritura.print(cuenta.getNombre() + "," + cuenta.getContraseña() + "," + cuenta.getNick() + ","
					+ cuenta.getNivel() + "," + cuenta.getSaldo() + "," + cuenta.getInventarioChamps().getCant());
			for (int j = 0; j < cuenta.getInventarioChamps().getCant(); j++) {
				CampeonPoseido champ = cuenta.getInventarioChamps().getCampeonPoseido(j);
				escritura.print("," + champ.getChamp().getNombre() + "," + champ.getSkinsPoseidas().getCant());
				for (int n = 0; n < champ.getSkinsPoseidas().getCant(); n++) {
					escritura.print("," + champ.getSkinsPoseidas().getSkinPoseida(n).getSkin().getNombre());
				}
			}
			escritura.print("," + cuenta.getRegion());
			escritura.println();
		}
		escritura.close();
		return file3;
	}

}
