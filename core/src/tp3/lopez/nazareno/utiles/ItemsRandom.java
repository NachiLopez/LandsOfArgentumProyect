package tp3.lopez.nazareno.utiles;

import tp3.lopez.nazareno.items.ArmaduraElfo;
import tp3.lopez.nazareno.items.ArmaduraGuerrero;
import tp3.lopez.nazareno.items.Barsagrado;
import tp3.lopez.nazareno.items.BastonElfico;
import tp3.lopez.nazareno.items.CarmesiPotala;
import tp3.lopez.nazareno.items.EspadaGhost;
import tp3.lopez.nazareno.items.EspadaSartial;
import tp3.lopez.nazareno.items.FuerzaGratificante;
import tp3.lopez.nazareno.items.Item;
import tp3.lopez.nazareno.items.LanzaLonga;
import tp3.lopez.nazareno.items.LaudMagico;
import tp3.lopez.nazareno.items.MallaBardo;
import tp3.lopez.nazareno.items.PocionDeMagia;
import tp3.lopez.nazareno.items.PocionDeVida;
import tp3.lopez.nazareno.items.SantoGrial;
import tp3.lopez.nazareno.items.TunicaMago;

public final class ItemsRandom {

	public static int[] numItem = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
	
	public static Item getItemRandom() {
		int indice = Utiles.r.nextInt(numItem.length);
		Item vestimenta = null;
		if(indice == 0) {
			vestimenta = new ArmaduraElfo();
		} else if(indice == 1) {
			vestimenta = new ArmaduraGuerrero();
		} else if(indice == 2) {
			vestimenta = new Barsagrado();
		} else if(indice == 3) {
			vestimenta = new BastonElfico();
		} else if(indice == 4) {
			vestimenta = new CarmesiPotala();
		} else if(indice == 5) {
			vestimenta = new EspadaGhost();
		} else if(indice == 6) {
			vestimenta = new EspadaSartial();
		} else if(indice == 7) {
			vestimenta = new FuerzaGratificante();
		} else if(indice == 8) {
			vestimenta = new LanzaLonga();
		} else if(indice == 9) {
			vestimenta = new LaudMagico();
		} else if(indice == 10) {
			vestimenta = new MallaBardo();
		} else if(indice == 11) {
			vestimenta = new PocionDeMagia();
		} else if(indice == 12) {
			vestimenta = new PocionDeVida();
		} else if(indice == 13) {
			vestimenta = new SantoGrial();
		} else if(indice == 14) {
			vestimenta = new TunicaMago();
		} 
		return vestimenta;
	}
	
	
}
