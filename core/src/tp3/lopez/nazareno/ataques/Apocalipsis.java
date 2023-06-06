package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Apocalipsis extends Ataques {

	public Apocalipsis() {
		super("Apocalipsis", 75, 200, 400, false, 1.5f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Apocalipsis/apocalipsisAnimation.txt"));
		spriteAttack = atlas.createSprite("apoca");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("apoca1"),
				atlas.createSprite("apoca2"),
				atlas.createSprite("apoca3"),
				atlas.createSprite("apoca4"),
				atlas.createSprite("apoca5"),
				atlas.createSprite("apoca6"),
				atlas.createSprite("apoca7"),
				atlas.createSprite("apoca8"),
				atlas.createSprite("apoca9"),
				atlas.createSprite("apoca10"),
				atlas.createSprite("apoca11"),
				atlas.createSprite("apoca12"),
				atlas.createSprite("apoca13"),
				atlas.createSprite("apoca14"),
				atlas.createSprite("apoca15"),
				atlas.createSprite("apoca16")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
