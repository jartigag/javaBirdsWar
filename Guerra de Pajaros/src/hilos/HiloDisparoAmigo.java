package hilos;

import objetos.DisparoAmigo;
import practica10.MiPanel;

public class HiloDisparoAmigo extends Thread {
	private MiPanel mp;

	public HiloDisparoAmigo(MiPanel miPanel) {
		this.mp = miPanel;
	}

	@Override
	public void run(){
		DisparoAmigo d = mp.getDisparoAmigo1();
		while(true){
			super.run();
			d.setCoordXDisparoAmigo(d.getCoordXDisparoAmigo() + 5); //velocidad del disparo
			d.setnImg(d.getnImg()+1);
			if (d.getnImg() == 4){
				d.setnImg(0);
			}
			mp.repaint();
			
			try {
				Thread.sleep(100); //velocidad de refresco del disparo
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
