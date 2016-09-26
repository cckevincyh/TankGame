package tankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	private int time = 0; //定义时间
	//定义敌人的子弹集合(可以存放敌人的子弹)
	private Vector<Bullet> enemyTankbullets = new Vector<Bullet>();
	//敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹死亡后
	
	//定义一个容器，可以访问到MyPanel上所有敌人坦克(用来获取MyPanel上的坦克)
	private Vector<EnemyTank> enemyTanks = null;
	
	//定义一个我的坦克变量，用来访问MyPanel上的我的坦克
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
			//判断是否需要给敌人坦克添加子弹
			//遍历每辆敌人的坦克
			if(this.time%2 == 0){
				if(this.isLive()){
					//判断敌人坦克的子弹
					if(this.getEnemyTankbullets().size()<5){
						Bullet thisEnemyTankBullet = null;
						//没有子弹
						//添加
						//判断子弹方向
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
						//启动敌人子弹线程
						Thread t = new Thread(thisEnemyTankBullet);
						t.start();
					}
				}
			
			
				//让敌人坦克产生一个随机方向
				this.setDirect((int)(Math.random()*4));
			
				if(this.isLive() == false){
					//退出线程
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

	
	//判断是否碰到了别的敌人的坦克
		public boolean isTouchOtherEnemy()
		{
			boolean isTouch=false;
			
			switch(this.getDirect())
			{
			case 0:
				//我的坦克向上
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
					//如果不是自己
					if(et!=this)
					{
						//如果敌人的方向是向上或者向下
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
				//我的坦克向下
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
					//如果不是自己
					if(et!=this)
					{
						//如果敌人的方向是向上或者向下
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
				//我的坦克向左
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
					//如果不是自己
					if(et!=this)
					{
						//如果敌人的方向是向上或者向下
						if(et.getDirect()==0||et.getDirect()==1)
						{
							//我的上一点
							if(this.getX()>=et.getX()&&this.getX()<=et.getX()+20&&this.getY()>=getY()&&this.getY()<=et.getY()+30)
							{
								return true;
							}
							//我的下一点
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
				//我的坦克向右
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
					//如果不是自己
					if(et!=this)
					{
						//如果敌人的方向是向上或者向下
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
	
	//判断敌人坦克是否碰到了我的坦克
		public boolean isTouchMyTank(){
			boolean isTouch=false;
			
			switch(this.myTank.getDirect())
			{
			case 0:
				//我的坦克向上
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
						//如果敌人的方向是向上或者向下
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
				//我的坦克向下
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
						//如果敌人的方向是向上或者向下
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
				//我的坦克向左
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
						//如果敌人的方向是向上或者向下
						if(et.getDirect()==0||et.getDirect()==1)
						{
							//我的上一点
							if(this.myTank.getX()>=et.getX()&&this.myTank.getX()<=et.getX()+20&&this.myTank.getY()>=getY()&&this.myTank.getY()<=et.getY()+30)
							{
								return true;
							}
							//我的下一点
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
				//我的坦克向右
				//取出所有的敌人的坦克
				for(int i=0; i<enemyTanks.size(); i++)
				{
					//取出第一个坦克
					EnemyTank et=enemyTanks.get(i);
						//如果敌人的方向是向上或者向下
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