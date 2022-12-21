package Client;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;


public class S_L {
	static Socket socket;
	static player k;
	public static int Save() throws ConnectException {
		try {
			socket=new Socket("127.0.0.1",2000);
			if (k == null) {
				JOptionPane.showMessageDialog(null, "单机用户请先登录", "确认", JOptionPane.INFORMATION_MESSAGE);
				return 0;
			}else {
				player r=new player(run.data.money,run.data.wargroup,run.base.members,run.shop.st.equip,run.bag.bag.item,run.map.buttons);
				r.setuser(k.getuser());r.setname(k.getname());r.setmima(k.getmima());r.setid(k.getid());
				int times = k.getloginTimes();
				times = times+1;
				r.setloginTimes(times);
				System.out.println(r.getuser()+"\n"+r.getname()+"\n");
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.write(2);
				oos.writeObject(r);
				JOptionPane.showMessageDialog(null, "游戏已保存", "保存", JOptionPane.INFORMATION_MESSAGE);
				return 1;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void Load(player r) {
		if (r.getloginTimes() == 1) {
			k = r;
		}
		else {
			k=r;
			run.data.money =r.getmoney();
			run.data.wargroup = r.getwargoup();
			run.base.members = r.getmembers();

			run.shop.st.equip = r.getequip();
			for (int index = 0; index < run.shop.st.des.length; index++)
				run.shop.st.des[index].setText(run.shop.st.equip[index].print());

			run.bag.bag.item = r.getitem();
			System.out.println(r.getuser()+"\n"+r.getname()+"\n"+run.bag.bag.item[0].print()+r.item[0].print());
			System.out.println("2");
			for (int index = 0; index < run.bag.bag.display.length; index++) {
				if (run.bag.bag.item[index] instanceof Blank)
					run.bag.bag.display[index].setText("");
				else{
					System.out.println("3");
					run.bag.bag.display[index].setText(run.bag.bag.item[index].print());
				}
					
			}

			JButton[][] buttons = r.getbuttons();
			for (int count = 0; count < buttons.length; count++)
				for (int index = 0; index < buttons[count].length; index++)
					run.map.buttons[count][index].setForeground(buttons[count][index].getForeground());
		}
	}
	
	}
