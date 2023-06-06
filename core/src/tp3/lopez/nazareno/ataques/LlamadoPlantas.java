package tp3.lopez.nazareno.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class LlamadoPlantas extends Ataques {

	public LlamadoPlantas() {
		super("llamado a plantas", 75, 200, 400, false, 1.4f);
		
		atlas = new TextureAtlas(Gdx.files.internal("ataques/Llamado_Plantas/llamadoPlantasAnimation.txt"));
		spriteAttack = atlas.createSprite("llamadoPlantas");
		animationAttack = new Animation<Sprite>(
				(1f/10f),
				atlas.createSprite("llamadoPlantas1"),
				atlas.createSprite("llamadoPlantas2"),
				atlas.createSprite("llamadoPlantas3"),
				atlas.createSprite("llamadoPlantas4"),
				atlas.createSprite("llamadoPlantas5"),
				atlas.createSprite("llamadoPlantas6"),
				atlas.createSprite("llamadoPlantas7"),
				atlas.createSprite("llamadoPlantas8"),
				atlas.createSprite("llamadoPlantas9"),
				atlas.createSprite("llamadoPlantas10"),
				atlas.createSprite("llamadoPlantas11"),
				atlas.createSprite("llamadoPlantas12"),
				atlas.createSprite("llamadoPlantas13"),
				atlas.createSprite("llamadoPlantas14")
		);
		
		if(stateTime>getTimeAnimation()) {
			stateTime = 0;
		}
	}

}
