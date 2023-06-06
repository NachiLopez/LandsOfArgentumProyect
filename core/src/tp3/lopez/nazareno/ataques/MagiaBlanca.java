package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class MagiaBlanca extends Ataques {

	public MagiaBlanca() {
		super("Magia blanca", 75, -1, 0, false, 1.2f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Magia_Blanca/magiaBlancaAnimation.txt"));
		spriteAttack = atlas.createSprite("magiaBlanca");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("magiaBlanca1"),
				atlas.createSprite("magiaBlanca2"),
				atlas.createSprite("magiaBlanca3"),
				atlas.createSprite("magiaBlanca4"),
				atlas.createSprite("magiaBlanca5"),
				atlas.createSprite("magiaBlanca6"),
				atlas.createSprite("magiaBlanca7"),
				atlas.createSprite("magiaBlanca8"),
				atlas.createSprite("magiaBlanca9"),
				atlas.createSprite("magiaBlanca10"),
				atlas.createSprite("magiaBlanca11"),
				atlas.createSprite("magiaBlanca12")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
