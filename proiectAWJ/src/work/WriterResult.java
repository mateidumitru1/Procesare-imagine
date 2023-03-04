package work;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

// Thread-ul unde fac scrierea
public class WriterResult extends MyThread {
	
	private DataInputStream in;
	
	public WriterResult(Buffer b, DataInputStream in) {
		
		super(b, "WriterResult");
		this.in = in;
	}
	
	public void start() {
	
		super.start();
	}
	
	public void run() {
		
		long startTime = System.nanoTime();
		
		BufferedImage outputImage = buf.getImage();
		
		int width = buf.getImage().getWidth();
		int height = buf.getImage().getHeight();
		
		int value = 0;
		
		// Preiau fiecare pixel transmis de Consumer si il setez in imaginea rezultat
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				try {
					value = in.readInt();
					outputImage.setRGB(j, i, value);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(i == height / 4 && j == width - 1){
					
					System.out.println(getName() + " a primit un sfert.");
				}
				else if(i == height / 2 && j == width - 1){
					
					System.out.println(getName() + " a primit jumatate.");
				}
				else if(i == 3 * height / 4 && j == width - 1){
						
					System.out.println(getName() + " a primit 3 sferturi.");
				}
				else if(i == height - 1 && j == width - 1){
					
					System.out.println(getName() + " a primit in intregime.");
				}
			}
		
		String imageName = null;
		
		try {
			// Citirea numelui fisierului rezultat
			System.out.print("Introduceti numele cu care vreti sa salvati imaginea: ");
			
			Scanner output = new Scanner(System.in);
			
			long startTime1 = System.nanoTime();
			imageName = output.next();
			long endTime1 = System.nanoTime();
			
			// Salvarea timpului de citire a numelui imaginii rezultate
			TimeDuration.setOutputDetailsTime((endTime1 - startTime1) / 1_000_000);
			
			output.close();
			
			// Scrierea imaginii
	 		ImageIO.write(outputImage, "bmp", new File(imageName));
	 		
	 		System.out.println("Imaginea a fost salvata.");
	 		
	 	} catch (IOException e) {
	 		
	 		System.out.println("Imaginea " + imageName + " nu a putut fi salvata!!");
    	}
		
		System.out.println("Ending thread = " + getName());
		
		long endTime = System.nanoTime();
		
		// Salvarea timpului de scriere
		TimeDuration.setWriteFileTime((endTime - startTime) / 1_000_000);
		
		// Afisare timpi
		System.out.println("Timpul pentru citirea numelui imaginii de intrare: " + TimeDuration.getInputDetailsTime());
	 	System.out.println("Timpul pentru citirea numelui imaginii rezultat: " + TimeDuration.getOutputDetailsTime());
	 	System.out.println("Timpul pentru citirea imaginii de intrare: " + TimeDuration.getReadFileTime());
	 	System.out.println("Timpul pentru procesarea imaginii de intrare: " + TimeDuration.getProcessFileTime());
	 	System.out.println("Timpul pentru scrierea imaginii rezultat: " + TimeDuration.getWriteFileTime());
	}
}
