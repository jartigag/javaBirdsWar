package hilos;

import practica10.MiPanel;

public class HiloFondo extends Thread {
	private MiPanel mp;
	
	public HiloFondo(MiPanel miPanel){
		this.mp = miPanel;
	}

	@Override
	public void run(){
		while(true){
			super.run();
			mp.setCoordXFondo(mp.getCoordXFondo() - 2); //velocidad del fondo
			mp.repaint();
			
			try {
				Thread.sleep(10); //velocidad de refresco del fondo
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
