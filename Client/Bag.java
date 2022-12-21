package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//背包界面
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

// 当前装备
class dataBag extends JTabbedPane {
	int contain = 0;
	int capacity = 20;
	JLabel des = new JLabel("容量      " + contain + "/" + capacity);
	JPanel big = new JPanel();
	JPanel bag = new JPanel();
	Equipment[] item = new Equipment[capacity];
	JTextArea[] display = new JTextArea[capacity];
	dataDes data;
	GridLayout layout = new GridLayout(5, 4, 20, 20);

	public dataBag(dataDes data) {
		this.data = data;//传递数据

		bag.setLayout(layout);//网状布局
		//为每个装备框赋值并排版
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

		add("背包", big);
	}

	// 向背包添加装备
	public void equipAdd(Equipment equip) {
		for (int index = 0; index < capacity; index++) {
			// 该格子没有物品时 添加该物品
			if (item[index] instanceof Blank) {
				if (!(equip instanceof Blank)) {
					item[index] = equip;
					display[index].setText(item[index].print());
					des.setText("容量      " + (++contain) + "/" + capacity);
				}
				return;
			}
		}
		// 没有return 表明背包已满
		JOptionPane.showMessageDialog(null, "背包已满，无法添加装备！", "背包已满", JOptionPane.INFORMATION_MESSAGE);

	}

	// 右键弹出菜单
	public JPopupMenu popup(final int index) {
		final JPopupMenu menu = new JPopupMenu();
		menu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				menu.show();
			}
		});

		// 右键装备功能
		JMenuItem equipV = new JMenuItem("装备");
		equipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animals ani = run.base.members[data.getSelectedIndex()];
				item[index] = ani.changeEquip(item[index]);
				if (item[index] instanceof Blank) {
					display[index].setText("");
					des.setText("容量      " + (--contain) + "/" + capacity);
				} else
					display[index].setText(item[index].print());
			}
		});

		// 右键出售功能
		JMenuItem sellV = new JMenuItem("出售");
		sellV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run.data.money += item[index].cost;
				item[index] = new Blank();
				display[index].setText("");
				des.setText("背包      " + (--contain) + "/" + capacity);
			}
		});

		// 右键丢弃功能
		JMenuItem throwV = new JMenuItem("丢弃");
		throwV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item[index] = new Blank();
				display[index].setText("");
				des.setText("容量      " + (--contain) + "/" + capacity);
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

// 当前属性
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

	// 创建新角色标签
	public void createTab(int index, Animals animal) {
		synchronized (this) { 
		des[index] = new createDes(animal);
		add(animal.name, des[index]);
		}
	}

	// 实时更新
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