package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class Piedra extends MiObjeto {

	public Piedra(int coordX, int coordY, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.setnImg(0);
	}
}
