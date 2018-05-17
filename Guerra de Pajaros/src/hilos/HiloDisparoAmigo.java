package hilos;

import java.awt.Image;
import java.util.ArrayList;

import objetos.DisparoAmigo;
import objetos.PajaroMio;
import practica10.MiPanel;

public class HiloDisparoAmigo extends Thread {
	private MiPanel mp;
	private ArrayList<Image> auxImgsDisparoAmigo1;
	private ArrayList<DisparoAmigo> disparosAmigo1 = new ArrayList<DisparoAmigo>();

	public HiloDisparoAmigo(MiPanel miPanel, ArrayList<Image> auxImgsDisparoAmigo1) {
		super();
		this.mp = miPanel;
	}

	@Override
	public void run(){
		int t = 50; //velocidad de refresco (para avance y giro) del disparo 
		while(true){
			super.run();
			// mover disparos:
			for (int i=0; i<disparosAmigo1.size(); i++) {
				DisparoAmigo disparoActual = disparosAmigo1.get(i);
				disparoActual.setCoordXDisparoAmigo(disparoActual.getCoordXDisparoAmigo() + 25); //velocidad del disparo
				disparoActual.setnImg(disparoActual.getnImg()+1);
				if (disparoActual.getnImg() == 4){
					disparoActual.setnImg(0);
				}
			}
			// borrar disparos:
			for (int i=0; i<disparosAmigo1.size(); i++) {
				DisparoAmigo disparoActual = disparosAmigo1.get(i);
				if (disparoActual.getCoordXDisparoAmigo()>1000) {//si posición del disparo fuera de la pantalla
					disparosAmigo1.remove(i);
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

	public ArrayList<DisparoAmigo> getDisparosAmigo1() {
		return disparosAmigo1;
	}

	public void setDisparosAmigo1(ArrayList<DisparoAmigo> disparosAmigo1) {
		this.disparosAmigo1 = disparosAmigo1;
	}

}
