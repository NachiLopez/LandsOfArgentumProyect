package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class EspadaSartial extends ItemEquipable {

	public EspadaSartial() {
		super("Sartial", "Guerrero", "items/equipables/sartial_icono.png", "mano");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setFuerza(player.getFuerza() + 8);
		}
		
		if(isDesequipado()) {
			player.setFuerza(player.getFuerza() - 8);
		}
	}

}
