package work;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;


	// Clasa buffer din curs, folosita pentru sincronizare
public class Buffer {
	
	// Pentru a putea avea acces la imagine in thread-uri
	private BufferedImage image;
	
	// Pentru a avea acces la transformare
	private static Process pr;
	
	// Pentru a salva pixelul (locatie si valoare)
	private Pixel pix;
	
	private boolean available = false;
	
	static {
		
		pr = Process.createProcess();
	}
	
	public Buffer() {
		
		
		String imageName = null;
		try {
			
			System.out.print("Introduceti numele imaginii: ");
			
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			long startTime = System.nanoTime();
			imageName = input.next();
			long endTime = System.nanoTime();
			
			// Salvarea timpului de citire a imaginii initiale
			TimeDuration.setInputDetailsTime((endTime - startTime) / 1_000_000);
			
			setImage(ImageIO.read(new File(imageName)));
			
			System.out.println("Doriti sa modificati contrastul si brightness-ul?(y/n)");
			String answer = input.next();
			
			// Citire contrast si brightness
			if(answer.equals("y")) {
				
				System.out.print("Introduceti valoarea constrastului (default = 1): ");
				pr.setContrast(input.nextDouble());
				
				System.out.print("Introduceti valoarea brightness-ului (default = 0): ");
				pr.setBrightness(input.nextDouble());
			}
			
		} catch (IOException e1) {
			
			System.out.println("Imaginea " + imageName + " nu a putut fi gasita!!");
			System.exit(1);
		}
	}
	
	public Process getPr() {
		return pr;
	}

	public synchronized Pixel get() {
		while (!available) {
			try {
				wait();
				// Asteapta producatorul sa puna o valoare
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		available = false;
		notifyAll ();
		return pix;
	}
	
	public synchronized void put (Pixel pix) {
		while (available) {
			try {
				wait();
				// Asteapta consumatorul sa preia valoarea
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		this.pix = pix;
		available = true;
		notifyAll ();
	}
	
	public BufferedImage getImage(){
		
		BufferedImage img = image;
		return img;
	}
	
	public void setImage(BufferedImage image){
		
		this.image = image;
	}
}
