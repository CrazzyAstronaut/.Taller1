package Dominio;

public class Skin {
	private String nombre;
	private Campeon champ;
	private String calidad;
	private int precio;
	
	public Skin(String nombre, Campeon champ, String calidad) {
		this.nombre = nombre;
		this.champ = champ;
		this.calidad = calidad;
		this.precio = getPrecioCalidad(calidad);
	}
	
	public int getPrecioCalidad(String calidad) {
		if(calidad.equals("M")) {return 3250;}
		if(calidad.equals("D")) {return 2750;}
		if(calidad.equals("L")) {return 1820;}
		if(calidad.equals("E")) {return 1350;}
		if(calidad.equals("N")) {return 975;}
		return -1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Campeon getChamp() {
		return champ;
	}

	public void setChamp(Campeon champ) {
		this.champ = champ;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getPrecio() {
		return precio;
	}

}
