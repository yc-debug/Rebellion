package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��������
public class Bag extends JFrame {
	JPanel center = new JPanel();
	dataDes data = new dataDes();
	dataBag bag = new dataBag(data);

	public Bag(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		center.setLayout(new GridLayout(1, 2));
		center.add(bag);
		center.add(data);
		add(center, BorderLayout.CENTER);

		add(run.data.Gold[1], BorderLayout.SOUTH);

	}
}

// ��ǰװ��
class dataBag extends JTabbedPane {
	int contain = 0;
	int capacity = 20;
	JLabel des = new JLabel("����      " + contain + "/" + capacity);
	JPanel big = new JPanel();
	JPanel bag = new JPanel();
	Equipment[] item = new Equipment[capacity];
	JTextArea[] display = new JTextArea[capacity];
	dataDes data;
	GridLayout layout = new GridLayout(5, 4, 20, 20);

	public dataBag(dataDes data) {
		this.data = data;//��������

		bag.setLayout(layout);//��״����
		//Ϊÿ��װ����ֵ���Ű�
		for (int index = 0; index < capacity; index++) {
			item[index] = new Blank();
			display[index] = new JTextArea();
			display[index].setEditable(false);
			display[index].setComponentPopupMenu(popup(index));
			bag.add(display[index]);
		}

		big.setLayout(new BorderLayout());
		big.add(bag, BorderLayout.CENTER);
		big.add(des, BorderLayout.SOUTH);

		add("����", big);
	}

	// �򱳰����װ��
	public void equipAdd(Equipment equip) {
		for (int index = 0; index < capacity; index++) {
			// �ø���û����Ʒʱ ��Ӹ���Ʒ
			if (item[index] instanceof Blank) {
				if (!(equip instanceof Blank)) {
					item[index] = equip;
					display[index].setText(item[index].print());
					des.setText("����      " + (++contain) + "/" + capacity);
				}
				return;
			}
		}
		// û��return ������������
		JOptionPane.showMessageDialog(null, "�����������޷����װ����", "��������", JOptionPane.INFORMATION_MESSAGE);

	}

	// �Ҽ������˵�
	public JPopupMenu popup(final int index) {
		final JPopupMenu menu = new JPopupMenu();
		menu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				menu.show();
			}
		});

		// �Ҽ�װ������
		JMenuItem equipV = new JMenuItem("װ��");
		equipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animals ani = run.base.members[data.getSelectedIndex()];
				item[index] = ani.changeEquip(item[index]);
				if (item[index] instanceof Blank) {
					display[index].setText("");
					des.setText("����      " + (--contain) + "/" + capacity);
				} else
					display[index].setText(item[index].print());
			}
		});

		// �Ҽ����۹���
		JMenuItem sellV = new JMenuItem("����");
		sellV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run.data.money += item[index].cost;
				item[index] = new Blank();
				display[index].setText("");
				des.setText("����      " + (--contain) + "/" + capacity);
			}
		});

		// �Ҽ���������
		JMenuItem throwV = new JMenuItem("����");
		throwV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item[index] = new Blank();
				display[index].setText("");
				des.setText("����      " + (--contain) + "/" + capacity);
			}
		});

		for (int count = 0; count < capacity; count++) {
			menu.add(equipV);
			menu.add(sellV);
			menu.add(throwV);
		}
		return menu;
	}

}

// ��ǰ����
class dataDes extends JTabbedPane {
	createDes[] des = new createDes[run.base.members.length];

	public dataDes() {

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
		synchronized (this) { 
		des[index] = new createDes(animal);
		add(animal.name, des[index]);
		}
	}

	// ʵʱ����
	private class Description implements Runnable {
		public void run() {
			while (true) {

				try {
					Thread.sleep(107);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null)
						des[index].words.setText((run.base.members[index].print()));
				}
			}
		}
	}
}