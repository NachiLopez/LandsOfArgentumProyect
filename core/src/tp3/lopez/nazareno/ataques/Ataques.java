package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Ataques {
	
	protected String nombre;
	protected int dañoMin;
	protected int dañoMax;
	protected int magia;
	protected boolean fisico;
	protected float x;
	protected float y;
	private float timeAnimation;
	
	protected Sprite spriteAttack;
	protected Sprite keyFrame;
	
	protected Animation<Sprite> animationAttack;
	
	protected TextureAtlas atlas;
	
	protected float stateTime;
	
	public Ataques(String nombre, int magia, int dañoMin, int dañoMax, boolean fisico, float timeAnimation) {
		this.nombre = nombre;
		this.dañoMin = dañoMin;
		this.dañoMax = dañoMax;
		this.magia = magia;
		this.fisico = fisico;
		this.timeAnimation = timeAnimation;
	}
	
	// PARA CREAR ANIMACIONES TENGO Q USAR EL TEXTURE PACKER, PONER LAS IMAGENES UNA POR UNA Y PROCESARLO, AL ARCHIVO TXT 
	// QUE SURGA DE AHI LES TENGO QUE AGREGAR LOS NUMEROS, EJ: "rayo" a "rayo_1", "rayo_2", "rayo_3", etc.
	
	public void dibujar(SpriteBatch b) {
		stateTime += Gdx.graphics.getDeltaTime();
		keyFrame = animationAttack.getKeyFrame(stateTime, true);
		b.draw(keyFrame, x, y);
	}

	public String getNombre() {
		return nombre;
	}

	public int getDañoMin() {
		return dañoMin;
	}

	public int getDañoMax() {
		return dañoMax;
	}

	public int getMagia() {
		return magia;
	}

	public boolean isFisico() {
		return fisico;
	}
	
	public float getTimeAnimation() {
		return timeAnimation;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void dispose() {
		atlas.dispose();
	}
	
}
