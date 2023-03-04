package work;

public class Process extends LinearGrayLevelTransform {
	
	static boolean done = false;
	
	private Process(){}
	
	public static Process createProcess(){
		
		if(done == false) {
			
			done = true;
			return new Process();
		}
		return null;
	}
}
