package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class LaudMagico extends ItemEquipable {

	public LaudMagico() {
		super("Laud mágico", "Bardo", "items/equipables/laud_icono.png", "mano");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setMagiaDefault(player.getMagiaDefault() + 150);
		} 
		
		if(isDesequipado()) {
			player.setMagiaDefault(player.getMagiaDefault() - 150);
		}
		if(player.getVida() > player.getVidaDefault()) {
			player.setVida(player.getVidaDefault());
		}
		if(player.getMagia() > player.getMagiaDefault()) {
			player.setMagia(player.getMagiaDefault());
		}
	}

}
