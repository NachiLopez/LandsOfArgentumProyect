package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class FuerzaGratificante extends ItemConsumible {

	public FuerzaGratificante() {
		super("Fuerza gratificante", "Todas", "items/consumibles/fuerzaGratificante.png", "consumible");
		
	}

	@Override
	public void consumirPocion(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		player.setFuerza(player.getFuerza() + 2);
	}

}
