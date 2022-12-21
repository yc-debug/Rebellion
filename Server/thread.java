package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import Client.*;

public class thread implements Runnable{
	Socket as;
	public thread(Socket as){
		this.as=as;
	}
	public void run() {
		// TODO Auto-generated method stub
//while(true){
		try {
			InputStream is = as.getInputStream();
	        ObjectInputStream ois=new ObjectInputStream(is);
	        int k;
			k = ois.read();
			 if(k==0){
		        	player get=(player) ois.readObject();
		        	PrintStream o=new PrintStream(as.getOutputStream());
		        	File file = new File("game.txt");
					FileInputStream input = new FileInputStream(file);
					ObjectInputStream input1 = new ObjectInputStream(input);
					ArrayList<player> list=(ArrayList<player>) input1.readObject();
					int n=0;
					for(int i=0;i<list.size();i++){
						if(list.get(i).getuser().equals(get.getuser())) {
							o.println("1");
							n=1;
						}
						if(list.get(i).getname().equals(get.getname())) {
							o.println("2");
							n=1;
						}
						if(list.get(i).getid().equals(get.getid())) {
							o.println("3");
							n=1;
						}
					}
					if(n==0){
						list.add(get);
						FileOutputStream output=new FileOutputStream(file);
						ObjectOutputStream output1 = new ObjectOutputStream(output);
						output1.writeObject(list);
						o.println("0");
						System.out.println("1");
					}
		        }
		        if(k==1){
		        	player get=(player) ois.readObject();
					File file = new File("game.txt");
					FileInputStream input = new FileInputStream(file);
					ObjectInputStream input1 = new ObjectInputStream(input);
					ArrayList<player> list=(ArrayList<player>) input1.readObject();
					OutputStream os = as.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					int n=0;
					for(int i=0;i<list.size();i++){
						if(list.get(i).getuser().equals(get.getuser())){
							if(list.get(i).getname().equals(get.getname())){
								if(list.get(i).getid().equals(get.getid())){
									oos.write(0);
									oos.writeObject(list.get(i));
									n=1;
								}	
							}
						}
					}
					if(n==0){
						oos.write(1);
					}
					
		        }
		        if(k==2){
		        	player r=(player) ois.readObject();
		        	File file = new File("game.txt");
					FileInputStream input = new FileInputStream(file);
					ObjectInputStream input1 = new ObjectInputStream(input);
					ArrayList<player> list=(ArrayList<player>) input1.readObject();
					for(int i=0;i<list.size();i++){
						if(list.get(i).getuser().equals(r.getuser())){
							list.remove(i);
							System.out.println(r.getuser()+"\n"+r.getname()+"\n"+r.item[0].print());
							list.add(r);
						}
					}
					FileOutputStream output=new FileOutputStream(file);
					ObjectOutputStream output1 = new ObjectOutputStream(output);
					output1.writeObject(list);
		        }
		        if(k==3){
		        	player r=(player) ois.readObject();
		        	File file = new File("game.txt");
					FileInputStream input = new FileInputStream(file);
					ObjectInputStream input1 = new ObjectInputStream(input);
					ArrayList<player> list=(ArrayList<player>) input1.readObject();
					OutputStream os = as.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					int n=0;
					for(int i=0;i<list.size();i++){
						if(list.get(i).getuser().equals(r.getuser())){
							if(list.get(i).getmima().equals(r.getmima())){
							oos.write(0);
							System.out.println(list.get(i).getuser()+"\n"+list.get(i).getname()+"\n");
							oos.writeObject(list.get(i));
							n=1;
							}
						}
					}
					if(n==0){
						oos.write(1);
						oos.writeObject(new player(0,null,null,null,null,null));
					}
					
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//	}  
}
