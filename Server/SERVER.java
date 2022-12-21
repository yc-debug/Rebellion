package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.ArrayList;

import Client.*;
public class SERVER {
	public static void main (String[] args) throws IOException, ClassNotFoundException{
		File f=new File("game.txt");
		FileInputStream input=new FileInputStream(f);
		if(input.read()==-1) {
			player r=new player(200,null,null,null,null,null); 
			r.setuser("0");r.setname("yc");r.setmima("123456");r.setid("18301085");
			r.setloginTimes(1);
			ArrayList<player> list=new ArrayList<player>();
			list.add(r);
			FileOutputStream out=new FileOutputStream(f);
			ObjectOutputStream out1 = new ObjectOutputStream(out);
			out1.writeObject(list);
		}
		ServerSocket a = new ServerSocket(2000);
		while(true){
			Socket as=a.accept();
			Thread k=new Thread(new thread(as));
			k.start();
		}
		
	}
}
