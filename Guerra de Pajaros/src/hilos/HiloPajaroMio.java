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
			int t=100; //velocidad de refresco de PajaroMio
			super.run();
			if (p.getImgsPajaroMio().size()==7) { //si imgsPajaroMio=auxImgsExplosion
				if (p.getnImg()==6) { //si ha acabado la animación de explosión
					if (mp.getVidas()>0) {
						mp.setVidas(mp.getVidas()-1);
					}
					p.setImgsPajaroMio(mp.getAuxImgsPajaroMio());
					p.setCoordXPajaroMio(100); p.setCoordYPajaroMio(350);
					p.setnImg(0);
				}
			}
			//Nuestro pájaro estará moviendo las alas
			if (p.getnImg() == p.getImgsPajaroMio().size()-1){
				p.setnImg(-1);
			}
			p.setnImg(p.getnImg()+1);
			mp.repaint();
			
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}
}
