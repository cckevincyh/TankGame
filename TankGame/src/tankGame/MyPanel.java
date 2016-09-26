package tankGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class MyPanel extends JPanel implements KeyListener,Runnable{
	//定义我的一部坦克
	private MyTank myTank = null;
	//定义敌人坦克组
	private Vector<EnemyTank> enemyTank = null;
	//定义敌人坦克坐标方向集合
	private Vector<Node> nodes = null;
	private int MyLife = 3;	//我的生命值
	private int EnemyTankLife = 20;	//敌人坦克生命值
	private int enemyNum = 3;	//敌人坦克数
	//定义3张图片(3张图片组成一个炸弹)
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	//定义炸弹的集合
	Vector<Bomb> bombs = null; 
	public MyPanel(){
		
	}
	//构造方法
	public MyPanel(String flag){
		//恢复记录(如果文件存在就读取回复)
		if(new File("myRecord.dat").exists()){
			Recorder.getRecording();
		}
		//初始化我的坦克
		myTank = new MyTank(200,200);
		myTank.setSpeed(3);
		//初始化敌人坦克组
		enemyTank = new Vector<EnemyTank>();
		//初始化炸弹集合
		bombs = new Vector<Bomb>();
		//先为炸弹组中添加一次爆炸(解决第一次不爆炸的情况)
		bombs.add(new Bomb());
		
		if(flag.equals("newGame")){
			for(int i = 0; i<enemyNum ; i++){
				//初始化坦克组的每辆坦克
				EnemyTank badTank = new EnemyTank((i+1)*50,0);
				//加入坦克组
				enemyTank.add(badTank);
				badTank.setDirect(1);
				//将MyPanel的敌人坦克向量交给该敌人坦克
				badTank.setEnemyTanks(enemyTank);
				//将MyPanel的敌人坦克向量交给我的坦克类
				 myTank.setEnemyTanks(enemyTank);
				 //将MyPanel上我的坦克交给敌人的坦克类
				 badTank.setMyTank(myTank);
				//启动敌人坦克线程
				Thread t = new Thread(badTank);
				t.start();
				//给敌人坦克添加一颗子弹
				Bullet enemyTankBullet = new Bullet(badTank.getX()+8,badTank.getY()+30,badTank.getDirect());
				//加入给敌人坦克子弹组
				badTank.getEnemyTankbullets().add(enemyTankBullet);
				//启动敌人子弹线程
				Thread t2 = new Thread(enemyTankBullet);
				t2.start();
				
			}
		}else{
			this.nodes = Recorder.getRecordEnemyTank();
			for(int i = 0; i<nodes.size() ; i++){
				//初始化坦克组的每辆坦克
				EnemyTank badTank = new EnemyTank(nodes.get(i).getX(),nodes.get(i).getY());
				//加入坦克组
				enemyTank.add(badTank);
				badTank.setDirect(nodes.get(i).getDirect());
				//将MyPanel的敌人坦克向量交给该敌人坦克
				badTank.setEnemyTanks(enemyTank);
				//将MyPanel的敌人坦克向量交给我的坦克类
				 myTank.setEnemyTanks(enemyTank);
				 //将MyPanel上我的坦克交给敌人的坦克类
				 badTank.setMyTank(myTank);
				//启动敌人坦克线程
				Thread t = new Thread(badTank);
				t.start();
				//给敌人坦克添加一颗子弹
				Bullet enemyTankBullet = new Bullet(badTank.getX()+8,badTank.getY()+30,badTank.getDirect());
				//加入给敌人坦克子弹组
				badTank.getEnemyTankbullets().add(enemyTankBullet);
				//启动敌人子弹线程
				Thread t2 = new Thread(enemyTankBullet);
				t2.start();
			}
			
		}
		//播放开战声音
		PlayMusic apw=new PlayMusic("Start.wav");
		Thread T = new Thread(apw);
		T.start();
		
		
		//将MyPanel的敌人坦克向量交给记录类
		Recorder.setEnemyTanks(enemyTank);
		
		//初始化图片
		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	
	
	//重新paint
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//画出我的坦克(如果存活就画)(原先的坦克)
		if(this.myTank.isLive()){
			paintTank(this.myTank.getX(),this.myTank.getY(),g,this.myTank.getDirect(),this.myTank.getType());
		}else if(!this.myTank.isLive() && this.MyLife>0){
			paintTank(this.myTank.getX(),this.myTank.getY(),g,this.myTank.getDirect(),this.myTank.getType());
		}
		//画出敌人坦克
		this.paintEnemyTank(g);
		//画出子弹
		this.paintBullet(g);
		//画出爆炸效果
		this.paintBomb(g);
		//画出提示坦克
		this.paintInfoTank(g);
		
		
		if(Recorder.getMyTankNum()==0){
			GameOver o = new GameOver();
			Thread t = new Thread(o);
			t.start();
			this.updateUI();//解决刷新不了的问题
			this.getParent().add(o);
			this.getParent().remove(this);
			//播放GameOver音效
			PlayMusic p = new PlayMusic("GameOver.wav");
			Thread t2 = new Thread(p);
			t2.start();
		}else if(Recorder.getEnemyTankNum()==0){
			Win w = new Win();
			Thread t = new Thread(w);
			t.start();
			this.updateUI();//解决刷新不了的问题
			this.getParent().add(w);
			this.getParent().remove(this);
			//播放Win音效
			PlayMusic p = new PlayMusic("Win.wav");
			Thread t2 = new Thread(p);
			t2.start();
		}
	}
	
	
	
	
	
	
	/**
	 * 画坦克的方法
	 * @param x x坐标
	 * @param y y坐标
	 * @param g 画笔
	 * @param direct 坦克方向
	 * @param type 坦克类型
	 */
	public  static void paintTank(int x,int y,Graphics g,int direct,int type){
		//判断坦克类型
		switch(type){
			case 0:	//0为我的坦克
					g.setColor(Color.yellow);
					break; 
			case 1:	//1为敌人的坦克
					g.setColor(Color.cyan);
					break;
		}
		switch(direct){
			case 0:	//0为向上方向
					//画出左边的矩形
				   g.fill3DRect(x, y, 5, 30, false);
				   //画出右边的矩形
				   g.fill3DRect(x+15, y, 5, 30, false);
				   //画出中间的矩形
				   g.fill3DRect(x+5, y+5, 10, 20, false);
				   //画出中间的圆形
				   g.fillOval(x+4, y+10, 10, 10);
				   //画出中间的线
				   g.drawLine(x+9, y+15, x+9, y);
					break;
			case 1:	//1为向下方向
				   g.fill3DRect(x, y, 5, 30, false);
				   g.fill3DRect(x+15, y, 5, 30, false);
				   g.fill3DRect(x+5, y+5, 10, 20, false);
				   g.fillOval(x+4, y+10, 10, 10);
				   g.drawLine(x+9, y+30, x+9, y+15);
					break;
					
			case 2:	//2为向左方向
				   g.fill3DRect(x, y+5, 30, 5, false);
				   g.fill3DRect(x, y+20, 30, 5, false);
				   g.fill3DRect(x+5, y+10, 20, 10, false);
				   g.fillOval(x+9, y+10, 10, 10);
				   g.drawLine(x, y+15, x+9, y+15);
					break;
					
			case 3: //3为向右方向
				   g.fill3DRect(x, y+5, 30, 5, false);
				   g.fill3DRect(x, y+20, 30, 5, false);
				   g.fill3DRect(x+5, y+10, 20, 10, false);
				   g.fillOval(x+9, y+10, 10, 10);
				   g.drawLine(x+9, y+15, x+30, y+15);
					break;
				
		}
		
	}
	//键盘按下
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W){
			if(this.myTank.getY()>0){
				this.myTank.setDirect(0);
				if(!this.myTank.isTouchOtherEnemy()){
					this.myTank.move_Up();
					this.repaint();
				}
			}
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S){
			if(this.myTank.getY()<271){
				this.myTank.setDirect(1);
				if(!this.myTank.isTouchOtherEnemy()){
					this.myTank.move_Down();
					this.repaint();
				}
			}
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A){
			if(this.myTank.getX()>0){
				this.myTank.setDirect(2);
				if(!this.myTank.isTouchOtherEnemy()){
					this.myTank.move_Left();
					this.repaint();
				}
			}
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D){
			if(this.myTank.getX()<371){
				this.myTank.setDirect(3);
				if(!this.myTank.isTouchOtherEnemy()){
					this.myTank.move_Right();
					this.repaint();
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE){
			//定义坦克一次最多只能发五颗子弹
			if(this.myTank.getBullet().size()<5 && this.myTank.isLive()){
				this.myTank.shot();
				PlayMusic bb=new PlayMusic("Shot.wav");
				Thread t = new Thread(bb);
				t.start();
				this.repaint();
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 线程run()方法
	 */
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//判断子弹是否击中敌人的坦克
			this.shotEnemyTank();
			//判断敌人子弹是否击中我的坦克
			this.shotMyTank();
			this.repaint();
		}
		
	}
	
	



	/**
	 * 专门判断子弹是否击中坦克的方法
	 * 并且在击中坦克同时产生爆炸的效果
	 * @param bullet 射出的子弹
	 * @param tank	被击中的坦克
	 */
	public void hitTank(Bullet bullet, Tank tank){
		//判断敌人坦克的方向
		switch(tank.getDirect()){
			case 0:
			case 1:
					if(bullet.getX()>tank.getX() && bullet.getX()<tank.getX()+20 && bullet.getY()>tank.getY() && bullet.getY()<tank.getY()+30){
						//子弹死亡
						bullet.setLive(false);
						//坦克死亡(然后产生爆炸效果)
						tank.setLive(false);
						//产生爆炸音效
						PlayMusic p = new PlayMusic("Bomb.wav");
						Thread t = new Thread(p);
						t.start();
						//如果是我的坦克死亡
						if(tank instanceof MyTank){
							Recorder.reduceMyTankNum();
							this.MyLife--; //生命值减少
							//产生我的新坦克
							if(MyLife>0){
								myTank = new MyTank(200,200);
								myTank.setSpeed(2);
								//重新把敌人坦克组交给我的坦克类
								myTank.setEnemyTanks(enemyTank);
							}
						}
						//如果是敌人的坦克死亡
						if(tank instanceof EnemyTank){
							Recorder.reduceEnemyTankNum();
							Recorder.addShotEnemyTankNum();
							this.EnemyTankLife--;//敌人生命减少
							//产生新的敌人坦克
							if(EnemyTankLife>=3){
								//随机生成x
								int x = (int)(Math.random()*300+1);
								EnemyTank e= new EnemyTank(x,0);
								e.setDirect(1);
								enemyTank.add(e);
								//将MyPanel的敌人坦克交给敌人的坦克类
								e.setEnemyTanks(enemyTank);
								//将MyPanel的我的坦克交给敌人的坦克类
								e.setMyTank(this.myTank);
								Thread T = new Thread(e);
								T.start();
							}
						}
						//创建炸弹(爆炸效果)
						//爆炸地点为敌人坦克目前所在的地点
						Bomb bomb = new Bomb(tank.getX(),tank.getY());
						//加入炸弹集合
						bombs.add(bomb);	
					}
					 break;
			case 2:
			case 3:
					if(bullet.getX()>tank.getX() && bullet.getX()<tank.getX()+30 && bullet.getY()>tank.getY() && bullet.getY()<tank.getY()+20){
						//子弹死亡
						bullet.setLive(false);
						//坦克死亡(然后产生爆炸效果)
						tank.setLive(false);
						//产生爆炸音效
						PlayMusic p = new PlayMusic("Bomb.wav");
						Thread t = new Thread(p);
						t.start();
						//如果是我的坦克死亡
						if(tank instanceof MyTank){
							Recorder.reduceMyTankNum();
							this.MyLife--; //生命值减少
							//产生我的新坦克
							if(MyLife>0){
								myTank = new MyTank(200,200);
								myTank.setSpeed(2);
								//重新把敌人坦克组交给我的坦克类
								myTank.setEnemyTanks(enemyTank);
							}
						}
						//如果是敌人的坦克死亡
						if(tank instanceof EnemyTank){
							Recorder.reduceEnemyTankNum();
							Recorder.addShotEnemyTankNum();
							this.EnemyTankLife--;//敌人生命减少
							//产生新的敌人坦克
							if(EnemyTankLife>=3){
								int x = (int)(Math.random()*300+1);
								EnemyTank e= new EnemyTank(x,0);
								e.setDirect(1);
								enemyTank.add(e);
								//将MyPanel的敌人坦克交给敌人的坦克类
								e.setEnemyTanks(enemyTank);
								//将MyPanel的我的坦克交给敌人的坦克类
								e.setMyTank(this.myTank);
								Thread T = new Thread(e);
								T.start();
							}
						}
						//创建炸弹(爆炸效果)
						//爆炸地点为敌人坦克目前所在的地点
						Bomb bomb = new Bomb(tank.getX(),tank.getY());
						//加入炸弹集合
						bombs.add(bomb);
					}
					break;
		}
	}
	
	
	
	
	/**
	 * 画出炸弹的爆炸效果
	 * @param g
	 */
	public void paintBomb(Graphics g){
		//画出炸弹(爆炸效果)
		for(int i = 0; i<this.bombs.size(); i++){
			//取出每颗炸弹
			Bomb thisBomb = this.bombs.get(i);
			if(thisBomb.getLife()>6){
				//画出第一种爆炸效果
				g.drawImage(image1, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}else if(thisBomb.getLife()>4){
				//画出第二种爆炸效果
				g.drawImage(image2, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}else{
				//画出第三种爆炸效果
				g.drawImage(image3, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}
			//让炸弹的声明周期减小
			thisBomb.lifeDown();
			//如果炸弹的生命为0，则炸弹已经死亡，并且从炸弹组中移除
			if(thisBomb.getLife()==0){
				thisBomb.setLive(false);
				bombs.remove(thisBomb);
			}
		}
	}
	
	
	
	/**
	 * 画出子弹
	 * @param g
	 */
	public void paintBullet(Graphics g){
		//取出每颗子弹，并画出(集合遍历)
		//画出子弹(当子弹不为空和子弹存活的时候画出子弹)
		//迭代器迭代会抛出异常,ConcurrentModificationException：当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常。
		for(int i = 0;i<this.myTank.getBullet().size();i++){
			Bullet mybullet = this.myTank.getBullet().get(i);
			if(mybullet!=null && mybullet.isLive()){
				g.setColor(Color.yellow);
				g.fill3DRect(mybullet.getX(), mybullet.getY(), 2, 2, false);
			}
			if(mybullet.isLive()==false){
				//从子弹集合移除子弹
				this.myTank.getBullet().remove(mybullet);
			}
		}
	}

	
	
	/**
	 * 画出敌人的坦克
	 * @param g
	 */
	public void paintEnemyTank(Graphics g){
		//画出敌人的坦克(存活就画出)
				for(int i = 0; i<enemyTank.size(); i++){
					//得到每部坦克
					EnemyTank thisEnemyTank = this.enemyTank.get(i);
					//敌人坦克存在，并画出
					if(thisEnemyTank.isLive()){
						paintTank(thisEnemyTank.getX(),thisEnemyTank.getY(),g,thisEnemyTank.getDirect(),1);
						//在画敌人的子弹
						for(int j = 0; j<thisEnemyTank.getEnemyTankbullets().size();j++){
							//取出每颗子弹
							Bullet thisEnemyTankBullet = thisEnemyTank.getEnemyTankbullets().get(j);
							if(thisEnemyTankBullet.isLive()){
								g.fill3DRect(thisEnemyTankBullet.getX(),thisEnemyTankBullet.getY(), 2, 2, false);
							}else{
								//把敌人子弹从敌人子弹组中移除
								thisEnemyTank.getEnemyTankbullets().remove(thisEnemyTankBullet);
							}
						}
					}else{
						//如果敌人的坦克死亡了，就从坦克组移除
						this.enemyTank.remove(thisEnemyTank);
					}
				}
	}

	
/**
 * 判断敌人的坦克是否被击中
 */
	public void shotEnemyTank(){
		//判断敌人坦克是否被击中
		//先取出每颗子弹，在取出每部坦克，一个个比对
		//取出每颗子弹
		for(int i = 0;i<this.myTank.getBullet().size(); i++){
			//得到每颗子弹
			Bullet thisBullet = this.myTank.getBullet().get(i);
			//如果子弹存活
			if(thisBullet.isLive()){
				//取出每部坦克
				for(int j = 0;j<this.enemyTank.size(); j++){
					//得到每部坦克
					EnemyTank thisEneymTank = this.enemyTank.get(j);
					//如果坦克存活
					if(thisEneymTank.isLive()){
						//将其设置为被击中
						this.hitTank(thisBullet, thisEneymTank);
					}
				}
			}
			
		}
	}

	/**
	 * 判断敌人的子弹是否击中我的坦克
	 */
	public void shotMyTank() {
		//先取出敌人的每辆坦克
		for(int i = 0; i<this.enemyTank.size(); i++){
			//得到每辆坦克
			EnemyTank thisEnemyTank = this.enemyTank.get(i);
			//得到这辆坦克的每颗子弹
			for(int j = 0; j<thisEnemyTank.getEnemyTankbullets().size(); j++){
				//得到每一颗敌人子弹
				Bullet enemyBullet = thisEnemyTank.getEnemyTankbullets().get(j);
				//如果我的坦克存活
				if(this.myTank.isLive()){
					//判断子弹是否击中坦克,击中则将其设置为死亡
					this.hitTank(enemyBullet, this.myTank);
				}
			}
		}
		
	}
	
	
	
	/**
	 * 画出提示信息坦克
	 */
	public void paintInfoTank(Graphics g){
		//画出提示信息的坦克(该坦克不参与战斗)
		this.paintTank(80, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnemyTankNum()+"", 110, 350);
		this.paintTank(130, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyTankNum()+"", 165, 350);
		
		//画出玩家的总成绩
		g.setColor(Color.black);
		Font font = new Font("宋体",Font.BOLD,20);
		g.setFont(font);
		g.drawString("您的总成绩", 420, 30);
		this.paintTank(420, 60, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getShotEnemyTankNum()+"", 460, 80);
	}
}
