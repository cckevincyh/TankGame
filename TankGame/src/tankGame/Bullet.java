package tankGame;
/**
 * 子弹的类
 * @author c
 *
 */
public class Bullet implements Runnable{
	private int x;	//子弹的x坐标
	private int y;	//子弹的y坐标
	private boolean isLive = false;	//子弹是否存活
	private int speed = 3;	//子弹速度
	private int direct;	//子弹方向
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
 * 子弹线程的run()方法
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
			//判断子弹方向
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
			//判断子弹何时死亡
			if(this.x<-2 || this.x>402 || this.y<-2 || this.y>302){
				this.isLive = false;
				break;
			}
		}
		
	}
	
	
}
