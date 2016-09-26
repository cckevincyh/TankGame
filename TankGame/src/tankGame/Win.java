package tankGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 赢提示
 * @author c
 *
 */
public class Win extends JPanel implements Runnable{
	private int time = 0;
	public Win(){
		
	}
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//提示信息
		//闪动效果
		if(time%2==0){
			g.setColor(Color.white);
			//字体
			Font myFont = new Font("华文新魏",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("You Win", 120, 150);
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
