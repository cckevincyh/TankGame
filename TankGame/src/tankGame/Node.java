package tankGame;
/**
 * ���ڼ�¼�����������
 * @author c
 *
 */
public class Node {
	private int x;	//x����
	private int y;	//y����
	private int direct;	//����
	
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
