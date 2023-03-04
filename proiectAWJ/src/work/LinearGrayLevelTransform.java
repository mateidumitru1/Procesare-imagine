package work;


public abstract class LinearGrayLevelTransform extends Transform {
	
	// Parametri pentru functie
	protected double contrast, brightness;
	
	// Initializare (default)
	{
		contrast = 1;
		brightness = 0;
	}
	
	// Algoritmul de transformare
	public int convertPixel(int pixel){
		
		// Extrag componentele r, g, b
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = pixel & 0xff;
		
		// Calculez media
		int avg = (r + g + b) / 3;
		
		// Transformarea
		int function = (int) (contrast * avg + brightness);
		
		// Atribui pixelului valoarea mediei
		pixel =  (function<<16) | (function<<8) | function;
		
		return pixel;
	}

	public double getContrast() {
		return contrast;
	}

	public void setContrast(double contrast) {
		this.contrast = contrast;
	}

	public double getBrightness() {
		return brightness;
	}

	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}
}
