package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class BombaMagica extends Ataques {

	public BombaMagica() {
		super("Bomba magica", 60, 150, 250, false, 2.5f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Bomba_Magica/bombaMagicaAnimation.txt"));
		spriteAttack = atlas.createSprite("bombaMagica");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("bombaMagica1"),
				atlas.createSprite("bombaMagica2"),
				atlas.createSprite("bombaMagica3"),
				atlas.createSprite("bombaMagica4"),
				atlas.createSprite("bombaMagica5"),
				atlas.createSprite("bombaMagica6"),
				atlas.createSprite("bombaMagica7"),
				atlas.createSprite("bombaMagica8"),
				atlas.createSprite("bombaMagica9"),
				atlas.createSprite("bombaMagica10"),
				atlas.createSprite("bombaMagica11"),
				atlas.createSprite("bombaMagica12"),
				atlas.createSprite("bombaMagica13"),
				atlas.createSprite("bombaMagica14"),
				atlas.createSprite("bombaMagica15"),
				atlas.createSprite("bombaMagica16"),
				atlas.createSprite("bombaMagica17"),
				atlas.createSprite("bombaMagica18"),
				atlas.createSprite("bombaMagica19"),
				atlas.createSprite("bombaMagica20"),
				atlas.createSprite("bombaMagica21"),
				atlas.createSprite("bombaMagica22"),
				atlas.createSprite("bombaMagica23"),
				atlas.createSprite("bombaMagica24"),
				atlas.createSprite("bombaMagica25")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
