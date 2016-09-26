package tankGame;

import java.util.Vector;

/**
 * 坦克大战游戏v1.0
 * @author c
 * 坦克的抽象类
 */
public abstract class Tank {
	private int x;	//x坐标
	private int y;	//y坐标
	private int speed = 1;	//坦克速度
	private int type;	//坦克类型
	private int direct = 0;	//坦克方向
	private boolean isLive = false;	//坦克是否存活
	private Vector<Bullet> bullet = new Vector<Bullet>();	//坦克有弹夹
	private Bullet myBullet = null;	//坦克有子弹
	
	public Vector<Bullet> getBullet() {
		return bullet;
	}

	public void setBullet(Vector<Bullet> bullet) {
		this.bullet = bullet;
	}

	public Bullet getMyBullet() {
		return myBullet;
	}

	public void setMyBullet(Bullet myBullet) {
		this.myBullet = myBullet;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public Tank(){
		
	}
	
	public Tank(int x,int y){
		this.x = x;
		this.y = y;
		this.isLive = true;
	}
	
	public Tank(int x,int y,int direct,int speed){
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.speed = speed;
	}
	
	//坦克向上移动
	public void move_Up(){
		this.y -= speed;
	}
	//坦克向下移动
	public void move_Down(){
		this.y += speed;
	}
	//坦克向左移动
	public void move_Left(){
		this.x -= speed;
	}
	//坦克向右移动
	public void move_Right(){
		this.x += speed;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//坦克有发子弹的功能
	public void shot(){
		
		//判断子弹的方向
		switch(this.direct){
			case 0:
					this.myBullet = new Bullet(x+9, y+1, 0);
					bullet.add(myBullet);
					break;
			case 1:
					this.myBullet = new Bullet(x+9, y+30, 1);
					bullet.add(myBullet);
					break;
					
			case 2:
					this.myBullet = new Bullet(x, y+15, 2);
					bullet.add(myBullet);
					break;
					
			case 3: 
					this.myBullet = new Bullet(x+30, y+15, 3);
					bullet.add(myBullet);
					break;
		}
		Thread t = new Thread(this.myBullet);
		//启动子弹线程
		t.start();
	}



	
	
	
}
