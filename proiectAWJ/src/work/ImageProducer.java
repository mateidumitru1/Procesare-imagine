package work;

public class ImageProducer extends MyThread {

	
	public ImageProducer(Buffer b, String threadName) {
		
		super(b, threadName);
	}

	public void start() {
		
		super.start();
	}
	
	@Override
	public void run() {
		
		long startTime = System.nanoTime();
		
		System.out.println("Se citeste imaginea");
		
		int width = buf.getImage().getWidth();
		int height = buf.getImage().getHeight();
		
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++){
				
				// Creez un obiect de tip pixel
				Pixel pix = new Pixel(j, i, buf.getImage().getRGB(j, i));
				
				// Il pun in buffer
				buf.put(pix);
				
				if(i == height / 4 && j == width - 1){
					
					System.out.println(getName() + " a citit un sfert.");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i == height / 2 && j == width - 1){
					
					System.out.println(getName() + " a citit jumatate.");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i == 3 * height / 4 && j == width - 1){
						
					System.out.println(getName() + " a citit 3 sferturi.");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i == height - 1 && j == width - 1){
					
					System.out.println(getName() + " a citit in intregime.");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		System.out.println("Ending thread = " + getName());
		
		long endTime = System.nanoTime();
		
		// Salvarea timpului de citire
		TimeDuration.setReadFileTime((endTime - startTime) / 1_000_000);
	}
}
