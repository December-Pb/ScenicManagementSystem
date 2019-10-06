package scenic;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath {
	int start = -1;
	int total = 0;
	ArrayList<Vex> vexList;
	ArrayList<String> route = new ArrayList<String>();
	
	public ShortestPath(ArrayList<Vex> vexList,String start){
		this.vexList = vexList;
		for (int i = 0; i < vexList.size(); i++) {
			if (vexList.get(i).getName().equals(start)) {
				this.start = i;
				total++;
			}
		}
		for (int i = 0; i < vexList.size(); i++) {
			vexList.get(i).visited = false;
		}
		vexList.get(this.start).visited = true;
		vexList.get(this.start).distance = 0;
	}
	
	void run(){
		updateDistance();
		print();
	}
	void updateDistance(){
		while(true){
			for(int i = 0; i<vexList.size(); i++){
				if (vexList.get(i).visited == false) {
					continue;
				}
				vexList.get(i).getHead().cursor = vexList.get(i).getHead().getHead().next;
				for(int j = 0; j<vexList.get(i).getHead().getCount(); j++){
					for(int k = 0; k<vexList.size(); k++){
						if (vexList.get(k).getName().equals(vexList.get(i).getHead().cursor.vexName)) {
							if (vexList.get(k).distance > vexList.get(i).distance+vexList.get(i).getHead().cursor.weight) {
								vexList.get(k).distance = vexList.get(i).distance+vexList.get(i).getHead().cursor.weight;
								vexList.get(k).visited = true;
								vexList.get(k).pre = vexList.get(i);
								total++;
							}
						}
					}
					vexList.get(i).getHead().cursor = vexList.get(i).getHead().cursor.next;
				}
			}
			
			if (total == vexList.size()) {
				break;
			}
		}
	}

	void print(){
		System.out.println("Please enter the end point");
		String end = new Scanner(System.in).nextLine();
		int i = 0;
		Vex temp;
		for (i = 0; i < vexList.size(); i++) {
			if (end.equals(vexList.get(i).getName())) {
				System.out.println("The shortest distance is "+vexList.get(i).distance);
				break;
			}
		}
		temp = vexList.get(i);
		while (temp != null) {
			route.add(temp.getName());
			temp = temp.pre;
		}
		System.out.print("The shortest path is ");
		for (int j = route.size()-1; j >= 0; j--) {
			System.out.print(route.get(j)+" ");
		}
	}
}
