package tankGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * ����ʾ
 * @author c
 *
 */
public class GameOver extends JPanel implements Runnable{
	private int time = 0;
	public GameOver(){
		
	}
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//��ʾ��Ϣ
		//����Ч��
		if(time%2==0){
			g.setColor(Color.white);
			//����
			Font myFont = new Font("������κ",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("Game Over", 120, 150);
		}
	}
	
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
			this.repaint();
		}
	}
	
}
