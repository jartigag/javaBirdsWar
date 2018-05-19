package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoAmigo extends MiObjeto {

	private int vidaQuita;

	public DisparoAmigo(int coordX, int coordY, int tipoDisparo, ArrayList <Image> imgs) {
		super(coordX, coordY, imgs);
		if (tipoDisparo==2) {
			//Cuando tengo el disparo tipo 2, con 1 solo disparo me basta para matar cualquier tipo de pájaro. 
			this.vidaQuita = 3;
		} else {
			this.vidaQuita = 1;
		}
		this.setnImg(0);
	}

	public int getVidaQuita() {
		return vidaQuita;
	}
}
