package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Terremoto extends Ataques {
	
	public Terremoto() {
		super("Terremoto", 0, 5, 20, true, 1f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Terremoto/terremotoAnimation.txt"));
		spriteAttack = atlas.createSprite("terremoto");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("terremoto_1"),
				atlas.createSprite("terremoto_2"),
				atlas.createSprite("terremoto_3"),
				atlas.createSprite("terremoto_4"),
				atlas.createSprite("terremoto_5"),
				atlas.createSprite("terremoto_6"),
				atlas.createSprite("terremoto_7"),
				atlas.createSprite("terremoto_8"),
				atlas.createSprite("terremoto_9"),
				atlas.createSprite("terremoto_10")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
