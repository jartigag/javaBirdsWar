package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoAmigo extends MiObjeto {
	
	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int nImg;

	public DisparoAmigo(int coordX, int coordY,	ArrayList <Image> imgs) {
		super(coordY, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.nImg = 0;
	}
}
