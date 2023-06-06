package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.utiles.Imagen;

public abstract class Item {

	private String nombre;
	private String clase;
	private Imagen icono;
	private String tipo;
	private int x, y;

	public Item(String nombre, String clase, String ruta, String tipo) {
		this.nombre = nombre;
		this.clase = clase;
		this.icono = new Imagen(ruta);
		this.tipo = tipo;

	}

	public String getNombre() {
		return nombre;
	}

	public String getClase() {
		return clase;
	}

	public Imagen getIcono() {
		return icono;
	}

	public void dibujar() {
		icono.dibujar();
	}
	
	public void dibujar(int x, int y) {
		icono.setXY(x, y);
		icono.dibujar();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return (int) icono.getWidth();
	}

	public int getHeight() {
		return (int) icono.getHeight();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
