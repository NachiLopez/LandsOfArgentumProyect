package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;

public abstract class ItemConsumible extends Item {
	
	public ItemConsumible(String nombre, String clase, String ruta, String tipo) {
		super(nombre, clase, ruta, tipo);
	}
	
	public abstract void consumirPocion(Jugador usuario);
	
}
