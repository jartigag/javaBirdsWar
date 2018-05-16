package hilos;

import java.awt.Image;
import java.util.ArrayList;

import objetos.DisparoAmigo;
import objetos.PajaroMio;
import practica10.MiPanel;

public class HiloDisparoAmigo extends Thread {
	private MiPanel mp;
	private ArrayList<Image> auxImgsDisparoAmigo1;
	private ArrayList<DisparoAmigo> disparosAmigo1 = new ArrayList<DisparoAmigo>();

	public HiloDisparoAmigo(MiPanel miPanel, ArrayList<Image> auxImgsDisparoAmigo1) {
		this.mp = miPanel;
		PajaroMio pj = miPanel.getPajaroMio();
		this.disparosAmigo1.add(new DisparoAmigo(pj.getCoordXPajaroMio()+pj.getImgsPajaroMio().get(0).getWidth(null),
				pj.getCoordYPajaroMio()+pj.getImgsPajaroMio().get(0).getHeight(null)/2, auxImgsDisparoAmigo1));
				// se ha elegido disparar desde coordY+height/2 para poder disparar a cualquier altura, incluidos los bordes de pantalla
	}

	@Override
	public void run(){
		DisparoAmigo d = disparosAmigo1.get(disparosAmigo1.size()-1);
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

	public ArrayList<DisparoAmigo> getdisparosAmigo1() {
		return disparosAmigo1;
	}
	public void setdisparosAmigo1(ArrayList<DisparoAmigo> disparosAmigo1) {
		this.disparosAmigo1 = disparosAmigo1;
	}

}
