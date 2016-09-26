package tankGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * 记录类，同时保存玩家设置
 * @author c
 *
 */
public class Recorder {
	//用于存档的io流
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	//用于读取存档的io流
	private static FileReader fr = null;
	private static BufferedReader br = null;
	//获取MyPanel里的敌人的坦克集合
	private static Vector<EnemyTank> enemyTanks =null;
	//从文件中恢复记录点
	private static Vector<Node> nodes = new Vector<Node>();
	//记录每关有多少敌人
	private static int enemyTankNum = 20;

	//设置我有多少台坦克
	private static int myTankNum = 3;

	//设置我消灭了多少台坦克
	private static int shotEnemyTankNum = 0;
	
	public static Vector<EnemyTank> getEnemyTanks() {
		return enemyTanks;
	}

	public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
		Recorder.enemyTanks = enemyTanks;
	}

	public static int getShotEnemyTankNum() {
		return shotEnemyTankNum;
	}

	public static void setShotEnemyTankNum(int shotEnemyTankNum) {
		Recorder.shotEnemyTankNum = shotEnemyTankNum;
	}

	public static int getEnemyTankNum() {
		return enemyTankNum;
	}

	public static void setEnemyTankNum(int enemyTankNum) {
		Recorder.enemyTankNum = enemyTankNum;
	}

	public static int getMyTankNum() {
		return myTankNum;
	}

	public static void setMyTankNum(int myTankNum) {
		Recorder.myTankNum = myTankNum;
	}
	
	//敌人坦克减少
	public static void reduceEnemyTankNum(){
		enemyTankNum--;
	}
	//我的坦克减少
	public static void reduceMyTankNum(){
		myTankNum--;
	}
	
	//消灭的坦克数量增加
	public static void addShotEnemyTankNum(){
		shotEnemyTankNum++;
	}
	
	
	//把玩家击毁敌人坦克数量保存到文件中
	public static void keepRecording(){
		try {
			//创建文件
			fw = new FileWriter("myRecord.dat");
			bw = new BufferedWriter(fw);
			bw.write(shotEnemyTankNum+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}



	//从文件中读取,记录
	public static void getRecording(){
		try {
			fr = new FileReader("myRecord.dat");
			br = new BufferedReader(fr);
			String line = br.readLine();
			shotEnemyTankNum = Integer.parseInt(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
			
	}

	//保存击毁敌人的坦克数量和保存敌人的坐标
	public static void keepRecordEnemyTank(){
		try {
			//创建文件
			fw = new FileWriter("myRecord.dat");
			bw = new BufferedWriter(fw);
			bw.write(shotEnemyTankNum+"\r\n");
			//写入敌人坦克和我的坦克
			bw.write(Recorder.getEnemyTankNum()+" "+Recorder.getMyTankNum()+"\r\n");
			//保存当前活的敌人坦克的坐标和方向
			for(int i = 0; i<enemyTanks.size(); i++){
				//取出每辆坦克
				EnemyTank thisEnemyTank = enemyTanks.get(i);
				if(thisEnemyTank.isLive()){
					String record = thisEnemyTank.getX()+" "+thisEnemyTank.getY()+" "+thisEnemyTank.getDirect();
					//写入文件
					bw.write(record+"\r\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	//完成敌人的坦克数量和敌人坐标的读取任务
	public static Vector<Node> getRecordEnemyTank(){
		try {
			fr = new FileReader("myRecord.dat");
			br = new BufferedReader(fr);
			//先读取第一行
			String line = br.readLine();
			shotEnemyTankNum = Integer.parseInt(line);
			//在读取第二行
			String[] RecNum = br.readLine().split(" ");
			enemyTankNum = Integer.parseInt(RecNum[0]);
			myTankNum = Integer.parseInt(RecNum[1]);
			//读取敌人的坦克坐标和方向
			while((line=br.readLine())!=null){
				String[] records = line.split(" ");
				Node node = new Node(Integer.parseInt(records[0]),Integer.parseInt(records[1]),Integer.parseInt(records[2]));
				nodes.add(node);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return nodes;
	}
	
	
	
}
