package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.pantallas.Batalla;
import tp3.lopez.nazareno.pantallas.Level1;

public class BotonOk extends Boton {
	
	public static boolean tirarItemConfirmado;

	public BotonOk(Entradas entradas) {
		super(entradas, "botones/Ok.png", "botones/OkApretado.png", 560, 400, 150, 45);
	}

	public BotonOk(Entradas entradas, float x, float y) {
		super(entradas, "botones/Ok.png", "botones/OkApretado.png", x, y, 150, 45);
	}

	public BotonOk(Entradas entradas, float x, float y, float ancho, float alto) {
		super(entradas, "botones/Ok.png", "botones/OkApretado.png", x, y, ancho, alto);
	}

	@Override
	protected void ejecutarAccion() {
		Batalla.inventarioFunciones = false;
		Batalla.atacarFunciones = false;
		Batalla.movimiento = true;
		if(Level1.tirarItem) {
			Level1.tirarItem=false;
			tirarItemConfirmado = true;
		}
	}
}
