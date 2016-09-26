package tankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	private int time = 0; //����ʱ��
	//������˵��ӵ�����(���Դ�ŵ��˵��ӵ�)
	private Vector<Bullet> enemyTankbullets = new Vector<Bullet>();
	//��������ӵ���Ӧ���ڸոմ���̹�˺͵��˵�̹���ӵ�������
	
	//����һ�����������Է��ʵ�MyPanel�����е���̹��(������ȡMyPanel�ϵ�̹��)
	private Vector<EnemyTank> enemyTanks = null;
	
	//����һ���ҵ�̹�˱�������������MyPanel�ϵ��ҵ�̹��
	private MyTank myTank = null;
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public MyTank getMyTank() {
		return myTank;
	}

	public void setMyTank(MyTank myTank) {
		this.myTank = myTank;
	}

	public Vector<EnemyTank> getEnemyTanks() {
		return enemyTanks;
	}

	public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
		this.enemyTanks = enemyTanks;
	}

	public EnemyTank(int x,int y){
		
		super(x,y);
	}
	
	public EnemyTank(int x,int y,int direct,int speed){
		super(x,y,direct,speed);
	}
	
	@Override
	public void run() {
		while(true){
			switch(this.getDirect()){
				case 0:
						for(int i = 0; i<30; i++){
							if(this.getY()>0&&!this.isTouchOtherEnemy()&&!this.isTouchMyTank()){
								this.setY(this.getY()-this.getSpeed());
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						break;
				case 1:
						for(int i = 0; i<30; i++){
							if(this.getY()<271&&!this.isTouchOtherEnemy()&&!this.isTouchMyTank()){
								this.setY(this.getY()+this.getSpeed());
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
						break;
				case 2:
						for(int i = 0; i<30; i++){
							if(this.getX()>0&&!this.isTouchOtherEnemy()&&!this.isTouchMyTank()){
								this.setX(this.getX()-this.getSpeed());
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						break;
				case 3:
						for(int i = 0; i<30; i++){
							if(this.getX()<371&&!this.isTouchOtherEnemy()&&!this.isTouchMyTank()){
								this.setX(this.getX()+this.getSpeed());
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					break;
				}
			this.time++;
			//�ж��Ƿ���Ҫ������̹������ӵ�
			//����ÿ�����˵�̹��
			if(this.time%2 == 0){
				if(this.isLive()){
					//�жϵ���̹�˵��ӵ�
					if(this.getEnemyTankbullets().size()<5){
						Bullet thisEnemyTankBullet = null;
						//û���ӵ�
						//���
						//�ж��ӵ�����
						switch(this.getDirect()){
							case 0:
								thisEnemyTankBullet = new Bullet(this.getX()+9, this.getY()+1, 0);
								this.getEnemyTankbullets().add(thisEnemyTankBullet);
									break;
							case 1:
								thisEnemyTankBullet = new Bullet(this.getX()+9, this.getY()+30, 1);
								this.getEnemyTankbullets().add(thisEnemyTankBullet);
									break;
									
							case 2:
								thisEnemyTankBullet = new Bullet(this.getX(), this.getY()+15, 2);
								this.getEnemyTankbullets().add(thisEnemyTankBullet);
									break;
									
							case 3: 
								thisEnemyTankBullet = new Bullet(this.getX()+30, this.getY()+15, 3);
								this.getEnemyTankbullets().add(thisEnemyTankBullet);
									break;
						}
						//���������ӵ��߳�
						Thread t = new Thread(thisEnemyTankBullet);
						t.start();
					}
				}
			
			
				//�õ���̹�˲���һ���������
				this.setDirect((int)(Math.random()*4));
			
				if(this.isLive() == false){
					//�˳��߳�
					break;
				}
			}
		}
	
	}
	

	public Vector<Bullet> getEnemyTankbullets() {
		return enemyTankbullets;
	}

	public void setEnemyTankbullets(Vector<Bullet> enemyTankbullets) {
		this.enemyTankbullets = enemyTankbullets;
	}

	
	//�ж��Ƿ������˱�ĵ��˵�̹��
		public boolean isTouchOtherEnemy()
		{
			boolean isTouch=false;
			
			switch(this.getDirect())
			{
			case 0:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
					//��������Լ�
					if(et!=this)
					{
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+30)
							{
								return true;
							}
							if(this.getX()+20>=et.getX()&&this.getX()+20<=et.getX()+20&&this.getY()>=et.getY()&&this.getY()<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.getX()+20>=et.getX()&&this.getX()+20<=et.getX()+30&&this.getY()>=et.getY()&&this.getY()<=et.getY()+20)
							{
								return true;
							}
						}
					}
				}
				break;
				
			case 1:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
					//��������Լ�
					if(et!=this)
					{
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()+30>=getY()&&this.getY()+30<=et.getY()+30)
							{
								return true;
							}
							if(this.getX()+20>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()+30>=et.getY()&&this.getY()+30<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()+30>=getY()&&this.getY()+30<=et.getY()+20)
							{
								return true;
							}
							if(this.getX()+20>=et.getX()&&this.getX()+20<=et.getX()+30&&this.getY()+30>=et.getY()&&this.getY()+30<=et.getY()+20)
							{
								return true;
							}
						}
					}
				}
				break;	
				
			case 2:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
					//��������Լ�
					if(et!=this)
					{
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							//�ҵ���һ��
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+30)
							{
								return true;
							}
							//�ҵ���һ��
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()+20>=et.getY()&&this.getY()+20<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.getX()+20>=et.getX()&&this.getX()+20<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+30&&this.getY()+20>=et.getY()&&this.getY()+20<=et.getY()+20)
							{
								return true;
							}
						}
					}
				}
				break;
				
			case 3:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
					//��������Լ�
					if(et!=this)
					{
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.getX()+30>=et.getX()&&this.getX()+30<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+30)
							{
								return true;
							}
							if(this.getX()+30>=et.getX()&&this.getX()+30<=et.getX()+20&&this.getY()+20>=et.getY()&&this.getY()+20<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.getX()+30>=et.getX()&&this.getX()+30<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.getX()+30>=et.getX()&&this.getX()+30<=et.getX()+30&&this.getY()+20>=et.getY()&&this.getY()+20<=et.getY()+20)
							{
								return true;
							}
						}
					}
				}
				break;
				
			}
			return isTouch;
		}
	
	//�жϵ���̹���Ƿ��������ҵ�̹��
		public boolean isTouchMyTank(){
			boolean isTouch=false;
			
			switch(this.myTank.getDirect())
			{
			case 0:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+30)
							{
								return true;
							}
							if(this.myTank.getX()+20>=et.getX()&&this.myTank.getX()+20<=et.getX()+20&&this.myTank.getY()>=et.getY()&&this.myTank.getY()<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.myTank.getX()+20>=et.getX()&&this.myTank.getX()+20<=et.getX()+30&&this.myTank.getY()>=et.getY()&&this.myTank.getY()<=et.getY()+20)
							{
								return true;
							}
						}
					}
				break;
				
			case 1:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()+30>=getY()&&this.myTank.getY()+30<=et.getY()+30)
							{
								return true;
							}
							if(this.myTank.getX()+20>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()+30>=et.getY()&&this.myTank.getY()+30<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()+30>=getY()&&this.myTank.getY()+30<=et.getY()+20)
							{
								return true;
							}
							if(this.myTank.getX()+20>=et.getX()&&this.myTank.getX()+20<=et.getX()+30&&this.myTank.getY()+30>=et.getY()&&this.myTank.getY()+30<=et.getY()+20)
							{
								return true;
							}
						}
					}
				break;	
				
			case 2:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							//�ҵ���һ��
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+30)
							{
								return true;
							}
							//�ҵ���һ��
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()+20>=et.getY()&&this.myTank.getY()+20<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.myTank.getX()+20>=et.getX()&&this.myTank.getX()+20<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+30&&this.myTank.getY()+20>=et.getY()&&this.myTank.getY()+20<=et.getY()+20)
							{
								return true;
							}
						}
					}
				break;
				
			case 3:
				//�ҵ�̹������
				//ȡ�����еĵ��˵�̹��
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//ȡ����һ��̹��
					EnemyTank et=enemyTanks.get(i);
						//������˵ķ��������ϻ�������
						if(et.getDirect()==0||et.getDirect()==1)
						{
							if(this.myTank.getX()+30>=et.getX()&&this.myTank.getX()+30<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+30)
							{
								return true;
							}
							if(this.myTank.getX()+30>=et.getX()&&this.myTank.getX()+30<=et.getX()+20&&this.myTank.getY()+20>=et.getY()&&this.myTank.getY()+20<=et.getY()+30)
							{
								return true;
							}
						}
						if(et.getDirect()==2||et.getDirect()==3)
						{
							if(this.myTank.getX()+30>=et.getX()&&this.myTank.getX()+30<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+20)
							{
								return true;
							}
							if(this.myTank.getX()+30>=et.getX()&&this.myTank.getX()+30<=et.getX()+30&&this.myTank.getY()+20>=et.getY()&&this.myTank.getY()+20<=et.getY()+20)
							{
								return true;
							}
						}
					}
				
				break;
				
			}
			return isTouch;
		}
	
}