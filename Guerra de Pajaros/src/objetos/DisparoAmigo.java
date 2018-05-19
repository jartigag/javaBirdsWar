package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoAmigo extends MiObjeto {
	
	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int tipoDisparo;
	private int vidaQuita;
	private int nImg;

	public DisparoAmigo(int coordX, int coordY, int tipoDisparo, ArrayList <Image> imgs) {
		super(coordX, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.tipoDisparo = tipoDisparo;
		if (tipoDisparo==2) {
			//Cuando tengo el disparo tipo 2, con 1 solo disparo me basta para matar cualquier tipo de pájaro. 
			this.vidaQuita = 3;
		} else {
			this.vidaQuita = 1;
		}
		this.nImg = 0;
	}

	public int getVidaQuita() {
		return vidaQuita;
	}
}
