package tankGame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class RunTankGame {
	
	public static void main(String[] args) {
		TankGameFrame tankGame = new TankGameFrame();
		tankGame.setSize(600, 500);
		tankGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体居中
		TankGameFrame.setFrameCenter(tankGame);
		tankGame.setVisible(true);
		tankGame.setResizable(false);//设置窗口不能修改大小
		//设置窗口事件监听
		tankGame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				//保存
				Recorder.keepRecording();
			}
		});
	}
}
