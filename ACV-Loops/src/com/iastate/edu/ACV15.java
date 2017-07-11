package com.iastate.edu;

import java.io.IOException;
import java.io.StreamTokenizer;

public class ACV15 {

	public static void parse(StreamTokenizer st) throws IOException {
		
		int val;
		
		while(true) {
			val = st.nextToken();
			
			// token processing logic
			// code excluded
			
			if(String.valueOf(val) == "}") {
				break;
			}
		}
		
	}
	
}
