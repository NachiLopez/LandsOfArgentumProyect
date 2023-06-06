package tp3.lopez.nazareno.ui;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.utiles.Imagen;

public abstract class Boton {
	
	protected Imagen imagenesBoton[] = new Imagen[2];
	private boolean presionado = false;
	protected Entradas entradas;
	
	private int tics = 0;
	private boolean ejecutaAccion = false;
	
	public Boton(Entradas entradas, String rutaBtnNormal, String rutaBtnApretado) {
		this.entradas = entradas;
		imagenesBoton[0] = new Imagen(rutaBtnNormal);
		imagenesBoton[1] = new Imagen(rutaBtnApretado);

	}
	
	public Boton(Entradas entradas, String rutaBtnNormal, String rutaBtnApretado, float x, float y) {
		
		this(entradas, rutaBtnNormal,rutaBtnApretado);
		for (int i = 0; i < imagenesBoton.length; i++) {
			imagenesBoton[i].setXY(x, y);
		}
	}
	
	public Boton(Entradas entradas, String rutaBtnNormal, String rutaBtnApretado, float x, float y, float ancho, float alto) {
		
		this(entradas, rutaBtnNormal,rutaBtnApretado,x,y);
		for (int i = 0; i < imagenesBoton.length; i++) {
			imagenesBoton[i].setSize(ancho, alto);
		}

	}

	public void dibujar() {
		if(!presionado) {
			imagenesBoton[0].dibujar();
		} else {
			imagenesBoton[1].dibujar();
		}
		
		if(ejecutaAccion) {
			tics++;
			if(tics==19) {
				ejecutarAccion();
				tics=0;
				ejecutaAccion = false;
			}
		}
		
		chequearClic();
		
	}

	private void chequearClic() {
		int mouseX = entradas.getMouseX();
		int mouseY = entradas.getMouseY();
		
		if(mouseX >= imagenesBoton[0].getX() && (mouseX <= imagenesBoton[0].getX() + imagenesBoton[0].getWidth())) {
			if(mouseY >= imagenesBoton[0].getY() && (mouseY <= imagenesBoton[0].getY() + imagenesBoton[0].getHeight())) {
				if(entradas.isClick()) {
					presionado = true;
					ejecutaAccion = true;
				} else { presionado = false;}
			}
		}
	}

	protected abstract void ejecutarAccion();
	
	public void dispose() {
		for (int i = 0; i < imagenesBoton.length; i++) {
			imagenesBoton[i].dispose();
		}		
	}
	
	
	public void setXY(float x, float y) {
		for (int i = 0; i < imagenesBoton.length; i++) {
			imagenesBoton[i].setXY(x, y);
		}
	}
	
	public void setSize(float ANCHO, float ALTO) {
		for (int i = 0; i < imagenesBoton.length; i++) {
			imagenesBoton[i].setSize(ANCHO, ALTO);
		}
	}

	public float getWidth() {
		return imagenesBoton[0].getWidth();
	}

	public float getHeight() {
		return imagenesBoton[0].getHeight();
	}
	
	public float getX() {
		return imagenesBoton[0].getX();
	}
	
	public float getY() {
		return imagenesBoton[0].getY();
	}

}
