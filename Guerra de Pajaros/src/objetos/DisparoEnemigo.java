package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoEnemigo {
	
	private int coordXDisparoEnemigo;
	private int coordYDisparoEnemigo;
	private ArrayList <Image> imgsDisparoEnemigo;
	private int nImg;

	public DisparoEnemigo(int coordXDisparoEnemigo, int coordYDisparoEnemigo, ArrayList<Image> imgsDisparoEnemigo) {
		super();
		this.coordXDisparoEnemigo = coordXDisparoEnemigo;
		this.coordYDisparoEnemigo = coordYDisparoEnemigo;
		this.imgsDisparoEnemigo = imgsDisparoEnemigo;
		this.nImg = 0;
	}

	public int getCoordXDisparoEnemigo() {
		return coordXDisparoEnemigo;
	}
	public void setCoordXDisparoEnemigo(int coordXDisparoEnemigo) {
		this.coordXDisparoEnemigo = coordXDisparoEnemigo;
	}
	public int getCoordYDisparoEnemigo() {
		return coordYDisparoEnemigo;
	}
	public void setCoordYDisparoEnemigo(int coordYDisparoEnemigo) {
		this.coordYDisparoEnemigo = coordYDisparoEnemigo;
	}
	public ArrayList<Image> getImgsDisparoEnemigo() {
		return imgsDisparoEnemigo;
	}
	public void setImgsDisparoEnemigo(ArrayList<Image> imgsDisparoEnemigo) {
		this.imgsDisparoEnemigo = imgsDisparoEnemigo;
	}
	public int getnImg() {
		return nImg;
	}
	public void setnImg(int nImg) {
		this.nImg = nImg;
	}
}
