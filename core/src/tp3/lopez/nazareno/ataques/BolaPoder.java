package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class BolaPoder extends Ataques {

	public BolaPoder() {
		super("Bola de poder", 50, 50, 200, false, 0.8f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Bola_Poder/bolaPoderAnimation.txt"));
		spriteAttack = atlas.createSprite("bolaPoder");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("102"),
				atlas.createSprite("103"),
				atlas.createSprite("104"),
				atlas.createSprite("105"),
				atlas.createSprite("106"),
				atlas.createSprite("107"),
				atlas.createSprite("113"),
				atlas.createSprite("114")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
