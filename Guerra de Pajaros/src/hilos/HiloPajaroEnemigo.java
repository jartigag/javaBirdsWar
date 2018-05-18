package hilos;

import java.awt.Image;
import java.util.ArrayList;

import objetos.PajaroEnemigo;
import objetos.Piedra;
import practica10.MiPanel;

public class HiloPajaroEnemigo extends Thread {
	private MiPanel mp;
	private ArrayList<Image> auxImgsPajaroEnemigo;
	private ArrayList<PajaroEnemigo> pajarosEnemigos = new ArrayList<PajaroEnemigo>();
	
	public HiloPajaroEnemigo(MiPanel miPanel){
		this.mp = miPanel;
	}
	
	@Override
	public void run(){
		int t = 100; //velocidad de refresco de PajaroEnemigo
		int temporizadorPajaroEnemigo = 0;
		while(true){
			super.run();
			temporizadorPajaroEnemigo++;
			if (temporizadorPajaroEnemigo==50) { //cada 50*t = 5000 ms
				//WIP: Además, también de forma aleatoria saldrán pájaros diferentes, de los 3 tipos de pájaros enemigos que tenemos
				// crear pajaroEnemigo nuevo:
				int rndTipo = (int)(Math.random()*3);
//System.out.println("rndTipo: "+rndTipo);
				ArrayList<ArrayList> tipoEnemigo = mp.getAuxImgsEnemigo();
				PajaroEnemigo nuevoPajaroEnemigo = new PajaroEnemigo(1000, (int)(Math.random()*700), tipoEnemigo.get(rndTipo)); //TODO: rndTipo
				this.pajarosEnemigos.add(nuevoPajaroEnemigo);//Desde la parte de la derecha además de las piedras nos irán saliendo pájaros enemigos.
				//Esto saldrán de forma aleatoria en diferentes alturas
				temporizadorPajaroEnemigo= 0;
			}
			// mover pajarosEnemigos:
			for (int i=0; i<pajarosEnemigos.size(); i++) {
				PajaroEnemigo pajaroEnemigoActual = pajarosEnemigos.get(i);
				pajaroEnemigoActual.setCoordXPajaroEnemigo(pajaroEnemigoActual.getCoordXPajaroEnemigo() - 20); //velocidad del PajaroEnemigo
				//Y las piedras irán girando.
				pajaroEnemigoActual.setnImg(pajaroEnemigoActual.getnImg()+1);
				if (pajaroEnemigoActual.getnImg() == 8){
					pajaroEnemigoActual.setnImg(0);
				}
			}
			// borrar pajarosEnemigos:
			for (int i=0; i<pajarosEnemigos.size(); i++) {
				PajaroEnemigo pajaroEnemigoActual = pajarosEnemigos.get(i);
				if (pajaroEnemigoActual.getCoordXPajaroEnemigo()<0) {//si posición de PajaroEnemigo fuera de la pantalla
					mp.setPuntuacion(mp.getPuntuacion()+10);
					pajarosEnemigos.remove(i);
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

	public ArrayList<PajaroEnemigo> getPajarosEnemigos() {
		return pajarosEnemigos;
	}

	public void setPajarosEnemigos(ArrayList<PajaroEnemigo> pajarosEnemigos) {
		this.pajarosEnemigos = pajarosEnemigos;
	}

}
