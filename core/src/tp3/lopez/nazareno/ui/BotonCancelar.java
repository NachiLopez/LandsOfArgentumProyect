package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.pantallas.Level1;

public class BotonCancelar extends Boton {

	public BotonCancelar(Entradas entradas) {
		super(entradas, "botones/Cancelar.png", "botones/CancelarApretado.png", 560, 400, 150, 45);
	}

	public BotonCancelar(Entradas entradas, float x, float y) {
		super(entradas, "botones/Cancelar.png", "botones/CancelarApretado.png", x, y, 150, 45);
	}

	public BotonCancelar(Entradas entradas, float x, float y, float ancho, float alto) {
		super(entradas, "botones/Cancelar.png", "botones/CancelarApretado.png", x, y, ancho, alto);
	}

	@Override
	protected void ejecutarAccion() {
		Level1.tirarItem = false;
	}
}
