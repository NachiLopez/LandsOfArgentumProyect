package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class PocionDeVida extends ItemConsumible {

	public PocionDeVida() {
		super("Pocion de vida", "Todas", "items/consumibles/pocionVida.png", "consumible");
		
	}

	@Override
	public void consumirPocion(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		player.setVida(player.getVida() + 200);
		if(player.getVida() > player.getVidaDefault()) {
			player.setVida(player.getVidaDefault());
		}
	}

}
