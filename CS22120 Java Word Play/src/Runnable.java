import java.io.IOException;

import uk.ac.aber.dcs.cs21120.data.AltStart;
import uk.ac.aber.dcs.cs21120.data.ReadDict;
import uk.ac.aber.dcs.cs21120.gui.MainFrame;

public class Runnable {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MainFrame mf = new MainFrame();
		mf.MainFrameCreate();
		System.out.println("Welcome To WordPlay! Please Select An Option.");
		ReadDict read = new ReadDict();
		read.read();
		AltStart start = new AltStart();
		start.start();
	}

}