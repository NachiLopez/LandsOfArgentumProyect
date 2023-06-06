package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Personaje;

public class ArmaduraElfo extends ItemEquipable {
	public ArmaduraElfo() {
		super("Paradela Elfica", "Elfo", "items/equipables/paradela_elfo_icono.png", "cuerpo");
		super.setDesequipado(true);
		super.setEquipado(false);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		Personaje player = usuario.getPersonaje();
		if (isEquipado()) {
			player.setVidaDefault(player.getVidaDefault() + 200);
		}else if (isDesequipado()) {
			player.setVidaDefault(player.getVidaDefault() - 200);
		}
		if(player.getVida() > player.getVidaDefault()) {
			player.setVida(player.getVidaDefault());
		}
		if(player.getMagia() > player.getMagiaDefault()) {
			player.setMagia(player.getMagiaDefault());
		}
	}

}
