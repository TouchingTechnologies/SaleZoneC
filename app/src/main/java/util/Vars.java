package util;

import android.content.Context;

public class Vars {
	Boolean print = true;
	
	public Vars(Context context) {
		
	}
	
	public void log(String string){
		if (print){
		System.out.println(string);
		}
	}

}
