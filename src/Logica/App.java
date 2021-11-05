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
		String option = ingresarOpcion();
		while(option.equals("3") == false) {
			if(option.equals("1")) {
				iniciarSesion(sistema);
			}
			option = ingresarOpcion();
		}
	}

	private static void iniciarSesion(GameSystem sistema) {
		Scanner S0 = new Scanner(System.in);
		System.out.println("Ingrese su nombre de usuario");
		String nombreCuenta = S0.nextLine();
		System.out.println("Ingrese su contraseña");
		String contraseña = S0.nextLine();
		while((sistema.iniciarSesion(nombreCuenta,contraseña)==false)&&(nombreCuenta.equals("ADMIN")==false||
				contraseña.equals("ADMIN")==false)&&(nombreCuenta.equals("3")==false)) {
			System.out.println("Nombre de cuenta o contraseña incorrectos, si desea volver al menu anterior escriba (3)");
			System.out.println("Ingrese su nombre de usuario");
			nombreCuenta = S0.nextLine();
			System.out.println("Ingrese su nombre de usuario");
			contraseña = S0.nextLine();
		}
		if(nombreCuenta.equals("3")) {return;}
		if(nombreCuenta.equals("ADMIN")) {
			
		}
		if(sistema.iniciarSesion(nombreCuenta, contraseña)) {
			
		}
		return;
	}



	public static String ingresarOpcion() {
		String MainOption;
		System.out.println("==========================================================================================");
		System.out.println("		1)	Iniciar sesión	2)	Registrarse	3)	Salir");
		System.out.println("==========================================================================================");
		Scanner S = new Scanner(System.in);
		MainOption = S.nextLine();
		while ((MainOption.equals("1")) == false && (MainOption.equals("2")) == false
				&& (MainOption.equals("3")) == false) {
			System.out.print("Opcion invalida");
			Scanner S1 = new Scanner(System.in);
			String MainOptiontry = S1.nextLine();
			MainOption = MainOptiontry;
		}
		return MainOption;
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
