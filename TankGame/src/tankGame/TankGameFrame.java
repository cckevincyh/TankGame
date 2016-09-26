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
 * 坦克大战的游戏窗口
 * @author c
 *
 */
public class TankGameFrame extends JFrame{
	//做出菜单
	private JMenuBar jmb =null;
	//开始游戏
	private JMenu jm1 = null;
	private JMenuItem jmi1 = null;
	private JMenuItem jmi2 = null;
	private JMenuItem jmi3 = null;
	private JMenuItem jmi4 = null;
	public TankGameFrame(){
		super("坦克大战");
		Container c = getContentPane();
		//创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		//设置快捷键(Alt+G)
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("开始游戏(N)");
		jmi2 = new JMenuItem("继续上局游戏(C)");
		jmi3 = new JMenuItem("存盘退出(S)");
		jmi4 = new JMenuItem("退出游戏(E)");
		//设置快捷键(Alt+N)
		jmi1.setMnemonic('N');
		//设置快捷键(Alt+C)
		jmi2.setMnemonic('C');
		//设置快捷键(Alt+S)
		jmi3.setMnemonic('S');
		//设置快捷键(Alt+E)
		jmi4.setMnemonic('E');
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);

		//开始提示
		StartTip startTip = new StartTip();
		c.add(startTip);
		Thread t = new Thread(startTip);
		t.start();
		this.setJMenuBar(jmb);
		//jmi1设置事件监听
		jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//创建战场面板
				MyPanel myPanel = new MyPanel("newGame");
				//注册监听(面板的事件)
				addKeyListener(myPanel);
				myPanel.repaint();
				Thread t = new Thread(myPanel);
				//启动面板线程
				t.start();
				c.add(myPanel);
				//先移除旧的开始面板
				remove(startTip);
				setVisible(true);
				//设置不允许在按开始游戏
				jmi1.setEnabled(false);
				//设置不允许再按继续上局游戏
				jmi2.setEnabled(false);
			}
		});
		//jmi2设置事件监听
		jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					//创建继续上局的战场面板
					MyPanel myPanel = new MyPanel("continue");
					//注册监听(面板的事件)
					addKeyListener(myPanel);
					myPanel.repaint();
					Thread t = new Thread(myPanel);
					//启动面板线程
					t.start();
					c.add(myPanel);
					//先移除旧的开始面板
					remove(startTip);
					setVisible(true);
					//设置不允许再按开始游戏
					jmi1.setEnabled(false);
				}catch(NullPointerException n){
					JOptionPane.showMessageDialog(null, "找不到存档");
				}finally{
					//设置不允许再按继续上局游戏
					jmi2.setEnabled(false);
				}
				
			}
		});
		//jmi3设置事件监听
		jmi3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//保存击毁敌人的数量和敌人的坐标
				Recorder.keepRecordEnemyTank();
				//退出
				System.exit(0);
			}
		});
		 
		//jmi4设置事件监听
		jmi4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//同时保存记录
				Recorder.keepRecording();
				System.exit(0);
			}
		});

		
		
	}
	
	  
	  //设置窗体居中
	    public static void setFrameCenter(JFrame jf) {
	        /*
	         思路：
	         A:获取屏幕的宽和高
	         B:获取窗体的宽和高
	         C:(用屏幕的宽-窗体的宽)/2，(用屏幕的高-窗体的高)/2作为窗体的新坐标。
	         */
	        //获取工具对象
	        Toolkit tk = Toolkit.getDefaultToolkit();

	        //获取屏幕的宽和高
	        Dimension d = tk.getScreenSize();
	        double srceenWidth = d.getWidth();
	        double srceenHeigth = d.getHeight();

	        //获取窗体的宽和高
	        int frameWidth = jf.getWidth();
	        int frameHeight = jf.getHeight();

	        //获取新的宽和高
	        int width = (int) (srceenWidth - frameWidth) / 2;
	        int height = (int) (srceenHeigth - frameHeight) / 2;

	        //设置窗体坐标
	        jf.setLocation(width, height);
	    }
}
