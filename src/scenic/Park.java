package scenic;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;


public class Park {
	final int  max = 5;
	Scanner sc = new Scanner(System.in);
	ArrayList<Car> inside = new ArrayList<Car>();
	ArrayList<Car> outside = new ArrayList<Car>();
	ArrayList<Car> modification = new ArrayList<Car>();
	int cmd = 0;
	
	void menu(){

		while(true){
			System.out.println("====================================\n"
					+"                                      欢迎使用停车场管理系统\n"
					+"           ***请选择菜单***\n"
					+"====================================\n"
					+"1.进入停车场\n"
					+"2.离开停车场\n"
					+"3.场内车辆信息\n"
					+"4.返回上一单元\n\n"
					+"请选择："
					);
			cmd = sc.nextInt();
			switch (cmd) {
			case 1:
				getin();
				System.out.println("按任意键继续。");
				cmd = sc.nextInt();
				break;
			case 2:
				getout();
				System.out.println("按任意键继续。");
				cmd = sc.nextInt();
				break;
			case 3:
				print();
				System.out.println("按任意键继续。");
				cmd = sc.nextInt();
				break;
			case 4:
				return;
			default:
				break;
			}
		}
	}

	void getin(){
		if (inside.size()>=max) {
			System.out.print("车位已满，请在便道等待。");
			Car tempCar = new Car(0,0);
			outside.add(tempCar);
		}
		else{
			System.out.print("请输入车牌号：");
			Car tempCar = new Car(sc.nextInt(),1);
			inside.add(tempCar);
			System.out.println("进入停车场，开始计时计费");
			tempCar.startTime = new SimpleDateFormat("hh:mm:ss").format(new java.util.Date());
		}
	}
	
	void getout(){
		System.out.print("Please enter number plate");
		int num = sc.nextInt();
		for(int i = 0; i<inside.size(); i++){
			if(inside.get(i).num == num){
				inside.get(i).endTime = new SimpleDateFormat("hh:mm:ss").format(new java.util.Date());
				System.out.println("Charge ￥10");
				for(int j = inside.size()-1; j>i; j--){
					modification.add(inside.get(j));
					inside.remove(j);
				}
				inside.remove(i);
				for(int j = modification.size()-1; j>=0; j--){
					inside.add(modification.get(j));
					modification.remove(j);
				}
			}
		}
		if (outside.size() != 0) {
			System.out.println("便道车辆请输入车牌号并进入停车场：");
			outside.get(0).num = sc.nextInt();
			inside.add(outside.get(0));
			outside.remove(0);
		}
	}
	
	void print(){
		System.out.println("Number of Parking Lot      Number Plate      Time of Parking");
		for (int i = 0; i < inside.size(); i++) {
			System.out.println((i+1)+"      "+inside.get(i).num+"   "+inside.get(i).startTime);
		}
	}
}
