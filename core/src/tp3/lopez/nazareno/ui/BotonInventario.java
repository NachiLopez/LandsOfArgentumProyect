package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.pantallas.Batalla;

public class BotonInventario extends Boton {

	public BotonInventario(Entradas entradas) {
		super(entradas, "botones/inventario.png", "botones/inventarioApretado.png", 580, 308, 121, 37);
	}

	public BotonInventario(Entradas entradas, int x, int y) {
		super(entradas, "botones/inventario.png", "botones/inventarioApretado.png", x, y);
	}

	public BotonInventario(Entradas entradas, int x, int y, int ancho, int alto) {
		super(entradas, "botones/inventario.png", "botones/inventarioApretado.png", x, y, ancho, alto);
	}
	
	@Override
	protected void ejecutarAccion() {
		Batalla.inventarioFunciones = true;
		Batalla.movimiento = false;
	}

}
