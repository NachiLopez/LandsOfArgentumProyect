package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;

public class VestimentaPrincipiante extends ItemEquipable {
	
	public VestimentaPrincipiante(Jugador usuario) {
		super("Vestimenta de principiante", "Todas", "items/equipables/vestimenta_principiante_icono.png", "cuerpo");
		super.setDesequipado(false);
		super.setEquipado(true);
	}

	@Override
	public void aplicarEfecto(Jugador usuario) {
		
	}

}
