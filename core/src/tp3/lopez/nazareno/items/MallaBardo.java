package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class MallaBardo extends ItemEquipable {

	public MallaBardo() {
		super("Malla del bardo", "Bardo", "items/equipables/malla_icono.png", "cuerpo");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setVidaDefault(player.getVidaDefault() + 120);
		}
		
		if(isDesequipado()) {
			player.setVidaDefault(player.getVidaDefault() - 120);
		}
		if(player.getVida() > player.getVidaDefault()) {
			player.setVida(player.getVidaDefault());
		}
		if(player.getMagia() > player.getMagiaDefault()) {
			player.setMagia(player.getMagiaDefault());
		}
	}

}
