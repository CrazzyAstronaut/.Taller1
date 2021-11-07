package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		GameSystem sistema = new GameSystemImplements();
		LeerArchivos(sistema);
		MenuPrincipal(sistema);
		EscribirArchivos(sistema);
	}

	private static void MenuPrincipal(GameSystem sistema) {
		System.out.println("Bienvenido a Juego gen�rico");
		System.out.println("[1]	Iniciar sesi�n	[2]	Registrarse	[0]	Salir");
		System.out.println("Nota: Si quiere volver atras en cualquier punto escriba (0)");
		Scanner S3 = new Scanner(System.in);
		String MainOption = S3.nextLine();
		while ((MainOption.equals("1"))==false&&(MainOption.equals("2"))==false&&(MainOption.equals("0"))==false) {
			System.out.println("Opcion invalida");
			MainOption = S3.nextLine();
		}

		while(MainOption.equals("0") == false) {
			if(MainOption.equals("1")) {
				iniciarSesion(sistema);
			}
			if(MainOption.equals("2")) {
				registrar(sistema);
			}
			System.out.println("[1]	Iniciar sesi�n	[2]	Registrarse	[0]	Salir");
			MainOption = S3.nextLine();
			while ((MainOption.equals("1"))==false&&(MainOption.equals("2"))==false&&(MainOption.equals("0"))==false) {
				System.out.println("Opcion invalida");
				MainOption = S3.nextLine();
			}
		}
	}

	private static void registrar(GameSystem sistema) {
		Scanner S = new Scanner(System.in);
		System.out.println("Ingrese su nombre de usuario: ");
		String nombreCuenta= S.nextLine();
		if(nombreCuenta.equals("0")) {return;}
		System.out.println("Ingrese su contrase�a (Debe tner un minimo de caracteres de 4)");
		String contrase�a= S.nextLine();
		while(contrase�a.equals("0")==false&&contrase�a.length()<4) {
			System.out.println("Ingrese un valor valido: ");contrase�a = S.nextLine();}
		if(contrase�a.equals("0")) {return;}
		System.out.println("Ingrese su nickname: ");
		String nickname= S.nextLine();
		System.out.println("Ingrese su regi�n: ");
		String region = S.nextLine();
		sistema.agregarCuenta(nombreCuenta, contrase�a, nickname, region, 0, 0);
		System.out.println("Se ha ingresado con exito...");
	}

	private static void iniciarSesion(GameSystem sistema) {
		System.out.println("Ingrese su nombre de usuario");
		Scanner S0 = new Scanner(System.in);
		String nombreCuenta = S0.nextLine();
		System.out.println("Ingrese su contrase�a");
		String contrase�a = S0.nextLine();
		while((sistema.iniciarSesion(nombreCuenta,contrase�a)==1)&&(nombreCuenta.equals("ADMIN")==false||
				contrase�a.equals("ADMIN")==false)&&(nombreCuenta.equals("0")==false)) {
			System.out.println("Nombre de cuenta o contrase�a incorrectos, si desea volver al menu anterior escriba (0)");
			System.out.println("Ingrese su nombre de usuario");
			nombreCuenta = S0.nextLine();
			System.out.println("Ingrese su nombre de usuario");
			contrase�a = S0.nextLine();
		}
		if(nombreCuenta.equals("0")) {
			return;}
		if(nombreCuenta.equals("ADMIN")) {
			adminOptions(sistema);
		}
		if(sistema.iniciarSesion(nombreCuenta, contrase�a)==2) {
			System.out.println("Su cuenta ha sido bloqueada");
		}
		if(sistema.iniciarSesion(nombreCuenta, contrase�a)==0) {
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
				System.out.println(" - Menu de compra de skin - ");
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
				System.out.println("Volviendo al menu...");
			}
			if(op.equals("2")) {
				System.out.println(" - Menu de compra de personaje - ");
				System.out.println("Ingrese el nombre del personaje que decea comprar");
				String nombreChamp = S.nextLine();
				while(sistema.comprarPersonaje(nombreChamp, nombreCuenta)==false&&nombreChamp.equals("0")==false) {
					System.out.println("Intentelo denuevo");
					System.out.println("Ingrese el nombre del personaje que decea comprar");
					nombreChamp = S.nextLine();
				}
				System.out.println("Volviendo al menu...");				
			}
			if(op.equals("3")) {System.out.println(" - Skins disponibles para comprar - ");sistema.desplegarSkinsDisponibles(nombreCuenta);}
			if(op.equals("4")) {System.out.println(" - Inventario - ");sistema.desplegarInventario(nombreCuenta);}
			if(op.equals("5")) {
				System.out.println(" - Recargar RP - ");
				System.out.println("Ingrese la cantidad de RP que desea agregar a su cuenta");
				String RPadd = S.nextLine();
				while(correctNumber(RPadd)==false) {System.out.println("Ingrese una cantidad valida");RPadd = S.nextLine();}
				int RP = Integer.parseInt(RPadd);
				if(sistema.recargarRP(nombreCuenta, RP)) {
					System.out.println("Compra exitosa");
				}
				System.out.println("Volviendo al menu...");
			}
			if(op.equals("6")) {
				System.out.println(" - Informacion de cuenta - ");
				sistema.desplegarDatosCuenta(nombreCuenta);
				System.out.println("�Desea cambiar su contrase�a? ([0] No	[1] Si)");
				String o = S.nextLine();
				while(o.equals("0")==false&&o.equals("1")==false) {System.out.println("Ingrese una opcion valida");
					o = S.nextLine();}
				if (o.equals("1")) {
					System.out.println("Ingrese su contrase�a");
					String oldPass = S.nextLine();
					while(sistema.iniciarSesion(nombreCuenta, oldPass)!=0&&oldPass.equals("0")==false) {
						System.out.println("Contrase�a incorrecta :");
						oldPass = S.nextLine();
					}
					if(oldPass.equals("0")==false) {
						System.out.println("Ingrese la nueva contrase�a (Debe tener un minimo de 4 caracteres)");
						String newPass1 = S.nextLine();
						while(newPass1.equals("0")==false&&newPass1.length()<4) {
							System.out.println("Intentelo denuevo: ");newPass1 = S.nextLine();
						}
						if (newPass1.equals("0")==false) {
							System.out.println("Ingrese nuevamente la contrase�a");
							String newPass2 = S.nextLine();
							while(newPass2.equals("0")==false&&newPass2.equals(newPass1)==false) {
								System.out.println("Porfavor intentelo denuvo");newPass2 = S.nextLine();
							}
							if(newPass2.equals("0")==false) {
								sistema.cambiarContrase�a(nombreCuenta, newPass2);
								System.out.println("La contrase�a se ha cambiado");
							}
						}
					}
				}
			}
			System.out.println("Seleccione una opcion [1] Comprar Skin		[2] Comprar Personaje	[3] Skins Disponibles	[4] Mostrar Inventario  ");
			System.out.println("                      [5] RecargarRP		[6] Mostrar datos		[0] Salir");
			op = S.nextLine();
			while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
					&&(op.equals("0"))==false) {
				System.out.println("Opcion invalida");
				op = S.nextLine();
			}
		}
	}

	private static void adminOptions(GameSystem sistema) {
		System.out.println("Bienvenido al menu de administrador");
		System.out.println("Seleccione una opcion [1] Ventas por rol		[2] Ventas por regi�n		[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
		System.out.println("                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas		[0] Salir");
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
		System.out.println("Seleccione una opcion [1] Ventas por rol		[2] Ventas por regi�n	[3] Ventas por personaje	[4] Cantidad de personajes por rol  ");
		System.out.println("                      [5] Agregar personaje al juego	[6] Agregar skin al juego	[7] Bloquear jugador	[8] Desplegar cuentas	[0] Salir");
		op = S.nextLine();
		while ((op.equals("1")) ==false&&(op.equals("2"))==false&&(op.equals("3"))==false&&(op.equals("4"))==false&&(op.equals("5"))==false&&(op.equals("6"))==false
				&&(op.equals("7"))==false&&(op.equals("8"))==false&&(op.equals("0"))==false) {
				System.out.println("Opcion invalida");
				op = S.nextLine();
			}
		}
		
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
            String contrase�a = partes[1];
            String nick = partes[2];
            int nivel = Integer.parseInt(partes[3]);
            int saldo = Integer.parseInt(partes[4]);
            String region = partes[partes.length-1];
            sistema.agregarCuenta(nombreCuenta, contrase�a, nick, region, nivel, saldo);
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
        
		File file3 = new File("Estad�sticas.txt");
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
	
	private static void EscribirArchivos(GameSystem sistema) throws IOException {
		FileWriter file1 = new FileWriter("Personajes.txt");
		sistema.guardarPersonajes(file1);
		FileWriter file2 = new FileWriter("Estad�sticas.txt");
		sistema.guardarEstadisticas(file2);
		FileWriter file3 = new FileWriter("Cuentas.txt");
		sistema.guardarCuentas(file3);
	}
	public static boolean correctNumber(String numero) {
        String partes[] = numero.split("");
        if( 8 <= partes.length && partes.length <= 9) {
        	for(int i = 0; i < partes.length-1; i++) {
        		if(partes[i].equals("0")==false&&partes[i].equals("1")==false&&partes[i].equals("2")==false&&
        				partes[i].equals("3")==false&&partes[i].equals("4")==false&&partes[i].equals("5")==false&&
        				partes[i].equals("6")==false&&partes[i].equals("7")==false&&partes[i].equals("8")==false&&
        				partes[i].equals("9")==false) {
        			return false;
        		}
        	}
        }
        return true;
	}
}
