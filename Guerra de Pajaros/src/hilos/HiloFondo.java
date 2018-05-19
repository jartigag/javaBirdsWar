package hilos;

import practica10.MiPanel;

public class HiloFondo extends Thread {
	private MiPanel mp;
	
	public HiloFondo(MiPanel mp){
		this.mp = mp;
	}

	@Override
	public void run(){
		while(true){
			int t = 10; //velocidad de refresco del fondo
			super.run();
			mp.setCoordXFondo(mp.getCoordXFondo() - 2); //velocidad del fondo
			mp.repaint();
			
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
