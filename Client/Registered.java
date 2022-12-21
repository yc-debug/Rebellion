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
	Font f=new Font("����",Font.BOLD,20);
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
	    l1=new JLabel("�������û�����");l1.setSize(190,70);l1.setLocation(10,20);l1.setFont(f);l1.setOpaque(false);
	    l2=new JLabel("������������");l2.setSize(190,70);l2.setLocation(10,120);l2.setFont(f);l2.setOpaque(false);
	    l3=new JLabel("���������֤�ţ�");l3.setSize(190,70);l3.setLocation(10,220);l3.setFont(f);l3.setOpaque(false);
	    l4=new JLabel("���������룺");l4.setSize(190,70);l4.setLocation(10,320);l4.setFont(f);l4.setOpaque(false);
	    l5=new JLabel("�������������룺");l5.setSize(190,70);l5.setLocation(10,420);l5.setFont(f);l5.setOpaque(false);
	    p.add(l1);p.add(l2);p.add(l3);p.add(l4);p.add(l5);
	    user=new JTextField();user.setSize(350,70);user.setLocation(200,20);user.setFont(f);user.setOpaque(false);
	    name=new JTextField();name.setSize(350,70);name.setLocation(200,120);name.setFont(f);name.setOpaque(false);
	    id=new JTextField();id.setSize(350,70);id.setLocation(200,220);id.setFont(f);id.setOpaque(false);
	    mima1=new JPasswordField();mima1.setSize(350,70);mima1.setLocation(200,320);mima1.setFont(f);mima1.setOpaque(false);
	    mima2=new JPasswordField();mima2.setSize(350,70);mima2.setLocation(200,420);mima2.setFont(f);mima2.setOpaque(false);
	    p.add(user);p.add(name);p.add(id);p.add(mima1);p.add(mima2);
	    
	    b1=new JButton("ȷ��");b1.setSize(100,50);b1.setLocation(200,500);b1.setOpaque(false);
	    b2=new JButton("ȡ��");b2.setSize(100,50);b2.setLocation(500,500);b2.setOpaque(false);
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
					JLabel ZooLa = new JLabel("                  Area 1 ����԰                             ");
					JButton[] ZooBt = { new JButton("          �����ѳ�            "), 
							new JButton("           �ֵ�ͻΧ             "), 
							new JButton("            ϣ��֮��             ")};
					
					JPanel Land = new BackgroundPanel(land.getImage());
					JLabel LandLa = new JLabel("            Area 2-1 ½��                      ");
					JButton[] LandBt = { new JButton("           ʹ��͵����              ") ,
							new JButton("           ��ս���Գ�              "),
							new JButton("          ���ط������䳵       "),
							new JButton("           ����Ұζ�͹�          "),
							new JButton("          �ݻ�ëƤ����           ")};

					JPanel Sea = new BackgroundPanel(sea.getImage());
					JLabel SeaLa = new JLabel("                Area 2-2 ����                    ");
					JButton[] SeaBt = { new JButton("              ��ֹ�Ĳ�����           "),
							new JButton("              ӭս������                ") ,
							new JButton("              ����������ҵ            "),
							new JButton("              ���ֺ��ʲ͹�             "),
							new JButton("             ����Σ������              ")};

					JPanel Sky = new BackgroundPanel(sky.getImage());
					JLabel SkyLa = new JLabel("           Area 2-3 ���              ");
					JButton[] SkyBt = { new JButton("         ���������ѵ��ܺ���   "),
							new JButton("          ʹ������                    "),
							new JButton("          ���������г�                "),
							new JButton("          ��ֹ��ɱ����                "),
							new JButton("         �����ϰ���ɻ�             ")};

					JPanel down = new BackgroundPanel(peace.getImage());
					JPanel End = new JPanel();
					JLabel EndLa = new JLabel("         Area 3 ����           ");
					JButton[] EndBt = { new JButton("       ��ս      ") ,
							new JButton("       ��     	"),
							new JButton("       ��ս        ") ,
							new JButton("       ��ս         ") ,
							new JButton("       ���Ѻ�̸          ") };

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
						JOptionPane.showMessageDialog(null,"ע��ɹ�!");
						jf.setVisible(false);
						j.setVisible(true);
					}
					if(re==1){
						JOptionPane.showMessageDialog(null,"�û�������!�����");
						socket.close();
					}
					if(re==2){
						JOptionPane.showMessageDialog(null,"�����ѱ�ע��!�����");
						socket.close();
					}
					if(re==3){
						JOptionPane.showMessageDialog(null,"���֤�ѱ�ע��!�����");
						socket.close();
					}
					}else{
						JOptionPane.showMessageDialog(null,"�����������벻ͬ!");
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
