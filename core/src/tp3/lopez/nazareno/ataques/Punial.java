package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Punial extends Ataques {
	
	public Punial() {
		super("Punial", 0, 5, 20, true, 1.3f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Punial/punialAnimation.txt"));
		spriteAttack = atlas.createSprite("punial");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("punial1"),
				atlas.createSprite("punial2"),
				atlas.createSprite("punial3"),
				atlas.createSprite("punial4"),
				atlas.createSprite("punial5"),
				atlas.createSprite("punial6"),
				atlas.createSprite("punial7"),
				atlas.createSprite("punial8"),
				atlas.createSprite("punial9"),
				atlas.createSprite("punial10"),
				atlas.createSprite("punial11"),
				atlas.createSprite("punial12"),
				atlas.createSprite("punial13")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
