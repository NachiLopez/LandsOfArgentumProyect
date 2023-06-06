package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class LanzaLonga extends ItemEquipable {

	public LanzaLonga() {
		super("Lanza Longa", "Guerrero", "items/equipables/lanza_icono.png", "mano");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setFuerza(player.getFuerza() + 5);
		} 
		
		if(isDesequipado()) {
			player.setFuerza(player.getFuerza() - 5);
		}
	}

}
