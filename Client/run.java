package Client;

import java.awt.*;
import java.io.Serializable;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class run implements Serializable{
	static String title = "Rebellion";
	static Beginning begin = new Beginning(title);
	static Map map = new Map(title);
	static Data data = new Data(title);
	static Base base = new Base(title);
	static Bag bag = new Bag(title);
	static Shop shop = new Shop(title);

	static JFrame[] frames = { begin,map,data,bag,shop,base};

	public static void main(String args[]) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
              UIManager.setLookAndFeel(info.getClassName());
              break;
          }
		  }
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高
		
		for (int index = 0; index < frames.length; index++) {
			frames[index].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frames[index].setPreferredSize(new Dimension(1000,900));
			frames[index].setPreferredSize(new Dimension(screenWidth,screenHeight-50));
            screencentre(frames[index]);

			frames[index].pack();
		}
		frames[0].setVisible(true);
		
	}
    //使框架显示在屏幕中央
	public static void screencentre(JFrame frame) {

		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸

		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高

		
		frame.setLocation(0, 0);// 设置窗口居中显示
	}
}