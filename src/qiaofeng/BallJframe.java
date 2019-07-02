package qiaofeng;

import javax.swing.JFrame;

public class BallJframe {
	// 创建一个窗体
	JFrame frame = new JFrame();
	
	public void init() {
		//设置标题
		frame.setTitle("打弹珠");
		
		//设置窗体大小， 位置
		frame.setBounds(550, 270, 800, 600);
		
		//设置默认关闭方式
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建画布对象
		BallJpanel jpanel = new BallJpanel();
		
		//将画布设置到窗体中
		frame.add(jpanel);
		
		//设置窗口可见
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		BallJframe bf = new BallJframe();
		bf.init();
	}
	
}
