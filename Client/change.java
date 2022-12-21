package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

public class change {
	Socket socket;
	JFrame j,f;
	player r;
	JLabel lab0,lab1,lab2,lab3;
	JTextField fd1,fd2,fd3;
	JButton bt1,bt2;
	BackgroundPanel pa;
	ImageIcon icon2=new ImageIcon("C:\\Users\\于说\\Desktop\\b5.jpg");
	Font f1=new Font("宋体",Font.BOLD,50);
	Font f2=new Font("宋体",Font.BOLD,20);
	public change(player r,JFrame j,Socket socket){
		this.r=r;
		this.socket=socket;
		this.j=j;
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
	public void run() {
		//初始化组件
		f=new JFrame();
		lab0=new JLabel("修改密码");lab1=new JLabel("请输入原密码：");lab2=new JLabel("请输入新的密码：");lab3=new JLabel("请再次输入新密码：");
		fd1=new JPasswordField();fd2=new JPasswordField();fd3=new JPasswordField();
		bt1=new JButton("确定");bt2=new JButton("取消");
		//为f设置属性
		f.setLocation(0,0);f.setSize(800,600);f.setLayout(null);//为界面设置绝对布局
		//为各种label设置属性
		lab0.setFont(f1);lab0.setSize(400,100);lab0.setLocation(250,0);
		lab0.setForeground(Color.orange);
		lab1.setFont(f2);lab1.setSize(400,100);lab1.setLocation(150,100);
		lab1.setForeground(Color.orange);
		lab2.setFont(f2);lab2.setSize(400,100);lab2.setLocation(150,200);
		lab2.setForeground(Color.orange);
		lab3.setFont(f2);lab3.setSize(400,100);lab3.setLocation(150,300);
		lab3.setForeground(Color.orange);
		//为各种password field设置属性
		fd1.setFont(f2);fd1.setSize(300,50);fd1.setLocation(350,125);
		fd2.setFont(f2);fd2.setSize(300,50);fd2.setLocation(350,225);
		fd3.setFont(f2);fd3.setSize(300,50);fd3.setLocation(350,325);
		//为两个按钮设置属性
		bt1.setFont(f2);bt1.setSize(75,50);bt1.setLocation(150,450);
		bt1.setForeground(Color.orange);
		bt2.setFont(f2);bt2.setSize(75,50);bt2.setLocation(450,450);
		bt2.setForeground(Color.orange);
		//为BackgroundPanel设置属性
		pa=new BackgroundPanel(icon2.getImage());
		pa.setLocation(0, 0);pa.setSize(800, 600);pa.setLayout(null);
		pa.add(lab0);pa.add(lab1);pa.add(lab2);pa.add(lab3);
		pa.add(fd1);pa.add(fd2);pa.add(fd3);
		pa.add(bt1);pa.add(bt2);
		bt1.addActionListener(new button());bt2.addActionListener(new button());
		
		f.add(pa);
		f.setVisible(true);
	}
	class button implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt1){
				if(fd1.getText().equals(r.getmima())){
						r.mima=fd2.getText();
						S_L.Load(r);
						try {
							S_L.Save();
						} catch (ConnectException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,"找回成功!\n新密码为："+r.getmima());
						f.setVisible(false);
						j.setVisible(true);
				}
				if(!(fd2.getText().equals(fd3.getText()))){
					JOptionPane.showMessageDialog(null,"两次密码必须相同！");
				}
			}
			if(e.getSource()==bt2){
				f.setVisible(false);
				j.setVisible(true);
			}
		}		
	}
}
