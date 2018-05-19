package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroMio extends MiObjeto {

	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int vida;
	private int nImg;

	public PajaroMio(int coordX, int coordY, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.vida = 100;
		this.nImg = 0;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}