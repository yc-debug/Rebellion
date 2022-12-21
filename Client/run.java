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
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�

		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�

		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		
		for (int index = 0; index < frames.length; index++) {
			frames[index].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frames[index].setPreferredSize(new Dimension(1000,900));
			frames[index].setPreferredSize(new Dimension(screenWidth,screenHeight-50));
            screencentre(frames[index]);

			frames[index].pack();
		}
		frames[0].setVisible(true);
		
	}
    //ʹ�����ʾ����Ļ����
	public static void screencentre(JFrame frame) {

		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�

		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�

		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�

		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�

		
		frame.setLocation(0, 0);// ���ô��ھ�����ʾ
	}
}