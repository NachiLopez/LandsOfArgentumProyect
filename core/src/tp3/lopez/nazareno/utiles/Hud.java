package tp3.lopez.nazareno.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tp3.lopez.nazareno.jugadores.Jugador;

public class Hud implements Disposable {

	public Stage stage;
	private Viewport viewport;
	private Label nombreLabel;
	private Label tipoPersonajeLabel;
	protected World world = new World(new Vector2(0, -10), true);;
	private Imagen display = new Imagen("display/hud.png");
	Jugador usuario;
	
	public Hud(SpriteBatch b, Jugador usuario) {
		this.usuario = usuario;
		
		Image overlay = new Image(display.getTexture());
		viewport = new FitViewport(Config.ANCHO, Config.ALTO, new OrthographicCamera());
		stage = new Stage(viewport, b);

		Table table = new Table();
		table.top();
		table.setFillParent(true);
		nombreLabel = new Label(usuario.getNombre(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		nombreLabel.setFontScaleX(2);
		nombreLabel.setFontScaleY(2);
		tipoPersonajeLabel = new Label(usuario.getPersonaje().getClase(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(nombreLabel).expandX().padTop(35).padLeft(1000);
		table.row();
		table.add(tipoPersonajeLabel).padLeft(1000);
		table.row();
		
		stage.addActor(overlay);
		stage.addActor(table);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
