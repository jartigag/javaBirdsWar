package hilos;

import objetos.PajaroMio;
import practica10.MiPanel;

public class HiloPajaroMio extends Thread {
	private MiPanel mp;
	
	public HiloPajaroMio(MiPanel miPanel){
		this.mp = miPanel;
	}

	@Override
	public void run(){
		PajaroMio p = mp.getPajaroMio();
		while(true){
			super.run();
			//Nuestro pájaro estará moviendo las alas
			p.setnImg(p.getnImg()+1);
			if (p.getnImg() == p.getImgsPajaroMio().size()){
				p.setnImg(0);
			}
			mp.repaint();
			
			try {
				Thread.sleep(100); //velocidad de refresco de PajaroMio
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
