package Client;

import java.applet.*;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

public class Music implements Runnable{
	File f;
	URI uri;
	URL ur1; 
	AudioClip aau;
	public Music(String path) throws MalformedURLException{
			f=new File(path);
			uri=f.toURI();
			ur1=uri.toURL();
			aau=Applet.newAudioClip(ur1);
		}
	 public void loop(){
		aau.loop();
	}
	 public void stop(){
		aau.stop();
	}	
	public void run() {
		loop();
	}

}
