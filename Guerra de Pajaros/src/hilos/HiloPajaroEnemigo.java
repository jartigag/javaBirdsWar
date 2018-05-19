package hilos;

import java.util.ArrayList;
import objetos.PajaroEnemigo;
import practica10.MiPanel;

public class HiloPajaroEnemigo extends Thread {
	private MiPanel mp;
	private ArrayList<PajaroEnemigo> pajarosEnemigos = new ArrayList<PajaroEnemigo>();
	
	public HiloPajaroEnemigo(MiPanel miPanel){
		this.mp = miPanel;
	}
	
	@Override
	public void run(){
		int t = 50; //velocidad de refresco de PajaroEnemigo
		int temporizadorPajaroEnemigo = 0;
		while(true){
			super.run();
			temporizadorPajaroEnemigo++;
			if (temporizadorPajaroEnemigo==100) { //cada 100*t = 5000 ms
				//Además, también de forma aleatoria saldrán pájaros diferentes, de los 3 tipos de pájaros enemigos que tenemos
				// crear pajaroEnemigo nuevo:
				int rndTipo = (int)(Math.random()*3+1);
				ArrayList<ArrayList> tipoEnemigo = mp.getAuxImgsEnemigo();
				PajaroEnemigo nuevoPajaroEnemigo = new PajaroEnemigo(800, (int)(Math.random()*700), rndTipo, tipoEnemigo.get(rndTipo-1));
				this.pajarosEnemigos.add(nuevoPajaroEnemigo);//Desde la parte de la derecha además de las piedras nos irán saliendo pájaros enemigos.
				//Estos saldrán de forma aleatoria en diferentes alturas
				if (mp.gethDisparoEnemigo().isAlive()==false) { // Si no se ha iniciado hDisparoEnemigo
					mp.gethDisparoEnemigo().start();
				}
				temporizadorPajaroEnemigo= 0;
			}
			// mover pájaros:
			for (int i=0; i<pajarosEnemigos.size(); i++) {
				PajaroEnemigo pajaroEnemigoActual = pajarosEnemigos.get(i);
				
				// mover pajaroEnemigoActual:
				if (pajaroEnemigoActual.getImgs().size()==7) { //si imgsPajaroEnemigo=auxImgsExplosion
					pajaroEnemigoActual.setnImg(pajaroEnemigoActual.getnImg()+1);
					if (pajaroEnemigoActual.getnImg()==6) { //si ha acabado la animación de explosión,
						pajarosEnemigos.remove(pajarosEnemigos.indexOf(pajaroEnemigoActual)); //borrar pajaroEnemigoActual
					}
				} else { //si no está explotando
					pajaroEnemigoActual.setnImg(pajaroEnemigoActual.getnImg()+1);
					if (pajaroEnemigoActual.getnImg() == 8) {
						pajaroEnemigoActual.setnImg(0);
					}
					// pajaroEnemigoActual dispara:
					if (pajaroEnemigoActual.getTemporizadorDisparo()==20) {
						mp.gethDisparoEnemigo().disparar(pajaroEnemigoActual); //cada pajaroEnemigo dispara cada 20*t = 1000ms
						pajaroEnemigoActual.setTemporizadorDisparo(0);
					} else {
						pajaroEnemigoActual.setTemporizadorDisparo(pajaroEnemigoActual.getTemporizadorDisparo()+1);
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

	public ArrayList<PajaroEnemigo> getPajarosEnemigos() {
		return pajarosEnemigos;
	}

	public void setPajarosEnemigos(ArrayList<PajaroEnemigo> pajarosEnemigos) {
		this.pajarosEnemigos = pajarosEnemigos;
	}
}
