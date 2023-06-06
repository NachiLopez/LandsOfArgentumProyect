package tp3.lopez.nazareno.utiles;

import java.util.Random;
import java.util.Scanner;

public class Utiles {
	public static Random r = new Random();
	public static Scanner s = new Scanner(System.in);

	public static void esperar(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	---------A nivel clase-------------
//	Texto test;
//	---------------En el show-------------------
//	test = new Texto("River Adventurer.ttf", 30, Color.WHITE, true);
//	test.setX(10); test.setY(50);
//	---------En el render-------
//	test.setTexto("Coord X: " + entradas.getMouseX() + " Coord Y: " + entradas.getMouseY());
//	test.dibujar();
	
}