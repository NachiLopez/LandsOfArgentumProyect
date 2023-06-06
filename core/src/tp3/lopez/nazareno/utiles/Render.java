package tp3.lopez.nazareno.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import tp3.lopez.nazareno.Principal;

public class Render {

	public static SpriteBatch batch;	
	public static ShapeRenderer sr = new ShapeRenderer();
	public static Principal app;
	
	public static void limpiarPantalla(float r, float g, float b, float a) {
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
