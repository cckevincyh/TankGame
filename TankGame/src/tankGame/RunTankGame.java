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
		//���ô������
		TankGameFrame.setFrameCenter(tankGame);
		tankGame.setVisible(true);
		tankGame.setResizable(false);//���ô��ڲ����޸Ĵ�С
		//���ô����¼�����
		tankGame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				//����
				Recorder.keepRecording();
			}
		});
	}
}
