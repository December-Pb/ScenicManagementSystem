package scenic;

import java.text.SimpleDateFormat;

public class Car {
	public int status = 0;//用来标记停车信息，0表示在便道停车，1表示在停车场内停车
	public int num = 0;
	public String startTime = "";
	public String endTime = "";
	
	public Car(int num,int status) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.status = status;
	}
}
