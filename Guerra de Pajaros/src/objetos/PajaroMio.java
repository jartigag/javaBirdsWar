package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroMio extends MiObjeto {

	private int vida;

	public PajaroMio(int coordX, int coordY, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.vida = 100;
		this.setnImg(0);
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}