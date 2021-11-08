package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Vicente Rojas
 * @author Claudio Cortes
 */
public class App {

	private static Scanner s;

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GameSystem sistema = new GameSystemImplements();
		LeerArchivos(sistema);
		MenuPrincipal(sistema);
		EscribirArchivos(sistema);
	}

	/**
	 * This function allows the management between the other options of the App
	 * 
	 * @param sistema
	 */
	private static void MenuPrincipal(GameSystem sistema) {
		System.out.println("Bienvenido a Juego genérico");
		System.out.println("[1]	Iniciar sesión	[2]	Registrarse	[0]	Salir");
		System.out.println("Nota: Si quiere volver atras en cualquier punto escriba (0)");
		s = new Scanner(System.in);
		String MainOption = s.nextLine();
		while ((MainOption.equals("1")) == false && (MainOption.equals("2")) == false
				&& (MainOption.equals("0")) == false) {
			System.out.println("Opcion invalida");
			MainOption = s.nextLine();
		}

		while (MainOption.equals("0") == false) {
			if (MainOption.equals("1")) {
				iniciarSesion(sistema);
			}
			if (MainOption.equals("2")) {
				registrar(sistema);
			}
			System.out.println("[1]	Iniciar sesión	[2]	Registrarse	[0]	Salir");
			MainOption = s.nextLine();
			while ((MainOption.equals("1")) == false && (MainOption.equals("2")) == false
					&& (MainOption.equals("0")) == false) {
				System.out.println("Opcion invalida");
				MainOption = s.nextLine();
			}
		}
	}

	/**
	 * This function asks for the necessary data to create another account and enter
	 * it into the system
	 * 
	 * @param sistema
	 */
	private static void registrar(GameSystem sistema) {
		s = new Scanner(System.in);
		System.out.println("Ingrese su nombre de usuario: ");
		String nombreCuenta = s.nextLine();
		while (sistema.iniciarSesion(nombreCuenta, null) != 1 && nombreCuenta.equals("0") == false) {
			System.out.println("Este nombre de cuenta ya no puede ser ocupado");
			nombreCuenta = s.nextLine();
		}
		if (nombreCuenta.equals("0")) {
			return;
		}
		System.out.println("Ingrese su contraseña (Debe tener un minimo de caracteres de 4): ");
		String contraseña = s.nextLine();
		while (contraseña.equals("0") == false && contraseña.length() < 4) {
			System.out.println("Ingrese un valor valido: ");
			contraseña = s.nextLine();
		}
		if (contraseña.equals("0")) {
			return;
		}
		System.out.println("Ingrese su nickname: ");
		String nickname = s.nextLine();
		if (nickname.equals("0")) {
			return;
		}
		System.out.println("Ingrese su región (LAS,LAN,EUW,KR,NA,RU): ");
		String region = s.nextLine();
		while (region.equals("LAS") == false && region.equals("LAN") == false && region.equals("EUW") == false
				&& region.equals("KR") == false && region.equals("NA") == false && region.equals("RU") == false
				&& region.equals("0")) {
			System.out.println("Ingrese un valor valido: ");
			region = s.nextLine();
		}
		if (region.equals("0")) {
			return;
		}
		sistema.agregarCuenta(nombreCuenta, contraseña, nickname, region, 0, 0);
		System.out.println("Se ha registrado con exito...");
	}

	/**
	 * This function asks for account data already registered and allows access to
	 * the options
	 * 
	 * @param sistema
	 */
	private static void iniciarSesion(GameSystem sistema) {
		System.out.println("Ingrese su nombre de usuario: ");
		s = new Scanner(System.in);
		String nombreCuenta = s.nextLine();
		System.out.println("Ingrese su contraseña: ");
		String contraseña = s.nextLine();
		while ((sistema.iniciarSesion(nombreCuenta, contraseña) == 1
				|| sistema.iniciarSesion(nombreCuenta, contraseña) == 2)
				&& (nombreCuenta.equals("ADMIN") == false || contraseña.equals("ADMIN") == false)
				&& nombreCuenta.equals("0") == false) {
			System.out
					.println("Nombre de cuenta o contraseña incorrectos, si desea volver al menu anterior escriba (0)");
			System.out.println("Ingrese su nombre de usuario: ");
			nombreCuenta = s.nextLine();
			System.out.println("Ingrese su contraseña: ");
			contraseña = s.nextLine();
		}
		if (nombreCuenta.equals("0")) {
			return;
		}
		if (nombreCuenta.equals("ADMIN")) {
			adminOptions(sistema);
		}
		if (sistema.iniciarSesion(nombreCuenta, contraseña) == 3) {
			System.out.println("Su cuenta ha sido bloqueada");
		}
		if (sistema.iniciarSesion(nombreCuenta, contraseña) == 0) {
			cuentaOptions(sistema, nombreCuenta);
		}
	}

	/**
	 * This function shows the options available for a user account
	 * 
	 * @param sistema
	 * @param nombreCuenta
	 */
	private static void cuentaOptions(GameSystem sistema, String nombreCuenta) {
		System.out.println("Bienvenido de vuelta");
		System.out.println(
				"Seleccione una opcion [1] Comprar Skin		[2] Comprar Personaje	[3] Skins Disponibles	[4] Mostrar Inventario  ");
		System.out.println("                      [5] RecargarRP		[6] Mostrar datos		[0] Salir");
		s = new Scanner(System.in);
		String op = s.nextLine();
		while ((op.equals("1")) == false && (op.equals("2")) == false && (op.equals("3")) == false
				&& (op.equals("4")) == false && (op.equals("5")) == false && (op.equals("6")) == false
				&& (op.equals("0")) == false) {
			System.out.println("Opcion invalida");
			op = s.nextLine();
		}
		while (op.equals("0") == false) {
			if (op.equals("1")) {
				System.out.println(" - Menu de compra de skin - ");
				System.out.println("Ingrese el nombre del personaje que desea comprar la skin: ");
				String nombreChamp = s.nextLine();
				System.out.println("Ingrese el nombre de la skin que desea comprar: ");
				String nombreSkin = s.nextLine();
				while (sistema.comprarSkin(nombreChamp, nombreSkin, nombreCuenta) == false
						&& nombreChamp.equals("0") == false && nombreSkin.equals("0") == false) {
					System.out.println("Intentelo denuevo");
					System.out.println("Ingrese el nombre del personaje que desea comprar la skin: ");
					nombreChamp = s.nextLine();
					System.out.println("Ingrese el nombre de la skin que desea comprar:");
					nombreSkin = s.nextLine();
				}
				System.out.println("Volviendo al menu...");
			}
			if (op.equals("2")) {
				System.out.println(" - Menu de compra de personaje - ");
				System.out.println("Ingrese el nombre del personaje que desea comprar: ");
				String nombreChamp = s.nextLine();
				while (sistema.comprarPersonaje(nombreChamp, nombreCuenta) == false
						&& nombreChamp.equals("0") == false) {
					System.out.println("Intentelo denuevo");
					System.out.println("Ingrese el nombre del personaje que desea comprar: ");
					nombreChamp = s.nextLine();
				}
				System.out.println("Volviendo al menu...");
			}
			if (op.equals("3")) {
				System.out.println(" - Skins disponibles para comprar - ");
				sistema.desplegarSkinsDisponibles(nombreCuenta);
			}
			if (op.equals("4")) {
				System.out.println(" - Inventario - ");
				sistema.desplegarInventario(nombreCuenta);
			}
			if (op.equals("5")) {
				System.out.println(" - Recargar RP - ");
				System.out.println("Ingrese la cantidad de RP que desea agregar a su cuenta: ");
				String RPadd = s.nextLine();
				while (correctNumber(RPadd) == false) {
					System.out.println("Ingrese una cantidad valida: ");
					RPadd = s.nextLine();
				}
				int RP = Integer.parseInt(RPadd);
				if (sistema.recargarRP(nombreCuenta, RP)) {
					System.out.println("Compra exitosa");
				}
				System.out.println("Volviendo al menu...");
			}
			if (op.equals("6")) {
				System.out.println(" - Informacion de cuenta - ");
				sistema.desplegarDatosCuenta(nombreCuenta);
				System.out.println("¿Desea cambiar su contraseña? ([0] No	[1] Si)");
				String o = s.nextLine();
				while (o.equals("0") == false && o.equals("1") == false) {
					System.out.println("Ingrese una opcion valida: ");
					o = s.nextLine();
				}
				if (o.equals("1")) {
					System.out.println("Ingrese su contraseña: ");
					String oldPass = s.nextLine();
					while (sistema.iniciarSesion(nombreCuenta, oldPass) == 2 && oldPass.equals("0") == false) {
						System.out.println("Contraseña incorrecta :");
						oldPass = s.nextLine();
					}
					if (oldPass.equals("0") == false) {
						System.out.println("Ingrese la nueva contraseña (Debe tener un minimo de 4 caracteres) :");
						String newPass1 = s.nextLine();
						while (newPass1.equals("0") == false && newPass1.length() < 4) {
							System.out.println("Intentelo denuevo: ");
							newPass1 = s.nextLine();
						}
						if (newPass1.equals("0") == false) {
							System.out.println("Ingrese nuevamente la contraseña: ");
							String newPass = s.nextLine();
							while (newPass.equals("0") == false && newPass.equals(newPass1) == false) {
								System.out.println("Porfavor intentelo denuvo");
								newPass = s.nextLine();
							}
							if (newPass.equals("0") == false) {
								sistema.cambiarContraseña(nombreCuenta, newPass);
								System.out.println("La contraseña se ha cambiado");
							}
						}
					}
				}
				System.out.println("Volviendo al menu...");
			}
			System.out.println(
					"Seleccione una opcion [1] Comprar Skin		[2] Comprar Personaje	[3] Skins Disponibles	[4] Mostrar Inventario  ");
			System.out.println("                      [5] RecargarRP		[6] Mostrar datos	[0] Salir");
			op = s.nextLine();
			while ((op.equals("1")) == false && (op.equals("2")) == false && (op.equals("3")) == false
					&& (op.equals("4")) == false && (op.equals("5")) == false && (op.equals("6")) == false
					&& (op.equals("0")) == false) {
				System.out.println("Opcion invalida");
				op = s.nextLine();
			}
		}
	}

	/**
	 * This function shows the options available for the administrator menu
	 * 
	 * @param sistema
	 */
	private static void adminOptions(GameSystem sistema) {
		System.out.println("Bienvenido al menu de administrador");
		System.out.println(
				"Seleccione una opcion [1] Ventas por rol		[2] Ventas por región		[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
		System.out.println(
				"                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas		[0] Salir");
		s = new Scanner(System.in);
		String op = s.nextLine();
		while ((op.equals("1")) == false && (op.equals("2")) == false && (op.equals("3")) == false
				&& (op.equals("4")) == false && (op.equals("5")) == false && (op.equals("6")) == false
				&& (op.equals("7")) == false && (op.equals("8")) == false && (op.equals("0")) == false) {
			System.out.println("Opcion invalida");
			op = s.nextLine();
		}
		while (op.equals("0") == false) {
			if (op.equals("1")) {
				sistema.desplegarVentasRol();
			}
			if (op.equals("2")) {
				sistema.desplegarVentasRegion();
			}
			if (op.equals("3")) {
				sistema.desplegarVentasPersonajes();
			}
			if (op.equals("4")) {
				sistema.desplegarCantPersonajesRol();
			}
			if (op.equals("5")) {
				System.out.println("Ingrese el nombre del nuevo personaje: ");
				String nombreChamp = s.nextLine();
				while (nombreChamp.equals("0")) {
					System.out.println("Ingrese otro nombre: ");
					nombreChamp = s.nextLine();
				}
				System.out.println("Ingrese el rol que tendra (SUP,ADC,MID,JG,TOP): ");
				String rol = s.nextLine();
				while (rol.equals("SUP") == false && rol.equals("ADC") == false && rol.equals("MID") == false
						&& rol.equals("JG") == false && rol.equals("TOP") == false) {
					System.out.println("Ingrese uno de los roles que se muestran entre parentesis: ");
					rol = s.nextLine();
				}
				while (sistema.agregarCampeon(nombreChamp, rol) == false) {
					System.out.println("Ingrese el nombre del nuevo personaje: ");
					nombreChamp = s.nextLine();
					while (nombreChamp.equals("0")) {
						System.out.println("Ingrese otro nombre: ");
						nombreChamp = s.nextLine();
					}
					System.out.println("Ingrese el rol que tendra (SUP,ADC,MID,JG,TOP)");
					rol = s.nextLine();
					while (rol.equals("SUP") == false && rol.equals("ADC") == false && rol.equals("MID") == false
							&& rol.equals("JG") == false && rol.equals("TOP") == false) {
						System.out.println("Ingrese uno de los roles que se muestran entre parentesis: ");
						rol = s.nextLine();
					}
				}
				System.out.println("Ingrese el nombre de una skin: ");
				String nombreSkin = s.nextLine();
				while (nombreSkin.equals("0")) {
					System.out.println("Ingrese otro nombre: ");
					nombreSkin = s.nextLine();
				}
				System.out.println("Ingrese la calidad de la skin (N,E,L,D,M)");
				String calidad = s.nextLine();
				while (calidad.equals("N") == false && calidad.equals("E") == false && calidad.equals("L") == false
						&& calidad.equals("D") == false && calidad.equals("M") == false) {
					System.out.println("Ingrese una de las opciones que se muestran entre parentesis: ");
					calidad = s.nextLine();
				}
				while (sistema.agregarSkin(nombreSkin, nombreChamp, calidad) == false) {
					System.out.println("Ingrese el nombre de una skin: ");
					nombreSkin = s.nextLine();
					while (nombreSkin.equals("0")) {
						System.out.println("Ingrese otro nombre: ");
						nombreSkin = s.nextLine();
					}
					System.out.println("Ingrese la calidad de la skin (N,E,L,D,M): ");
					calidad = s.nextLine();
					while (calidad.equals("N") == false && calidad.equals("E") == false && calidad.equals("L") == false
							&& calidad.equals("D") == false && calidad.equals("M") == false) {
						System.out.println("Ingrese una de las opciones que se muestran entre parentesi: ");
						calidad = s.nextLine();
					}
				}
			}

			if (op.equals("6")) {
				System.out.println("Ingrese el nombre del personaje que desea agregarle: ");
				String nombreChamp = s.nextLine();
				System.out.println("Ingrese el nombre de una skin:");
				String nombreSkin = s.nextLine();
				while (nombreSkin.equals("0")) {
					System.out.println("Ingrese otro nombre:");
					nombreSkin = s.nextLine();
				}
				System.out.println("Ingrese la calidad de la skin (N,E,L,D,M):");
				String calidad = s.nextLine();
				while (calidad.equals("N") == false && calidad.equals("E") == false && calidad.equals("L") == false
						&& calidad.equals("D") == false && calidad.equals("M") == false) {
					System.out.println("Ingrese una de las opciones que se muestran entre parentesis: ");
					calidad = s.nextLine();
				}
				while (sistema.agregarSkin(nombreSkin, nombreChamp, calidad) == false) {
					System.out.println("Ingrese el nombre de una skin: ");
					nombreSkin = s.nextLine();
					while (nombreSkin.equals("0")) {
						System.out.println("Ingrese otro nombre: ");
						nombreSkin = s.nextLine();
					}
					System.out.println("Ingrese la calidad de la skin (N,E,L,D,M): ");
					calidad = s.nextLine();
					while (calidad.equals("N") == false && calidad.equals("E") == false && calidad.equals("L") == false
							&& calidad.equals("D") == false && calidad.equals("M") == false) {
						System.out.println("Ingrese una de las opciones que se muestran entre parentesis: ");
						calidad = s.nextLine();
					}
				}
			}
			if (op.equals("7")) {
				System.out.println("Ingrese el nombre de cuenta: ");
				String nombre = s.nextLine();
				while (sistema.bloquearJugador(nombre) == false && nombre.equals("0") == false) {
					System.out.println("Ingrese el nombre de cuenta: ");
					nombre = s.nextLine();
				}
			}
			if (op.equals("8")) {
				sistema.desplegarCuentas();
			}
			System.out.println(
					"Seleccione una opcion [1] Ventas por rol		[2] Ventas por región	[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
			System.out.println(
					"                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas	[0] Salir");
			op = s.nextLine();
			while ((op.equals("1")) == false && (op.equals("2")) == false && (op.equals("3")) == false
					&& (op.equals("4")) == false && (op.equals("5")) == false && (op.equals("6")) == false
					&& (op.equals("7")) == false && (op.equals("8")) == false && (op.equals("0")) == false) {
				System.out.println("Opcion invalida");
				op = s.nextLine();
			}
		}

	}

	/**
	 * This function reads the text files and enters the data into the system.
	 * 
	 * @param sistema
	 * @throws FileNotFoundException
	 */
	private static void LeerArchivos(GameSystem sistema) throws FileNotFoundException {
		File file1 = new File("Personajes.txt");
		Scanner arch1 = new Scanner(file1);
		while (arch1.hasNextLine()) {
			String linea = arch1.nextLine();
			String[] partes = linea.split(",");
			String nombreChamp = partes[0];
			String rol = partes[1];
			sistema.agregarCampeon(nombreChamp, rol);
			int cantSkins = Integer.parseInt(partes[2]);
			int j = 0;
			for (int i = 0; i < cantSkins; i++) {
				String nombreSkin = partes[3 + j];
				String calidad = partes[4 + j];
				sistema.agregarSkin(nombreSkin, nombreChamp, calidad);
				j += 2;
			}
		}
		arch1.close();

		File file2 = new File("Cuentas.txt");
		Scanner arch2 = new Scanner(file2);
		while (arch2.hasNextLine()) {
			String linea = arch2.nextLine();
			String[] partes = linea.split(",");
			String nombreCuenta = partes[0];
			String contraseña = partes[1];
			String nick = partes[2];
			int nivel = Integer.parseInt(partes[3]);
			int saldo = Integer.parseInt(partes[4]);
			String region = partes[partes.length - 1];
			sistema.agregarCuenta(nombreCuenta, contraseña, nick, region, nivel, saldo);
			int cantChamps = Integer.parseInt(partes[5]);
			int y = 0;
			for (int x = 0; x < cantChamps; x++) {
				String nombreChamp = partes[6 + y];
				sistema.asociarCampeonCuenta(nombreChamp, nombreCuenta);
				int cantSkin = Integer.parseInt(partes[7 + y]);
				for (int z = 0; z < cantSkin; z++) {
					String nombreSkin = partes[8 + z + y];
					sistema.asociarSkinCuenta(nombreSkin, nombreCuenta, nombreChamp);
				}
				y += (2 + cantSkin);
			}
		}
		arch2.close();

		File file3 = new File("Estadísticas.txt");
		Scanner arch3 = new Scanner(file3);
		while (arch3.hasNextLine()) {
			String linea = arch3.nextLine();
			String[] partes = linea.split(",");
			String nombreChamp = partes[0];
			int recaudado = Integer.parseInt(partes[1]);
			sistema.setRecaudacion(nombreChamp, recaudado);
		}
		arch3.close();
	}

	/**
	 * This function saves the system data in its corresponding text files
	 * 
	 * @param sistema
	 * @throws IOException
	 */
	private static void EscribirArchivos(GameSystem sistema) throws IOException {
		FileWriter file1 = new FileWriter("Personajes.txt");
		sistema.guardarPersonajes(file1);
		FileWriter file2 = new FileWriter("Estadísticas.txt");
		sistema.guardarEstadisticas(file2);
		FileWriter file3 = new FileWriter("Cuentas.txt");
		sistema.guardarCuentas(file3);
	}

	/**
	 * This function allows you to know if a String is well written to be an integer
	 * 
	 * @param numero
	 * @return
	 */
	public static boolean correctNumber(String numero) {
		String partes[] = numero.split("");
		if (8 <= partes.length && partes.length <= 9) {
			for (int i = 0; i < partes.length - 1; i++) {
				if (partes[i].equals("0") == false && partes[i].equals("1") == false && partes[i].equals("2") == false
						&& partes[i].equals("3") == false && partes[i].equals("4") == false
						&& partes[i].equals("5") == false && partes[i].equals("6") == false
						&& partes[i].equals("7") == false && partes[i].equals("8") == false
						&& partes[i].equals("9") == false) {
					return false;
				}
			}
		}
		return true;
	}
}
