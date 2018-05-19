package hilos;

import java.util.ArrayList;
import objetos.DisparoAmigo;
import practica10.MiPanel;

public class HiloDisparoAmigo extends Thread {
	private MiPanel mp;
	private ArrayList<DisparoAmigo> disparosAmigo = new ArrayList<DisparoAmigo>();

	public HiloDisparoAmigo(MiPanel miPanel) {
		super();
		this.mp = miPanel;
	}

	@Override
	public void run(){
		int t = 50; //velocidad de refresco (para avance y giro) del disparo 
		while(true){
			super.run();
			// mover disparos:
			for (int i=0; i<disparosAmigo.size(); i++) {
				DisparoAmigo disparoActual = disparosAmigo.get(i);
				disparoActual.setCoordX(disparoActual.getCoordX() + 25); //velocidad del disparo
				disparoActual.setnImg(disparoActual.getnImg()+1);
				if (disparoActual.getnImg() == disparosAmigo.get(i).getImgs().size()){
					disparoActual.setnImg(0);
				}
			}
			// borrar disparos:
			for (int i=0; i<disparosAmigo.size(); i++) {
				DisparoAmigo disparoActual = disparosAmigo.get(i);
				if (disparoActual.getCoordX()>1000) {//si posición del disparo fuera de la pantalla
					disparosAmigo.remove(i);
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

	public ArrayList<DisparoAmigo> getDisparosAmigo() {
		return disparosAmigo;
	}

	public void setDisparosAmigo1(ArrayList<DisparoAmigo> disparosAmigo) {
		this.disparosAmigo = disparosAmigo;
	}
}
