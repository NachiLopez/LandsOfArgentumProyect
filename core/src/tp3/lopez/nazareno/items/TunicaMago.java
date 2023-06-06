package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class TunicaMago extends ItemEquipable {

	public TunicaMago() {
		super("Tunica de mago", "Mago", "items/equipables/vestimenta_mago_icono.png", "cuerpo");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		
		if(isEquipado()) {
			player.setVidaDefault(player.getVidaDefault() + 100);
			player.setMagiaDefault(player.getMagiaDefault() + 200);
		}
		
		if(isDesequipado()) {
			player.setVidaDefault(player.getVidaDefault() - 100);
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