package work;

public class Pixel implements Point {
	
	private int x, y, value;
	
	public Pixel(int x, int y, int value){
		
		setX(x);
		setY(y);
		setValue(value);
	}

	@Override
	public int getX() {
		
		return x;
	}

	@Override
	public void setX(int x) {
		
		this.x = x;
		
		
	}

	@Override
	public int getY() {
		
		return y;
	}

	@Override
	public void setY(int y) {
		
		this.y = y;
	}

	public int getValue() {
		
		return value;
	}

	public void setValue(int value) {
		
		this.value = value;
	}

	@Override
	public void print() {

		System.out.println("x: " + x + "; y: " + y + "; value: " + value);
	}
}
