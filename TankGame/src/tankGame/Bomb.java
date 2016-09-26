package tankGame;
/**
 * 坦克爆炸的炸弹类
 * @author c
 *
 */
public class Bomb {
	private int x;	//爆炸地点的x坐标
	private int y;	//爆炸地点的y坐标
	private int life = 9;	//炸弹爆炸的生命周期
	private boolean isLive = true;	//炸弹是否存活
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
	
	//炸弹生命周期剪短
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
