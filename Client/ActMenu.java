package Client;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.swing.*;

public class ActMenu extends JPanel implements Serializable {
	//创建按登陆后的界面的北部一行选择按钮
	JFrame options = new JFrame("选项");

	JButton[] buttons = { new JButton("战斗"), new JButton("当前出战"), new JButton("背包"), new JButton("商店"),
			new JButton("基地"), new JButton("选项") };
	//登陆后界面上端的选择按钮
	JButton[] ops = { new JButton("返回主界面"),new JButton("保存游戏"), new JButton("在线模式"),new JButton("  帮助  ")};
	//选项被点击后出来的界面
	public ActMenu() {
 
		setLayout(new FlowLayout());
		//流式布局
		options(options);
		//为顶端的选择按钮添加监听器
		for (int index = 0; index < buttons.length; index++)
			buttons[index].addActionListener(new ActLis());

		for (int index = 0; index < buttons.length; index++)
			add(buttons[index]);

	}

	public void shutdown() {
		//关闭所有界面
		for (int index = 0; index < run.frames.length; index++)
			run.frames[index].setVisible(false);
	}

	private class ActLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttons[0]) {
				shutdown();
				run.frames[1].setVisible(true);
			} else if (e.getSource() == buttons[1]) {
				shutdown();
				run.frames[2].setVisible(true);
			} else if (e.getSource() == buttons[2]) {
				shutdown();
				run.frames[3].setVisible(true);
			} else if (e.getSource() == buttons[3]) {
				shutdown();
				run.frames[4].setVisible(true);
			} else if (e.getSource() == buttons[4]) {
				shutdown();
				run.frames[5].setVisible(true);
				
			} else if (e.getSource() == buttons[5]) {
				options.setVisible(true);

			}
		}
	}
	
	//选项按钮
	
	public void options(JFrame options) {
		//为选项界面初始化并布局
		JPanel[] three = new JPanel[4];
		
		options.setLayout(new GridLayout(3, 1));
		for (int index = 0; index < three.length; index++) {
			three[index] = new JPanel();
			ops[index].addActionListener(new OpLis());
			//给选项界面的四个选择按钮添加监听器
			three[index].add(ops[index]);
			options.add(three[index]);
		}
		options.setPreferredSize(new Dimension(300, 200));
		run.screencentre(options);
		options.pack();
		
		
	}
	private class OpLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ops[0]) {
				options.setVisible(false);
				run.frames[1].setVisible(false);
				run.frames[0].setVisible(true);
			}
			else if(e.getSource()==ops[1]){
				int result = 0;
				try {
					result = S_L.Save();
				} catch (ConnectException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				options.setVisible(false);
				if(result == 1) {
					run.frames[1].setVisible(false);
					run.frames[0].setVisible(true);
				}
				
			}
			else if(e.getSource()==ops[2]){
				shutdown();
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
				//JOptionPane.showMessageDialog(null, "存档已载入", "读取存档", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==ops[3]){
				run.begin.help.setVisible(true);
				options.setVisible(false);
			}
		}
	}
	
}
