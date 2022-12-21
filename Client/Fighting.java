package Client;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

//战斗界面
public class Fighting extends JFrame {

	int hucount = 0, anicount = 0, current = 0, gain, lost, area, process, experience;
	Humen[] humans;
	JPanel fighting = new JPanel();
	JTextArea EnHP = new JTextArea();
	JTextArea MyHP = new JTextArea();
	JLabel EnPic = new JLabel();
	JLabel MyPic = new JLabel(run.data.wargroup[0].image);
	String des = "";
	JTextArea Description = new JTextArea(des);
	JScrollPane jp = new JScrollPane(Description);
	JButton[] buts = new JButton[3];

	public Fighting(String name, int area, int process, Humen[] humans) {
		super(name);
		this.area = area;
		this.process = process;
		this.humans = humans;
		this.gain = (area + 1) * 100 + process * 20;
		this.lost = (area + 1) * 50 + process * 10;
		this.experience = (area + 1) * 50 + process * 10;
		EnPic.setIcon(humans[0].image);

		CheckAlive();
		fighting.setLayout(new GridLayout(3, 2));

		EnHP.setEditable(false);
		EnHP.setOpaque(false);
		MyHP.setEditable(false);
		MyHP.setOpaque(false);
		EnHP.setText(printHP(humans));
		MyHP.setText(printHP(run.data.wargroup));
		fighting.add(EnHP);
		fighting.add(MyHP);

		fighting.add(EnPic);
		fighting.add(MyPic);

		Description.setEditable(false);
		Description.setOpaque(false);
		fighting.add(jp);

		fighting.add(oPanel());
		add(fighting);

	}

	// HP界面
	public String printHP(Animals[] animal) {
		String HP = "";
		for (int index = 0; index < animal.length; index++) {
			HP += animal[index].name + "   HP:" + animal[index].HP + "/" + animal[index].TotalHP + "   level:"
					+ animal[index].level + "\n\n";
		}
		return HP;
	}

	public String printHP(Humen[] humans) {
		String HP = "";
		for (int index = 0; index < humans.length; index++) {
			HP += humans[index].name + " HP:" + humans[index].HP + "/" + humans[index].TotalHP + "\n\n";
		}
		return HP;
	}

	// 确定开始战斗时本方生存状态
	public void CheckAlive() {
		for (int index = 0; index < run.data.wargroup.length; index++)
			if (run.data.wargroup[index].HP == 0)
				anicount++;
	}

	// 攻击
	public void MyAttack(Humen[] humans) {
		// 使有效对象攻击
		if (run.data.wargroup[current].HP == 0) {
			for (int index = 0; index < run.data.wargroup.length; index++) {
				if (run.data.wargroup[index].HP != 0) {
					des += run.data.wargroup[index].attack(humans, current);
					break;
				}
			}
		} else
			des += run.data.wargroup[current].attack(humans, current);

		// 战斗信息描述
		EnHP.setText(printHP(humans));
		Description.setText(des);
		// 装备耐久降低
		run.data.wargroup[current].enduranceDown();
		// 计算敌人倒下数
		hucount = 0;
		for (int index = 0; index < humans.length; index++)
			if (humans[index].HP <= 0) {
				hucount++;
			}
		// 胜利 敌人全部倒下
		if (hucount >= humans.length) {

			ImageIcon victory = new ImageIcon("Images/Victory.JPG");
			Equipment drop = drop();
			// 获得经验 金钱 装备
			String win = "战斗胜利\n获得 " + experience + " 经验！\n获得 " + gain + " Gold!\n";
			if (drop != null) {
				run.bag.bag.equipAdd(drop);
				win += "获得装备：" + drop.name + "\n";
			}
			run.data.money += gain;
			// 等级提升信息
			for (int index = 0; index < run.data.wargroup.length; index++)
				if (run.data.wargroup[index].levelUp(experience))
					win += "\n" + run.data.wargroup[index].name + " 升到 " + run.data.wargroup[index].level + " 级！";
			// 弹出信息框
			JOptionPane.showMessageDialog(null, win, "胜利", JOptionPane.INFORMATION_MESSAGE, victory);
			
			//获得新成员
			run.base.AddMember(area,process);
			
			this.setVisible(false);
			// 本关已征服
			if (process < run.map.buttons[area].length) {
				run.map.buttons[area][process].setForeground(Color.blue);
			}
			run.map.pack();
			run.map.setVisible(true);
			
		}

	}

	public void EnAttack(Animals[] animal) {
		// 使有效对象攻击
		if (humans[current].HP == 0) {
			for (int index = 0; index < humans.length; index++) {
				if (humans[index].HP != 0) {
					des += humans[index].attack(animal, current);
					break;
				}
			}
		} else
			des += humans[current].attack(animal, current);

		MyHP.setText(printHP(run.data.wargroup));
		Description.setText(des);
		current++;
		if (current % 3 == 0)
			current = 0;
		// 计算我方倒下数
		anicount = 0;
		for (int index = 0; index < animal.length; index++)
			if (animal[index].HP <= 0) {
				anicount++;
			}
		// 失败 我方全部倒下
		if (anicount >= animal.length) {
			//扣钱
			if(run.data.money<=lost)
				lost=run.data.money;
			run.data.money-=lost;
			
			ImageIcon lose = new ImageIcon("Images/Lost.JPG");
			JOptionPane.showMessageDialog(null, "战斗失败\n失去 " + lost + " Gold!", "失败", JOptionPane.INFORMATION_MESSAGE,
					lose);
			this.setVisible(false);

			run.map.pack();
			run.map.setVisible(true);
		}

	}

	// 随机掉装备
	public Equipment drop() {
		Equipment drop = null;
		Random gene = new Random();
		int rand = gene.nextInt(25) + 1;
		if (rand == 5)
			drop = new Teeth().changeQuality();
		else if (rand == 10)
			drop = new Claws().changeQuality();
		else if (rand == 15)
			drop = new Helmet().changeQuality();
		else if (rand == 20)
			drop = new Breastplate().changeQuality();
		else if (rand == 25)
			drop = new Kneecap().changeQuality();

		return drop;
	}

	// 战斗操作
	public JPanel oPanel() {
		JPanel operation = new JPanel();
		JPanel[] op = new JPanel[3];
		buts[0] = new JButton("攻击");
		buts[1] = new JButton("治疗(急救包:" + run.base.aidnum + "/10)");
		buts[2] = new JButton("逃跑");
		operation.setLayout(new GridLayout(3, 1));
		for (int index = 0; index < buts.length; index++) {
			buts[index].addActionListener(new attackLis());
			op[index] = new JPanel();
			op[index].add(buts[index]);
			operation.add(op[index]);
		}
		return operation;
	}

	private class attackLis implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 攻击键监听器
			if (e.getSource() == buts[0]) {
				if(run.data.wargroup[current].name.equals("狮")){
					ImageIcon icon = new ImageIcon("Images/lion.gif");//图片的大小需要调整到合适程度 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("虎")){
					ImageIcon icon = new ImageIcon("Images/tiger.gif");//图片的大小需要调整到合适程度 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("熊")){
					ImageIcon icon = new ImageIcon("Images/bear.gif");//图片的大小需要调整到合适程度 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("象")){
					ImageIcon icon = new ImageIcon("Images/xiang.gif");//图片的大小需要调整到合适程度 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				MyAttack(humans);
				EnAttack(run.data.wargroup);

				MyPic.setIcon(run.data.wargroup[current].image);

			}
			// 治疗键
			else if (e.getSource() == buts[1]) {
				for (int index = 0; index < run.base.Kits.length; index++) {
					if (run.base.Kits[index] != null) {
						run.base.Kits[index].cure(run.data.wargroup[current]);
						run.base.aidnum--;
						des += run.data.wargroup[current].name + " 回复了 " + run.base.Kits[index].recover + " 生命\n";
						Description.setText(des);
						run.base.Kits[index] = null;
						run.base.disaid.setText("急救包(单体治疗50)：" + run.base.aidnum + "/10");
						buts[1].setText("治疗(急救包:" + run.base.aidnum + "/10)");
						break;
					}
				}
				EnAttack(run.data.wargroup);
				current++;
				if (current % 3 == 0)
					current = 0;
				MyPic.setIcon(run.data.wargroup[current].image);
				EnHP.setText(printHP(humans));
				MyHP.setText(printHP(run.data.wargroup));
			}
			// 逃跑键
			else if (e.getSource() == buts[2]) {
				EnAttack(run.data.wargroup);
				dispose();

				run.map.pack();
				run.map.setVisible(true);
			}
		}
	}
}
