package work;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Thread-ul Consumer
public class ImageConsumer extends MyThread {

	private DataInputStream in;
	private DataOutputStream out;
	
	// Constructorul cu parametri
	public ImageConsumer(Buffer b, String threadName, DataInputStream in, DataOutputStream out){
		
		super(b, threadName);
		this.in = in;
		this.out = out;
	}

	public void start() {
		
		super.start();
	}
	
	@Override
	public void run() {
		
		// timpul de start al procesarii
		long startTime = System.nanoTime();
		
		Pixel pix;
		
		int width = buf.getImage().getWidth();
		int height = buf.getImage().getHeight();
		
		BufferedImage resultImage = buf.getImage();
		
		// Construiesc imaginea rezultat
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				
				// Iau pixelul din buffer
				pix = buf.get();
				
				// Aplic transformarea
				pix.setValue(buf.getPr().convertPixel(pix.getValue()));
				
				// Salvez rezultatul
				resultImage.setRGB(pix.getX(), pix.getY(), pix.getValue());
			}
		
		try {
			sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Instantiez WriterResult thread
		WriterResult wr = new WriterResult(buf, in);
		wr.start();
	
		
		// Parcurg rezultatul pentru a-l transmite in WriterResult
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				try {
					
					out.writeInt(resultImage.getRGB(j, i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(i == height / 4 && j == width - 1){
					
					System.out.println(getName() + " a transmis un sfert.");
				}
				else if(i == height / 2 && j == width - 1){
					
					System.out.println(getName() + " a transmis jumatate.");
				}
				else if(i == 3 * height / 4 && j == width - 1){
					
					System.out.println(getName() + " a transmis 3 sferturi.");
				}
				else if(i == height - 1 && j == width - 1){
					
					System.out.println(getName() + " a transmis in intregime.");
				}
			}
		
		// Opresc PipeStream-ul
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Ending thread = " + getName());

		// timpul de end al procesarii
		long endTime = System.nanoTime();

		// Salvarea timpului de procesare
		TimeDuration.setProcessFileTime((endTime - startTime) / 1_000_000);
	}
}

