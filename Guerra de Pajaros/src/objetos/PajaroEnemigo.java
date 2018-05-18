package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroEnemigo extends MiObjeto {

	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int tipoEnemigo;
	private int nImg;

	public PajaroEnemigo(int coordX, int coordY, int tipoEnemigo, ArrayList<Image> imgs) {
		super(coordY, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.tipoEnemigo = tipoEnemigo;
		this.nImg = 0;
	}
}
