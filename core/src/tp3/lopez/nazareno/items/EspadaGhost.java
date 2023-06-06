package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class EspadaGhost extends ItemEquipable {

	public EspadaGhost() {
		super("Ghost", "Elfo", "items/equipables/ghost_icono.png", "mano");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setFuerza(player.getFuerza() + 7);
		} 
		
		if(isDesequipado()) {
			player.setFuerza(player.getFuerza() - 7);
		}
	}

}
