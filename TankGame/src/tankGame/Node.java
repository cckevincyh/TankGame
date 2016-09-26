package tankGame;
/**
 * 用于记录敌人坐标的类
 * @author c
 *
 */
public class Node {
	private int x;	//x坐标
	private int y;	//y坐标
	private int direct;	//方向
	
	public Node(){
		
	}
	
	public Node(int x, int y, int direct){
		this.x = x;
		this.y = y;
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
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
}
