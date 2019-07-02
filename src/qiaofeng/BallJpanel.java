package qiaofeng;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BallJpanel extends JPanel implements MouseMotionListener, MouseListener {
	
	
	
	//���ڵ��������
	static int dx = 200, dy = 500;
	//�ɼ�
	static int score = 0;
	//����
	static int n = 10;
	//����ֵ
	static int hp = n;
	//��Ϸ�ٶ�
	int speed = 5;
	//��ʼ״̬
	static final int START = 0;
	//����״̬
	static final int RUNNING = 1;
	//��ͣ״̬
	static final int PASS = 2;
	//����״̬
	static final int OVER = 3;
	//��ǰ�˶�״̬
	int state = START;
	//����List���ϴ洢Ball��
	List<Ball> list = new ArrayList<Ball>();
	public BallJpanel() {
		// TODO Auto-generated constructor stub
		//���С����
		for(int i = 0; i < 5; i++) {
			Ball ball = new Ball();
			list.add(ball);
		}
		
		
		move();
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		//���ñ�����ɫ
		setBackground(Color.black);
		//��������
		g.setColor(Color.blue);
		g.fillRect(dx, dy, 100, 20);
		
		//����С��
		for(int i=0; i<list.size(); i++) {
			Ball ball  = list.get(i);
			g.setColor(Color.white);
			
			g.fillOval(ball.getX(), ball.getY(), 2 * ball.getR(), 2 * ball.getR());
		}
			//��ʾ�ɼ�������ֵ
			g.drawString("Ŀǰ�÷�" + score, 20, 20);
			g.drawString("����ֵ" + hp, 20, 40);
			//��ʼʱ���óɼ�������ֵ���ٶȣ���Ļ����ʾ��Ϸ��ʼ
			if(state == START) {
				score = 0;
				hp = n;
				speed = 5;
				g.setColor(Color.red);
				g.setFont(new Font("΢���ź�", Font.PLAIN, 40));
				g.drawString("��Ϸ��ʼ", 300, 250);
			}
			//��ͣʱ��Ļ����ʾ��Ϸ��ͣ
			if(state == PASS) {
				g.setColor(Color.red);
				g.setFont(new Font("΢���ź�", Font.PLAIN, 40));
				g.drawString("��Ϸ��ͣ", 300, 250);
			}
			//����ֵΪ0��ʱ����Ϸ����,��Ļ��ʾ��Ϸ����
			if(hp == 0) {
				state = OVER;
				g.setColor(Color.red);
				g.setFont(new Font("΢���ź�", Font.PLAIN, 40));
				g.drawString("��Ϸ����", 300, 250);
			}
			//���ݳɼ��Զ������ٶ�
			if (score >= 40) {
				speed = 4;
			}
			if (score >= 60) {
				speed = 3;
			}
			if (score >= 80) {
				speed = 2;
			}
			if (score >= 100) {
				speed = 1;
			}

		}
		
		
		public void move() {
			new Thread() {
				public void run() {
					while (true) {
						//������ʱ
						if (state == RUNNING) {
							for (int i=0;i<list.size();i++) {
								Ball ball = list.get(i);
								//����ballMove��С������
								ball.ballMove();
								ball.ballBorder();
								//С����ײ
								for(int j=i+1; j<list.size(); j++) {
									Ball ball2 = list.get(j);
									ball.ballCrash(ball2);
								}
							}
						}else if (state == START) {
							//��ʼʱ�������Ƶ��м�
							dx = 350;
						}else {
							//��ͣ
						}
						//�ػ�
						repaint();
						try {
							Thread.sleep(speed);  //С����˶��ٶȵ���
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		
		

	
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����е�ʱ�򵲰��������ƶ�
		if (state == RUNNING) {
			dx = e.getX() - 100 / 2;
			if (dx <= 0) {
				dx = 0;
			}
			if (dx >= 800 - 116) {
				dx = 684;
			}
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��ʼʱ����������У�����ʱ���������ͣ����ͣʱ������꿪ʼ����
		if (state == START) {
			state = RUNNING;
		}else if (state == RUNNING) {
			state = PASS;
		}else if (state == PASS) {
			state = RUNNING;
		}else if (state == OVER) {
			state = START;
			//����С��λ��
			for(int i=0; i<= list.size(); i++) {
				Ball ball = list.get(i);
				ball.reset();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����봰����ͣ�������
		if (state == PASS) {
			state = RUNNING;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪�������б��ֹͣ
		if (state == RUNNING) {
			state = PASS;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
