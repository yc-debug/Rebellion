package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

//数据界面
public class Data extends JFrame {
	Animals[] wargroup = { new Tiger(), new Lion(), new Bear() };
	int money = 200;
	JTextArea[] Gold = new JTextArea[4];
	JPanel center = new JPanel();
	createDes[] data = new createDes[3];

	public Data(String name) {
		super(name);
		setLayout(new BorderLayout());

		for (int index = 0; index < data.length; index++) {
			data[index] = new createDes(wargroup[index],1);

		}
		for (int index = 0; index < Gold.length; index++) {
			Gold[index] = new JTextArea("Gold:" + money);
			Gold[index].setEditable(false);
		}

		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();

		add(new ActMenu(), BorderLayout.NORTH);

		center.setLayout(new GridLayout(1, 3));
		center.add(data[0]);
		center.add(data[1]);
		center.add(data[2]);
		add(center, BorderLayout.CENTER);

		add(Gold[0], BorderLayout.SOUTH);
	}

	// 实时更新
	private class Description implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(111);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < data.length; index++) {
					if (wargroup[index] != null) {
						data[index].words.setText((wargroup[index].print()));
						data[index].pic.setIcon(wargroup[index].image);
					} else {
						data[index].words.setText("");
						data[index].pic.setIcon(new ImageIcon("Images/Blank.jpg"));
						;
					}
				}
				for (int index = 0; index < Gold.length; index++)
					Gold[index].setText("Gold:" + money);
			}
		}
	}

}

class createDes extends JPanel {
	JLabel pic = new JLabel();

	JTextPane words = new JTextPane();
	JScrollPane jp = new JScrollPane(words);

	JButton unload = new JButton("卸下装备");

	public createDes(final Animals animal) {
		setLayout(new BorderLayout());
		JPanel c = new JPanel();
		JPanel s = new JPanel();

		pic.setIcon(animal.image);

		words.setEditable(false);
		words.setText((animal.print()));
		words.setOpaque(false);

		// 添加鼠标滑轮滚动事件
		jp.getVerticalScrollBar().setUnitIncrement(10);
		jp.setWheelScrollingEnabled(true);

		unload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int index = 0; index < animal.equips.length; index++) {
					if (!(animal.equips[index] instanceof Blank)) {
						run.bag.bag.equipAdd(animal.equips[index]);
						animal.equips[index] = new Blank();
					}
				}
			}
		});

		c.setLayout(new GridLayout(2, 1));
		c.add(pic);
		c.add(jp);
		add(c, BorderLayout.CENTER);
		s.add(unload);
		add(s, BorderLayout.SOUTH);
	}
	
	//第二个构造方法，为了消除战斗组中下阵无法取消“卸下装备”按钮，再次上阵按钮关联有误的BUG
	public createDes(Animals animal,int index) {
		setLayout(new BorderLayout());
		JPanel c = new JPanel();

		pic.setIcon(animal.image);

		words.setEditable(false);
		words.setText((animal.print()));
		words.setOpaque(false);

		// 添加鼠标滑轮滚动事件
		jp.getVerticalScrollBar().setUnitIncrement(10);
		jp.setWheelScrollingEnabled(true);

		c.setLayout(new GridLayout(2, 1));
		c.add(pic);
		c.add(jp);
		add(c, BorderLayout.CENTER);
	}
}
