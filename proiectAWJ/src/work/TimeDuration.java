package work;

public class TimeDuration {
	
	static long inputDetailsTime, outputDetailsTime, readFileTime, processFileTime, writeFileTime;

	public static long getOutputDetailsTime() {
		return outputDetailsTime;
	}

	public static void setOutputDetailsTime(long outputDetailsTime) {
		TimeDuration.outputDetailsTime = outputDetailsTime;
	}

	static public long getInputDetailsTime() {
		return inputDetailsTime;
	}

	static public void setInputDetailsTime(long readInputDetailsTime) {
		TimeDuration.inputDetailsTime = readInputDetailsTime;
	}

	static public long getReadFileTime() {
		return readFileTime;
	}

	static public void setReadFileTime(long readFileTime) {
		TimeDuration.readFileTime = readFileTime;
	}

	static public long getProcessFileTime() {
		return processFileTime;
	}

	static public void setProcessFileTime(long processFileTime) {
		TimeDuration.processFileTime = processFileTime;
	}

	static public long getWriteFileTime() {
		return writeFileTime;
	}

	static public void setWriteFileTime(long writeFileTime) {
		TimeDuration.writeFileTime = writeFileTime;
	}
}
