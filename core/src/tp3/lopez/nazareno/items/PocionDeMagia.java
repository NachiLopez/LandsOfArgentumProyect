package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class PocionDeMagia extends ItemConsumible {

	public PocionDeMagia() {
		super("Pocion de magia", "Todas", "items/consumibles/pocionMagia.png", "consumible");
		
	}

	@Override
	public void consumirPocion(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		player.setMagia(player.getMagia() + 50);
		if(player.getMagia() > player.getMagiaDefault()) {
			player.setMagia(player.getMagiaDefault());
		}
	}

}
