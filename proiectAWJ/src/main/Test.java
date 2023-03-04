package main;


import work.ImageProducer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import work.Buffer;
import work.ImageConsumer;

public class Test {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// Initializez buffer-ul
		Buffer buf = new Buffer();
		
		// Creez pipe-ul
		PipedOutputStream pipeOut = new PipedOutputStream();
		PipedInputStream pipeIn = new PipedInputStream(pipeOut);
		DataOutputStream out = new DataOutputStream(pipeOut);
		DataInputStream in = new DataInputStream(pipeIn);

		// Instantiez thread-urile
		ImageProducer producerThread1 = new ImageProducer(buf, "Producator");
		ImageConsumer consumerThread1 = new ImageConsumer(buf, "Consumator ", in, out);
		
		// Pornesc thread-urile
		producerThread1.start();
	 	consumerThread1.start();
	 	
	 	// Astept ca thread-urile sa termine
	 	producerThread1.join();
	 	consumerThread1.join();
	}
}
