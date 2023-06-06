package tp3.lopez.nazareno.inputoutput;

import com.badlogic.gdx.Input.Keys;

import tp3.lopez.nazareno.utiles.Config;

import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean abajo=false, arriba=false, enter = false, click=false, izquierda=false, derecha=false, A=false, E=false, T=false;
	private int mouseX=0, mouseY=0;
	
	public void reset() {
		 abajo=false; arriba=false; izquierda=false; derecha=false; E=false; T=false;
	}
	
	public boolean isAbajo() {
		return abajo;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public boolean isArriba() {
		return arriba;
	}
	
	public boolean isEnter() {
		return enter;
	}
	
	public boolean isA() {
		return A;
	}
	
	public boolean isE() {
		return E;
	}
	
	public boolean isT() {
		return T;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.DOWN) {
			abajo = true;
		}
		if(keycode == Keys.UP) {
			arriba = true;
		}
		if(keycode == Keys.ENTER) {
			enter = true;
		}
		if(keycode == Keys.RIGHT) {
			derecha = true;
		}
		if(keycode == Keys.LEFT) {
			izquierda = true;
		}
		if(keycode == Keys.A) {
			A = true;
		}
		if(keycode == Keys.E) {
			E = true;
		}
		if(keycode == Keys.T) {
			T = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.DOWN) {
			abajo = false;
		}
		if(keycode == Keys.UP) {
			arriba = false;
		}
		if(keycode == Keys.ENTER) {
			enter = false;
		}
		if(keycode == Keys.RIGHT) {
			derecha = false;
		}
		if(keycode == Keys.LEFT) {
			izquierda = false;
		}
		if(keycode == Keys.A) {
			A = false;
		}
		if(keycode == Keys.E) {
			E = false;
		}
		if(keycode == Keys.T) {
			T = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Config.ALTO - screenY;
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		
		return false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public boolean isClick() {
		return click;
	}

}
