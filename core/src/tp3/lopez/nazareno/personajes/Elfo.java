package tp3.lopez.nazareno.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import tp3.lopez.nazareno.ataques.Apocalipsis;
import tp3.lopez.nazareno.ataques.Ataques;
import tp3.lopez.nazareno.ataques.Conjuro;
import tp3.lopez.nazareno.ataques.Punietazo;

public final class Elfo extends Personaje {

	public Elfo(World world) {
		super("Elfo", 1500, 8, 150, world); // vida 1750
		
		imagen = new Texture(Gdx.files.internal("personajes/elfo/mov elfo vestimenta comun.png"));
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
		
		ataque = new Ataques[3];
		ataque[0] = new Conjuro();
		ataque[1] = new Apocalipsis();
		ataque[2] = new Punietazo();
		
	}

}
