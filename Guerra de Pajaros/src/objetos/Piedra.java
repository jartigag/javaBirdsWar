package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class Piedra {
	
	private int coordXPiedra;
	private int coordYPiedra;
	private ArrayList <Image> imgsPiedra;
	private int nImg;

	public Piedra(int coordXPiedra, int coordYPiedra, ArrayList<Image> imgsPiedra) {
		super();
		this.coordXPiedra = coordXPiedra;
		this.coordYPiedra = coordYPiedra;
		this.imgsPiedra = imgsPiedra;
		this.nImg = 0;
	}

	public int getCoordXPiedra() {
		return coordXPiedra;
	}
	public void setCoordXPiedra(int coordXPiedra) {
		this.coordXPiedra = coordXPiedra;
	}
	public int getCoordYPiedra() {
		return coordYPiedra;
	}
	public void setCoordYPiedra(int coordYPiedra) {
		this.coordYPiedra = coordYPiedra;
	}
	public ArrayList<Image> getImgsPiedra() {
		return imgsPiedra;
	}
	public void setImgsPiedra(ArrayList<Image> imgsPiedra) {
		this.imgsPiedra = imgsPiedra;
	}
	public int getnImg() {
		return nImg;
	}
	public void setnImg(int nImg) {
		this.nImg = nImg;
	}
}
