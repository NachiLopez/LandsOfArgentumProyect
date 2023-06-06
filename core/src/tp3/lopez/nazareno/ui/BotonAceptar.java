package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.pantallas.Level1;
import tp3.lopez.nazareno.utiles.Render;

public class BotonAceptar extends Boton {
	
	public BotonAceptar(Entradas entradas) {
		super(entradas, "botones/Aceptar.png", "botones/AceptarApretado.png", 560, 400, 150, 45);
	}

	public BotonAceptar(Entradas entradas, int x, int y) {
		super(entradas, "botones/Aceptar.png", "botones/AceptarApretado.png", x, y, 150, 45);
	}

	public BotonAceptar(Entradas entradas, int x, int y, int ancho, int alto) {
		super(entradas, "botones/Aceptar.png", "botones/AceptarApretado.png", x, y, ancho, alto);
	}

	@Override
	protected void ejecutarAccion() {
		if(!Level1.onlyOne) {
			Render.app.pantallaDesert = new Level1();
			Level1.onlyOne = true;
		}
		if(Level1.cofre) {
			Level1.cofre = false;
			Level1.darItem = true;
		} else {
			Render.app.setScreen(Render.app.pantallaDesert);
		}
	}


}
