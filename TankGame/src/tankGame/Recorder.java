package tankGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * ��¼�࣬ͬʱ�����������
 * @author c
 *
 */
public class Recorder {
	//���ڴ浵��io��
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	//���ڶ�ȡ�浵��io��
	private static FileReader fr = null;
	private static BufferedReader br = null;
	//��ȡMyPanel��ĵ��˵�̹�˼���
	private static Vector<EnemyTank> enemyTanks =null;
	//���ļ��лָ���¼��
	private static Vector<Node> nodes = new Vector<Node>();
	//��¼ÿ���ж��ٵ���
	private static int enemyTankNum = 20;

	//�������ж���̨̹��
	private static int myTankNum = 3;

	//�����������˶���̨̹��
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
	
	//����̹�˼���
	public static void reduceEnemyTankNum(){
		enemyTankNum--;
	}
	//�ҵ�̹�˼���
	public static void reduceMyTankNum(){
		myTankNum--;
	}
	
	//�����̹����������
	public static void addShotEnemyTankNum(){
		shotEnemyTankNum++;
	}
	
	
	//����һ��ٵ���̹���������浽�ļ���
	public static void keepRecording(){
		try {
			//�����ļ�
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



	//���ļ��ж�ȡ,��¼
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

	//������ٵ��˵�̹�������ͱ�����˵�����
	public static void keepRecordEnemyTank(){
		try {
			//�����ļ�
			fw = new FileWriter("myRecord.dat");
			bw = new BufferedWriter(fw);
			bw.write(shotEnemyTankNum+"\r\n");
			//д�����̹�˺��ҵ�̹��
			bw.write(Recorder.getEnemyTankNum()+" "+Recorder.getMyTankNum()+"\r\n");
			//���浱ǰ��ĵ���̹�˵�����ͷ���
			for(int i = 0; i<enemyTanks.size(); i++){
				//ȡ��ÿ��̹��
				EnemyTank thisEnemyTank = enemyTanks.get(i);
				if(thisEnemyTank.isLive()){
					String record = thisEnemyTank.getX()+" "+thisEnemyTank.getY()+" "+thisEnemyTank.getDirect();
					//д���ļ�
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
	
	
	//��ɵ��˵�̹�������͵�������Ķ�ȡ����
	public static Vector<Node> getRecordEnemyTank(){
		try {
			fr = new FileReader("myRecord.dat");
			br = new BufferedReader(fr);
			//�ȶ�ȡ��һ��
			String line = br.readLine();
			shotEnemyTankNum = Integer.parseInt(line);
			//�ڶ�ȡ�ڶ���
			String[] RecNum = br.readLine().split(" ");
			enemyTankNum = Integer.parseInt(RecNum[0]);
			myTankNum = Integer.parseInt(RecNum[1]);
			//��ȡ���˵�̹������ͷ���
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
