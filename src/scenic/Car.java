package scenic;

import java.text.SimpleDateFormat;

public class Car {
	public int status = 0;//�������ͣ����Ϣ��0��ʾ�ڱ��ͣ����1��ʾ��ͣ������ͣ��
	public int num = 0;
	public String startTime = "";
	public String endTime = "";
	
	public Car(int num,int status) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.status = status;
	}
}
