package Client;

import java.io.Serializable;

import javax.swing.JButton;

public class player implements Serializable{
	private String user;
	private String name;
	private String id;
	private int loginTimes = 0;
	public String mima;
	public int money;
	public Animals[] wargroup;
	public Animals[] members;
	public Equipment[] equip;
	public Equipment[] item;
	public JButton[][] buttons;
	public player(int money,Animals[] wargroup,Animals[] members,Equipment[] equip,Equipment[] item,JButton[][] buttons){
		this.money=money;
		this.wargroup=wargroup;this.members=members;this.equip=equip;this.item=item;
		this.buttons=buttons;
	}
	public int getmoney(){
		return this.money;
	}
	public void setuser(String user){
		this.user=user;
	}
	public void setmima(String mima){
		this.mima=mima;
	}
	public void setname(String name){
		this.name=name;
	}
	public void setid(String id){
		this.id=id;
	}
	public String getmima(){
		return this.mima;
	}
	public String getuser(){
		return this.user;
	}
	public String getname(){
		return this.name;
	}
	public String getid(){
		return this.id;
	}
	
	public Animals[] getwargoup(){
		return this.wargroup;
	}
	public Animals[] getmembers(){
		return this.members;
	}
	public Equipment[] getequip(){
		return this.equip;
	}
	public Equipment[] getitem(){
		return this.item;
	}
	public JButton[][] getbuttons(){
		return this.buttons;
	}
	public int getloginTimes() {
		return this.loginTimes;
	}
	public void setloginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	
}
