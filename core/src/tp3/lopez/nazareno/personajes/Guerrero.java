package tp3.lopez.nazareno.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import tp3.lopez.nazareno.ataques.Ataques;
import tp3.lopez.nazareno.ataques.FuerzaImplacable;
import tp3.lopez.nazareno.ataques.Patada;
import tp3.lopez.nazareno.ataques.Punietazo;
import tp3.lopez.nazareno.ataques.Rayo;
import tp3.lopez.nazareno.ataques.Terremoto;

public final class Guerrero extends Personaje {

	public Guerrero(World world) {
		super("Guerrero", 1000, 14, 200, world);
		
		imagen = new Texture(Gdx.files.internal("personajes/guerrero/mov guerrero vestimenta comun.png"));
		TextureRegion [][] temporal = TextureRegion.split(imagen, imagen.getWidth()/6, imagen.getHeight()/4);
		personajeQuieto = new TextureRegion(imagen, 0, 0, 30, 57);
		
		movimientosAbajo = new TextureRegion[6];
		movimientosArriba = new TextureRegion[6];
		movimientosIzquierda = new TextureRegion[5];
		movimientosDerecha = new TextureRegion[5];
		
		for(int i= 0; i<6; i++) {
			movimientosAbajo[i] = temporal[0][i];
			movimientosArriba[i] = temporal[1][i];
		}
		for(int i= 0; i<5; i++) {
			movimientosIzquierda[i] = temporal[2][i];
			movimientosDerecha[i] = temporal[3][i];
		}
		
		animationAbajo = new Animation<TextureRegion>(1f/10f, movimientosAbajo);
		animationArriba = new Animation<TextureRegion>(1f/10f, movimientosArriba);
		animationIzquierda = new Animation<TextureRegion>(1f/10f, movimientosIzquierda);
		animationDerecha = new Animation<TextureRegion>(1f/10f, movimientosDerecha);
		
		tiempo = 0f;
		
		ataque = new Ataques[5];
		ataque[0] = new Rayo();
		ataque[1] = new Terremoto();
		ataque[2] = new FuerzaImplacable();
		ataque[3] = new Patada();
		ataque[4] = new Punietazo();
	}

}
