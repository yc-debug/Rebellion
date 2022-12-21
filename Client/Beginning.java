package Client;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.swing.*;

//��ʼ����
public class Beginning extends JFrame {
    JPanel panel = new JPanel();
    JPanel all;
    ImageIcon icon=new ImageIcon("Images/nature.jpg");
	JLabel im = new JLabel(new ImageIcon("Images/1.jpg"));
	JLabel title = new JLabel("Rebellion",JLabel.CENTER);
	JPanel[] panels = new JPanel[4];
	JButton[] buttons = {
		new JButton("����"),
		new JButton ("����"),
		new JButton("����"),
        new JButton("�˳�") };
	JFrame help = new JFrame();
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
	public Beginning(String name) {
		super(name);
		all=new BackgroundPanel(icon.getImage());
		all.setLayout(new GridLayout(3,1));
		//��������
		Font f=new Font("����",Font.BOLD,100);
		Font f1=new Font("����",Font.BOLD,30);
		title.setFont(new Font("rebellion",Font.BOLD,150) );
	    title.setForeground(Color.orange);
	    title.setOpaque(false);
		all.add(title);
		all.add(im);
		
		helpcre();
		
		panel.setLayout(new GridLayout(3,2));
		for(int index=0;index<buttons.length;index++){
			buttons[index].addActionListener(new BeginLis());
			buttons[index].setFont(f1);
			panels[index]=new JPanel();
			panels[index].add(buttons[index]);
			panels[index].setOpaque(false);
			panel.add(panels[index]);
			
		}
		panel.setOpaque(false);	
		all.add(panel);	
		getContentPane().add(all);	
	}
    //��������
	public void helpcre(){
		JTextArea d = new JTextArea();
    	d.setEditable(false);
		d.setOpaque(false);
		d.setLineWrap(true);
    	d.setFont(new Font("",0,25));
    	d.setText(
"һ����Ϸ����\n\n"+
"        ����Ŀ��һ���غ���RPG ��Ϸ�������˶������ǶԿ����࣬���ӳ�����԰��ʼ��һ·������Կ���������������Ѱ���¶���������壬�Ƶ����಻�ò���̸֮�У��������������˺�ƽЭ��Ĺ��¡���ҿ���ͨ��ȫ������ٿ���������Ϸ��   \n\n\n"+

"������Ϸ��������\n\n"+
"        1.��ʼ���ӵ��������ս��ɫ����ս������Area1��������غ󣬿���ƽ���ƽ�Area 2-1 2-2  2-3 �ĸ����ؿ���\n\n"+
"        2.������Area���ԵĲ�ͬ������ӵ��¶�Ա����������ʾ�����ܺ�����ڻ��ذ������������˳��\n\n"+
"        3.�ؿ���������䲻ͬƷ�ʵ�װ����Ҳ����ȥ�̵깺��������ǿ��ɫս������\n\n"+
"        4.����ȼ������ߣ�����װ��Ʒ�ʲ���п���ս��ʧ�ܣ������ظ���ս��ǰ�ؿ������ȼ�ʵ�������ܽ�Ǯ�����������ء�\n\n"+
"        5.Area 2 ȫ�������󣬽�������Area 3 �Ĺؿ���\n");
    	 try {
			final Music music=new Music("Music\\��ʼ.wav");
			JButton back = new JButton("����");
	    	back.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
			        help.setVisible(false);
		            }
	    	});
	    	JButton l=new JButton("������������");
	    	l.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					music.loop();
				}   		
	    	});
	    	JButton stop=new JButton("�رձ�������");
	    	stop.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					music.stop();
				}   		
	    	});
	    	JPanel s = new JPanel(new FlowLayout());
	    	s.add(back);s.add(l);s.add(stop);
	    	
	    	help.setLayout(new BorderLayout());
	    	help.add(d,BorderLayout.CENTER);
	    	help.add(s,BorderLayout.SOUTH);
	    	
	    	help.setPreferredSize(new Dimension(1000,900));
	        run.screencentre(help);
	        help.pack();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	}
	
    private class BeginLis implements ActionListener {

	    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==buttons[0]){
            	setVisible(false);
            	run.frames[1].setVisible(true);
            }
            else if(e.getSource()==buttons[1]){
            	setVisible(false);
//            	run.frames[1].setVisible(true);
            	start k=new start();
            	try {
					k.jframe();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if(e.getSource()==buttons[2]){
                help.setVisible(true);
            }
            else if(e.getSource()==buttons[3]){
            	System.exit(0);
            }
		}

	}
}
