package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Bastonazo extends Ataques {

	public Bastonazo() {
		super("Bastonazo", 0, 5, 20, true, 1f);
	
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Bastonazo/bastonazoAnimation.txt"));
		spriteAttack = atlas.createSprite("bastonazo");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("bastonazo_1"),
				atlas.createSprite("bastonazo_2"),
				atlas.createSprite("bastonazo_3"),
				atlas.createSprite("bastonazo_4"),
				atlas.createSprite("bastonazo_5"),
				atlas.createSprite("bastonazo_6"),
				atlas.createSprite("bastonazo_7"),
				atlas.createSprite("bastonazo_8"),
				atlas.createSprite("bastonazo_9"),
				atlas.createSprite("bastonazo_10")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
