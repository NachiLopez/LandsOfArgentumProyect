package tp3.lopez.nazareno;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.pantallas.EntryScreen;
import tp3.lopez.nazareno.utiles.Render;

public class Principal extends Game {
	
	public Screen pantallaDesert;
	public Screen pantallaMazmorra;
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new EntryScreen());
	}

	@Override
	public void render () {
		super.render();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		Render.batch.dispose();
		
	}
}
