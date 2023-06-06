package tp3.lopez.nazareno.interaccion;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import tp3.lopez.nazareno.pantallas.Level1;

public class ZonaPelea extends InteraccionTiled {
	
	public ZonaPelea(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);
	}

	@Override
	public void bodyTouch() {
		Level1.pelea = true;
	}
}
