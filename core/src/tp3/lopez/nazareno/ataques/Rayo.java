package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Rayo extends Ataques {

	public Rayo() {
		super("Rayo", 50, 50, 350, false, 1.5f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Rayo/rayoAnimation.txt"));
		spriteAttack = atlas.createSprite("rayo");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("rayo_1"),
				atlas.createSprite("rayo_2"),
				atlas.createSprite("rayo_3"),
				atlas.createSprite("rayo_4"),
				atlas.createSprite("rayo_5"),
				atlas.createSprite("rayo_6"),
				atlas.createSprite("rayo_7"),
				atlas.createSprite("rayo_8"),
				atlas.createSprite("rayo_9"),
				atlas.createSprite("rayo_10"),
				atlas.createSprite("rayo_11"),
				atlas.createSprite("rayo_12"),
				atlas.createSprite("rayo_13"),
				atlas.createSprite("rayo_14"),
				atlas.createSprite("rayo_15")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
