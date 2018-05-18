package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroEnemigo {

	private int coordXPajaroEnemigo;
	private int coordYPajaroEnemigo;
	private ArrayList <Image> imgsPajaroEnemigo;
	private int tipoEnemigo;
	private int nImg;

	public PajaroEnemigo(int coordXPajaroEnemigo, int coordYPajaroEnemigo, int tipoEnemigo, ArrayList<Image> imgsPajaroEnemigo) {
		super();
		this.coordXPajaroEnemigo = coordXPajaroEnemigo;
		this.coordYPajaroEnemigo = coordYPajaroEnemigo;
		this.imgsPajaroEnemigo = imgsPajaroEnemigo;
		this.tipoEnemigo = tipoEnemigo;
		this.nImg = 0;
	}

	public int getCoordXPajaroEnemigo() {
		return coordXPajaroEnemigo;
	}

	public void setCoordXPajaroEnemigo(int coordXPajaroEnemigo) {
		this.coordXPajaroEnemigo = coordXPajaroEnemigo;
	}

	public int getCoordYPajaroEnemigo() {
		return coordYPajaroEnemigo;
	}

	public void setCoordYPajaroEnemigo(int coordYPajaroEnemigo) {
		this.coordYPajaroEnemigo = coordYPajaroEnemigo;
	}

	public ArrayList<Image> getImgsPajaroEnemigo() {
		return imgsPajaroEnemigo;
	}

	public void setImgsPajaroEnemigo(ArrayList<Image> imgsPajaroEnemigo) {
		this.imgsPajaroEnemigo = imgsPajaroEnemigo;
	}

	public int getTipoEnemigo() {
		return tipoEnemigo;
	}

	public void setTipoEnemigo(int tipoEnemigo) {
		this.tipoEnemigo = tipoEnemigo;
	}

	public int getnImg() {
		return nImg;
	}

	public void setnImg(int nImg) {
		this.nImg = nImg;
	}
}
