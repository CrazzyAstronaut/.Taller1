package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		GameSystem sistema = new GameSystemImplements();
		LeerArchivos(sistema);
		MenuPrincipal(sistema);
		EscribirArchivos(sistema);
	}

	private static void MenuPrincipal(GameSystem sistema) {
		System.out.println("Bienvenido a Juego genérico");
		System.out.println("[1]	Iniciar sesión	[2]	Registrarse	[3]	Salir");
		Scanner S3 = new Scanner(System.in);
		String MainOption = S3.nextLine();
		while ((MainOption.equals("1"))==false&&(MainOption.equals("2"))==false&&(MainOption.equals("3"))==false) {
			System.out.println("Opcion invalida");
			MainOption = S3.nextLine();
		}

		while(MainOption.equals("3") == false) {
			if(MainOption.equals("1")) {
				iniciarSesion(sistema);
			}
			if(MainOption.equals("2")) {
				registrar(sistema);
			}
			System.out.println("[1]	Iniciar sesión	[2]	Registrarse	[3]	Salir");
			MainOption = S3.nextLine();
			while ((MainOption.equals("1"))==false&&(MainOption.equals("2"))==false&&(MainOption.equals("3"))==false) {
				System.out.println("Opcion invalida");
				MainOption = S3.nextLine();
			}
		}
	}

	private static void registrar(GameSystem sistema) {
		
	}

	private static void iniciarSesion(GameSystem sistema) {
		System.out.println("Ingrese su nombre de usuario");
		Scanner S0 = new Scanner(System.in);
		String nombreCuenta = S0.nextLine();
		System.out.println("Ingrese su contraseña");
		String contraseña = S0.nextLine();
		while((sistema.iniciarSesion(nombreCuenta,contraseña)==1)&&(nombreCuenta.equals("ADMIN")==false||
				contraseña.equals("ADMIN")==false)&&(nombreCuenta.equals("3")==false)) {
			System.out.println("Nombre de cuenta o contraseña incorrectos, si desea volver al menu anterior escriba (3)");
			System.out.println("Ingrese su nombre de usuario");
			nombreCuenta = S0.nextLine();
			System.out.println("Ingrese su nombre de usuario");
			contraseña = S0.nextLine();
		}
		if(nombreCuenta.equals("3")) {
			S0.close();return;}
		if(nombreCuenta.equals("ADMIN")) {
			adminOptions(sistema);
		}
		if(sistema.iniciarSesion(nombreCuenta, contraseña)==2) {
			System.out.println("Su cuenta ha sido bloqueada");
		}
		if(sistema.iniciarSesion(nombreCuenta, contraseña)==0) {
			cuentaOptions(sistema,nombreCuenta);
		}
	}

	private static void cuentaOptions(GameSystem sistema,String nombreCuenta) {
		System.out.println("Bienvenido de vuelta");
		System.out.println("Seleccione una opcion [1] Comprar Skin		[2] Comprar Personaje	[3] Skins Disponibles	[4] Mostrar Inventario  ");
		System.out.println("                      [5] RecargarRP	[6] Mostrar datos	[0] Salir");
		Scanner S = new Scanner(System.in);
		String op = S.nextLine();
		while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
				&&(op.equals("0"))==false) {
			System.out.println("Opcion invalida");
			op = S.nextLine();
		}
		while(op.equals("0")==false) {
			if(op.equals("1")) {
				System.out.println("Ingrese el nombre del personaje que decea comprar la skin");
				String nombreChamp = S.nextLine();
				System.out.println("Ingrese el nombre de la skin que desea comprar");
				String nombreSkin = S.nextLine();
				while(sistema.comprarSkin(nombreChamp, nombreSkin, nombreCuenta)==false&&nombreChamp.equals("0")==false&&nombreSkin.equals("0")==false) {
					System.out.println("Intentelo denuevo");
					System.out.println("Ingrese el nombre del personaje que decea comprar la skin");
					nombreChamp = S.nextLine();
					System.out.println("Ingrese el nombre de la skin que desea comprar");
					nombreSkin = S.nextLine();
				}
			}
			if(op.equals("2")) {
				System.out.println("Ingrese el nombre del personaje que decea comprar");
				String nombreChamp = S.nextLine();
				while(sistema.comprarPersonaje(nombreChamp, nombreCuenta)==false&&nombreChamp.equals("0")==false) {
					System.out.println("Intentelo denuevo");
					System.out.println("Ingrese el nombre del personaje que decea comprar");
					nombreChamp = S.nextLine();
				}
			}
			if(op.equals("3")) {sistema.desplegarSkinsDisponibles(nombreCuenta);}
			if(op.equals("4")) {sistema.desplegarInventario(nombreCuenta);}
			if(op.equals("5")) {
				sistema.recargarRP(nombreCuenta, 0);
				}
			if(op.equals("6")) {
				sistema.desplegarDatosCuenta(nombreCuenta);
				
			}
			System.out.println("Seleccione una opcion [1] Comprar Skin		[2] Comprar Personaje	[3] Skins Disponibles	[4] Mostrar Inventario  ");
			System.out.println("                      [5] RecargarRP	[6] Mostrar datos	[0] Salir");
			op = S.nextLine();
			while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
					&&(op.equals("0"))==false) {
				System.out.println("Opcion invalida");
				op = S.nextLine();
			}
		}
		S.close();
	}

//agregar bloqueado de cuenta

	private static void adminOptions(GameSystem sistema) {
		System.out.println("Bienvenido al menu de administrador");
		System.out.println("Seleccione una opcion [1] Ventas por rol		[2] Ventas por región	[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
		System.out.println("                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas	[0] Salir");
		Scanner S = new Scanner(System.in);
		String op = S.nextLine();
		while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
				&&(op.equals("7"))==false&&(op.equals("8"))==false&&(op.equals("0"))==false) {
			System.out.println("Opcion invalida");
			op = S.nextLine();
		}
		while(op.equals("0")==false) {
		if(op.equals("1")) {sistema.desplegarVentasRol();}
		if(op.equals("2")) {sistema.desplegarVentasRegion();}
		if(op.equals("3")) {sistema.desplegarVentasPersonajes();}
		if(op.equals("4")) {sistema.desplegarCantPersonajesRol();}
		if(op.equals("5")) {
			System.out.println("Ingrese el nombre del nuevo personaje");
			String nombreChamp = S.nextLine();
			while(nombreChamp.equals("0")) {System.out.println("Ingrese otro nombre");nombreChamp = S.nextLine();}
			System.out.println("Ingrese el rol que tendra (SUP,ADC,MID,JG,TOP)");
			String rol = S.nextLine();
			while(rol.equals("SUP")==false&&rol.equals("ADC")==false&&rol.equals("MID")==false&&rol.equals("JG")==false&&rol.equals("TOP")==false) {
				System.out.println("Ingrese uno de los roles que se muestran entre parentesis");rol = S.nextLine();}
			while(sistema.agregarCampeon(nombreChamp, rol)==false) {
				System.out.println("Ingrese el nombre del nuevo personaje");
				nombreChamp = S.nextLine();
				while(nombreChamp.equals("0")) {System.out.println("Ingrese otro nombre");nombreChamp = S.nextLine();}
				System.out.println("Ingrese el rol que tendra (SUP,ADC,MID,JG,TOP)");
				rol = S.nextLine();
				while(rol.equals("SUP")==false&&rol.equals("ADC")==false&&rol.equals("MID")==false&&rol.equals("JG")==false&&rol.equals("TOP")==false) {
					System.out.println("Ingrese uno de los roles que se muestran entre parentesis");rol = S.nextLine();}
			}
			System.out.println("Ingrese el nombre de una skin");
			String nombreSkin = S.nextLine();
			while(nombreSkin.equals("0")) {System.out.println("Ingrese otro nombre");nombreSkin = S.nextLine();}
			System.out.println("Ingrese la calidad de la skin (N,E,L,D,M)");
			String calidad = S.nextLine();
			while(calidad.equals("N")==false&&calidad.equals("E")==false&&calidad.equals("L")==false&&calidad.equals("D")==false&&calidad.equals("M")==false) {
				System.out.println("Ingrese una de las opciones que se muestran entre parentesis");calidad = S.nextLine();}
			while(sistema.agregarSkin(nombreSkin, nombreChamp, calidad)==false) {
				System.out.println("Ingrese el nombre de una skin");
				nombreSkin = S.nextLine();
				while(nombreSkin.equals("0")) {System.out.println("Ingrese otro nombre");nombreSkin = S.nextLine();}
				System.out.println("Ingrese la calidad de la skin (N,E,L,D,M)");
				calidad = S.nextLine();
				while(calidad.equals("N")==false&&calidad.equals("E")==false&&calidad.equals("L")==false&&calidad.equals("D")==false&&calidad.equals("M")==false) {
					System.out.println("Ingrese una de las opciones que se muestran entre parentesis");calidad = S.nextLine();}
			}
		}
			
		if(op.equals("6")) {
			System.out.println("Ingrese el nombre del personaje que desea agregarle ");
			String nombreChamp = S.nextLine();
			System.out.println("Ingrese el nombre de una skin");
			String nombreSkin = S.nextLine();
			while(nombreSkin.equals("0")) {System.out.println("Ingrese otro nombre");nombreSkin = S.nextLine();}
			System.out.println("Ingrese la calidad de la skin (N,E,L,D,M)");
			String calidad = S.nextLine();
			while(calidad.equals("N")==false&&calidad.equals("E")==false&&calidad.equals("L")==false&&calidad.equals("D")==false&&calidad.equals("M")==false) {
				System.out.println("Ingrese una de las opciones que se muestran entre parentesis");calidad = S.nextLine();}
			while(sistema.agregarSkin(nombreSkin, nombreChamp, calidad)==false) {
				System.out.println("Ingrese el nombre de una skin");
				nombreSkin = S.nextLine();
				while(nombreSkin.equals("0")) {System.out.println("Ingrese otro nombre");nombreSkin = S.nextLine();}
				System.out.println("Ingrese la calidad de la skin (N,E,L,D,M)");
				calidad = S.nextLine();
				while(calidad.equals("N")==false&&calidad.equals("E")==false&&calidad.equals("L")==false&&calidad.equals("D")==false&&calidad.equals("M")==false) {
					System.out.println("Ingrese una de las opciones que se muestran entre parentesis");calidad = S.nextLine();}
			}
		}
		if(op.equals("7")) {
			System.out.println("Ingrese el nombre de cuenta");
			String nombre = S.nextLine();
			while(sistema.bloquearJugador(nombre)&&nombre.equals("0")) {
				System.out.println("Ingrese el nombre de cuenta");
				nombre = S.nextLine();
			}
		}
		if(op.equals("8")) {sistema.desplegarCuentas();}
		System.out.println("Seleccione una opcion [1] Ventas por rol		[2] Ventas por región	[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
		System.out.println("                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas	[0] Salir");
		op = S.nextLine();
		while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
				&&(op.equals("7"))==false&&(op.equals("8"))==false&&(op.equals("0"))==false) {
				System.out.println("Opcion invalida");
				op = S.nextLine();
			}
		}
		S.close();
		
	}

	private static void LeerArchivos(GameSystem sistema) throws FileNotFoundException {
		File file1 = new File("Personajes.txt");
        Scanner arch1 = new Scanner(file1);
        while(arch1.hasNextLine()) {
            String linea = arch1.nextLine();
            String [] partes = linea.split(",");
            String nombreChamp = partes[0];
            String rol = partes[1];
            sistema.agregarCampeon(nombreChamp, rol);
            int cantSkins = Integer.parseInt(partes[2]);
        	int j = 0;
            for(int i = 0 ; i < cantSkins; i++) {
            	String nombreSkin = partes[3+j];
            	String calidad = partes[4+j];
            	sistema.agregarSkin(nombreSkin, nombreChamp, calidad);
            	j+=2;
            }
		}
        arch1.close();
        
		File file2 = new File("Cuentas.txt");
        Scanner arch2 = new Scanner(file2);
        while(arch2.hasNextLine()) {
            String linea = arch2.nextLine();
            String [] partes = linea.split(",");
            String nombreCuenta = partes[0];
            String contraseña = partes[1];
            String nick = partes[2];
            int nivel = Integer.parseInt(partes[3]);
            int saldo = Integer.parseInt(partes[4]);
            String region = partes[partes.length-1];
            sistema.agregarCuenta(nombreCuenta, contraseña, nick, region, nivel, saldo);
            int cantChamps = Integer.parseInt(partes[5]);
            int y = 0;
            for(int x = 0 ; x < cantChamps ; x++) {
            	String nombreChamp = partes[6+y];
            	sistema.asociarCampeonCuenta(nombreChamp, nombreCuenta);
            	int cantSkin = Integer.parseInt(partes[7+y]);
            	for(int z = 0 ; z < cantSkin ; z++ ) {
            		String nombreSkin = partes[8+z+y];
            		sistema.asociarSkinCuenta(nombreSkin, nombreCuenta, nombreChamp);
            	}
            	y+=(2+cantSkin);
            }
        }
        arch2.close();
        
		File file3 = new File("Estadísticas.txt");
        Scanner arch3 = new Scanner(file3);
        while(arch3.hasNextLine()) {
            String linea = arch3.nextLine();
            String [] partes = linea.split(",");
            String nombreChamp = partes[0];
            int recaudado = Integer.parseInt(partes[1]);
            sistema.setRecaudacion(nombreChamp, recaudado);
        }
        arch3.close();
	}
	private static void EscribirArchivos(GameSystem sistema) {
		// TODO Auto-generated method stub
		
	}
}
