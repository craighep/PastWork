package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AltStart {
public void start() throws IOException{
	String option = "";
	BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
	
	while (!option.equals("1") || !option.equals("2")){
	System.out.println("Type for: 1- WordLadder, 2-StepLadder     ('x' to exit)");
	option = in.readLine();
	
	if (option.equals("1")){
	WordLadder wl = new WordLadder();
	wl.run();
	}
	if (option.equals("2")){
	StepLadder sl = new StepLadder();
	sl.run();
	}
	if(option.equals("x")){
		System.exit(0);
	}
}
}

	
}
