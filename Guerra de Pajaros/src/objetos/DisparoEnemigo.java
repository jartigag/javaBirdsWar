package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoEnemigo extends MiObjeto {
	
	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int tipoDisparo;
	private int vidaQuita;
	private int nImg;

	public DisparoEnemigo(int coordX, int coordY, int tipoDisparo, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.tipoDisparo = tipoDisparo;
		/*TODO: Si me alcanza un disparo 1, me quita 10 puntos de vida.
		Si me alcanza un disparo 2, me quita 20 puntos de vida.
		Y si me alcanza un disparo 3, me quita 30 puntos de vida.*/
		switch (tipoDisparo) {
			case 1:
				this.vidaQuita = 10;
				break;
			case 2:
				this.vidaQuita = 20;
				break;
			case 3:
				this.vidaQuita = 30;
				break;
		}
		this.nImg = 0;
	}

	public int getVidaQuita() {
		return vidaQuita;
	}
}
