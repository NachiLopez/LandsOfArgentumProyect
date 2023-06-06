package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Conjuro extends Ataques {

	public Conjuro() {
		super("Conjuro", 50, 20, 200, false, 1f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Conjuro/conjuroAnimation.txt"));
		spriteAttack = atlas.createSprite("conjuro");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("conjuro1"),
				atlas.createSprite("conjuro2"),
				atlas.createSprite("conjuro3"),
				atlas.createSprite("conjuro4"),
				atlas.createSprite("conjuro5"),
				atlas.createSprite("conjuro6"),
				atlas.createSprite("conjuro7"),
				atlas.createSprite("conjuro8"),
				atlas.createSprite("conjuro9"),
				atlas.createSprite("conjuro10")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
