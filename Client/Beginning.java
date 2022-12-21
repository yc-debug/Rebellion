package Client;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.swing.*;

//开始界面
public class Beginning extends JFrame {
    JPanel panel = new JPanel();
    JPanel all;
    ImageIcon icon=new ImageIcon("Images/nature.jpg");
	JLabel im = new JLabel(new ImageIcon("Images/1.jpg"));
	JLabel title = new JLabel("Rebellion",JLabel.CENTER);
	JPanel[] panels = new JPanel[4];
	JButton[] buttons = {
		new JButton("单机"),
		new JButton ("在线"),
		new JButton("帮助"),
        new JButton("退出") };
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
		//设置字体
		Font f=new Font("宋体",Font.BOLD,100);
		Font f1=new Font("宋体",Font.BOLD,30);
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
    //帮助界面
	public void helpcre(){
		JTextArea d = new JTextArea();
    	d.setEditable(false);
		d.setOpaque(false);
		d.setLineWrap(true);
    	d.setFont(new Font("",0,25));
    	d.setText(
"一、游戏剧情\n\n"+
"        该项目是一个回合制RPG 游戏，讲述了动物主角对抗人类，自逃出动物园开始，一路与人类对抗，提升能力，并寻找新队友组成团体，逼得人类不得不与之谈判，最终与人类达成了和平协议的故事。玩家可以通过全鼠标点击操控来体验游戏。   \n\n\n"+

"二、游戏基本流程\n\n"+
"        1.初始玩家拥有三个出战角色，在战斗界面Area1中完成三关后，可以平行推进Area 2-1 2-2  2-3 的各个关卡。\n\n"+
"        2.其间根据Area属性的不同会随机接到新队员请求加入的提示，接受后可以在基地安排它们上阵的顺序。\n\n"+
"        3.关卡会随机掉落不同品质的装备，也可以去商店购买，用于增强角色战斗力。\n\n"+
"        4.如果等级不够高，或者装备品质差，很有可能战斗失败，可以重复挑战先前关卡提升等级实力，积攒金钱，不建议跳关。\n\n"+
"        5.Area 2 全部结束后，进行终章Area 3 的关卡。\n");
    	 try {
			final Music music=new Music("Music\\开始.wav");
			JButton back = new JButton("返回");
	    	back.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
			        help.setVisible(false);
		            }
	    	});
	    	JButton l=new JButton("开启背景音乐");
	    	l.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					music.loop();
				}   		
	    	});
	    	JButton stop=new JButton("关闭背景音乐");
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
