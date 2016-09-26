package tankGame;
/**
 * �ӵ�����
 * @author c
 *
 */
public class Bullet implements Runnable{
	private int x;	//�ӵ���x����
	private int y;	//�ӵ���y����
	private boolean isLive = false;	//�ӵ��Ƿ���
	private int speed = 3;	//�ӵ��ٶ�
	private int direct;	//�ӵ�����
	public Bullet(){
		
	}
	
	public Bullet(int x,int y,int direct){
		this.x = x;
		this.y = y;
		this.isLive = true;
		this.direct = direct;
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
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

/**
 * �ӵ��̵߳�run()����
 */
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//�ж��ӵ�����
			switch(this.direct){
				case 0:
						y -= this.speed;
						break;
				case 1:
						y += this.speed;
						break;
				case 2:
						x -= this.speed;
						break;
				case 3:
						x += this.speed;
						break;
			}
			//�ж��ӵ���ʱ����
			if(this.x<-2 || this.x>402 || this.y<-2 || this.y>302){
				this.isLive = false;
				break;
			}
		}
		
	}
	
	
}
