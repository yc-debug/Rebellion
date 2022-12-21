package Client;

import java.io.Serializable;
import java.util.Random;
import javax.swing.*;

public class Equipment implements Serializable{
	String name=" ";
	ImageIcon image;
	int ATK, DEF, endurance, cost;

	public Equipment(String name, int ATK, int DEF, int endurance) {
		this.name = name;
		this.ATK = ATK;
		this.DEF = DEF;
		this.endurance = endurance;

	}
	
	// ���װ������
	public String print() {
		String des = name + "      \n������" + ATK + "      \n������" + DEF + "      \n�;ã�" + endurance + "      \n�۸�" + cost;
		return des;
	}

	// ����������������ɵ�װ��Ʒ��
	public  Equipment changeQuality() {
		Random generator = new Random();
		int rand = generator.nextInt(100) + 1;

		int level = 0;
		if (rand >= 1 && rand <= 20) {
			cost /= 2;
			name = "���õ�" + name;
			if (this instanceof AddATK)
				ATK /= 2;
			else if (this instanceof AddDEF)
				DEF /= 2;
		} else {
			if (rand >= 21 && rand <= 50) {
				name = "��ͨ��" + name;
				level = 1;
			} else if (rand >= 51 && rand <= 75) {
				name = "�����" + name;
	      		level = 2;
			} else if (rand >= 76 && rand <= 90) {
				name = "������" + name;
				level = 3;
			} else if (rand >= 91 && rand <= 96) {
    			name = "��Ʒ��" + name;
				level = 4;
			} else if (rand >= 97 && rand <= 99) {
				name = "ʷʫ��" + name;
				level = 5;
			} else if (rand >= 100 && rand <= 100) {
				name = "��˵��" + name;
				level = 6;
			}
			cost *= level;
			if (this instanceof AddATK)
				ATK += 3 * level;
			else if (this instanceof AddDEF)
				DEF += 3 * level;
		}
		return this;
	}
   
}

class Blank extends Equipment {
	public Blank() {
		super("", 0, 0, 9999);
	}
}

class AddATK extends Equipment {
	public AddATK(String name, int ATK, int endurance) {
		super(name, ATK, 0, endurance);

	}
}

class Teeth extends AddATK {
	public Teeth() {
		super("����", 5, 100);
		this.cost = 200;
	}
}

class Claws extends AddATK {
	public Claws() {
		super("��צ", 8, 100);
		this.cost = 300;
	}
}

class AddDEF extends Equipment {
	public AddDEF(String name, int DEF, int endurance) {
		super(name, 0, DEF, endurance);
	}
}

class Helmet extends AddDEF {
	public Helmet() {
		super("ͷ��", 5, 100);
		this.cost = 200;
	}
}

class Breastplate extends AddDEF {
	public Breastplate() {
		super("�ؼ�", 8, 100);
		this.cost = 300;
	}
}

class Kneecap extends AddDEF {
	public Kneecap() {
		super("��ϥ", 3, 100);
		this.cost = 100;
	}
}
