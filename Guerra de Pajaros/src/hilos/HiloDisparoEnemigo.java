package hilos;

import java.util.ArrayList;
import objetos.PajaroEnemigo;
import objetos.DisparoEnemigo;
import practica10.MiPanel;

public class HiloDisparoEnemigo extends Thread {
	private MiPanel mp;
	private ArrayList<DisparoEnemigo> disparosEnemigo = new ArrayList<DisparoEnemigo>();

	public HiloDisparoEnemigo(MiPanel miPanel) {
		super();
		this.mp = miPanel;
	}
	
	public void disparar(PajaroEnemigo pe) {
		// los enemigos disparan desde el centro de su cuadro:
		DisparoEnemigo nuevoDisparoEnemigo = new DisparoEnemigo(pe.getCoordX()+pe.getImgs().get(0).getWidth(null),
					pe.getCoordY()+pe.getImgs().get(0).getHeight(null)/2, pe.getTipoEnemigo(),
					mp.getAuxImgsDisparoEnemigo().get(pe.getTipoEnemigo()-1));
		disparosEnemigo.add(nuevoDisparoEnemigo); // crear disparo nuevo
	}

	@Override
	public void run() {
		int t = 50; //velocidad de refresco (para avance y giro) del disparo 
		while(true){
			super.run();
			// mover disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				disparoActual.setCoordX(disparoActual.getCoordX() - 30); //velocidad del disparo
				disparoActual.setnImg(disparoActual.getnImg()+1);
				if (disparoActual.getnImg() == disparosEnemigo.get(i).getImgs().size()){
					disparoActual.setnImg(0);
				}
			}
			// borrar disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				if (disparoActual.getCoordX()<0) {//si posición del disparo fuera de la pantalla
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

	public ArrayList<DisparoEnemigo> getDisparosEnemigo() {
		return disparosEnemigo;
	}

	public void setDisparosEnemigo(ArrayList<DisparoEnemigo> disparosEnemigo) {
		this.disparosEnemigo = disparosEnemigo;
	}
}
