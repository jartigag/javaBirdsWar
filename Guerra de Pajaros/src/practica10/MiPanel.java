package practica10;

import hilos.HiloFondo;
import hilos.HiloPajaroMio;
import hilos.HiloPiedras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import objetos.DisparoAmigo;
import objetos.PajaroMio;
import objetos.Piedra;

public class MiPanel extends JPanel {
	
	private int pantalla;
	private int coordXFondo = 0;
	private int puntuacion = 0;
	private int vida100=300;

	private Image fondo;
	private Image pajaroVida;
	
	private PajaroMio pajaroMio;
	private ArrayList<Piedra> piedras = new ArrayList<Piedra>();
	private DisparoAmigo disparoAmigo1;

	public MiPanel() {
		Toolkit t = Toolkit.getDefaultToolkit();
		pantalla = 0;

		// Cargar imágenes
		fondo = t.getImage(getClass().getResource("fondo.jpg"));
		pajaroVida = t.getImage(getClass().getResource("Mi pajaro/pajarovida.png"));
		//Las piedras irán girando. Para ello utilizaremos las imágenes: piedra01.png hasta piedra08.png
		ArrayList<Image> auxImgsPiedra = new ArrayList<Image>();
		for (int i = 1; i <= 8;i++){
			Image image = t.getImage(getClass().getResource("piedra/piedra0"+i+".png"));
			auxImgsPiedra.add(image);
		}
		//Nuestro pájaro estará moviendo las alas y para ello vamos a utilizar las imágenes: pajaromio01.png hasta pajaromio08.png
		ArrayList<Image> auxImgsPajaroMio = new ArrayList<Image>();
		for (int i = 1; i <= 8;i++){
			Image image = t.getImage(getClass().getResource("Mi pajaro/pajaromio0"+i+".png"));
			auxImgsPajaroMio.add(image);
		}
		ArrayList<Image> auxImgsDisparoAmigo1 = new ArrayList<Image>();
		for (int i = 1; i <= 4;i++){
			Image image = t.getImage(getClass().getResource("disparo amigo/disparoamigo1-0"+i+".png"));
			auxImgsDisparoAmigo1.add(image);
		}
		
		// Crear objetos
		pajaroMio = new PajaroMio(0, 350, auxImgsPajaroMio);
		piedras.add(new Piedra(1000, (int)Math.random()*700, auxImgsPiedra)); //Estas piedras aparecerán de forma aleatoria por la parte derecha de la pantalla.
		//TODO 3: Con Math.random, CoordYPiedra = 0
		disparoAmigo1 = new DisparoAmigo(100,100, auxImgsDisparoAmigo1);

		// Crear hilos
		HiloFondo hFondo = new HiloFondo(this);
		hFondo.start();

		HiloPiedras hPiedras = new HiloPiedras(this);
		hPiedras.start();
		HiloPajaroMio hPajaroMio = new HiloPajaroMio(this);
		hPajaroMio.start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		g.drawImage(fondo, getCoordXFondo(), 0, this); // Siempre el mismo fondo
		g.drawImage(fondo, getCoordXFondo()-fondo.getWidth(null), 0, this); //TODO 0: repetir la imagen bien
				
		if (pantalla==0) { // pantalla de inicio
			inicio(g);
		} else {
			barraSuperior(g);
			dibujarPiedras(g);
			dibujarPajaroMio(g); 
			dibujarDisparoAmigo(g);
		}
	}

	public void inicio(Graphics g){
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.GRAY);
		//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
		g.drawString("Pulsa BARRA ESPACIADORA para empezar a jugar", 100, 100);
	}
	
	public void barraSuperior(Graphics g){
		// Marcador de puntuación
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.BLACK);
		g.drawString(String.format("%07d", puntuacion), 820, 30);

		// Vidas
		/* Como en mi ordenador al usar
		 * Image resizedPajaro = pajaro.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		 * la imagen redimensionada parpadeaba, he redimensionado a mano el pájaro para usarlo en el contador de vidas
		 */
		g.drawImage(pajaroVida, 20, 10, this);
		g.drawImage(pajaroVida, 100, 10, this);
		g.drawImage(pajaroVida, 180, 10, this);

		// Nivel de vida
		//En el centro de la barra superior vamos a colocar una franja de color para ir indicando el estado de la vida que tenemos actualmente.
		g.setColor(Color.RED);
		g.fillRect(350, 20, vida100, 10); //La franja roja siempre será de 100%
		g.setColor(Color.BLUE);
		g.fillRect(350, 20, (int) (vida100*0.1), 10); //Encima, una franja azul que iremos poniendo más pequeña
	}
	
	public void dibujarPiedras(Graphics g){
		Piedra piedraActual = getPiedras().get(0);
		System.out.println(piedraActual.getCoordYPiedra());
		g.drawImage(piedraActual.getImgsPiedra().get(piedraActual.getnImg()), piedraActual.getCoordXPiedra(), piedraActual.getCoordYPiedra(), this);
	}
	public void dibujarPajaroMio(Graphics g){
		//Nuestro pájaro deberá moverse por la pantalla evitando dichas piedras.
		//Y en el caso de que choque con alguna de ellas, en ese caso explotará.
		g.drawImage(pajaroMio.getImgsPajaroMio().get(pajaroMio.getnImg()), pajaroMio.getCoordXPajaroMio(), pajaroMio.getCoordYPajaroMio(), this);
	}

	private void dibujarDisparoAmigo(Graphics g) {
		if (puntuacion<500) {
			g.drawImage(disparoAmigo1.getImgsDisparoAmigo().get(disparoAmigo1.getnImg()), disparoAmigo1.getCoordXDisparoAmigo(), disparoAmigo1.getCoordYDisparoAmigo(), this);	
		}	
	}

	// GETTERS Y SETTERS
	public int getPantalla(){return pantalla;}
	public void setPantalla(int pantalla){this.pantalla = pantalla;}
	public int getCoordXFondo(){return coordXFondo;}
	public void setCoordXFondo(int coordXFondo){this.coordXFondo = coordXFondo;}
	public int getPuntuacion(){return puntuacion;}
	public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}
	public int getVida100(){return vida100;}
	public void setVida100(int vida100){this.vida100 = vida100;}
	public Image getFondo(){return fondo;}
	public void setFondo(Image fondo){this.fondo = fondo;}
	public Image getPajaroVida(){return pajaroVida;}
	public void setPajaroVida(Image pajaroVida){this.pajaroVida = pajaroVida;}
	public PajaroMio getPajaroMio(){return pajaroMio;}
	public void setPajaroMio(PajaroMio pajaroMio){this.pajaroMio = pajaroMio;}
	public ArrayList<Piedra> getPiedras(){return piedras;}
	public void setPiedras(ArrayList<Piedra> piedras){this.piedras = piedras;}
	public DisparoAmigo getDisparoAmigo1(){return disparoAmigo1;}
	public void setDisparoAmigo1(DisparoAmigo disparoAmigo1){this.disparoAmigo1 = disparoAmigo1;}
}