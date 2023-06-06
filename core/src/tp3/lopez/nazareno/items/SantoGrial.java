package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class SantoGrial extends ItemConsumible {

	public SantoGrial() {
		super("Santo Grial", "Todas", "items/consumibles/santoGrial.png", "consumible");
		
	}

	@Override
	public void consumirPocion(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		player.setVidaDefault(player.getVidaDefault() + 200);
		
	}

}
