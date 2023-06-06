package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class Barsagrado extends ItemEquipable {

	public Barsagrado() {
		super("Barsagrado", "Bardo", "items/equipables/barsagrado_icono.png", "cuerpo");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setMagiaDefault(player.getMagiaDefault() + 200);
		}
		
		if(isDesequipado()) {
			player.setMagiaDefault(player.getMagiaDefault() - 200);
		}
		
		if(player.getVida() > player.getVidaDefault()) {
			player.setVida(player.getVidaDefault());
		}
		if(player.getMagia() > player.getMagiaDefault()) {
			player.setMagia(player.getMagiaDefault());
		}
	}

}
