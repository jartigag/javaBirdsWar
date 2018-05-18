package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class Piedra extends MiObjeto {
	
	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int nImg;

	public Piedra(int coordX, int coordY, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.nImg = 0;
	}
}
