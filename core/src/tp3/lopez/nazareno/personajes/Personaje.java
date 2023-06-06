package tp3.lopez.nazareno.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import tp3.lopez.nazareno.ataques.Ataques;
import tp3.lopez.nazareno.utiles.Config;

public abstract class Personaje extends Sprite {
	
	protected float tiempo;
	
	private String clase;
	private int vida;
	private int vidaDefault;
	private int vidaBase;
	private int fuerza;
	private int magia;
	private int magiaDefault;
	private int magiaBase;
	protected Ataques[] ataque;
	
	protected TextureRegion personajeQuieto;
	
	protected Animation<TextureRegion> animationAbajo;
	protected Animation<TextureRegion> animationArriba;
	protected Animation<TextureRegion> animationIzquierda;
	protected Animation<TextureRegion> animationDerecha;
	
	protected TextureRegion[] movimientosAbajo;
	protected TextureRegion[] movimientosArriba;
	protected TextureRegion[] movimientosIzquierda;
	protected TextureRegion[] movimientosDerecha;
	
	protected TextureRegion frameActual;
	protected Texture imagen;
	
	public World world;
	public Body b2body;
	
	public Personaje(String clase, int vida, int fuerza, int magia, World world) {
		
		this.clase = clase;
		this.vida = vida;
		this.fuerza = fuerza;
		this.magia = magia;
		this.vidaDefault = vida;
		this.magiaDefault = magia;
		this.vidaBase = vida;
		this.magiaBase = magia;
		
		this.world = world;
		definePersonaje();
	}

	public void update(float delta) {
		tiempo += Gdx.graphics.getDeltaTime();
		
		getEstado();
	}
	
	public TextureRegion getEstado() {
		if((b2body.getLinearVelocity().x == 0) && (b2body.getLinearVelocity().y == 0)) {
			frameActual = personajeQuieto;
		} else if(b2body.getLinearVelocity().y < 0) {
			frameActual = animationAbajo.getKeyFrame(tiempo, true);
		} else if(b2body.getLinearVelocity().y > 0) {
			frameActual = animationArriba.getKeyFrame(tiempo, true);
		} else if(b2body.getLinearVelocity().x > 0) {
			frameActual = animationDerecha.getKeyFrame(tiempo, true);
		} else if(b2body.getLinearVelocity().x < 0) {
			frameActual = animationIzquierda.getKeyFrame(tiempo, true);
		}
		return frameActual;
	}

	public void definePersonaje() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(800 / Config.PPM, 470 / Config.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(19/2, 49/4);
		fdef.shape = bodyShape;
		b2body.createFixture(fdef).setUserData("personaje");
	}

	//-------funciones------//
	
	public TextureRegion getFrameActual() {
		return frameActual;
	}
	
	public TextureRegion getPjQuieto() {
		return personajeQuieto;
	}
	
	public float getB2bodyX() {
		return b2body.getPosition().x;
	}
	
	public float getB2bodyY() {
		return b2body.getPosition().y;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public int getVida() {
		return vida;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}
	
	public int getMagia() {
		return magia;
	}

	public Ataques getAtaque(int indice) {
		return ataque[indice];
	}
	
	public int getCantAtk() {
		return ataque.length;
	}
	
	public String getClase() {
		return clase;
	}
	public int getFuerza() {
		return fuerza;
	}

	public int getVidaDefault() {
		return vidaDefault;
	}

	public void setVidaDefault(int vidaDefault) {
		this.vidaDefault = vidaDefault;
	}

	public int getMagiaDefault() {
		return magiaDefault;
	}

	public void setMagiaDefault(int magiaDefault) {
		this.magiaDefault = magiaDefault;
	}

	public int getVidaBase() {
		return vidaBase;
	}
	
	public int getMagiaBase() {
		return magiaBase;
	}

}