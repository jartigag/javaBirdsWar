package objetos;

import java.awt.Image;
import java.util.ArrayList;

public class DisparoAmigo {
	
	private int coordXDisparoAmigo;
	private int coordYDisparoAmigo;
	private ArrayList <Image> imgsDisparoAmigo;
	private int nImg;

	public DisparoAmigo(int coordXDisparoAmigo, int coordYDisparoAmigo,	ArrayList <Image> imgsDisparoAmigo) {
		super();
		this.coordXDisparoAmigo = coordXDisparoAmigo;
		this.coordYDisparoAmigo = coordYDisparoAmigo;
		this.imgsDisparoAmigo = imgsDisparoAmigo;
		this.nImg = 0;
	}

	public int getCoordXDisparoAmigo() {
		return coordXDisparoAmigo;
	}
	public void setCoordXDisparoAmigo(int coordXDisparoAmigo) {
		this.coordXDisparoAmigo = coordXDisparoAmigo;
	}
	public int getCoordYDisparoAmigo() {
		return coordYDisparoAmigo;
	}
	public void setCoordYDisparoAmigo(int coordYDisparoAmigo) {
		this.coordYDisparoAmigo = coordYDisparoAmigo;
	}
	public ArrayList<Image> getImgsDisparoAmigo() {
		return imgsDisparoAmigo;
	}
	public void setImgsDisparoAmigo(ArrayList<Image> imgsDisparoAmigo) {
		this.imgsDisparoAmigo = imgsDisparoAmigo;
	}
	public int getnImg() {
		return nImg;
	}
	public void setnImg(int nImg) {
		this.nImg = nImg;
	}
}
