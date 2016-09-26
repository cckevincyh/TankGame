package tankGame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
/**
 * ̹�˴�ս����Ϸ����
 * @author c
 *
 */
public class TankGameFrame extends JFrame{
	//�����˵�
	private JMenuBar jmb =null;
	//��ʼ��Ϸ
	private JMenu jm1 = null;
	private JMenuItem jmi1 = null;
	private JMenuItem jmi2 = null;
	private JMenuItem jmi3 = null;
	private JMenuItem jmi4 = null;
	public TankGameFrame(){
		super("̹�˴�ս");
		Container c = getContentPane();
		//�����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		//���ÿ�ݼ�(Alt+G)
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("��ʼ��Ϸ(N)");
		jmi2 = new JMenuItem("�����Ͼ���Ϸ(C)");
		jmi3 = new JMenuItem("�����˳�(S)");
		jmi4 = new JMenuItem("�˳���Ϸ(E)");
		//���ÿ�ݼ�(Alt+N)
		jmi1.setMnemonic('N');
		//���ÿ�ݼ�(Alt+C)
		jmi2.setMnemonic('C');
		//���ÿ�ݼ�(Alt+S)
		jmi3.setMnemonic('S');
		//���ÿ�ݼ�(Alt+E)
		jmi4.setMnemonic('E');
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);

		//��ʼ��ʾ
		StartTip startTip = new StartTip();
		c.add(startTip);
		Thread t = new Thread(startTip);
		t.start();
		this.setJMenuBar(jmb);
		//jmi1�����¼�����
		jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ս�����
				MyPanel myPanel = new MyPanel("newGame");
				//ע�����(�����¼�)
				addKeyListener(myPanel);
				myPanel.repaint();
				Thread t = new Thread(myPanel);
				//��������߳�
				t.start();
				c.add(myPanel);
				//���Ƴ��ɵĿ�ʼ���
				remove(startTip);
				setVisible(true);
				//���ò������ڰ���ʼ��Ϸ
				jmi1.setEnabled(false);
				//���ò������ٰ������Ͼ���Ϸ
				jmi2.setEnabled(false);
			}
		});
		//jmi2�����¼�����
		jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					//���������Ͼֵ�ս�����
					MyPanel myPanel = new MyPanel("continue");
					//ע�����(�����¼�)
					addKeyListener(myPanel);
					myPanel.repaint();
					Thread t = new Thread(myPanel);
					//��������߳�
					t.start();
					c.add(myPanel);
					//���Ƴ��ɵĿ�ʼ���
					remove(startTip);
					setVisible(true);
					//���ò������ٰ���ʼ��Ϸ
					jmi1.setEnabled(false);
				}catch(NullPointerException n){
					JOptionPane.showMessageDialog(null, "�Ҳ����浵");
				}finally{
					//���ò������ٰ������Ͼ���Ϸ
					jmi2.setEnabled(false);
				}
				
			}
		});
		//jmi3�����¼�����
		jmi3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//������ٵ��˵������͵��˵�����
				Recorder.keepRecordEnemyTank();
				//�˳�
				System.exit(0);
			}
		});
		 
		//jmi4�����¼�����
		jmi4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ͬʱ�����¼
				Recorder.keepRecording();
				System.exit(0);
			}
		});

		
		
	}
	
	  
	  //���ô������
	    public static void setFrameCenter(JFrame jf) {
	        /*
	         ˼·��
	         A:��ȡ��Ļ�Ŀ�͸�
	         B:��ȡ����Ŀ�͸�
	         C:(����Ļ�Ŀ�-����Ŀ�)/2��(����Ļ�ĸ�-����ĸ�)/2��Ϊ����������ꡣ
	         */
	        //��ȡ���߶���
	        Toolkit tk = Toolkit.getDefaultToolkit();

	        //��ȡ��Ļ�Ŀ�͸�
	        Dimension d = tk.getScreenSize();
	        double srceenWidth = d.getWidth();
	        double srceenHeigth = d.getHeight();

	        //��ȡ����Ŀ�͸�
	        int frameWidth = jf.getWidth();
	        int frameHeight = jf.getHeight();

	        //��ȡ�µĿ�͸�
	        int width = (int) (srceenWidth - frameWidth) / 2;
	        int height = (int) (srceenHeigth - frameHeight) / 2;

	        //���ô�������
	        jf.setLocation(width, height);
	    }
}
