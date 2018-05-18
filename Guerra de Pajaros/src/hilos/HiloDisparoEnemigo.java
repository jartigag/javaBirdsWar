package hilos;

import java.util.ArrayList;
import objetos.DisparoEnemigo;
import practica10.MiPanel;

public class HiloDisparoEnemigo extends Thread {
	private MiPanel mp;
	private ArrayList<DisparoEnemigo> disparosEnemigo = new ArrayList<DisparoEnemigo>();

	public HiloDisparoEnemigo(MiPanel miPanel) {
		super();
		this.mp = miPanel;
	}

	@Override
	public void run(){
		int t = 50; //velocidad de refresco (para avance y giro) del disparo 
		while(true){
			super.run();
			// mover disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				disparoActual.setCoordXDisparoEnemigo(disparoActual.getCoordXDisparoEnemigo() + 25); //velocidad del disparo
				disparoActual.setnImg(disparoActual.getnImg()+1);
				if (disparoActual.getnImg() == 4){
					disparoActual.setnImg(0);
				}
			}
			// borrar disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				if (disparoActual.getCoordXDisparoEnemigo()>1000) {//si posición del disparo fuera de la pantalla
					disparosEnemigo.remove(i);
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

}
