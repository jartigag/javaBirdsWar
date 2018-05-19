package hilos;

import objetos.PajaroMio;
import practica10.MiPanel;

public class HiloPajaroMio extends Thread {
	private MiPanel mp;
	
	public HiloPajaroMio(MiPanel mp){
		this.mp = mp;
		mp.setPajaroMio(new PajaroMio(0, 350, mp.getAuxImgsPajaroMio())); //crear pajaroMio
	}

	@Override
	public void run(){
		PajaroMio p = mp.getPajaroMio();
		while(true){
			int t=100; //velocidad de refresco de PajaroMio
			super.run();
			if (p.getImgs().size()==7) { //si imgsPajaroMio=auxImgsExplosion
				if (p.getnImg()==6) { //si ha acabado la animación de explosión,
					//reiniciar posición PajaroMio
					p.setImgs(mp.getAuxImgsPajaroMio());
					p.setCoordX(100); p.setCoordY(350);
					p.setnImg(0);
				}
			}
			//Nuestro pájaro estará moviendo las alas
			if (p.getnImg() == p.getImgs().size()-1){
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
