package qiaofeng;

public class Ball {
	//定义小球运动方向
	public static final int LEFT_UP = 0;
	public static final int LEFT_DOWN = 1;
	public static final int RIGHT_UP = 2;
	public static final int RIGHT_DOWN = 3;
	//定义小球当前运动方向
	private int f = LEFT_UP;
	//定义小球坐标
	private int x,y;
	//定义小球半径
	private int r;
	
	public Ball() {
		x = (int)(Math.random() * 800);
		y = (int)(Math.random() * 400);
		r = 10;
		int ran = (int)(Math.random() * 2);
		if(ran == 0) {
			f = LEFT_UP;
		}else if(ran == 1) {
			f = RIGHT_UP;
		}
	}
	
	public Ball(int x, int y, int r, int f) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.f = f;
	}
	
	public int getF() {
		return f;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	

	public int getR() {
		return r;
	}
 
	public void setR(int r) {
		this.r = r;
	}
	
	//小球移动
	public void ballMove() {
		if(f == LEFT_UP) {
			x--;
			y--;
		}
		if(f == LEFT_DOWN) {
			x--;
			y++;
		}
		if(f == RIGHT_UP) {
			x++;
			y--;
		}
		if(f == RIGHT_DOWN) {
			x++;
			y++;
		}
	}
	
	//小球越界处理
	public void ballBorder() {
		//如果越出上界
		if (y <= 0) { 
			if (f == LEFT_UP) {
				f = LEFT_DOWN;
			}
			if (f== RIGHT_UP) {
				f = RIGHT_DOWN;
			}
		}
		//如果越出左边界
		if (x <= 0) {
			if (f == LEFT_UP) {
				f = RIGHT_UP;
			}
			if (f == LEFT_DOWN) {
				f = RIGHT_DOWN;
			}
		}
		//小球碰到挡板
		if (y >= BallJpanel.dy - 2 * r) {
			if (x >= BallJpanel.dx && x <= BallJpanel.dx + 100) {
				if (f == LEFT_DOWN) {
					f = LEFT_UP;
				}
				if (f == RIGHT_DOWN) {
					f = RIGHT_UP;
				}
				y-=3;
				BallJpanel.score++;
			}else if(y == 600 - 2 * r - 40) {
				BallJpanel.hp--;
			}else if (y >= 600 - 2 * r - 40) {
				x = 900;
				y = 900;
			}
		}
		//如果越出右边界
		if (x >= 800 - 2 * r - 15) {
			if (f == RIGHT_UP) {
				f = LEFT_UP;
			}
			if (f == RIGHT_DOWN) {
				f = LEFT_DOWN;
			}
		}
	}
	
	//小球碰撞检测
	public boolean isCrash(Ball ball) {
		int lx = Math.abs(x+r -(ball.x+ball.r));
		int ly = Math.abs(y+r -(ball.y+ball.r));
		boolean h = lx<=(r+ball.r)&&ly<=(r+ball.r);
		return h;
	}
	
	//小球碰撞反弹
	public void ballCrash(Ball another) {
		if(isCrash(another)) {
			x++;
			y++;
			int temp = this.f;
			this.f = another.f;
			another.f = temp;
		}
	}
	
	//重置小球位置
	public void reset() {
		x = (int)(Math.random() * 800);
		y = (int)(Math.random() * 500);
		int ran = (int)(Math.random() * 2);
		if (ran == 0) {
			f = LEFT_UP;
		}else if(ran == 1){
			f = RIGHT_UP;
		}
	}
	
	
}
