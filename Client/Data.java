package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

//���ݽ���
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

	// ʵʱ����
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

	JButton unload = new JButton("ж��װ��");

	public createDes(final Animals animal) {
		setLayout(new BorderLayout());
		JPanel c = new JPanel();
		JPanel s = new JPanel();

		pic.setIcon(animal.image);

		words.setEditable(false);
		words.setText((animal.print()));
		words.setOpaque(false);

		// �����껬�ֹ����¼�
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
	
	//�ڶ������췽����Ϊ������ս�����������޷�ȡ����ж��װ������ť���ٴ�����ť���������BUG
	public createDes(Animals animal,int index) {
		setLayout(new BorderLayout());
		JPanel c = new JPanel();

		pic.setIcon(animal.image);

		words.setEditable(false);
		words.setText((animal.print()));
		words.setOpaque(false);

		// �����껬�ֹ����¼�
		jp.getVerticalScrollBar().setUnitIncrement(10);
		jp.setWheelScrollingEnabled(true);

		c.setLayout(new GridLayout(2, 1));
		c.add(pic);
		c.add(jp);
		add(c, BorderLayout.CENTER);
	}
}
