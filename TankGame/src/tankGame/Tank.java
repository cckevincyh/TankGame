package tankGame;

import java.util.Vector;

/**
 * ̹�˴�ս��Ϸv1.0
 * @author c
 * ̹�˵ĳ�����
 */
public abstract class Tank {
	private int x;	//x����
	private int y;	//y����
	private int speed = 1;	//̹���ٶ�
	private int type;	//̹������
	private int direct = 0;	//̹�˷���
	private boolean isLive = false;	//̹���Ƿ���
	private Vector<Bullet> bullet = new Vector<Bullet>();	//̹���е���
	private Bullet myBullet = null;	//̹�����ӵ�
	
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
	
	//̹�������ƶ�
	public void move_Up(){
		this.y -= speed;
	}
	//̹�������ƶ�
	public void move_Down(){
		this.y += speed;
	}
	//̹�������ƶ�
	public void move_Left(){
		this.x -= speed;
	}
	//̹�������ƶ�
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
	
	//̹���з��ӵ��Ĺ���
	public void shot(){
		
		//�ж��ӵ��ķ���
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
		//�����ӵ��߳�
		t.start();
	}



	
	
	
}
