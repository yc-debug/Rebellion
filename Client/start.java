package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

import javax.swing.*;


public class start {
	JFrame j;
	JTextField t1,t2;
	JButton b1,b2,b3,b4;
	ImageIcon icon=new ImageIcon("Images/start.gif");
	JPanel p;
	Font f1=new Font("宋体",Font.BOLD,40);
	Font f2=new Font("宋体",Font.BOLD,20);
	Socket socket;
	public void jframe() throws UnknownHostException, IOException{
		j=new JFrame();
		t1=new JTextField(null);t2=new JTextField();
		t1.setFont(f2);t2.setFont(f2);
		t1.setSize(400,80);t1.setLocation(100,100);
		t1.setOpaque(false);
		t1.setFocusable(false);
		t1.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				t1.setFocusable(true);
			}
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub			
			}
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		t1.setText("账号/身份证号");
		t1.setForeground(Color.GRAY);//设置颜色
		t1.addFocusListener(new FocusListener() {
		     
		 public void focusLost(FocusEvent e) {//失去焦点的时候
		        //如果内容为空,设置文本
		        if(t1.getText().trim().equals("")){
		            t1.setText("账号/身份证号");
		        }
		    }
		     
		    public void focusGained(FocusEvent e) {//得到焦点的时候
		        if(t1.getText().trim().equals("账号/身份证号")){
		            t1.setText("");//让文本为空白
		        }
		    }
		});
		  t2.setText("密码");
		  t2.setForeground(Color.GRAY);//设置颜色
		  t2.addFocusListener(new FocusListener() {
		     
		    public void focusLost(FocusEvent e) {//失去焦点的时候
		        //如果内容为空,设置文本
		        if(t2.getText().trim().equals("")){
		            t2.setText("密码");
		        }
		    }
		     
		    public void focusGained(FocusEvent e) {//得到焦点的时候
		        if(t2.getText().trim().equals("密码")){
		            t2.setText("");//让文本为空白
		        }
		    }
		});
		t2.setSize(400,80);t2.setLocation(100,250);
		t2.setOpaque(false);
		b1=new JButton("开始游戏");b2=new JButton("退出游戏");
		b1.setFont(f2);b2.setFont(f2);
		b1.addActionListener(new button());b2.addActionListener(new button());
		b1.setSize(150,60);b1.setLocation(150,450);
		b2.setSize(150,60);b2.setLocation(350,450);
		b3=new JButton("注册账号");b4=new JButton("忘记密码");
		b3.setFont(f2);b3.setOpaque(false);b4.setFont(f2);b4.setOpaque(false);
		b3.setSize(150,80);b3.setLocation(550,100);
		b4.setSize(150,80);b4.setLocation(550,250);
		b3.addActionListener(new button());b4.addActionListener(new button());
		p=new BackgroundPanel(icon.getImage());		
		p.add(t1);p.add(t2);p.add(b1);p.add(b2);
		p.add(b3);p.add(b4);
		j=new JFrame();j.setLocation(0,0);j.setSize(800,600);j.setLayout(null);
		p.setLocation(0, 0);p.setSize(800, 600);p.setLayout(null);
		j.add(p);
		j.getContentPane();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
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
//	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, UnknownHostException, IOException{
//		 for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//             if ("Nimbus".equals(info.getName())) {
//                 UIManager.setLookAndFeel(info.getClassName());
//                 break;
//             }
//		  }
//		start l=new start();
//		l.jframe();
//	}
	class button implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==b1){				
				try {
					socket=new Socket("127.0.0.1",2000);
					player r=new player(0,null,null,null,null,null);
					r.setuser(t1.getText());r.setmima(t2.getText());
					OutputStream os = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.write(3);
					oos.writeObject(r);	
					InputStream is = socket.getInputStream();
			        ObjectInputStream ois=new ObjectInputStream(is);
			        int k=ois.read();
			        System.out.println("7");
			        if(k==0){
			        	player ru=(player) ois.readObject();
			        	System.out.println(ru.getuser()+"\n"+ru.getname()+"\n");
			        	S_L.Load(ru);
			        	j.setVisible(false);
			        	t1.setText(null);t2.setText(null);
			        	run.frames[1].setVisible(true);
			        }
			        if(k==1){
			        	player ru=(player) ois.readObject();
			        	JOptionPane.showMessageDialog(null,"信息错误，登陆失败，请重新输入!");
			        }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if(e.getSource()==b2){
				System.exit(0);
			}
			if(e.getSource()==b3){
				try {
					socket=new Socket("127.0.0.1",2000);
					j.setVisible(false);
					Registered registered;
					registered = new Registered(j,socket);
					registered.run();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==b4){
				j.setVisible(false);
				Forget forget=new Forget(j,socket);
				forget.run();
			}
		}
	}
//	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, UnsupportedLookAndFeelException{
//		run k=new run();
//	}
}
