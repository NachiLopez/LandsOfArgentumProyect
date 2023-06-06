package tp3.lopez.nazareno.items;

import tp3.lopez.nazareno.jugadores.Jugador;

public abstract class ItemEquipable extends Item {
	
	private boolean equipado;
	private boolean desequipado;
	
	public ItemEquipable(String nombre, String clase, String ruta, String tipo) {
		super(nombre, clase, ruta, tipo);
	}

	public abstract void aplicarEfecto(Jugador usuario);
	
	public boolean isEquipado() {
		return equipado;
	}

	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}

	public boolean isDesequipado() {
		return desequipado;
	}

	public void setDesequipado(boolean desequipado) {
		this.desequipado = desequipado;
	}
}
