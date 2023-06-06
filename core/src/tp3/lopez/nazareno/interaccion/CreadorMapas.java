package tp3.lopez.nazareno.interaccion;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import tp3.lopez.nazareno.utiles.Config;

public class CreadorMapas {
	public CreadorMapas(World world, TiledMap map) {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Fixture fixture;
		Body body;
		
		// LIMITES
		for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Config.PPM, (rect.getY() + rect.getHeight() / 2) / Config.PPM);
			body = world.createBody(bdef);
			shape.setAsBox((rect.getWidth() / 2) / Config.PPM, (rect.getHeight() / 2) / Config.PPM);
			fdef.shape = shape;
			fixture = body.createFixture(fdef);
			fixture.setUserData("limites");
		}
		
		// MAZMORRA
		for(MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Config.PPM, (rect.getY() + rect.getHeight() / 2) / Config.PPM);
			body = world.createBody(bdef);
			shape.setAsBox((rect.getWidth() / 2) / Config.PPM, (rect.getHeight() / 2) / Config.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			fixture = body.createFixture(fdef);
			fixture.setUserData("mazmorra");
		}
		
		// MAZMORRA ENTRADA
		for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.KinematicBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Config.PPM, (rect.getY() + rect.getHeight() / 2) / Config.PPM);
			body = world.createBody(bdef);
			shape.setAsBox((rect.getWidth() / 2) / Config.PPM, (rect.getHeight() / 2) / Config.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			fdef.isSensor = true;
			fixture = body.createFixture(fdef);
			fixture.setUserData("mazmorraEntrada");
		}
		
		// COFRE
		for(MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Cofre(world, map, rect);
		}
		
		// ZONA DE PELEA
		for(MapObject object : map.getLayers().get(15).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.KinematicBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Config.PPM, (rect.getY() + rect.getHeight() / 2) / Config.PPM);
			body = world.createBody(bdef);
			shape.setAsBox((rect.getWidth() / 2) / Config.PPM, (rect.getHeight() / 2) / Config.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			fdef.isSensor = true;
			fixture = body.createFixture(fdef);
			fixture.setUserData("zonaPelea");
			
		}
	}
}
