package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroEnemigo extends MiObjeto {

	private int coordX;
	private int coordY;
	private ArrayList <Image> imgs;
	private int tipoEnemigo;
	private int temporizadorDisparo;
	private int vida;
	private int nImg;

	public PajaroEnemigo(int coordX, int coordY, int tipoEnemigo, ArrayList<Image> imgs) {
		super(coordX, coordY, imgs);
		this.coordX = coordX;
		this.coordY = coordY;
		this.imgs = imgs;
		this.tipoEnemigo = tipoEnemigo;
		this.temporizadorDisparo = 0;
		/*Cuando yo tengo el disparo tipo 1, para matar el pájaro 1, sólo le tengo que dar una vez.
		Pero si es el pájaro 2, le tengo que dar 2 veces.
		Y si es el pájaro 3, le tengo que dar tres veces. Las piedras también se destruyen con 1 solo disparo.*/
		this.vida = tipoEnemigo;
		this.nImg = 0;
	}

	public int getTipoEnemigo() {
		return tipoEnemigo;
	}

	public void setTipoEnemigo(int tipoEnemigo) {
		this.tipoEnemigo = tipoEnemigo;
	}

	public int getTemporizadorDisparo() {
		return temporizadorDisparo;
	}

	public void setTemporizadorDisparo(int temporizadorDisparo) {
		this.temporizadorDisparo = temporizadorDisparo;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
