package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Punietazo extends Ataques {

	public Punietazo() {
		super("Punietazo", 0, 1, 30, true, 1f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Patada_punietazo/sangreAnimation.txt"));
		spriteAttack = atlas.createSprite("sangre");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("1"),
				atlas.createSprite("2"),
				atlas.createSprite("3"),
				atlas.createSprite("4"),
				atlas.createSprite("5")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
