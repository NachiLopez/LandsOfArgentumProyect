package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.utiles.Config;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.Render;
import tp3.lopez.nazareno.utiles.Texto;

public class EleccionPersonaje implements Screen {

	private Imagen eleccion;
	private Imagen flecha;
	private Imagen personajes[] = new Imagen[4];
	private SpriteBatch b;
	private Entradas entradas = new Entradas();
	private float a = 0;
	private boolean terminadoIn = false;
	protected static int opcEleccion;
	protected static String nombreDelJugador;
	
	private Texto estadisticas[] = new Texto[3];
	
	@Override
	public void show() {
		b = Render.batch;
		
		for (int i = 0; i < estadisticas.length; i++) {
				estadisticas[i] = new Texto("Adventure Script.otf", 62, Color.WHITE, true);
		}
		
		eleccion = new Imagen("pantallas/pantalla_eleccion.png");
		eleccion.setSize(Config.ANCHO, Config.ALTO);
		personajes[0] = new Imagen("personajes/guerrero_bueno_vestimenta_principiante.png");
		personajes[0].setSize(184, 436);
		personajes[0].setXY(100, 130);
		
		personajes[1] = new Imagen("personajes/elfo_bueno_vestimenta_principiante.png");
		personajes[1].setSize(184, 436);
		personajes[1].setXY(400, 130);
		
		personajes[2] = new Imagen("personajes/mago_bueno_vestimenta_principiante.png");
		personajes[2].setSize(184, 436);
		personajes[2].setXY(700, 130);
		
		personajes[3] = new Imagen("personajes/bardo_bueno_vestimenta_principiante.png");
		personajes[3].setSize(184, 436);
		personajes[3].setXY(1000, 130);
		
		flecha = new Imagen("display/FLECHA.png");
		
		Gdx.input.setInputProcessor(entradas);
	}
	

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		fadeIn();
		b.begin();
			int mouseX = entradas.getMouseX();
			int mouseY = entradas.getMouseY();
			eleccion.dibujar();
			for (int i = 0; i < personajes.length; i++) {
				personajes[i].dibujar();
				if(mouseX >= personajes[i].getX() && (mouseX <= (personajes[i].getX() + personajes[i].getWidth()))) {
					if(mouseY >= personajes[i].getY() && (mouseY <= (personajes[i].getY() + personajes[i].getHeight()))) {
						if(personajes[i] == personajes[0]) {
							estadisticas[0].setTexto("Vida: 1000");
							estadisticas[0].setX(90);
							estadisticas[0].setY(770);
							estadisticas[1].setTexto("Fuerza: 14");
							estadisticas[1].setX(90);
							estadisticas[1].setY(700);
							estadisticas[2].setTexto("Magia: 200");
							estadisticas[2].setX(90);
							estadisticas[2].setY(630);
							flecha.setXY(175, 25);
							
							estadisticas[0].dibujar();
							estadisticas[1].dibujar();
							estadisticas[2].dibujar();
							flecha.dibujar();
							
							if(entradas.isClick()) {
								opcEleccion = 1;
								Render.app.setScreen(new IngresarNombre());	
							}
						} else if(personajes[i] == personajes[1]) {
							estadisticas[0].setTexto("Vida: 1500");
							estadisticas[0].setX(380);
							estadisticas[0].setY(770);
							estadisticas[1].setTexto("Fuerza: 8");
							estadisticas[1].setX(380);
							estadisticas[1].setY(700);
							estadisticas[2].setTexto("Magia: 150");
							estadisticas[2].setX(380);
							estadisticas[2].setY(630);
							flecha.setXY(480, 25);
							
							estadisticas[0].dibujar();
							estadisticas[1].dibujar();
							estadisticas[2].dibujar();
							flecha.dibujar();
							
							if(entradas.isClick()) {
								opcEleccion = 2;
								Render.app.setScreen(new IngresarNombre());
							}
						}
						else if(personajes[i] == personajes[2]) {
							estadisticas[0].setTexto("Vida: 1000");
							estadisticas[0].setX(690);
							estadisticas[0].setY(770);
							estadisticas[1].setTexto("Fuerza: 5");
							estadisticas[1].setX(690);
							estadisticas[1].setY(700);
							estadisticas[2].setTexto("Magia: 700");
							estadisticas[2].setX(690);
							estadisticas[2].setY(630);
							flecha.setXY(785, 25);
							
							estadisticas[0].dibujar();
							estadisticas[1].dibujar();
							estadisticas[2].dibujar();
							flecha.dibujar();

							if(entradas.isClick()) {
								opcEleccion = 3;
								Render.app.setScreen(new IngresarNombre());
							}
						} else if(personajes[i] == personajes[3]) {
							estadisticas[0].setTexto("Vida: 1500");
							estadisticas[0].setX(980);
							estadisticas[0].setY(770);
							estadisticas[1].setTexto("Fuerza: 12");
							estadisticas[1].setX(980);
							estadisticas[1].setY(700);
							estadisticas[2].setTexto("Magia: 250");
							estadisticas[2].setX(980);
							estadisticas[2].setY(630);
							flecha.setXY(1085, 25);
							
							estadisticas[0].dibujar();
							estadisticas[1].dibujar();
							estadisticas[2].dibujar();
							flecha.dibujar();

							if(entradas.isClick()) {
								opcEleccion = 4;
								Render.app.setScreen(new IngresarNombre());
							}
						}
					}
				}
			}
			
		b.end();
	}

	private void fadeIn() {
		if (!terminadoIn ) {
			a += 0.01f;
			if (a > 1) {
				a = 1;
				terminadoIn = true;
			}
		}
		eleccion.setTransparencia(a);
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
		eleccion.dispose();
		flecha.dispose();
		for(int i=0; i<personajes.length; i++) {
			personajes[i].dispose();
		}
	}
	
	public static int getOpcEleccionPJ() {
		return opcEleccion;
	}

}
