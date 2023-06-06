package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class CarmesiPotala extends ItemConsumible {

	public CarmesiPotala() {
		super("Carmesi Potala", "Todas", "items/consumibles/carmesiPotala.png", "consumible");
		
	}

	@Override
	public void consumirPocion(Jugador usuario) {
		Personaje player = usuario.getPersonaje();

		player.setVida(player.getVidaDefault());
		player.setMagia(player.getMagiaDefault());
		player.setFuerza(player.getFuerza() - 1);
	}

}
