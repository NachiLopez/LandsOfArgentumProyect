package tp3.lopez.nazareno.utiles;

public final class NombresRandom {

	public static String[] nombresParaCpu = {"Mark", "Shake", "Palbo", "Nahic", "Rakitic", "Gordon"};

	public static String getNombreRandom() {
		int indice = Utiles.r.nextInt(nombresParaCpu.length);
		return nombresParaCpu[indice];
	}
	
	
}
