package hilos;

import objetos.Piedra;
import practica10.MiPanel;

public class HiloPiedras extends Thread {
	private MiPanel mp;

	public HiloPiedras(MiPanel miPanel) {
		super();
		this.mp = miPanel;
	}
	
	@Override
	public void run(){
		while(true){
			super.run();
			//TODO 1: lanzar varias piedras
			Piedra piedraActual = mp.getPiedras().get(0);
			piedraActual.setCoordXPiedra(piedraActual.getCoordXPiedra() - 2); //velocidad de la piedra

			//Y las piedras irán girando.
			piedraActual.setnImg(piedraActual.getnImg()+1);
			if (piedraActual.getnImg() == 8){
				piedraActual.setnImg(0);
			}

			mp.repaint();

			//TODO 2: eliminar piedra cuando se salga de la pantalla
			
			try {
				//Thread.sleep(muneco.getVelocidad()); //TODO: eliminar try-catch?
				Thread.sleep(50); //velocidad de refresco (para avance y giro) de la piedra
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}	
	}

}
