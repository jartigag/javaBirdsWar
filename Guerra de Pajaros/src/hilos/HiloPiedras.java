package hilos;

import java.util.ArrayList;
import objetos.Piedra;
import practica10.MiPanel;

public class HiloPiedras extends Thread {
	private MiPanel mp;
	private ArrayList<Piedra> piedras = new ArrayList<Piedra>();

	public HiloPiedras(MiPanel miPanel) {
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
			if (temporizadorPiedras==20) { //cada 20*t = 1000 ms
				// crear piedra nueva:
				Piedra nuevaPiedra = new Piedra(1000, (int)(Math.random()*700), mp.getAuxImgsPiedra());
				this.piedras.add(nuevaPiedra); //Estas piedras aparecerán de forma aleatoria por la parte derecha de la pantalla.;
				temporizadorPiedras= 0;
			}
			// mover piedras:
			for (int i=0; i<piedras.size(); i++) {
				Piedra piedraActual = piedras.get(i);
				piedraActual.setCoordX(piedraActual.getCoordX() - 20); //velocidad de la piedra
				if (piedraActual.getImgs().size()==7) { //si imgsPiedra=auxImgsExplosion
					piedraActual.setnImg(piedraActual.getnImg()+1);
					if (piedraActual.getnImg()==6) { //si ha acabado la animación de explosión,
						piedras.remove(piedras.indexOf(piedraActual)); //borrar piedraActual
					}
				} else { //si no está explotando
					//Las piedras irán girando.
					piedraActual.setnImg(piedraActual.getnImg()+1);
					if (piedraActual.getnImg() == 8) {
						piedraActual.setnImg(0);
					}
					if (piedraActual.getCoordX()<0) {//si posición de piedra fuera de la pantalla
						piedras.remove(i); //borrar piedraActual
					}
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
