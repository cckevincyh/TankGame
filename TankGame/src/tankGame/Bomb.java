package tankGame;
/**
 * ̹�˱�ը��ը����
 * @author c
 *
 */
public class Bomb {
	private int x;	//��ը�ص��x����
	private int y;	//��ը�ص��y����
	private int life = 9;	//ը����ը����������
	private boolean isLive = true;	//ը���Ƿ���
	public Bomb(){
		
	}
	
	public Bomb(int x,int y){
		this.x = x;
		this.y = y;
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
	
	//ը���������ڼ���
	public void lifeDown(){
		if(this.life>0){
			this.life--;
		}else{
			this.isLive=false;
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
}
