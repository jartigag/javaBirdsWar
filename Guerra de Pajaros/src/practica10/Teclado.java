package practica10;

import hilos.HiloDisparoAmigo;
import objetos.DisparoAmigo;
import objetos.PajaroMio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener {
	
	private MiPanel miPanel;

	public Teclado(MiPanel miPanel){
		this.miPanel = miPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		PajaroMio pj = miPanel.getPajaroMio();
		DisparoAmigo nuevoDisparo = new DisparoAmigo(pj.getCoordXPajaroMio()+pj.getImgsPajaroMio().get(0).getWidth(null),
				pj.getCoordYPajaroMio()+pj.getImgsPajaroMio().get(0).getHeight(null)/2,
				miPanel.getAuxImgsDisparoAmigo1());
		// se ha elegido disparar desde coordY+height/2 para poder disparar a cualquier altura, incluidos los bordes de pantalla
		if (e.getKeyCode()==32) {
			//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
			if (miPanel.getPantalla()==0) {
				miPanel.setPantalla(1);
				miPanel.gethPiedras().start();
			} else {
				//Y para disparar utilizaremos la barra espaciadora.
				if (miPanel.getPantalla()==1) {
					// crear disparo nuevo:
					miPanel.gethDisparoAmigo().getDisparosAmigo1().add(nuevoDisparo);
					if (miPanel.gethDisparoAmigo().isAlive()==false) { // Si no se ha iniciado hDisparoAmigo
						miPanel.gethDisparoAmigo().start();
					}
				}
			}	
			miPanel.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Auto-generated method stub
		
	}

}
