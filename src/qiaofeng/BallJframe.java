package qiaofeng;

import javax.swing.JFrame;

public class BallJframe {
	// ����һ������
	JFrame frame = new JFrame();
	
	public void init() {
		//���ñ���
		frame.setTitle("����");
		
		//���ô����С�� λ��
		frame.setBounds(550, 270, 800, 600);
		
		//����Ĭ�Ϲرշ�ʽ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ������������
		BallJpanel jpanel = new BallJpanel();
		
		//���������õ�������
		frame.add(jpanel);
		
		//���ô��ڿɼ�
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		BallJframe bf = new BallJframe();
		bf.init();
	}
	
}
