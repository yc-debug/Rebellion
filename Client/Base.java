package Client;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Base extends JFrame {
	JPanel base = new JPanel();
	JScrollPane home = new JScrollPane(base);
	Animals[] members = new Animals[999];
	createDes[] des = new createDes[members.length];
	JButton[] gotowar = new JButton[members.length];
	JButton[] gotorest = new JButton[members.length];

	JButton cure = new JButton("     治疗所有            ");
	JPanel aid = new JPanel();
	JPanel c = new JPanel();
	JPanel b = new JPanel();
	int aidnum = 0;
	JLabel disaid = new JLabel("急救包(单体治疗50)：0/10", JLabel.CENTER);
	cureKit[] Kits = new cureKit[10];
	JButton buyaid = new JButton("       购买急救包(100G)          ");

	public Base(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		base.setLayout(new GridLayout(0, 2, 15, 15));
		for (int index = 0; index < run.data.wargroup.length; index++) {
			members[index] = run.data.wargroup[index];
			des[index] = createCharacter(index, members[index]);
			gotowar[index].setEnabled(false);
			gotowar[index].setLabel("已上阵");
			gotorest[index].setEnabled(true);
			gotorest[index].setLabel("休息");
			base.add(des[index]);
		}

		// 添加鼠标滑轮滚动事件
		home.getVerticalScrollBar().setUnitIncrement(10);
		home.setWheelScrollingEnabled(true);

		cure.addActionListener(new cure());
		aid.setLayout(new GridLayout(4, 1));
		aid.add(new JLabel(new ImageIcon("Images/cure.jpg")));
		c.setLayout(new GridLayout(2, 1));
		c.add(new JLabel());
		JPanel s = new JPanel();
		s.add(cure);
		c.add(s);
		aid.add(c);
		aid.add(disaid);
		buyaid.addActionListener(new buyKit());
		b.add(buyaid);
		aid.add(b);

		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();

		add(home, BorderLayout.CENTER);
		add(aid, BorderLayout.EAST);
		add(run.data.Gold[3], BorderLayout.SOUTH);
	}

	// 创建角色面板
	public createDes createCharacter(int index, Animals animal) {
		createDes basepanel = new createDes(animal);
		JPanel big = new JPanel();
		JPanel c = new JPanel();
		JPanel ss = new JPanel();

		JPanel sgo = new JPanel();

		sgo.setLayout(new GridLayout(1, 2));
		gotowar[index] = new JButton("上阵");
		gotowar[index].addActionListener(new go());
		sgo.add(gotowar[index]);

		gotorest[index] = new JButton("休息中");
		gotorest[index].setEnabled(false);
		gotorest[index].addActionListener(new go());
		sgo.add(gotorest[index]);

		c.setLayout(new GridLayout(2, 1));
		c.add(basepanel.pic);
		c.add(basepanel.jp);

		big.setLayout(new BorderLayout());
		big.add(c, BorderLayout.CENTER);
		ss.add(basepanel.unload);
		big.add(ss, BorderLayout.SOUTH);

		basepanel.setLayout(new BorderLayout());
		basepanel.add(big, BorderLayout.CENTER);
		basepanel.add(sgo, BorderLayout.SOUTH);

		return basepanel;
	}

	// 上下阵监听器
	private class go implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int index = 0; index < members.length; index++)
				if (e.getSource() == gotowar[index]) {
					for (int count = 0; count < run.data.wargroup.length; count++)
						if (run.data.wargroup[count] == null) {
							run.data.wargroup[count] = members[index];
							gotowar[index].setEnabled(false);
							gotowar[index].setLabel("已上阵");
							gotorest[index].setEnabled(true);
							gotorest[index].setLabel("休息");
							break;
						}
				} else if (e.getSource() == gotorest[index]) {
					for (int count = 0; count < run.data.wargroup.length; count++)
						if (run.data.wargroup[count] == (members[index])) {
							run.data.wargroup[count] = null;
							gotorest[index].setEnabled(false);
							gotorest[index].setLabel("休息中");
							gotowar[index].setEnabled(true);
							gotowar[index].setLabel("上阵");
							break;
						}
				}

		}
	}

	// 加入新成员
	public void AddMember(int area, int process) {
		Animals newmember = null;
		// 每个区域第一关获得新成员
		if (process == 0) {
			switch (area) {
			case 1:
				newmember = new Elephant();
				break;
			case 2:
				newmember = new Whale();
				break;
			case 3:
				newmember = new Eagle();
				break;
			}
		}
		// 其余关随机可能性获得成员
		else {
			Random generator = new Random();
			int rand = generator.nextInt(10);
			if (rand == 5) {
				switch (area) {
				case 1:
					newmember = new Elephant();
					break;
				case 2:
					newmember = new Whale();
					break;
				case 3:
					newmember = new Eagle();
					break;
				}
			}
		}
		// 弹出确认框 选中“是”返回0,选中“否”返回 1
		if (newmember != null) {
			int n = JOptionPane.showConfirmDialog(null, newmember.name + " 想要加入你的队伍！\n" + newmember.print() + "\n是否同意?",
					"新成员", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, newmember.image);
			if (n == 0)
				for (int index = 0; index < members.length; index++)
					if (members[index] == null) {
						run.shop.cha.createTab(index, newmember);
						run.bag.data.createTab(index, newmember);
						des[index] = createCharacter(index, newmember);
						base.add(des[index]);
						// 最后添加成员 防止线程出现空指向
						members[index] = newmember;
						break;
					}
		}
	}

	// 实时更新
	private class Description implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(108);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null) {
						des[index].words.setText((run.base.members[index].print()));
					}
				}
			}
		}
	}

	// 治疗
	private class cure implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!isFullHealth(members)) {
				int cost10 = 0, cost;
				for (int index = 0; index < members.length; index++) {
					if (members[index] != null) {
						cost10 += members[index].TotalHP - members[index].HP;
						members[index].HP = members[index].TotalHP;
					}
				}
				cost = cost10 / 10;
				run.data.money -= cost;

				JOptionPane.showMessageDialog(null, "治疗成功！\n失去 " + cost + " Gold!", "治疗结束",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "血量为满，无需治疗！\n,", "治疗结束", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	// 检验是否全为满血
	public boolean isFullHealth(Animals[] animal) {
		boolean is = true;
		for (int index = 0; index < animal.length; index++) {
			if (animal[index] != null && animal[index].HP != animal[index].TotalHP)
				is = false;
		}
		return is;
	}

	// 购买急救包
	private class buyKit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (run.data.money >= 100) {
				for (int index = 0; index < Kits.length; index++) {
					if (Kits[index] == null) {
						Kits[index] = new cureKit(50);
						aidnum++;
						disaid.setText("急救包(单体治疗50)：" + aidnum + "/10");
						run.data.money -= 100;
						break;
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "金钱不足，无法购买！", "无法购买", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}

class cureKit {
	int recover;

	public cureKit(int recover) {
		this.recover = recover;
	}

	public void cure(Animals animal) {
		if (animal.TotalHP - animal.HP >= recover)
			animal.HP += recover;
		else
			animal.HP += animal.TotalHP - animal.HP;
	}
}
