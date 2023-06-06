package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class MagiaNegra extends Ataques {

	public MagiaNegra() {
		super("Magia negra", 100, 150, 400, false, 1.3f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Magia_Negra/magiaNegraAnimation.txt"));
		spriteAttack = atlas.createSprite("magiaNegra");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("magiaNegra1"),
				atlas.createSprite("magiaNegra2"),
				atlas.createSprite("magiaNegra3"),
				atlas.createSprite("magiaNegra4"),
				atlas.createSprite("magiaNegra5"),
				atlas.createSprite("magiaNegra6"),
				atlas.createSprite("magiaNegra7"),
				atlas.createSprite("magiaNegra8"),
				atlas.createSprite("magiaNegra9"),
				atlas.createSprite("magiaNegra10"),
				atlas.createSprite("magiaNegra11"),
				atlas.createSprite("magiaNegra12"),
				atlas.createSprite("magiaNegra13")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
