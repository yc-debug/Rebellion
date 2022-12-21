package Client;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

//ս������
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

	// HP����
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

	// ȷ����ʼս��ʱ��������״̬
	public void CheckAlive() {
		for (int index = 0; index < run.data.wargroup.length; index++)
			if (run.data.wargroup[index].HP == 0)
				anicount++;
	}

	// ����
	public void MyAttack(Humen[] humans) {
		// ʹ��Ч���󹥻�
		if (run.data.wargroup[current].HP == 0) {
			for (int index = 0; index < run.data.wargroup.length; index++) {
				if (run.data.wargroup[index].HP != 0) {
					des += run.data.wargroup[index].attack(humans, current);
					break;
				}
			}
		} else
			des += run.data.wargroup[current].attack(humans, current);

		// ս����Ϣ����
		EnHP.setText(printHP(humans));
		Description.setText(des);
		// װ���;ý���
		run.data.wargroup[current].enduranceDown();
		// ������˵�����
		hucount = 0;
		for (int index = 0; index < humans.length; index++)
			if (humans[index].HP <= 0) {
				hucount++;
			}
		// ʤ�� ����ȫ������
		if (hucount >= humans.length) {

			ImageIcon victory = new ImageIcon("Images/Victory.JPG");
			Equipment drop = drop();
			// ��þ��� ��Ǯ װ��
			String win = "ս��ʤ��\n��� " + experience + " ���飡\n��� " + gain + " Gold!\n";
			if (drop != null) {
				run.bag.bag.equipAdd(drop);
				win += "���װ����" + drop.name + "\n";
			}
			run.data.money += gain;
			// �ȼ�������Ϣ
			for (int index = 0; index < run.data.wargroup.length; index++)
				if (run.data.wargroup[index].levelUp(experience))
					win += "\n" + run.data.wargroup[index].name + " ���� " + run.data.wargroup[index].level + " ����";
			// ������Ϣ��
			JOptionPane.showMessageDialog(null, win, "ʤ��", JOptionPane.INFORMATION_MESSAGE, victory);
			
			//����³�Ա
			run.base.AddMember(area,process);
			
			this.setVisible(false);
			// ����������
			if (process < run.map.buttons[area].length) {
				run.map.buttons[area][process].setForeground(Color.blue);
			}
			run.map.pack();
			run.map.setVisible(true);
			
		}

	}

	public void EnAttack(Animals[] animal) {
		// ʹ��Ч���󹥻�
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
		// �����ҷ�������
		anicount = 0;
		for (int index = 0; index < animal.length; index++)
			if (animal[index].HP <= 0) {
				anicount++;
			}
		// ʧ�� �ҷ�ȫ������
		if (anicount >= animal.length) {
			//��Ǯ
			if(run.data.money<=lost)
				lost=run.data.money;
			run.data.money-=lost;
			
			ImageIcon lose = new ImageIcon("Images/Lost.JPG");
			JOptionPane.showMessageDialog(null, "ս��ʧ��\nʧȥ " + lost + " Gold!", "ʧ��", JOptionPane.INFORMATION_MESSAGE,
					lose);
			this.setVisible(false);

			run.map.pack();
			run.map.setVisible(true);
		}

	}

	// �����װ��
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

	// ս������
	public JPanel oPanel() {
		JPanel operation = new JPanel();
		JPanel[] op = new JPanel[3];
		buts[0] = new JButton("����");
		buts[1] = new JButton("����(���Ȱ�:" + run.base.aidnum + "/10)");
		buts[2] = new JButton("����");
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
			// ������������
			if (e.getSource() == buts[0]) {
				if(run.data.wargroup[current].name.equals("ʨ")){
					ImageIcon icon = new ImageIcon("Images/lion.gif");//ͼƬ�Ĵ�С��Ҫ���������ʳ̶� 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("��")){
					ImageIcon icon = new ImageIcon("Images/tiger.gif");//ͼƬ�Ĵ�С��Ҫ���������ʳ̶� 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("��")){
					ImageIcon icon = new ImageIcon("Images/bear.gif");//ͼƬ�Ĵ�С��Ҫ���������ʳ̶� 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				if(run.data.wargroup[current].name.equals("��")){
					ImageIcon icon = new ImageIcon("Images/xiang.gif");//ͼƬ�Ĵ�С��Ҫ���������ʳ̶� 
					JOptionPane.showMessageDialog(null, "","",JOptionPane.ERROR_MESSAGE,icon); 
				}
				MyAttack(humans);
				EnAttack(run.data.wargroup);

				MyPic.setIcon(run.data.wargroup[current].image);

			}
			// ���Ƽ�
			else if (e.getSource() == buts[1]) {
				for (int index = 0; index < run.base.Kits.length; index++) {
					if (run.base.Kits[index] != null) {
						run.base.Kits[index].cure(run.data.wargroup[current]);
						run.base.aidnum--;
						des += run.data.wargroup[current].name + " �ظ��� " + run.base.Kits[index].recover + " ����\n";
						Description.setText(des);
						run.base.Kits[index] = null;
						run.base.disaid.setText("���Ȱ�(��������50)��" + run.base.aidnum + "/10");
						buts[1].setText("����(���Ȱ�:" + run.base.aidnum + "/10)");
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
			// ���ܼ�
			else if (e.getSource() == buts[2]) {
				EnAttack(run.data.wargroup);
				dispose();

				run.map.pack();
				run.map.setVisible(true);
			}
		}
	}
}
