package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class PajaroMio {

	private int coordXPajaroMio;
	private int coordYPajaroMio;
	private ArrayList <Image> imgsPajaroMio;
	private int nImg;

	public PajaroMio(int coordXPajaroMio, int coordYPajaroMio, ArrayList<Image> imgsPajaroMio) {
		super();
		this.coordXPajaroMio = coordXPajaroMio;
		this.coordYPajaroMio = coordYPajaroMio;
		this.imgsPajaroMio = imgsPajaroMio;
		this.nImg = 0;
	}
	public int getCoordXPajaroMio() {
		return coordXPajaroMio;
	}
	public void setCoordXPajaroMio(int coordXPajaroMio) {
		this.coordXPajaroMio = coordXPajaroMio;
	}
	public int getCoordYPajaroMio() {
		return coordYPajaroMio;
	}
	public void setCoordYPajaroMio(int coordYPajaroMio) {
		this.coordYPajaroMio = coordYPajaroMio;
	}
	public ArrayList<Image> getImgsPajaroMio() {
		return imgsPajaroMio;
	}
	public void setImgsPajaroMio(ArrayList<Image> imgsPajaroMio) {
		this.imgsPajaroMio = imgsPajaroMio;
	}
	public int getnImg() {
		return nImg;
	}
	public void setnImg(int nImg) {
		this.nImg = nImg;
	}
}