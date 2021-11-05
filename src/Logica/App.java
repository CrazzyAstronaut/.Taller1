package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		GameSystem sistema = new GameSystemImplements();
		LeerArchivos(sistema);
	}

	private static void LeerArchivos(GameSystem sistema) throws FileNotFoundException {
		File file1 = new File("Personajes.txt");
        Scanner arch1 = new Scanner(file1);
        while(arch1.hasNextLine()){
            String linea = arch1.nextLine();
            String [] partes = linea.split(",");
            String nombreChamp = partes[1];
            String rol = partes[2];
            sistema.agregarCampeon(nombreChamp, rol);
            int cantSkins = Integer.parseInt(partes[3]);
            for(int i = 0 ; i < cantSkins; i++) {
            	
            }
		}
        arch1.close();
	}

}
