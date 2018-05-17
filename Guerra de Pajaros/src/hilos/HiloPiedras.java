package hilos;

import java.awt.Image;
import java.util.ArrayList;

import objetos.Piedra;
import practica10.MiPanel;

public class HiloPiedras extends Thread {
	private MiPanel mp;
	private ArrayList<Image> auxImgsPiedra;
	private ArrayList<Piedra> piedras = new ArrayList<Piedra>();

	public HiloPiedras(MiPanel miPanel, ArrayList<Image> auxImgsPiedra) {
		super();
		this.mp = miPanel;
		this.piedras.add(new Piedra(1000, (int)Math.random()*700, auxImgsPiedra)); //Estas piedras aparecerán de forma aleatoria por la parte derecha de la pantalla.;
		//TODO: Con Math.random, CoordYPiedra = 0
	}
	
	@Override
	public void run(){
		while(true){
			super.run();
			//WIP: lanzar varias piedras
			Piedra piedraActual = piedras.get(0);
			piedraActual.setCoordXPiedra(piedraActual.getCoordXPiedra() - 2); //velocidad de la piedra

			//Y las piedras irán girando.
			piedraActual.setnImg(piedraActual.getnImg()+1);
			if (piedraActual.getnImg() == 8){
				piedraActual.setnImg(0);
			}

			mp.repaint();

			//WIP: eliminar piedra cuando se salga de la pantalla
			
			try {
				Thread.sleep(50); //velocidad de refresco (para avance y giro) de la piedra
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public ArrayList<Piedra> getPiedras() {
		return piedras;
	}
	public void setPiedras(ArrayList<Piedra> piedras) {
		this.piedras = piedras;
	}

}
