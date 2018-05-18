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

	@Override
	public void run(){
		int t = 20; //velocidad de refresco (para avance y giro) del disparo 
		int temporizadorPajaroEnemigo = 0;
		while(true){
			super.run();
			temporizadorPajaroEnemigo++;
			if (temporizadorPajaroEnemigo==50) { //cada 50*t = 1000 ms
				//WIP: nuevoDisparoEnemigo. cuándo activar disparoEnemigo?
				PajaroEnemigo pe = mp.gethPajaroEnemigo().getPajarosEnemigos().get(0); //TODO: FOR .get(0,1,2)
				DisparoEnemigo nuevoDisparoEnemigo = new DisparoEnemigo(pe.getCoordXPajaroEnemigo()+pe.getImgsPajaroEnemigo().get(0).getWidth(null),
							pe.getCoordYPajaroEnemigo()+pe.getImgsPajaroEnemigo().get(0).getHeight(null)/2,
							mp.getAuxImgsDisparoEnemigo().get(1)); //TODO: FOR .get(0,1,2)
				// los enemigos disparan desde el centro de su cuadro
				// crear disparo nuevo:
				disparosEnemigo.add(nuevoDisparoEnemigo);
			}
			// mover disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				disparoActual.setCoordXDisparoEnemigo(disparoActual.getCoordXDisparoEnemigo() - 10); //velocidad del disparo
				disparoActual.setnImg(disparoActual.getnImg()+1);
				if (disparoActual.getnImg() == disparosEnemigo.get(i).getImgsDisparoEnemigo().size()){
					disparoActual.setnImg(0);
				}
			}
			// borrar disparos:
			for (int i=0; i<disparosEnemigo.size(); i++) {
				DisparoEnemigo disparoActual = disparosEnemigo.get(i);
				if (disparoActual.getCoordXDisparoEnemigo()<0) {//si posición del disparo fuera de la pantalla
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
