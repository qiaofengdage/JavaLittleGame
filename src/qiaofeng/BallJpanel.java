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
	
	
	
	//定于挡板的坐标
	static int dx = 200, dy = 500;
	//成绩
	static int score = 0;
	//球速
	static int n = 10;
	//生命值
	static int hp = n;
	//游戏速度
	int speed = 5;
	//开始状态
	static final int START = 0;
	//运行状态
	static final int RUNNING = 1;
	//暂停状态
	static final int PASS = 2;
	//结束状态
	static final int OVER = 3;
	//当前运动状态
	int state = START;
	//创建List集合存储Ball类
	List<Ball> list = new ArrayList<Ball>();
	public BallJpanel() {
		// TODO Auto-generated constructor stub
		//添加小球类
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
		//设置背景颜色
		setBackground(Color.black);
		//画出挡板
		g.setColor(Color.blue);
		g.fillRect(dx, dy, 100, 20);
		
		//画出小球
		for(int i=0; i<list.size(); i++) {
			Ball ball  = list.get(i);
			g.setColor(Color.white);
			
			g.fillOval(ball.getX(), ball.getY(), 2 * ball.getR(), 2 * ball.getR());
		}
			//显示成绩与生命值
			g.drawString("目前得分" + score, 20, 20);
			g.drawString("生命值" + hp, 20, 40);
			//开始时重置成绩、生命值与速度，屏幕上显示游戏开始
			if(state == START) {
				score = 0;
				hp = n;
				speed = 5;
				g.setColor(Color.red);
				g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
				g.drawString("游戏开始", 300, 250);
			}
			//暂停时屏幕上显示游戏暂停
			if(state == PASS) {
				g.setColor(Color.red);
				g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
				g.drawString("游戏暂停", 300, 250);
			}
			//生命值为0的时候游戏结束,屏幕显示游戏结束
			if(hp == 0) {
				state = OVER;
				g.setColor(Color.red);
				g.setFont(new Font("微软雅黑", Font.PLAIN, 40));
				g.drawString("游戏结束", 300, 250);
			}
			//根据成绩自动调整速度
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
						//当运行时
						if (state == RUNNING) {
							for (int i=0;i<list.size();i++) {
								Ball ball = list.get(i);
								//调用ballMove让小球动起来
								ball.ballMove();
								ball.ballBorder();
								//小球碰撞
								for(int j=i+1; j<list.size(); j++) {
									Ball ball2 = list.get(j);
									ball.ballCrash(ball2);
								}
							}
						}else if (state == START) {
							//开始时将挡板移到中间
							dx = 350;
						}else {
							//暂停
						}
						//重绘
						repaint();
						try {
							Thread.sleep(speed);  //小球的运动速度调节
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
		//当运行的时候挡板跟着鼠标移动
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
		//开始时单机鼠标运行，运行时单机鼠标暂停，暂停时单机鼠标开始运行
		if (state == START) {
			state = RUNNING;
		}else if (state == RUNNING) {
			state = PASS;
		}else if (state == PASS) {
			state = RUNNING;
		}else if (state == OVER) {
			state = START;
			//重置小球位置
			for(int i=0; i<= list.size(); i++) {
				Ball ball = list.get(i);
				ball.reset();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标进入窗口暂停变成运行
		if (state == PASS) {
			state = RUNNING;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开窗口运行变成停止
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
