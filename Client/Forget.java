package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
import javax.swing.*;

import Client.change.BackgroundPanel;


public class Forget {
	JFrame j,f;
	Socket socket;
	JLabel lab0,lab1,lab2;
	JTextField fd1,fd2,fd3;
	JButton bt1,bt2;
	BackgroundPanel pa;
	ImageIcon icon=new ImageIcon("Images/ls.gif");
	Font f1=new Font("宋体",Font.BOLD,50);
	Font f2=new Font("宋体",Font.BOLD,20);
	public Forget(JFrame j,Socket socket){
		this.j=j;
		this.socket=socket;
	}
	class BackgroundPanel extends JPanel{
		Image im;
		public BackgroundPanel(Image im){
			this.im=im;
			this.setOpaque(true);
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		}
	}
//	public static void main(String[] args){
//		JFrame jf = null;Socket s = null;
//		Forget k=new Forget(jf,s);
//		k.run();
//	}
	public void run(){
		f=new JFrame();
		lab0=new JLabel("请输入用户名");lab1=new JLabel("请输入姓名：");lab2=new JLabel("请输入身份证：");
		fd1=new JTextField();fd2=new JTextField();fd3=new JTextField();
		bt1=new JButton("确定");bt2=new JButton("取消");
		f.setLocation(0,0);f.setSize(800,600);f.setLayout(null);//为界面设置绝对布局
		//为各种label设置属性
		lab0.setFont(f2);lab0.setSize(400,100);lab0.setLocation(150,100);
		lab0.setForeground(Color.orange);
		lab1.setFont(f2);lab1.setSize(400,100);lab1.setLocation(150,200);
		lab1.setForeground(Color.orange);
		lab2.setFont(f2);lab2.setSize(400,100);lab2.setLocation(150,300);
		lab2.setForeground(Color.orange);
		//为各种password field设置属性
		fd1.setFont(f2);fd1.setSize(300,50);fd1.setLocation(350,125);
		fd2.setFont(f2);fd2.setSize(300,50);fd2.setLocation(350,225);
		fd3.setFont(f2);fd3.setSize(300,50);fd3.setLocation(350,325);
		//为两个按钮设置属性
		bt1.setFont(f2);bt1.setSize(150,50);bt1.setLocation(150,450);
		bt1.setForeground(Color.orange);
		bt2.setFont(f2);bt2.setSize(150,50);bt2.setLocation(450,450);
		bt2.setForeground(Color.orange);
		//为BackgroundPanel设置属性
		pa=new BackgroundPanel(icon.getImage());
		pa.setLocation(0, 0);pa.setSize(800, 600);pa.setLayout(null);
		pa.add(lab0);pa.add(lab1);pa.add(lab2);
		pa.add(fd1);pa.add(fd2);pa.add(fd3);
		pa.add(bt1);pa.add(bt2);
		bt1.addActionListener(new button());bt2.addActionListener(new button());
		
		f.add(pa);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class button implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt1){
				//f.setVisible(false);
				try {
					player n=new player(0,null,null,null,null,null);
					n.setuser(fd1.getText());
					n.setname(fd2.getText());
					n.setid(fd3.getText());
					OutputStream os = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.write(1);
					oos.writeObject(n);
					InputStream is = socket.getInputStream();
		            ObjectInputStream ois=new ObjectInputStream(is);
		            int re=ois.read();
		            if(re==0){
		            	player r=(player) ois.readObject();
		            	change c=new change(r,j,socket);
						c.run();
		            }
					if(re==1)JOptionPane.showMessageDialog(null,"信息错误!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if(e.getSource()==bt2){
				f.setVisible(false);
				j.setVisible(true);
			}
		}		
	}
}
