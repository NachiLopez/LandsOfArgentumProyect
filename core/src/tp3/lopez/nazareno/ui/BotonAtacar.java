package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.pantallas.Batalla;

public class BotonAtacar extends Boton {
	
	
	public BotonAtacar(Entradas entradas) {
		super(entradas, "botones/Atacar.png", "botones/AtacarApretado.png", 580, 370, 121, 37);
	}

	public BotonAtacar(Entradas entradas, float x, float y) {
		super(entradas, "botones/Atacar.png", "botones/AtacarApretado.png", x, y, 121, 37);
	}

	public BotonAtacar(Entradas entradas, float x, float y, int ancho, int alto) {
		super(entradas, "botones/Atacar.png", "botones/AtacarApretado.png", x, y, ancho, alto);
	}
	
	@Override
	protected void ejecutarAccion() {
		Batalla.atacarFunciones = true;
		Batalla.inventarioFunciones = false;
		Batalla.movimiento = false;
	}
}
