package Client;

import javax.swing.ImageIcon;

public class Humen {
	final int TotalHP;
	protected int HP, attack, defence;
	ImageIcon image;
	String name;

	public Humen(int HP, int attack, int defence) {
		this.TotalHP = HP;
		this.HP = HP;
		this.attack = attack;
		this.defence = defence;
	}

	public String attack(Animals[] animal, int current) {
		int hurt;
		// ������Ч����
		if (animal[current].HP == 0) {
			for (int index = 0; index < animal.length; index++) {
				if (animal[index].HP != 0) {
					current = index;
					break;
				}
			}
		}
		if (this.attack > animal[current].defence) {
			animal[current].HP -= (attack - animal[current].defence);
			hurt = attack - animal[current].defence;
			if (animal[current].HP < 0) {
				hurt-= 0 - animal[current].HP;
				animal[current].HP = 0;
			}
			return name + "�� " + animal[current].name + " �����" + hurt + "�˺�\n\n";
		} else {
			return name + "�Ĺ���δ���Ʒ���\n\n";
		}
	}

	public void setName(int sum) {
		this.name += " " + (sum + 1) + " ";
	}
}

class HumenZoo extends Humen {
	public HumenZoo(int process) {
		super(200 + process * 10, 25 + process * 2, 5 + process);
		switch (process) {
		case 0:
			this.name = "����԰����Ա";
			this.image = new ImageIcon("Images/ZooFeeders.JPG");
			break;
		case 1:
			this.name = "����԰�ο�";
			this.image = new ImageIcon("Images/ZooTourists.JPG");
			break;
		case 2:
			this.name = "����԰����";
			this.image = new ImageIcon("Images/ZooGuards.JPG");
			break;
		}

	}
}

class HumenLand extends Humen {
	public HumenLand(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "͵����";
			this.image = new ImageIcon("Images/LandPoacher.JPG");
			break;
		case 1:
			this.name = "������";
			this.image = new ImageIcon("Images/LandHunters.JPG");
			break;
		case 2:
			this.name = "����˾��";
			this.image = new ImageIcon("Images/LandCar.JPG");
			break;
		case 3:
			this.name = "Ұζ�͹��ϰ�";
			this.image = new ImageIcon("Images/LandRes.JPG");
			break;
		case 4:
			this.name = "��������";
			this.image = new ImageIcon("Images/LandGuards.JPG");
			break;
		}
	}
}

class HumenSea extends Humen {
	public HumenSea(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "����";
			this.image = new ImageIcon("Images/SeaFishing.JPG");
			break;
		case 1:
			this.name = "������";
			this.image = new ImageIcon("Images/SeaShip.JPG");
			break;
		case 2:
			this.name = "��������";
			this.image = new ImageIcon("Images/SeaGuards.JPG");
			break;
		case 3:
			this.name = "���ʲ͹�Ա��";
			this.image = new ImageIcon("Images/SeaStaff.JPG");
			break;
		case 4:
			this.name = "��������";
			this.image = new ImageIcon("Images/SeaSonar.JPG");
			break;

		}
	}
}

class HumenSky extends Humen {
	public HumenSky(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "�����ѵ��ܺ���";
			this.image = new ImageIcon("Images/SkyKids.JPG");
			break;
		case 1:
			this.name = "������";
			this.image = new ImageIcon("Images/SkyCatcher.JPG");
			break;
		case 2:
			this.name = "�г�С��";
			this.image = new ImageIcon("Images/SkyKeeper.JPG");
			break;
		case 3:
			this.name = "������";
			this.image = new ImageIcon("Images/SkyKiller.JPG");
			break;
		case 4:
			this.name = "�ɻ�";
			this.image = new ImageIcon("Images/SkyPlane.JPG");
			break;

		}
	}
}

class HumenEnd extends Humen {
	public HumenEnd(int process) {
		super(300 + process * 10, 55 + process * 5, 40 + process * 3);
		switch (process) {
		case 0:
			this.name = "̸����";
			this.image = new ImageIcon("Images/EndSpeaker.JPG");
			break;
		case 1:
			this.name = "����̸����";
			this.image = new ImageIcon("Images/EndProtest.JPG");
			break;
		case 2:
			this.name = "��Ⱥ";
			this.image = new ImageIcon("Images/EndPeople.JPG");
			break;
		case 3:
			this.name = "����";
			this.image = new ImageIcon("Images/EndArmy.JPG");
			break;
		case 4:
			this.name = "�����侯";
			this.image = new ImageIcon("Images/EndGuards.JPG");
			break;

		}
	}
}