package tp3.lopez.nazareno.pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.ui.Boton;
import tp3.lopez.nazareno.ui.BotonAceptar;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.Render;

public class IngresarNombre implements Screen {
	private Imagen ingresarNombre;
	private SpriteBatch b = Render.batch;
	private Entradas entradas = new Entradas();
	
	private Boton botonAceptar;

	private JTextField txfNombre;
	private JFrame frame;
	private JButton boton;
	
	public IngresarNombre(){
		frame = new JFrame();
		frame.setLayout(null);
		frame.setTitle("Nombre del personaje");
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txfNombre = new JTextField();
		txfNombre.setBounds(150, 20, 100, 30);
		frame.add(txfNombre);
		
		boton = new JButton("Establecer nombre");
		boton.setBounds(70, 70, 250, 40);
		boton.addActionListener(new Esp(txfNombre, frame));
		frame.add(boton);
		
		frame.setVisible(true);
		
		botonAceptar = new BotonAceptar(entradas);
	}
	
	@Override
	public void show() {
		ingresarNombre = new Imagen("pantallas/ingreseSuNombre.png");
		
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		
		b.begin();
			ingresarNombre.dibujar();
			botonAceptar.dibujar();
			
		b.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		b.dispose();
		ingresarNombre.dispose();
		botonAceptar.dispose();
	}

}

class Esp implements ActionListener{

	private JTextField txfNombre;
	private JFrame frame;
	public static String nombreDelJugador;
	
	public Esp(JTextField txfNombre, JFrame frame) {
		this.txfNombre = txfNombre;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		nombreDelJugador = txfNombre.getText();
		frame.dispose();
	}
	
}
