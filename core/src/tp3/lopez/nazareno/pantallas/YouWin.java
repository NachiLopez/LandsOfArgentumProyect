package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.items.Item;
import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.ui.Boton;
import tp3.lopez.nazareno.ui.BotonAceptar;
import tp3.lopez.nazareno.utiles.Config;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.ItemsRandom;
import tp3.lopez.nazareno.utiles.Render;
import tp3.lopez.nazareno.utiles.Texto;

public class YouWin implements Screen {

	private Jugador usuario;
	private boolean agregado = false;
	private Imagen youWinImage;
	private Boton btnAceptar;
	private Texto nombreItem;
	private Item itemObtenido;
	private Entradas entradas;
	private SpriteBatch b;
	
	public YouWin(Jugador usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public void show() {
		youWinImage = new Imagen("pantallas/youWinScreen.png");
		b = Render.batch;
		entradas = new Entradas();
		Gdx.input.setInputProcessor(entradas);
		
		btnAceptar = new BotonAceptar(entradas);
		btnAceptar.setXY((int) Config.ANCHO / 2 - (int) btnAceptar.getWidth() / 2, (int) Config.ALTO / 2 - (int) btnAceptar.getHeight() - 200);
		
		itemObtenido = ItemsRandom.getItemRandom();
		if(itemObtenido == null) {
			itemObtenido = ItemsRandom.getItemRandom();
		}
		itemObtenido.getIcono().setSize(48, 48);
		itemObtenido.getIcono().setXY(Config.ANCHO / 2 - itemObtenido.getWidth(), Config.ALTO - Config.ALTO / 2);
		
		nombreItem = new Texto("Adventure Script.otf", 20, Color.WHITE, true);
		nombreItem.setX(itemObtenido.getIcono().getX() - (itemObtenido.getIcono().getWidth() / 2));
		nombreItem.setY(itemObtenido.getIcono().getY() - 20);
		
		for (int i = 0; i < usuario.getInventario().length; i++) {
			if(i < 10 && usuario.getInventario()[i] == null && !agregado) {
				usuario.getInventario()[i] = itemObtenido;
				agregado = true;
			}
		}
		
		Batalla.movimiento = true;
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		b.begin();
			youWinImage.dibujar();
			itemObtenido.dibujar();
			nombreItem.setTexto(itemObtenido.getNombre());
			nombreItem.dibujar();
			btnAceptar.dibujar();
		b.end();
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
		this.dispose();
	}

	
	
}
