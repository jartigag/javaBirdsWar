package hilos;

import java.awt.Image;
import java.util.ArrayList;

import objetos.DisparoAmigo;
import objetos.Piedra;
import practica10.MiPanel;

public class HiloPiedras extends Thread {
	private MiPanel mp;
	private ArrayList<Image> auxImgsPiedra;
	private ArrayList<Piedra> piedras = new ArrayList<Piedra>();

	public HiloPiedras(MiPanel miPanel, ArrayList<Image> auxImgsPiedra) {
		super();
		this.mp = miPanel;
	}
	
	@Override
	public void run(){
		int t = 50; //velocidad de refresco (para avance y giro) de la piedra
		int temporizadorPiedras = 0;
		while(true){
			super.run();
			temporizadorPiedras++;
			if (temporizadorPiedras==20) { //cada 20*t = 2000 ms
				// crear piedra nueva:
				Piedra nuevaPiedra = new Piedra(1000, (int)(Math.random()*700), mp.getAuxImgsPiedra());
				this.piedras.add(nuevaPiedra); //Estas piedras aparecerán de forma aleatoria por la parte derecha de la pantalla.;
				temporizadorPiedras= 0;
			}
			// mover piedras:
			for (int i=0; i<piedras.size(); i++) {
				Piedra piedraActual = piedras.get(i);
				piedraActual.setCoordXPiedra(piedraActual.getCoordXPiedra() - 20); //velocidad de la piedra
				//Y las piedras irán girando.
				piedraActual.setnImg(piedraActual.getnImg()+1);
				if (piedraActual.getnImg() == 8){
					piedraActual.setnImg(0);
				}
			}
			// borrar piedras:
			for (int i=0; i<piedras.size(); i++) {
				Piedra piedraActual = piedras.get(i);
				if (piedraActual.getCoordXPiedra()<0) {//si posición de piedra fuera de la pantalla
					mp.setPuntuacion(mp.getPuntuacion()+10);
					piedras.remove(i);
				}
			}

			mp.repaint();

			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public ArrayList<Piedra> getPiedras() {
		return piedras;
	}
	public void setPiedras(ArrayList<Piedra> piedras) {
		this.piedras = piedras;
	}

}
