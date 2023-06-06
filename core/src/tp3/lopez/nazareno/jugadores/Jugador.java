package tp3.lopez.nazareno.jugadores;

import tp3.lopez.nazareno.items.ArmaduraElfo;
import tp3.lopez.nazareno.items.BastonElfico;
import tp3.lopez.nazareno.items.EspadaGhost;
import tp3.lopez.nazareno.items.Item;
import tp3.lopez.nazareno.items.LanzaLonga;
import tp3.lopez.nazareno.items.PocionDeMagia;
import tp3.lopez.nazareno.items.PocionDeVida;
import tp3.lopez.nazareno.items.SantoGrial;
import tp3.lopez.nazareno.items.TunicaMago;
import tp3.lopez.nazareno.items.VestimentaPrincipiante;
import tp3.lopez.nazareno.personajes.Personaje;

public abstract class Jugador {
	private String nombre;
	private Personaje personaje;
	private int cantOro = 0;
	public Item inventario[] = new Item[10];
	public static boolean inventarioIconos;
	
	public Jugador(String nombre, Personaje personaje) {
		this.nombre = nombre;
		this.personaje = personaje;
		inventario[0] = new VestimentaPrincipiante(this);
		// OBJETOS TEMPORAL
		inventario[1] = new ArmaduraElfo();
		inventario[2] = new EspadaGhost();
		inventario[3] = new LanzaLonga();
		inventario[4] = new TunicaMago();
		inventario[5] = new BastonElfico();
		inventario[6] = new PocionDeVida();
		inventario[7] = new SantoGrial();
		inventario[8] = new PocionDeMagia();
		
		inventarioIconos = true;
		
	}

	public String getNombre() {
		return nombre;
	}

	public Personaje getPersonaje() {
		return personaje;
	}
	
	public int getCantOro() {
		return cantOro;
	}
	
	public void setCantOro(int cantOro) {
		this.cantOro = cantOro;
	}
	
	public Item[] getInventario() {
		return inventario;
	}
	
}
