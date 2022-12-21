package Client;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Animals implements Serializable {
	//动物类
	int TotalHP;
	int HP, attack, defence, level = 1, exp = 0;
	ImageIcon image;
	String name;
	Equipment[] equips = new Equipment[5];

	public Animals(int HP, int attack, int defence) {
		TotalHP = HP;
		this.HP = HP;
		this.attack = attack;
		this.defence = defence;
		for (int index = 0; index < equips.length; index++) {
			equips[index] = new Blank();
		}
	}

	// 改变装备
	public Equipment changeEquip(Equipment equip) {
		Equipment oldequip = new Blank();
		if (equip instanceof Teeth) {
			oldequip = equips[0];
			equips[0] = equip;
		} else if (equip instanceof Claws) {
			oldequip = equips[1];
			equips[1] = equip;
		} else if (equip instanceof Helmet) {
			oldequip = equips[2];
			equips[2] = equip;
		} else if (equip instanceof Breastplate) {
			oldequip = equips[3];
			equips[3] = equip;
		} else if (equip instanceof Kneecap) {
			oldequip = equips[4];
			equips[4] = equip;
		}
		this.attack += (equip.ATK - oldequip.ATK);
		this.defence += (equip.DEF - oldequip.ATK);

		return oldequip;
	}

	// 耐久消耗
	public void enduranceDown() {
		for (int index = 0; index < equips.length; index++) {
			if (equips[index] != null) {
				equips[index].endurance--;
				if (equips[index].endurance <= 0) {
					attack -= equips[index].ATK;
					defence -= equips[index].DEF;
					equips[index] = new Blank();
				}
			}
		}
	}

	// 攻击
	public String attack(Humen[] human, int current) {
		int hurt;
		// 攻击有效对象
		if (human[current].HP == 0) {
			for (int index = 0; index < human.length; index++) {
				if (human[index].HP != 0) {
					current = index;
					break;
				}
			}
		}
		if (attack > human[current].defence) {
			//判断攻击力是否大于对面防御力
			human[current].HP -= (attack - human[current].defence);
			hurt = attack - human[current].defence;
			if (human[current].HP < 0) {
				hurt -= 0 - human[current].HP;
				human[current].HP = 0;
			}
			return name + "对 " + human[current].name + " 造成了" + hurt + "伤害\n";
		} else {
			return name + "的攻击未能破防!\n";
		}
	}

	// 属性面板
	public synchronized String print() {
		synchronized (this) {
			String des = "Name:" + name + "\n\nLevel:" + level + "\n\nExp:" + exp + "/" + (level * 100) + "\n\nHP:" + HP
					+ "/" + TotalHP + "\n\nAttack:" + attack + "\n\nDefence:" + defence + "\n\n";
			for (int index = 0; index < equips.length; index++) {
				if (equips[index] != null && !(equips[index] instanceof Blank))
					des += equips[index].print() + "\n\n";
			}
			return des;
		}
	}

	// 等级
	public boolean levelUp(int exp) {
		this.exp += exp;
		if (this.exp >= 100 * level) {
			this.exp -= 100 * level;
			level++;
			TotalHP += 10;
			attack += 1;
			defence += 1;
			return true;
		}
		return false;
	}
}
//陆地类的动物
class Land extends Animals {

	public Land(int HP, int attack, int defence) {
		super(HP, attack, defence);

	}

}

 class Tiger extends Land {
	public Tiger() {
		super(300, 25, 12);
		name = "虎";
		image = new ImageIcon("Images/Tiger.JPG");
	}

	public String toString() {
		return "Name:Tiger\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

 class Lion extends Land {
	public Lion() {
		super(300, 28, 10);
		name = "狮";
		image = new ImageIcon("Images/Lion.JPG");
	}

	public String toString() {
		return "Name:Lion\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

 class Bear extends Land {
	public Bear() {
		super(350, 23, 13);
		name = "熊";
		image = new ImageIcon("Images/Bear.JPG");
	}

	public String toString() {
		return "Name:Bear\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Elephant extends Land {
	public Elephant() {
		super(400, 30, 18);
		name = "象";
		image = new ImageIcon("Images/Elephant.JPG");
	}

	public String toString() {
		return "Name:Elephant\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}



//海洋类动物
class Ocean extends Animals {
	public Ocean(int HP, int attack, int defence) {
		super(HP, attack, defence);
	}
}

class Whale extends Ocean {
	public Whale() {
		super(400, 32, 17);
		name = "鲸";
		image = new ImageIcon("Images/Whale.JPG");
	}

	public String toString() {
		return "Name:Whale\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

//天空类动物
class Sky extends Animals {
	public Sky(int HP, int attack, int defence) {
		super(HP, attack, defence);
	}
}

class Eagle extends Sky {
	public Eagle() {
		super(400, 32, 17);
		name = "白头鹰";
		image = new ImageIcon("Images/Eagle.JPG");
	}

	public String toString() {
		return "Name:Whale\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}