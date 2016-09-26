package tankGame;

import java.util.Vector;

/**
 * 我的坦克类
 * @author c
 *
 */
public class MyTank extends Tank{
	
	private Vector<EnemyTank> enemyTanks = null;//定义敌人坦克组(接收MyPanel传过来的坦克组)
	public MyTank(int x,int y){
		super(x,y);
	}
	public MyTank(int x,int y,int direct,int speed){
		super(x,y,direct,speed);
	}

			//判断是否碰到了敌人的坦克
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
					
					break;
					
				}
				return isTouch;
			}

			public Vector<EnemyTank> getEnemyTanks() {
				return enemyTanks;
			}

			public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
				this.enemyTanks = enemyTanks;
			}
}
