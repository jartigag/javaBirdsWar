package practica10;

import hilos.HiloDisparoAmigo;
import hilos.HiloPajaroMio;
import hilos.HiloPiedras;
import objetos.DisparoAmigo;
import objetos.PajaroMio;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Teclado implements KeyListener {
	
	private MiPanel miPanel;

	public Teclado(MiPanel miPanel){
		this.miPanel = miPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		PajaroMio pj = miPanel.getPajaroMio();
		ArrayList<Image> auxImgsDisparoAmigo;
		//Cuando llego a un total de 500 puntos, a partir de ese momento, tendré el disparo de tipo "disparoamigo2" 
		if (miPanel.getPuntuacion()<500) {
			auxImgsDisparoAmigo = miPanel.getAuxImgsDisparoAmigo1();
		} else {
			auxImgsDisparoAmigo = miPanel.getAuxImgsDisparoAmigo2();
		}
		DisparoAmigo nuevoDisparo = new DisparoAmigo(pj.getCoordXPajaroMio()+pj.getImgsPajaroMio().get(0).getWidth(null),
				pj.getCoordYPajaroMio()+pj.getImgsPajaroMio().get(0).getHeight(null)/2,
				auxImgsDisparoAmigo);
		// se ha elegido disparar desde coordY+height/2 para poder disparar a cualquier altura, incluidos los bordes de pantalla
		if (e.getKeyCode()==32) {
			//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
			if (miPanel.getPantalla()==0) { //||miPanel.getPantalla()==2
				miPanel.setVidas(3);
				miPanel.sethPajaroMio(new HiloPajaroMio(miPanel));
				miPanel.gethPajaroMio().start();
				miPanel.sethPiedras(new HiloPiedras(miPanel)); // Las piedras se crean en hPiedra
				miPanel.gethPiedras().start();
				//WIP: nos irán saliendo pájaros enemigos
				miPanel.sethDisparoAmigo(new HiloDisparoAmigo(miPanel)); // Los disparos se crean en hDisparoAmigo
				miPanel.setPantalla(1);
			} else {
				//Y para disparar utilizaremos la barra espaciadora.
				if (miPanel.getPantalla()==1) {
					// crear disparo nuevo:
					miPanel.gethDisparoAmigo().getDisparosAmigo().add(nuevoDisparo);
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
