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
	//�����ҵ�һ��̹��
	private MyTank myTank = null;
	//�������̹����
	private Vector<EnemyTank> enemyTank = null;
	//�������̹�����귽�򼯺�
	private Vector<Node> nodes = null;
	private int MyLife = 3;	//�ҵ�����ֵ
	private int EnemyTankLife = 20;	//����̹������ֵ
	private int enemyNum = 3;	//����̹����
	//����3��ͼƬ(3��ͼƬ���һ��ը��)
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	//����ը���ļ���
	Vector<Bomb> bombs = null; 
	public MyPanel(){
		
	}
	//���췽��
	public MyPanel(String flag){
		//�ָ���¼(����ļ����ھͶ�ȡ�ظ�)
		if(new File("myRecord.dat").exists()){
			Recorder.getRecording();
		}
		//��ʼ���ҵ�̹��
		myTank = new MyTank(200,200);
		myTank.setSpeed(3);
		//��ʼ������̹����
		enemyTank = new Vector<EnemyTank>();
		//��ʼ��ը������
		bombs = new Vector<Bomb>();
		//��Ϊը���������һ�α�ը(�����һ�β���ը�����)
		bombs.add(new Bomb());
		
		if(flag.equals("newGame")){
			for(int i = 0; i<enemyNum ; i++){
				//��ʼ��̹�����ÿ��̹��
				EnemyTank badTank = new EnemyTank((i+1)*50,0);
				//����̹����
				enemyTank.add(badTank);
				badTank.setDirect(1);
				//��MyPanel�ĵ���̹�����������õ���̹��
				badTank.setEnemyTanks(enemyTank);
				//��MyPanel�ĵ���̹�����������ҵ�̹����
				 myTank.setEnemyTanks(enemyTank);
				 //��MyPanel���ҵ�̹�˽������˵�̹����
				 badTank.setMyTank(myTank);
				//��������̹���߳�
				Thread t = new Thread(badTank);
				t.start();
				//������̹�����һ���ӵ�
				Bullet enemyTankBullet = new Bullet(badTank.getX()+8,badTank.getY()+30,badTank.getDirect());
				//���������̹���ӵ���
				badTank.getEnemyTankbullets().add(enemyTankBullet);
				//���������ӵ��߳�
				Thread t2 = new Thread(enemyTankBullet);
				t2.start();
				
			}
		}else{
			this.nodes = Recorder.getRecordEnemyTank();
			for(int i = 0; i<nodes.size() ; i++){
				//��ʼ��̹�����ÿ��̹��
				EnemyTank badTank = new EnemyTank(nodes.get(i).getX(),nodes.get(i).getY());
				//����̹����
				enemyTank.add(badTank);
				badTank.setDirect(nodes.get(i).getDirect());
				//��MyPanel�ĵ���̹�����������õ���̹��
				badTank.setEnemyTanks(enemyTank);
				//��MyPanel�ĵ���̹�����������ҵ�̹����
				 myTank.setEnemyTanks(enemyTank);
				 //��MyPanel���ҵ�̹�˽������˵�̹����
				 badTank.setMyTank(myTank);
				//��������̹���߳�
				Thread t = new Thread(badTank);
				t.start();
				//������̹�����һ���ӵ�
				Bullet enemyTankBullet = new Bullet(badTank.getX()+8,badTank.getY()+30,badTank.getDirect());
				//���������̹���ӵ���
				badTank.getEnemyTankbullets().add(enemyTankBullet);
				//���������ӵ��߳�
				Thread t2 = new Thread(enemyTankBullet);
				t2.start();
			}
			
		}
		//���ſ�ս����
		PlayMusic apw=new PlayMusic("Start.wav");
		Thread T = new Thread(apw);
		T.start();
		
		
		//��MyPanel�ĵ���̹������������¼��
		Recorder.setEnemyTanks(enemyTank);
		
		//��ʼ��ͼƬ
		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	
	
	//����paint
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//�����ҵ�̹��(������ͻ�)(ԭ�ȵ�̹��)
		if(this.myTank.isLive()){
			paintTank(this.myTank.getX(),this.myTank.getY(),g,this.myTank.getDirect(),this.myTank.getType());
		}else if(!this.myTank.isLive() && this.MyLife>0){
			paintTank(this.myTank.getX(),this.myTank.getY(),g,this.myTank.getDirect(),this.myTank.getType());
		}
		//��������̹��
		this.paintEnemyTank(g);
		//�����ӵ�
		this.paintBullet(g);
		//������ըЧ��
		this.paintBomb(g);
		//������ʾ̹��
		this.paintInfoTank(g);
		
		
		if(Recorder.getMyTankNum()==0){
			GameOver o = new GameOver();
			Thread t = new Thread(o);
			t.start();
			this.updateUI();//���ˢ�²��˵�����
			this.getParent().add(o);
			this.getParent().remove(this);
			//����GameOver��Ч
			PlayMusic p = new PlayMusic("GameOver.wav");
			Thread t2 = new Thread(p);
			t2.start();
		}else if(Recorder.getEnemyTankNum()==0){
			Win w = new Win();
			Thread t = new Thread(w);
			t.start();
			this.updateUI();//���ˢ�²��˵�����
			this.getParent().add(w);
			this.getParent().remove(this);
			//����Win��Ч
			PlayMusic p = new PlayMusic("Win.wav");
			Thread t2 = new Thread(p);
			t2.start();
		}
	}
	
	
	
	
	
	
	/**
	 * ��̹�˵ķ���
	 * @param x x����
	 * @param y y����
	 * @param g ����
	 * @param direct ̹�˷���
	 * @param type ̹������
	 */
	public  static void paintTank(int x,int y,Graphics g,int direct,int type){
		//�ж�̹������
		switch(type){
			case 0:	//0Ϊ�ҵ�̹��
					g.setColor(Color.yellow);
					break; 
			case 1:	//1Ϊ���˵�̹��
					g.setColor(Color.cyan);
					break;
		}
		switch(direct){
			case 0:	//0Ϊ���Ϸ���
					//������ߵľ���
				   g.fill3DRect(x, y, 5, 30, false);
				   //�����ұߵľ���
				   g.fill3DRect(x+15, y, 5, 30, false);
				   //�����м�ľ���
				   g.fill3DRect(x+5, y+5, 10, 20, false);
				   //�����м��Բ��
				   g.fillOval(x+4, y+10, 10, 10);
				   //�����м����
				   g.drawLine(x+9, y+15, x+9, y);
					break;
			case 1:	//1Ϊ���·���
				   g.fill3DRect(x, y, 5, 30, false);
				   g.fill3DRect(x+15, y, 5, 30, false);
				   g.fill3DRect(x+5, y+5, 10, 20, false);
				   g.fillOval(x+4, y+10, 10, 10);
				   g.drawLine(x+9, y+30, x+9, y+15);
					break;
					
			case 2:	//2Ϊ������
				   g.fill3DRect(x, y+5, 30, 5, false);
				   g.fill3DRect(x, y+20, 30, 5, false);
				   g.fill3DRect(x+5, y+10, 20, 10, false);
				   g.fillOval(x+9, y+10, 10, 10);
				   g.drawLine(x, y+15, x+9, y+15);
					break;
					
			case 3: //3Ϊ���ҷ���
				   g.fill3DRect(x, y+5, 30, 5, false);
				   g.fill3DRect(x, y+20, 30, 5, false);
				   g.fill3DRect(x+5, y+10, 20, 10, false);
				   g.fillOval(x+9, y+10, 10, 10);
				   g.drawLine(x+9, y+15, x+30, y+15);
					break;
				
		}
		
	}
	//���̰���
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
			//����̹��һ�����ֻ�ܷ�����ӵ�
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
	 * �߳�run()����
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
			//�ж��ӵ��Ƿ���е��˵�̹��
			this.shotEnemyTank();
			//�жϵ����ӵ��Ƿ�����ҵ�̹��
			this.shotMyTank();
			this.repaint();
		}
		
	}
	
	



	/**
	 * ר���ж��ӵ��Ƿ����̹�˵ķ���
	 * �����ڻ���̹��ͬʱ������ը��Ч��
	 * @param bullet ������ӵ�
	 * @param tank	�����е�̹��
	 */
	public void hitTank(Bullet bullet, Tank tank){
		//�жϵ���̹�˵ķ���
		switch(tank.getDirect()){
			case 0:
			case 1:
					if(bullet.getX()>tank.getX() && bullet.getX()<tank.getX()+20 && bullet.getY()>tank.getY() && bullet.getY()<tank.getY()+30){
						//�ӵ�����
						bullet.setLive(false);
						//̹������(Ȼ�������ըЧ��)
						tank.setLive(false);
						//������ը��Ч
						PlayMusic p = new PlayMusic("Bomb.wav");
						Thread t = new Thread(p);
						t.start();
						//������ҵ�̹������
						if(tank instanceof MyTank){
							Recorder.reduceMyTankNum();
							this.MyLife--; //����ֵ����
							//�����ҵ���̹��
							if(MyLife>0){
								myTank = new MyTank(200,200);
								myTank.setSpeed(2);
								//���°ѵ���̹���齻���ҵ�̹����
								myTank.setEnemyTanks(enemyTank);
							}
						}
						//����ǵ��˵�̹������
						if(tank instanceof EnemyTank){
							Recorder.reduceEnemyTankNum();
							Recorder.addShotEnemyTankNum();
							this.EnemyTankLife--;//������������
							//�����µĵ���̹��
							if(EnemyTankLife>=3){
								//�������x
								int x = (int)(Math.random()*300+1);
								EnemyTank e= new EnemyTank(x,0);
								e.setDirect(1);
								enemyTank.add(e);
								//��MyPanel�ĵ���̹�˽������˵�̹����
								e.setEnemyTanks(enemyTank);
								//��MyPanel���ҵ�̹�˽������˵�̹����
								e.setMyTank(this.myTank);
								Thread T = new Thread(e);
								T.start();
							}
						}
						//����ը��(��ըЧ��)
						//��ը�ص�Ϊ����̹��Ŀǰ���ڵĵص�
						Bomb bomb = new Bomb(tank.getX(),tank.getY());
						//����ը������
						bombs.add(bomb);	
					}
					 break;
			case 2:
			case 3:
					if(bullet.getX()>tank.getX() && bullet.getX()<tank.getX()+30 && bullet.getY()>tank.getY() && bullet.getY()<tank.getY()+20){
						//�ӵ�����
						bullet.setLive(false);
						//̹������(Ȼ�������ըЧ��)
						tank.setLive(false);
						//������ը��Ч
						PlayMusic p = new PlayMusic("Bomb.wav");
						Thread t = new Thread(p);
						t.start();
						//������ҵ�̹������
						if(tank instanceof MyTank){
							Recorder.reduceMyTankNum();
							this.MyLife--; //����ֵ����
							//�����ҵ���̹��
							if(MyLife>0){
								myTank = new MyTank(200,200);
								myTank.setSpeed(2);
								//���°ѵ���̹���齻���ҵ�̹����
								myTank.setEnemyTanks(enemyTank);
							}
						}
						//����ǵ��˵�̹������
						if(tank instanceof EnemyTank){
							Recorder.reduceEnemyTankNum();
							Recorder.addShotEnemyTankNum();
							this.EnemyTankLife--;//������������
							//�����µĵ���̹��
							if(EnemyTankLife>=3){
								int x = (int)(Math.random()*300+1);
								EnemyTank e= new EnemyTank(x,0);
								e.setDirect(1);
								enemyTank.add(e);
								//��MyPanel�ĵ���̹�˽������˵�̹����
								e.setEnemyTanks(enemyTank);
								//��MyPanel���ҵ�̹�˽������˵�̹����
								e.setMyTank(this.myTank);
								Thread T = new Thread(e);
								T.start();
							}
						}
						//����ը��(��ըЧ��)
						//��ը�ص�Ϊ����̹��Ŀǰ���ڵĵص�
						Bomb bomb = new Bomb(tank.getX(),tank.getY());
						//����ը������
						bombs.add(bomb);
					}
					break;
		}
	}
	
	
	
	
	/**
	 * ����ը���ı�ըЧ��
	 * @param g
	 */
	public void paintBomb(Graphics g){
		//����ը��(��ըЧ��)
		for(int i = 0; i<this.bombs.size(); i++){
			//ȡ��ÿ��ը��
			Bomb thisBomb = this.bombs.get(i);
			if(thisBomb.getLife()>6){
				//������һ�ֱ�ըЧ��
				g.drawImage(image1, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}else if(thisBomb.getLife()>4){
				//�����ڶ��ֱ�ըЧ��
				g.drawImage(image2, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}else{
				//���������ֱ�ըЧ��
				g.drawImage(image3, thisBomb.getX(), thisBomb.getY(), 30, 30, this);
			}
			//��ը�����������ڼ�С
			thisBomb.lifeDown();
			//���ը��������Ϊ0����ը���Ѿ����������Ҵ�ը�������Ƴ�
			if(thisBomb.getLife()==0){
				thisBomb.setLive(false);
				bombs.remove(thisBomb);
			}
		}
	}
	
	
	
	/**
	 * �����ӵ�
	 * @param g
	 */
	public void paintBullet(Graphics g){
		//ȡ��ÿ���ӵ���������(���ϱ���)
		//�����ӵ�(���ӵ���Ϊ�պ��ӵ�����ʱ�򻭳��ӵ�)
		//�������������׳��쳣,ConcurrentModificationException����������⵽����Ĳ����޸ģ��������������޸�ʱ���׳����쳣��
		for(int i = 0;i<this.myTank.getBullet().size();i++){
			Bullet mybullet = this.myTank.getBullet().get(i);
			if(mybullet!=null && mybullet.isLive()){
				g.setColor(Color.yellow);
				g.fill3DRect(mybullet.getX(), mybullet.getY(), 2, 2, false);
			}
			if(mybullet.isLive()==false){
				//���ӵ������Ƴ��ӵ�
				this.myTank.getBullet().remove(mybullet);
			}
		}
	}

	
	
	/**
	 * �������˵�̹��
	 * @param g
	 */
	public void paintEnemyTank(Graphics g){
		//�������˵�̹��(���ͻ���)
				for(int i = 0; i<enemyTank.size(); i++){
					//�õ�ÿ��̹��
					EnemyTank thisEnemyTank = this.enemyTank.get(i);
					//����̹�˴��ڣ�������
					if(thisEnemyTank.isLive()){
						paintTank(thisEnemyTank.getX(),thisEnemyTank.getY(),g,thisEnemyTank.getDirect(),1);
						//�ڻ����˵��ӵ�
						for(int j = 0; j<thisEnemyTank.getEnemyTankbullets().size();j++){
							//ȡ��ÿ���ӵ�
							Bullet thisEnemyTankBullet = thisEnemyTank.getEnemyTankbullets().get(j);
							if(thisEnemyTankBullet.isLive()){
								g.fill3DRect(thisEnemyTankBullet.getX(),thisEnemyTankBullet.getY(), 2, 2, false);
							}else{
								//�ѵ����ӵ��ӵ����ӵ������Ƴ�
								thisEnemyTank.getEnemyTankbullets().remove(thisEnemyTankBullet);
							}
						}
					}else{
						//������˵�̹�������ˣ��ʹ�̹�����Ƴ�
						this.enemyTank.remove(thisEnemyTank);
					}
				}
	}

	
/**
 * �жϵ��˵�̹���Ƿ񱻻���
 */
	public void shotEnemyTank(){
		//�жϵ���̹���Ƿ񱻻���
		//��ȡ��ÿ���ӵ�����ȡ��ÿ��̹�ˣ�һ�����ȶ�
		//ȡ��ÿ���ӵ�
		for(int i = 0;i<this.myTank.getBullet().size(); i++){
			//�õ�ÿ���ӵ�
			Bullet thisBullet = this.myTank.getBullet().get(i);
			//����ӵ����
			if(thisBullet.isLive()){
				//ȡ��ÿ��̹��
				for(int j = 0;j<this.enemyTank.size(); j++){
					//�õ�ÿ��̹��
					EnemyTank thisEneymTank = this.enemyTank.get(j);
					//���̹�˴��
					if(thisEneymTank.isLive()){
						//��������Ϊ������
						this.hitTank(thisBullet, thisEneymTank);
					}
				}
			}
			
		}
	}

	/**
	 * �жϵ��˵��ӵ��Ƿ�����ҵ�̹��
	 */
	public void shotMyTank() {
		//��ȡ�����˵�ÿ��̹��
		for(int i = 0; i<this.enemyTank.size(); i++){
			//�õ�ÿ��̹��
			EnemyTank thisEnemyTank = this.enemyTank.get(i);
			//�õ�����̹�˵�ÿ���ӵ�
			for(int j = 0; j<thisEnemyTank.getEnemyTankbullets().size(); j++){
				//�õ�ÿһ�ŵ����ӵ�
				Bullet enemyBullet = thisEnemyTank.getEnemyTankbullets().get(j);
				//����ҵ�̹�˴��
				if(this.myTank.isLive()){
					//�ж��ӵ��Ƿ����̹��,������������Ϊ����
					this.hitTank(enemyBullet, this.myTank);
				}
			}
		}
		
	}
	
	
	
	/**
	 * ������ʾ��Ϣ̹��
	 */
	public void paintInfoTank(Graphics g){
		//������ʾ��Ϣ��̹��(��̹�˲�����ս��)
		this.paintTank(80, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnemyTankNum()+"", 110, 350);
		this.paintTank(130, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyTankNum()+"", 165, 350);
		
		//������ҵ��ܳɼ�
		g.setColor(Color.black);
		Font font = new Font("����",Font.BOLD,20);
		g.setFont(font);
		g.drawString("�����ܳɼ�", 420, 30);
		this.paintTank(420, 60, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getShotEnemyTankNum()+"", 460, 80);
	}
}
