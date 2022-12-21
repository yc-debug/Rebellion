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
	
	// 输出装备数据
	public String print() {
		String des = name + "      \n攻击：" + ATK + "      \n防御：" + DEF + "      \n耐久：" + endurance + "      \n价格" + cost;
		return des;
	}

	// 根据随机数决定生成的装备品质
	public  Equipment changeQuality() {
		Random generator = new Random();
		int rand = generator.nextInt(100) + 1;

		int level = 0;
		if (rand >= 1 && rand <= 20) {
			cost /= 2;
			name = "破烂的" + name;
			if (this instanceof AddATK)
				ATK /= 2;
			else if (this instanceof AddDEF)
				DEF /= 2;
		} else {
			if (rand >= 21 && rand <= 50) {
				name = "普通的" + name;
				level = 1;
			} else if (rand >= 51 && rand <= 75) {
				name = "优秀的" + name;
	      		level = 2;
			} else if (rand >= 76 && rand <= 90) {
				name = "精良的" + name;
				level = 3;
			} else if (rand >= 91 && rand <= 96) {
    			name = "极品的" + name;
				level = 4;
			} else if (rand >= 97 && rand <= 99) {
				name = "史诗的" + name;
				level = 5;
			} else if (rand >= 100 && rand <= 100) {
				name = "传说的" + name;
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
		super("尖牙", 5, 100);
		this.cost = 200;
	}
}

class Claws extends AddATK {
	public Claws() {
		super("利爪", 8, 100);
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
		super("头盔", 5, 100);
		this.cost = 200;
	}
}

class Breastplate extends AddDEF {
	public Breastplate() {
		super("胸甲", 8, 100);
		this.cost = 300;
	}
}

class Kneecap extends AddDEF {
	public Kneecap() {
		super("护膝", 3, 100);
		this.cost = 100;
	}
}
