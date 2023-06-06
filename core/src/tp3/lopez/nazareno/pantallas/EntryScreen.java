package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.Render;

public class EntryScreen implements Screen {
	Imagen intro;
	SpriteBatch b;
	float a = 0.00001f;
	boolean terminado = false, completo = false;
	int contEspera = 0;

	@Override
	public void show() {
		intro = new Imagen("pantallas/pantalla_intro.png");
		b = Render.batch;
		intro.setTransparencia(a);
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			Render.app.setScreen(new MenuPrincipal());
		}
		Render.limpiarPantalla(0, 0, 0, a);
		
		b.begin();
			intro.dibujar();
		b.end();

		fadeInOut();

	}

	private void fadeInOut() {
		if(!terminado) {
			a += 0.01f;
			if(a > 1) {
				a = 1;
				terminado = true;
			}
			
		} else {
			contEspera++;
			if (contEspera >= 100) {
				contEspera = 100;
				a-=0.01f;
				if(a < 0) {
					a = 0;
					completo = true;
				}
			}
		}
		intro.setTransparencia(a);
		if(completo) {
			Render.app.setScreen(new MenuPrincipal());
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		b.dispose();
		intro.dispose();
	}

}
