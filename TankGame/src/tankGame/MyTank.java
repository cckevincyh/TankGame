package tankGame;

import java.util.Vector;

/**
 * �ҵ�̹����
 * @author c
 *
 */
public class MyTank extends Tank{
	
	private Vector<EnemyTank> enemyTanks = null;//�������̹����(����MyPanel��������̹����)
	public MyTank(int x,int y){
		super(x,y);
	}
	public MyTank(int x,int y,int direct,int speed){
		super(x,y,direct,speed);
	}

			//�ж��Ƿ������˵��˵�̹��
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
