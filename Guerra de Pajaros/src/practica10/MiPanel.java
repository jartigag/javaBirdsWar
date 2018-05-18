package practica10;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import hilos.HiloDisparoAmigo;
import hilos.HiloDisparoEnemigo;
import hilos.HiloFondo;
import hilos.HiloPajaroEnemigo;
import hilos.HiloPajaroMio;
import hilos.HiloPiedras;

import objetos.DisparoAmigo;
import objetos.DisparoEnemigo;
import objetos.PajaroEnemigo;
import objetos.PajaroMio;
import objetos.Piedra;

public class MiPanel extends JPanel {
	
	private int pantalla;
	private int coordXFondo = 0;
	private int puntuacion = 0;
	private int vidas = 3;
	private int vida100 = 300;

	private Image fondo;
	private Image pajaroVida;
	
	private PajaroMio pajaroMio;
	private ArrayList<Image> auxImgsPajaroMio = new ArrayList<Image>();
	private ArrayList<ArrayList> auxImgsEnemigo = new ArrayList<ArrayList>();
	private ArrayList<Image> auxImgsPiedra = new ArrayList<Image>();
	private ArrayList<Image> auxImgsDisparoAmigo1 = new ArrayList<Image>();
	private ArrayList<Image> auxImgsDisparoAmigo2 = new ArrayList<Image>();
	private ArrayList<ArrayList> auxImgsDisparoEnemigo = new ArrayList<ArrayList>();
	private ArrayList<Image> auxImgsExplosion = new ArrayList<Image>();

	private HiloFondo hFondo;
	private HiloPajaroMio hPajaroMio;
	private HiloPiedras hPiedras;
	private HiloPajaroEnemigo hPajaroEnemigo;
	private HiloDisparoAmigo hDisparoAmigo;
	private HiloDisparoEnemigo hDisparoEnemigo;

	public MiPanel() {
		Toolkit t = Toolkit.getDefaultToolkit();
		pantalla = 0;

		// Cargar imágenes
		/*
		He recortado las imágenes a mano todo lo posible
		para ajustar mejor las colisiones
		*/
		fondo = t.getImage(getClass().getResource("fondo.jpg"));
		pajaroVida = t.getImage(getClass().getResource("Mi pajaro/pajarovida.png"));
		//Nuestro pájaro estará moviendo las alas y para ello vamos a utilizar las imágenes: pajaromio01.png hasta pajaromio08.png
		auxImgsPajaroMio = new ArrayList<Image>();
		for (int i=1; i<=8; i++){
			Image image = t.getImage(getClass().getResource("Mi pajaro/pajaromio0"+i+".png"));
			auxImgsPajaroMio.add(image);
		}
		//imágenes de explosion
		for (int i=1; i<=7; i++){
			Image image = t.getImage(getClass().getResource("explosion/explosion-0"+i+".png"));
			auxImgsExplosion.add(image);
		}
		//Las piedras irán girando. Para ello utilizaremos las imágenes: piedra01.png hasta piedra08.png
		for (int i=1; i<=8; i++){
			Image image = t.getImage(getClass().getResource("piedra/piedra0"+i+".png"));
			auxImgsPiedra.add(image);
		}
		//imágenes de pajaroEnemigo
		for (int i=1; i<=3; i++) {
			ArrayList<Image> auxImgsEnemigoTipo = new ArrayList<Image>();
			//imágenes de enemigo[1,2,3]
			for (int j=1; j<=9; j++){
				Image image = t.getImage(getClass().getResource("enemigos/enemigo"+i+"-0"+j+".png"));
				auxImgsEnemigoTipo.add(image);
			}
			auxImgsEnemigo.add(auxImgsEnemigoTipo);
		}
		//imágenes de disparoamigo1
		for (int i=1; i<=4; i++){
			Image image = t.getImage(getClass().getResource("disparo amigo/disparoamigo1-0"+i+".png"));
			auxImgsDisparoAmigo1.add(image);
		}
		//imágenes de disparoamigo2
		for (int i=1; i<=4; i++){
			Image image = t.getImage(getClass().getResource("disparo amigo/disparoamigo2-0"+i+".png"));
			auxImgsDisparoAmigo2.add(image);
		}
		//imágenes de disparoEnemigo
		for (int i=1; i<=3; i++) {
			ArrayList<Image> auxImgsDisparoEnemigoTipo = new ArrayList<Image>();
			//imágenes de disparoenemigo[1,2,3]
			for (int j=1; j<=4; j++){
				Image image = t.getImage(getClass().getResource("disparo enemigo/disparoenemigo"+i+"-0"+j+".png"));
				auxImgsDisparoEnemigoTipo.add(image);
			}
			auxImgsDisparoEnemigo.add(auxImgsDisparoEnemigoTipo);
		}

		// Iniciar hilos
		hFondo = new HiloFondo(this);
		hFondo.start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		g.drawImage(fondo, getCoordXFondo(), 0, this); // Siempre el mismo fondo
		g.drawImage(fondo, getCoordXFondo()+fondo.getWidth(null), 0, this);
		if (getCoordXFondo()<-fondo.getWidth(null))  {
			setCoordXFondo(0);
		}

		if (pantalla==0) { // pantalla de inicio
			inicio(g);
		}
		if (pantalla==1) { // pantalla de juego
			barraSuperior(g);
			dibujarPajaroMio(g);
			dibujarPiedras(g);
			dibujarPajaroEnemigo(g);
			if (gethDisparoAmigo().isAlive()) {
				dibujarDisparoAmigo(g);
			}
			//if (gethDisparoEnemigo().isAlive()) {
				dibujarDisparoEnemigo(g);
			//}
			
		}
		if (pantalla==2) { // pantalla de game over
			gameover(g);
		}
	}

	public void inicio(Graphics g){
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
		Rectangle areaJugar = new Rectangle(200,100,600,50);
		drawCenteredString(g,"Pulsa BARRA ESPACIADORA para empezar a jugar",areaJugar,fuente);
	}
	
	public void barraSuperior(Graphics g){
		// Marcador de puntuación
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.BLACK);
		Rectangle areaPuntuacion = new Rectangle(800,0,200,50);
		drawCenteredString(g,String.format("%07d", puntuacion),areaPuntuacion,fuente);

		// Vidas
		/* Como en mi ordenador al usar
		Image resizedPajaro = pajaro.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		la imagen redimensionada parpadeaba, he redimensionado a mano el pájaro para usarlo en el contador de vidas
		 */
		for (int i=0; i<vidas; i++) {
			g.drawImage(pajaroVida, 20+i*80, 10, this);
		}

		// Nivel de vida
		//En el centro de la barra superior vamos a colocar una franja de color
		//para ir indicando el estado de la vida que tenemos actualmente.
		g.setColor(Color.RED);
		g.fillRect(350, 20, vida100, 10); //La franja roja siempre será de 100%
		g.setColor(Color.BLUE);
		g.fillRect(350, 20, (int) (vida100*0.1), 10); //Encima, una franja azul que iremos poniendo más pequeña
	}

	public void dibujarPajaroMio(Graphics g){
		g.drawImage(pajaroMio.getImgsPajaroMio().get(pajaroMio.getnImg()),
				pajaroMio.getCoordXPajaroMio(), pajaroMio.getCoordYPajaroMio(), this);
	}

	public void dibujarPiedras(Graphics g){

		// dimensiones del cuadro de pajaroMio:
		int inixPj = pajaroMio.getCoordXPajaroMio(); int finxPj = inixPj + pajaroMio.getImgsPajaroMio().get(0).getWidth(null);
		int iniyPj = pajaroMio.getCoordYPajaroMio(); int finyPj = iniyPj + pajaroMio.getImgsPajaroMio().get(0).getHeight(null);

		for (int i=0; i<hPiedras.getPiedras().size(); i++) {
			Piedra piedraActual = hPiedras.getPiedras().get(i);

			// dimensiones del cuadro de piedra:
			int inixPd = piedraActual.getCoordXPiedra(); int finxPd = inixPd + auxImgsPiedra.get(0).getWidth(null);
			int iniyPd = piedraActual.getCoordYPiedra(); int finyPd = iniyPd + auxImgsPiedra.get(0).getHeight(null);

			// definición de colisión:
			boolean colision = (inixPj<finxPd && inixPd<finxPj && iniyPj<finyPd && iniyPd<finyPj);
//System.out.println("colis. piedra: "+colision);
			//En el caso de que choque con alguna de las piedras, explotará
			if (colision) {
				if (vidas==0) {
					pantalla=2;
					/*hPajaroMio.stop();;
					hPiedras.stop();
					hDisparoAmigo.stop();*/ //TODO: hilo.stop() no funciona
				}
				pajaroMio.setImgsPajaroMio(auxImgsExplosion); //pajaroMio explota
				// borrar piedras:
				//if (hPiedras.getPiedras()!=null) {
					for (int j=0; i<hPiedras.getPiedras().size(); j++) {
						hPiedras.getPiedras().remove(j); /*TODO: evitar "Exception in thread "AWT-EventQueue-1"
														java.lang.IndexOutOfBoundsException: Index: 1, Size: 1"*/
					}
				//}
				return;
			}
			g.drawImage(piedraActual.getImgsPiedra().get(piedraActual.getnImg()),
					piedraActual.getCoordXPiedra(), piedraActual.getCoordYPiedra(), this);
		}
	}

	private void dibujarPajaroEnemigo(Graphics g) {
		
		// dimensiones del cuadro de pajaroMio:
		int inixPj = pajaroMio.getCoordXPajaroMio(); int finxPj = inixPj + pajaroMio.getImgsPajaroMio().get(0).getWidth(null);
		int iniyPj = pajaroMio.getCoordYPajaroMio(); int finyPj = iniyPj + pajaroMio.getImgsPajaroMio().get(0).getHeight(null);
		
		for (int i=0; i<hPajaroEnemigo.getPajarosEnemigos().size(); i++) {
			PajaroEnemigo pajaroEnemigoActual = hPajaroEnemigo.getPajarosEnemigos().get(i);

			// dimensiones del cuadro de pajaroEnemigo:
			int inixPe = pajaroEnemigoActual.getCoordXPajaroEnemigo(); int finxPe = inixPe + pajaroEnemigoActual.getImgsPajaroEnemigo().get(0).getWidth(null);
			//(no uso auxImgs como hacía en auxImgsPiedra porque pajaroEnemigo es de tamaño variable)
			int iniyPe = pajaroEnemigoActual.getCoordYPajaroEnemigo(); int finyPe = iniyPe + pajaroEnemigoActual.getImgsPajaroEnemigo().get(0).getHeight(null);

			// definición de colisión:
			boolean colision = (inixPj<finxPe && inixPe<finxPj && iniyPj<finyPe && iniyPe<finyPj);
//System.out.println("colis. pajaroEnemigo: "+colision);
			if (colision) {
				if (vidas==0) {
					pantalla=2;
					/*hPajaroMio.stop();;
					hPiedras.stop();
					hDisparoAmigo.stop();*/ //TODO: hilo.stop() no funciona
				}
				pajaroMio.setImgsPajaroMio(auxImgsExplosion); //pajaroMio explota
				// borrar pajarosEnemigos:
				for (int j=0; i<hPajaroEnemigo.getPajarosEnemigos().size(); j++) {
					hPajaroEnemigo.getPajarosEnemigos().remove(j);
				}
				return;
			}

			g.drawImage(pajaroEnemigoActual.getImgsPajaroEnemigo().get(pajaroEnemigoActual.getnImg()),
					pajaroEnemigoActual.getCoordXPajaroEnemigo(), pajaroEnemigoActual.getCoordYPajaroEnemigo(), this);
		}	
	}

	private void dibujarDisparoAmigo(Graphics g) {
		for (int i=0; i<hDisparoAmigo.getDisparosAmigo().size(); i++) {
			DisparoAmigo disparoAmigoActual = hDisparoAmigo.getDisparosAmigo().get(i);
			g.drawImage(disparoAmigoActual.getImgsDisparoAmigo().get(disparoAmigoActual.getnImg()),
					disparoAmigoActual.getCoordXDisparoAmigo(), disparoAmigoActual.getCoordYDisparoAmigo(), this);
		}
	}

	private void dibujarDisparoEnemigo(Graphics g) {
		for (int i=0; i<hDisparoEnemigo.getDisparosEnemigo().size(); i++) {
			DisparoEnemigo disparoEnemigoActual = hDisparoEnemigo.getDisparosEnemigo().get(i);
			g.drawImage(disparoEnemigoActual.getImgsDisparoEnemigo().get(disparoEnemigoActual.getnImg()),
					disparoEnemigoActual.getCoordXDisparoEnemigo(), disparoEnemigoActual.getCoordYDisparoEnemigo(), this);
		}
	}

	private void gameover(Graphics g) {
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.RED);
		Rectangle areaGameOver = new Rectangle(200,50,600,50);
		drawCenteredString(g,"GAME OVER",areaGameOver,fuente);
		g.setColor(Color.WHITE);
		Rectangle areaJugar = new Rectangle(200,100,600,50);
		drawCenteredString(g,"Pulsa BARRA ESPACIADORA para volver a jugar",areaJugar,fuente);
		g.setColor(Color.BLACK);
		Rectangle areaPuntuacion = new Rectangle(200,300,600,100);
		drawCenteredString(g,String.format("%07d", puntuacion),areaPuntuacion,fuente);
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		/* Método para centrar los textos en un área */
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}

	// GETTERS Y SETTERS
	public int getPantalla(){return pantalla;}
	public void setPantalla(int pantalla){this.pantalla = pantalla;}
	public int getCoordXFondo(){return coordXFondo;}
	public void setCoordXFondo(int coordXFondo){this.coordXFondo = coordXFondo;}
	public int getPuntuacion(){return puntuacion;}
	public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}
	public int getVidas(){return vidas;}
	public void setVidas(int vidas){this.vidas = vidas;}
	public int getVida100(){return vida100;}
	public void setVida100(int vida100){this.vida100 = vida100;}
	public Image getFondo(){return fondo;}
	public void setFondo(Image fondo){this.fondo = fondo;}
	public Image getPajaroVida(){return pajaroVida;}
	public void setPajaroVida(Image pajaroVida){this.pajaroVida = pajaroVida;}
	public PajaroMio getPajaroMio(){return pajaroMio;}
	public void setPajaroMio(PajaroMio pajaroMio){this.pajaroMio = pajaroMio;}
	public ArrayList<Image> getAuxImgsPajaroMio(){return auxImgsPajaroMio;}
	public void setAuxImgsPajaroMio(ArrayList<Image> auxImgsPajaroMio){this.auxImgsPajaroMio = auxImgsPajaroMio;}
	public ArrayList<ArrayList> getAuxImgsEnemigo(){return auxImgsEnemigo;}
	public void setAuxImgsEnemigo(ArrayList<ArrayList> auxImgsEnemigo){this.auxImgsEnemigo = auxImgsEnemigo;}
	public ArrayList<Image> getAuxImgsPiedra(){return auxImgsPiedra;}
	public void setAuxImgsPiedra(ArrayList<Image> auxImgsPiedra){this.auxImgsPiedra = auxImgsPiedra;}
	public ArrayList<Image> getAuxImgsDisparoAmigo1(){return auxImgsDisparoAmigo1;}
	public void setAuxImgsDisparoAmigo1(ArrayList<Image> auxImgsDisparoAmigo1){this.auxImgsDisparoAmigo1 = auxImgsDisparoAmigo1;}
	public ArrayList<Image> getAuxImgsDisparoAmigo2(){return auxImgsDisparoAmigo2;}
	public void setAuxImgsDisparoAmigo2(ArrayList<Image> auxImgsDisparoAmigo2){this.auxImgsDisparoAmigo2 = auxImgsDisparoAmigo2;}
	public ArrayList<ArrayList> getAuxImgsDisparoEnemigo(){return auxImgsDisparoEnemigo;}
	public void setAuxImgsDisparoEnemigo(ArrayList<ArrayList> auxImgsDisparoEnemigo){this.auxImgsDisparoEnemigo = auxImgsDisparoEnemigo;}
	public HiloFondo gethFondo(){return hFondo;}
	public void sethFondo(HiloFondo hFondo){this.hFondo = hFondo;}
	public HiloPajaroMio gethPajaroMio(){return hPajaroMio;}
	public void sethPajaroMio(HiloPajaroMio hPajaroMio){this.hPajaroMio = hPajaroMio;}
	public HiloPiedras gethPiedras(){return hPiedras;}
	public void sethPiedras(HiloPiedras hPiedras){this.hPiedras = hPiedras;}
	public HiloPajaroEnemigo gethPajaroEnemigo(){return hPajaroEnemigo;}
	public void sethPajaroEnemigo(HiloPajaroEnemigo hPajaroEnemigo){this.hPajaroEnemigo = hPajaroEnemigo;}
	public HiloDisparoAmigo gethDisparoAmigo(){return hDisparoAmigo;}
	public void sethDisparoAmigo(HiloDisparoAmigo hDisparoAmigo){this.hDisparoAmigo = hDisparoAmigo;}
	public HiloDisparoEnemigo gethDisparoEnemigo(){return hDisparoEnemigo;}
	public void sethDisparoEnemigo(HiloDisparoEnemigo hDisparoEnemigo){this.hDisparoEnemigo = hDisparoEnemigo;}

}