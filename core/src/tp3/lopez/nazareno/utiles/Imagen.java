package tp3.lopez.nazareno.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Imagen {

	private Texture t;
	private Sprite s;
	GlyphLayout layout;
	
	public Imagen(String ruta) {
		
		t = new Texture(ruta);
		s = new Sprite(t);
		
	}
	
	public Imagen(String ruta, float x, float y) {
		
		t = new Texture(ruta);
		s = new Sprite(t);
		setXY(x, y);
		
	}
	
	public void dibujar() {
		s.draw(Render.batch);
	}
	
	public void dibujar(float x, float y) {
		s.setX(x);
		s.setY(y);
		s.draw(Render.batch);
	}
	
	public void dispose() {
		t.dispose();
	}
	
	public Texture getTexture() {
		return t;
	}
	
	public void setTransparencia(float a) {
		s.setAlpha(a);
	}

	public void setXY(float x, float y) {
		s.setPosition(x, y);
		
	}
	
	public void setSize(float ANCHO, float ALTO) {
		s.setSize(ANCHO, ALTO);
	}

	public float getWidth() {
		return s.getWidth();
	}

	public float getHeight() {
		return s.getHeight();
	}
	
	public float getX() {
		return s.getX();
	}
	
	public float getY() {
		return s.getY();
	}
	
}
