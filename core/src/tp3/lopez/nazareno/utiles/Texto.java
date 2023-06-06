package tp3.lopez.nazareno.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Texto {

	private BitmapFont fuente;
	private GlyphLayout layout;
	private String texto = "";
	private float x=0, y=0;

	public Texto(String nombreFuente, int size, Color color, boolean borde) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fuentes/" + nombreFuente));
	    FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    parameter.size = size;
	    parameter.color = color;
	    if(borde) {
	    parameter.borderColor     = Color.BLACK;
	    parameter.borderWidth     = 1.5f;
	    }
	    fuente = generator.generateFont(parameter);
	    layout = new GlyphLayout();
	}
	
	public void dibujar() {
		fuente.draw(Render.batch, texto, x, y); 
	}
	
	public void setColor(Color color) {
		fuente.setColor(color);
	}
	
	public float getAncho() {
		return layout.width;
	}
	
	public float getAlto() {
		return layout.height;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		layout.setText(fuente, texto);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
