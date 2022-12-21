package Client;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//�̵����
public class Shop extends JFrame {
	JPanel test = new JPanel();
	store st = new store();
	character cha = new character(st);

	public Shop(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		test.setLayout(new GridLayout(1, 2));
		test.add(st);
		test.add(cha);

		add(test);
		add(run.data.Gold[2], BorderLayout.SOUTH);
	}
}

// �̵����
class store extends JTabbedPane {

	JPanel buy = new JPanel();
	JButton refresh = new JButton("ˢ�µ�ǰ��Ʒ ( 1500 G )");
	JButton buybag = new JButton("����(��ӵ�����)");

	Equipment[] equip = { new Teeth().changeQuality(), new Claws().changeQuality(), new Helmet().changeQuality(),
			new Breastplate().changeQuality(), new Kneecap().changeQuality() };
	JCheckBox[] des = { new JCheckBox(equip[0].print()), new JCheckBox(equip[1].print()),
			new JCheckBox(equip[2].print()), new JCheckBox(equip[3].print()), new JCheckBox(equip[4].print()) };

	public store() {
		buy();
		add("����", buy);
	}

	// �������
	public void buy() {
		buy.setLayout(new GridLayout(7, 1));
		for (int index = 0; index < des.length; index++)
			buy.add(des[index]);

		// ����ť
		buybag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment[] purchase = getEquip();
				for (int index = 0; index < des.length; index++)
					des[index].setSelected(false);
				int sumcost = 0;

				// �ܼ�
				for (int count = 0; count < purchase.length; count++)
					if (purchase[count] != null) {
						sumcost += purchase[count].cost;
					}
				// ��ӵ�����
				if (run.data.money >= sumcost) {
					for (int count = 0; count < purchase.length; count++) {
						if (purchase[count] != null) {
							run.data.money -= purchase[count].cost;
							run.bag.bag.equipAdd(purchase[count]);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "��Ǯ���㣬�޷�����", "�޷�����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// ˢ���̵����
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (run.data.money >= 1500) {
						rollE();
						for (int index = 0; index < des.length; index++)
							des[index].setSelected(false);
						run.data.money -= 1500;
					} else
						JOptionPane.showMessageDialog(null, "��Ǯ���㣬�޷�ˢ�£�", "�޷�ˢ��", JOptionPane.INFORMATION_MESSAGE);
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JPanel b = new JPanel();
		JPanel r = new JPanel();
		b.add(buybag);
		r.add(refresh);
		buy.add(b);
		buy.add(r);
	}

	// ����ı�װ��Ʒ��
	public void rollE() throws InstantiationException, IllegalAccessException {
		for (int index = 0; index < equip.length; index++) {
			equip[index] = ((Equipment) equip[index].getClass().newInstance()).changeQuality();
			des[index].setText(equip[index].print());
		}
	}

	// �õ���ѡ��ѡ�е�װ��
	public Equipment[] getEquip() {
		Equipment[] purchased = new Equipment[5];
		for (int index = 0; index < equip.length; index++) {
			if (des[index].isSelected())
				purchased[index] = equip[index];
		}
		return purchased;
	}
}

// ��ɫ���
class character extends JTabbedPane {
	final int num = run.base.members.length;
	JPanel[] ani = new JPanel[num];
	JButton[] buyFor = new JButton[num];
	JPanel[] buy = new JPanel[num];
	JButton[] fixFor = new JButton[num];
	JPanel[] fix = new JPanel[num];
	createDes[] des = new createDes[num];
	store st;

	public character(store st) {
		this.st = st;
		for (int index = 0; index < run.base.members.length; index++) {
			if (run.base.members[index] != null) {
				createTab(index, run.base.members[index]);
			}
		}
		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();
	}

	// �����½�ɫ��ǩ
	public void createTab(int index, Animals animal) {
		buyFor[index] = new JButton("Ϊ������");
		buyFor[index].addActionListener(new buyListener());
		buy[index] = new JPanel();
		buy[index].add(buyFor[index]);

		des[index] = new createDes(animal);

		fixFor[index] = new JButton("����װ��");
		fixFor[index].addActionListener(new fixListener());
		fix[index] = new JPanel();
		fix[index].add(fixFor[index]);

		ani[index] = new JPanel();
		ani[index].setLayout(new BorderLayout());
		ani[index].add(buy[index], BorderLayout.NORTH);
		ani[index].add(des[index], BorderLayout.CENTER);
		ani[index].add(fix[index], BorderLayout.SOUTH);

		add(animal.name, ani[index]);
	}

	// ʵʱ����
	private class Description implements Runnable {
		public void run() {
			while (true) {

				try {
					Thread.sleep(109);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null) {
						des[index].words.setText((run.base.members[index].print()));
						setTitleAt(index, run.base.members[index].name);
					}
				}
			}

		}
	}

	// ����ť
	private class buyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Equipment[] purchase = st.getEquip();
			for (int index = 0; index < st.des.length; index++)
				st.des[index].setSelected(false);
			int aninum = 0, sumcost = 0;
			// Ϊ˭����
			for (int index = 0; index < run.base.members.length; index++) {
				if (e.getSource() == buyFor[index]) {
					aninum = index;
				}
			}
			// �ܼ�
			for (int count = 0; count < purchase.length; count++)
				if (purchase[count] != null) {
					sumcost += purchase[count].cost;
				}
			// �������
			if (run.data.money >= sumcost) {
				for (int count = 0; count < purchase.length; count++) {
					if (purchase[count] != null) {
						run.data.money -= purchase[count].cost;
						// ���Ϲ����װ���������µķ��뱳��
						run.bag.bag.equipAdd(run.base.members[aninum].changeEquip(purchase[count]));
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "��Ǯ���㣬�޷�����", "�޷�����", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	// ����ť
	private class fixListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int aninum = 0, sumcost = 0;

			// Ϊ˭����
			for (int index = 0; index < run.base.members.length; index++) {
				if (e.getSource() == fixFor[index]) {
					aninum = index;
				}
			}

			Equipment[] equip = run.base.members[aninum].equips;
			// ����
			for (int index = 0; index < equip.length; index++) {
				if (equip[index] != null) {
					equip[index].endurance++;
					run.data.money--;
				}
			}
		}
	}
}
