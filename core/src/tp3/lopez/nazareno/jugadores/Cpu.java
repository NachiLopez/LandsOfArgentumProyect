package tp3.lopez.nazareno.jugadores;

import tp3.lopez.nazareno.personajes.Personaje;
import tp3.lopez.nazareno.utiles.NombresRandom;

public final class Cpu extends Jugador {

	public Cpu(String nombre, Personaje personaje) {
		super(NombresRandom.getNombreRandom(), personaje);
		
	}

}
