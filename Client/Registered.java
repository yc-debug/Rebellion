package Client;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

import javax.swing.*;

import Client.*;
import Client.Map.BackgroundPanel;

public class Registered {
	JFrame j;
	JFrame jf;
	JPanel p;
	ImageIcon icon=new ImageIcon("Images/ls.gif");
	JLabel l1,l2,l3,l4,l5;
	JTextField user,name,id,mima1,mima2;
	JButton b1,b2;
	Font f=new Font("宋体",Font.BOLD,20);
	Socket socket;
//	public static void main(String[] args){
//		JFrame jh=new JFrame();
//		new Registered(jh).run();
//	}
	public Registered(JFrame j,Socket socket) throws UnknownHostException, IOException{
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
	public void run(){
	    jf=new JFrame();jf.setLocation(0,0);jf.setSize(800,600);jf.setLayout(null);
	    p=new BackgroundPanel(icon.getImage());
	    p.setLocation(0, 0);p.setSize(800, 600);p.setLayout(null);
	    l1=new JLabel("请输入用户名：");l1.setSize(190,70);l1.setLocation(10,20);l1.setFont(f);l1.setOpaque(false);
	    l2=new JLabel("请输入姓名：");l2.setSize(190,70);l2.setLocation(10,120);l2.setFont(f);l2.setOpaque(false);
	    l3=new JLabel("请输入身份证号：");l3.setSize(190,70);l3.setLocation(10,220);l3.setFont(f);l3.setOpaque(false);
	    l4=new JLabel("请输入密码：");l4.setSize(190,70);l4.setLocation(10,320);l4.setFont(f);l4.setOpaque(false);
	    l5=new JLabel("请重新输入密码：");l5.setSize(190,70);l5.setLocation(10,420);l5.setFont(f);l5.setOpaque(false);
	    p.add(l1);p.add(l2);p.add(l3);p.add(l4);p.add(l5);
	    user=new JTextField();user.setSize(350,70);user.setLocation(200,20);user.setFont(f);user.setOpaque(false);
	    name=new JTextField();name.setSize(350,70);name.setLocation(200,120);name.setFont(f);name.setOpaque(false);
	    id=new JTextField();id.setSize(350,70);id.setLocation(200,220);id.setFont(f);id.setOpaque(false);
	    mima1=new JPasswordField();mima1.setSize(350,70);mima1.setLocation(200,320);mima1.setFont(f);mima1.setOpaque(false);
	    mima2=new JPasswordField();mima2.setSize(350,70);mima2.setLocation(200,420);mima2.setFont(f);mima2.setOpaque(false);
	    p.add(user);p.add(name);p.add(id);p.add(mima1);p.add(mima2);
	    
	    b1=new JButton("确定");b1.setSize(100,50);b1.setLocation(200,500);b1.setOpaque(false);
	    b2=new JButton("取消");b2.setSize(100,50);b2.setLocation(500,500);b2.setOpaque(false);
	    b1.addActionListener(new button());  b2.addActionListener(new button());
	    p.add(b1);p.add(b2);
	    jf.add(p);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setVisible(true);
	}
	class button implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==b1){
				try {
					if(mima1.getText().equals(mima2.getText())){
					Animals[] wargroup = { new Tiger(), new Lion(), new Bear() };
					Animals[] members = new Animals[999];
					for(int index=0;index<wargroup.length;index++){
						members[index] = wargroup[index];
					}
//					//player n=new player(user.getText(),name.getText(),id.getText(),mima1.getText(),wargroup,200,null);
					Equipment[] equip = { new Teeth().changeQuality(), new Claws().changeQuality(), new Helmet().changeQuality(),
						new Breastplate().changeQuality(), new Kneecap().changeQuality() };
					Equipment[] item=new Equipment[20];
					for(int i=0;i<20;i++){
						item[i]=new Blank();
					}
					ImageIcon zoo=new ImageIcon("Images/zoo.jpg");
					ImageIcon land=new ImageIcon("Images/land.jpg");
					ImageIcon sea=new ImageIcon("Images/sea.jpg");
					ImageIcon sky=new ImageIcon("Images/sky.jpg");
					ImageIcon peace=new ImageIcon("Images/peace.jpg");
					JPanel up = new JPanel();
					JPanel Zoo = new JPanel();
					JLabel ZooLa = new JLabel("                  Area 1 动物园                             ");
					JButton[] ZooBt = { new JButton("          兽笼脱出            "), 
							new JButton("           街道突围             "), 
							new JButton("            希望之门             ")};
					
					JPanel Land = new BackgroundPanel(land.getImage());
					JLabel LandLa = new JLabel("            Area 2-1 陆地                      ");
					JButton[] LandBt = { new JButton("           痛击偷猎者              ") ,
							new JButton("           大战狩猎场              "),
							new JButton("          拦截贩卖运输车       "),
							new JButton("           攻陷野味餐馆          "),
							new JButton("          摧毁毛皮工厂           ")};

					JPanel Sea = new BackgroundPanel(sea.getImage());
					JLabel SeaLa = new JLabel("                Area 2-2 海洋                    ");
					JButton[] SeaBt = { new JButton("              阻止滥捕鱼类           "),
							new JButton("              迎战捕鲸船                ") ,
							new JButton("              整治排污企业            "),
							new JButton("              大闹海鲜餐馆             "),
							new JButton("             捣毁危险声纳              ")};

					JPanel Sky = new BackgroundPanel(sky.getImage());
					JLabel SkyLa = new JLabel("           Area 2-3 天空              ");
					JButton[] SkyBt = { new JButton("         暴揍掏鸟窝的熊孩子   "),
							new JButton("          痛打捕鸟者                    "),
							new JButton("          征服花鸟市场                "),
							new JButton("          禁止滥杀鸟类                "),
							new JButton("         击落障碍物飞机             ")};

					JPanel down = new BackgroundPanel(peace.getImage());
					JPanel End = new JPanel();
					JLabel EndLa = new JLabel("         Area 3 终章           ");
					JButton[] EndBt = { new JButton("       宣战      ") ,
							new JButton("       火并     	"),
							new JButton("       混战        ") ,
							new JButton("       决战         ") ,
							new JButton("       艰难和谈          ") };

					JPanel cen = new JPanel();
					JPanel[] panels = { Zoo, Land, Sea, Sky, End };
					JLabel[] labels = { ZooLa, LandLa, SeaLa, SkyLa, EndLa };
					JButton[][] buttons = { ZooBt, LandBt, SeaBt, SkyBt, EndBt };
					player r=new player(200,wargroup,members,equip,item,buttons);
					r.setuser(user.getText());r.setname(name.getText());r.setmima(mima1.getText());r.setid(id.getText());
					OutputStream os = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.write(0);
					oos.writeObject(r);
					BufferedReader ac = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			       int re=Integer.valueOf(ac.readLine());
		            System.out.println(re);
					if(re==0){
						JOptionPane.showMessageDialog(null,"注册成功!");
						jf.setVisible(false);
						j.setVisible(true);
					}
					if(re==1){
						JOptionPane.showMessageDialog(null,"用户名存在!请更改");
						socket.close();
					}
					if(re==2){
						JOptionPane.showMessageDialog(null,"姓名已被注册!请更改");
						socket.close();
					}
					if(re==3){
						JOptionPane.showMessageDialog(null,"身份证已被注册!请更改");
						socket.close();
					}
					}else{
						JOptionPane.showMessageDialog(null,"两次密码输入不同!");
					}
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==b2){
				jf.setVisible(false);
				j.setVisible(true);
			}
		}

	}
}
