package tp3.lopez.nazareno.interaccion;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import tp3.lopez.nazareno.pantallas.Level1;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		System.out.println("Still contact");
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA.getUserData() == "personaje" || fixB.getUserData() == "personaje") {
			Fixture personaje = fixA.getUserData() == "personaje" ? fixA : fixB; // Se obtiene el user data de fixA y si es igual a "personaje", entonces el fixture personaje es fixA (Así sacas cual es el fixture del personaje)
			Fixture objeto = personaje == fixA ? fixB : fixA; // Aca si el fixture del personaje es fixA, entonces el fixB es el objeto, en cambio si el fixA no es el personaje, entonces el fixA es el personaje (Así definimos cual es el fixture del objeto).
			
			if(objeto.getUserData() != null && InteraccionTiled.class.isAssignableFrom(objeto.getUserData().getClass())) { // En la segunda condicion basicamente le retorna true si el userData del objeto es interactivo (con la clase InteraccionTiled)
				((InteraccionTiled) objeto.getUserData()).bodyTouch(); // Entonces si lo es, se ejecuta la funcion de bodyTouch
			}
			
			if(fixA.getUserData() == "zonaPelea" || fixB.getUserData() == "zonaPelea" ) {
				Level1.pelea = true;
			}
			
			if(fixA.getUserData() == "mazmorraEntrada" || fixB.getUserData() == "mazmorraEntrada" ) {
				Level1.entrarMazmorra = true;
			}
		}
		

	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("Se termino");
		Level1.dibujar = false;
		Level1.cofre = false;
		
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA.getUserData() == "personaje" || fixB.getUserData() == "personaje") {
			
			if(fixA.getUserData() == "zonaPelea" || fixB.getUserData() == "zonaPelea" ) {
				Level1.pelea = false;
			}
			
			if(fixA.getUserData() == "mazmorraEntrada" || fixB.getUserData() == "mazmorraEntrada" ) {
				Level1.entrarMazmorra = false;
			}
		}
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
