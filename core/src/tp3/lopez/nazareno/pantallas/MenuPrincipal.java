package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.utiles.Config;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.Render;
import tp3.lopez.nazareno.utiles.Texto;
import tp3.lopez.nazareno.utiles.Utiles;

public class MenuPrincipal implements Screen {

	private Imagen menu;
	private SpriteBatch b;
	private Texto opciones[] = new Texto[3];
	private String textos[] = { "Rol Game", "Iniciar nueva partida", "Salir" };
	private float a = 0;
	private boolean terminadoIn = false;
	private int opc = 1;
	private Entradas entradas = new Entradas();

	@Override
	public void show() {
		menu = new Imagen("pantallas/pantalla_menuPrincipal.png");
		menu.setSize(Config.ANCHO, Config.ALTO);
		for (int i = 0; i < opciones.length; i++) {
			if (opciones[i] == opciones[0]) {
				opciones[0] = new Texto("Berkahi Blackletter.ttf", 62, Color.WHITE, true);
				opciones[0].setTexto(textos[i]);
				opciones[0].setX(545);
				opciones[0].setY(750);
			} else {
				opciones[i] = new Texto("River Adventurer.ttf", 58, Color.WHITE, true);
				opciones[i].setTexto(textos[i]);
				if (opciones[i] == opciones[1]) {
					opciones[1].setX(380);
					opciones[1].setY(450);
				}
				if (opciones[i] == opciones[2]) {
					opciones[2].setX(575);
					opciones[2].setY(350);
				}
			}

		}
		b = Render.batch;
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, a);
		
		fadeIn();
		b.begin();
		int mouseX = entradas.getMouseX();
		int mouseY = entradas.getMouseY();
		menu.dibujar();
		for (int i = 0; i < opciones.length; i++) {
			if(terminadoIn) {
				opciones[i].dibujar();
			}
		}
		b.end();
		if (terminadoIn) {
			if (entradas.isAbajo()) {
				opc++;
				if (opc == 3) {
					opc = 1;
				}
			}
			if (entradas.isArriba()) {
				opc--;
				if (opc == 0) {
					opc = 2;
				}
			}
			if (opc == 1) {
				opciones[1].setColor(Color.YELLOW);
				opciones[2].setColor(Color.WHITE);
				if(entradas.isEnter()) {
					Render.app.setScreen(new EleccionPersonaje());
				}
				Utiles.esperar(150);
			} else if (opc == 2) {
				opciones[2].setColor(Color.YELLOW);
				opciones[1].setColor(Color.WHITE);
				if(entradas.isEnter()) {
					Render.app.dispose();
				}
				Utiles.esperar(150);
			}
		}
			for (int i = 0; i < opciones.length; i++) {
				if ((mouseX >= opciones[i].getX()) && (mouseX <= (opciones[i].getX() + opciones[i].getAncho()))) {
					if (mouseY <= opciones[i].getY() && mouseY >= (opciones[i].getY() - opciones[i].getAlto())){
						if (opciones[i] == opciones[1]) {
							opc = 1;
							if(entradas.isClick()) {
								Render.app.setScreen(new EleccionPersonaje());
							}
						} else if (opciones[i] == opciones[2]) {
							opc = 2;
							if(entradas.isClick()) {
								Render.app.dispose();
							}
						}
						
					}
				}
			}
		}
		
	
	private void fadeIn() {
		if(Gdx.input.isKeyPressed(Keys.ENTER)) terminadoIn=true;;
		if (!terminadoIn) {
			a += 0.01f;
			if (a > 1) {
				a = 1;
				terminadoIn = true;
			}
		} else {
			a = 1;
			terminadoIn = true;
		}
		menu.setTransparencia(a);
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
		menu.dispose();
	}

}
