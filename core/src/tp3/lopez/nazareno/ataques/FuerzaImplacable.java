package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class FuerzaImplacable extends Ataques {

	public FuerzaImplacable() {
		super("Fuerza implacable", 0, 1, 40, true, 1.5f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Fuerza_Implacable/fuerzaImplacableAnimation.txt"));
		spriteAttack = atlas.createSprite("fuerzaImplacable");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("fuerzaImplacable1"),
				atlas.createSprite("fuerzaImplacable2"),
				atlas.createSprite("fuerzaImplacable3"),
				atlas.createSprite("fuerzaImplacable4"),
				atlas.createSprite("fuerzaImplacable5"),
				atlas.createSprite("fuerzaImplacable6"),
				atlas.createSprite("fuerzaImplacable7"),
				atlas.createSprite("fuerzaImplacable8"),
				atlas.createSprite("fuerzaImplacable9"),
				atlas.createSprite("fuerzaImplacable10"),
				atlas.createSprite("fuerzaImplacable11"),
				atlas.createSprite("fuerzaImplacable12"),
				atlas.createSprite("fuerzaImplacable13"),
				atlas.createSprite("fuerzaImplacable14"),
				atlas.createSprite("fuerzaImplacable15")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
