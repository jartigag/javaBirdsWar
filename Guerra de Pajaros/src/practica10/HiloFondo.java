package practica10;

import java.awt.Image;

public class HiloFondo extends Thread {
	private MiPanel mp;
	private Image fondo;
	
	public HiloFondo(MiPanel mp, Image fondo){
		this.mp = mp;
		fondo = mp.fondo;
	}

	@Override
	public void run(){
		while(true){
			super.run();
			mp.coordXFondo+=2; //velocidad del fondo
			mp.repaint();
			
			try {
				Thread.sleep(10); //velocidad de refresco del fondo
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
