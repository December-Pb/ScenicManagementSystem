package scenic;

import java.awt.Cursor;
import java.awt.List;
import java.awt.image.renderable.RenderableImageProducer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.basic.BasicComboPopup.InvocationKeyHandler;
import javax.xml.soap.Node;
import javax.xml.transform.Templates;

import org.omg.CORBA.portable.ValueBase;

public class Distribution {

	public int vexNum = 0;
	private int edgeNum = 0;
	public ArrayList<Vex> vexList = new ArrayList<Vex>();
	int output[][];
	String route = "";
	
	
	Scanner sc = new Scanner(System.in);
	
	void createGraph(){
		
		
		System.out.println("Please input number of spot");
		vexNum = sc.nextInt();
		
		System.out.println("Please input number of edge");
		edgeNum = sc.nextInt();
		
		System.out.println("Please input name of spot");
		for (int i = 0; i<vexNum; i++){
			String name = sc.next();
			vexList.add(new Vex());
			vexList.get(i).setName(name);
		}
		
		
		for(int i = 0; i<edgeNum; i++){
			System.out.println("\nPlease input the name of two spot connected by "+(i+1)+"th road and the length of road");
			String vex1,vex2;
			int weight;
			vex1 = sc.next();
			vex2 = sc.next();
			weight = sc.nextInt();
			for(int j = 0; j<vexList.size(); j++){
				if(vexList.get(j).getName().equals(vex1)){
					vexList.get(j).getHead().add(vex2, weight, null);
				}
				if(vexList.get(j).getName().equals(vex2)){
					vexList.get(j).getHead().add(vex1, weight, null);
				}
			}
		}
		for(int i = 0; i<vexNum; i++){
			vexList.get(i).getHead().setCount();
		}
	}
	
	void modification(){
		if(vexList.size() == 0){
			System.out.println("Please create scenic map first");
			return;
		}
		sc = new Scanner(System.in);
		
		String name = "";
		int i = 0;
		int cmd = 0;
		
		while(true){
			System.out.println("Please enter the spot you want to modify");
			name = sc.nextLine();
			for(i = 0; i<vexList.size(); i++){
				if(vexList.get(i).getName().equals(name)){
					break;
				}
				else{
					continue;
				}
			}
			if(i == vexList.size())
				System.out.println("Spot doesn't exist!");
			else
				break;
		}
		
		System.out.println("Choose the item to modify\n1.Name\n2.Description\n3.Popularity\n4.Rest Area\n5.Restroom\n6.Delete\n");
		cmd = sc.nextInt();
		
		sc = new Scanner(System.in);
		switch(cmd){
		case 1:
			System.out.print("Please Enter:");
			vexList.get(i).setName(sc.nextLine());
			break;
		case 2:
			System.out.print("Please Enter:");
			vexList.get(i).setDescription(sc.nextLine());
			break;
		case 3:
			System.out.print("Please Enter:");
			vexList.get(i).setPopularity(sc.nextInt());
			break;
		case 4:
			System.out.print("Please Enter:(Yes/No)");
			String booll = sc.nextLine();
			if(booll == "Yes")
				vexList.get(i).setLounge(true);
			if(booll == "No")
				vexList.get(i).setLounge(false);
			break;
		case 5:
			System.out.print("Please Enter:(Yes/No)");
			String boolt = sc.nextLine();
			if(boolt == "Yes")
				vexList.get(i).setToilet(true);
			if(boolt == "No")
				vexList.get(i).setToilet(false);
			break;
		case 6:
			vexList.remove(i);
			vexNum--;
			for(int j = 0; j<vexNum; j++){
				vexList.get(j).getHead().cursor = vexList.get(j).getHead().getHead().next;
				vexList.get(j).getHead().delete(name);
			}
			System.out.print("Successfully Delete");
			break;
		default:
			break;
		}
	}
	
	void outputGraph(){
		
		if(vexList.size() == 0){
			System.out.println("Please create scenic map");
			return;
		}
		
		for(int i = 0; i<vexNum+1; i++){
			for(int j = 0; j<vexNum+1; j++){
				if(i == 0 && j == 0)
					System.out.print("\t");
				if(i == 0 && j != 0)
					System.out.print(vexList.get(j-1).getName()+"\t");
				if(i != 0){
					if(j == 0)
						System.out.print("\n"+vexList.get(i-1).getName()+"\t");
					else
						System.out.print(output[i-1][j-1]+"\t");
				}
			}
		}
		System.out.println("\n");
	}
	
	void init(){
		output = new int[vexNum][vexNum];
		for(int i = 0; i<vexNum; i++){
			for(int j = 0; j<vexNum; j++){
				output[i][j] = 32767;
				if(i == j)
					output[i][j] = 0;
			}
		}
		for(int i = 0; i<vexNum-1; i++){
			for(int j = i+1; j<vexNum; j++){
					output[i][j] = output[j][i] = vexList.get(i).getHead().checkForWeight(vexList.get(j).getName());
			}
		}
	}
	
	void guideGraph(String vexName){
		if(vexList.size() == 0){
			System.out.println("Please create scenic map");
			return;
		}
		
		
		//找顶点
		for(int i = 0; i<vexNum; i++){
			if(vexList.get(i).getName().equals(vexName)){
				
				//标记
				vexList.get(i).visited = true;
				route += vexList.get(i).getName()+" ";
				
				//判断是否有与顶点相邻的点
				while (vexList.get(i).getHead().getHead().next != null) {
					vexList.get(i).getHead().cursor = vexList.get(i).getHead().getHead().next;
					
					//遍历顶点的链表
					for(int j = 0; j<vexNum; j++){
						
						//从list中找
						if (!vexList.get(i).getHead().cursor.vexName.equals(vexList.get(j).getName())) 
							continue;
						
						//找到后判断是否标记过
						else if (vexList.get(j).visited == false) {
							guideGraph(vexList.get(j).getName());
							int k = 0;
							for(k = 0; k<vexNum; k++){
								if(vexList.get(k).visited == false)
									break;
							}
							if(k == vexNum)
								return;
							route += vexList.get(i).getName()+" ";
						}
						//若标记过判断链表是否结束
						else if (vexList.get(i).getHead().cursor.next == null) {
							return;
						}
						//移动光标
						else{
							vexList.get(i).getHead().cursor = vexList.get(i).getHead().cursor.next;
						}
					}
				}
			}
		}
	}
	
	//Dijkstra
	void shortestPath(String start){
		if(vexList.size() == 0){
			System.out.println("Please create scenic map");
			return;
		}
	}
	
	void find(){
		if(vexList.size() == 0){
			System.out.println("Please create scenic map");
			return;
		}
		
		System.out.println("1.Search By Name  2.Search By Popularity");
		int cmd = new Scanner(System.in).nextInt();
		if (cmd == 1) {
			sc = new Scanner(System.in);
			for(int i = 0; i<vexNum; i++){
				vexList.get(i).visited = false;
			}
			String key = "";
			System.out.print("Please enter the key of search");
			key = sc.next();
			int i = 0;
			for(i = 0; i<vexNum; i++){
				if (vexList.get(i).getName().contains(key) && vexList.get(i).visited == false) {
					System.out.println(vexList.get(i).getName()+"\n"+vexList.get(i).getDescription());
					vexList.get(i).visited = true;
				}
				if (vexList.get(i).getDescription().contains(key) && vexList.get(i).visited == false) {
					System.out.println(vexList.get(i).getName()+"\n"+vexList.get(i).getDescription());
					vexList.get(i).visited = true;
				}
			}
			for(i = 0; i<vexNum; i++){
				if (vexList.get(i).visited == true) {
					return;
				}
			}
			System.out.println("No related spot");
		}
		if (cmd == 2) {
			sort(0,vexList.size()-1);
			System.out.print("Please enter: ");
			int value = new Scanner(System.in).nextInt();
			int low = 0;
			int high = vexList.size()-1;
			int mid = -1;
			while(true){
				int temp = mid;
				mid = (low + high)/2;
				if (temp == mid) {
					System.out.println("No related Spot");
					break;
				}
				if (vexList.get(mid).getPopularity() == value) {
					System.out.println(vexList.get(mid).getName()+"\n"+vexList.get(mid).getDescription());
					break;
				}
				if (vexList.get(mid).getPopularity() > value) {
					high = mid-1;
					continue;
				}
				if (vexList.get(mid).getPopularity() < value) {
					low = mid+1;
					continue;
				}
			}
		}
	}
	
	void sort(int l,int r){
		if(vexList.size() == 0){
			System.out.println("Please create scenic map");
			return;
		}
		
		if(l < r){
			int i = l;
			int j = r;
			Vex x = vexList.get(i);
			while(i<j){
				while(i < j && vexList.get(j).getPopularity() > x.getPopularity()){
					j--;
				}
				if(i < j){
					vexList.set(i, vexList.get(j));
					i++;
				}
				while(i < j && vexList.get(i).getPopularity() < x.getPopularity()){
					i++;
				}
				if(i < j){
					vexList.set(j, vexList.get(i));
					j--;
				}
			}
			vexList.set(i, x);
			
			sort(l,i-1);
			sort(j+1,r);
		}
	}
	
	//Kruskal
	void roadPlanning(){
		class Road {
			String vex1;
			String vex2;
			int weight;
			boolean select = false;
			
			public Road(String vex1,String vex2,int weight){
				this.vex1 = vex1;
				this.vex2 = vex2;
				this.weight = weight;
			}
		}
		
		ArrayList<String> graph = new ArrayList<String>();
		
		
		//初始化roadList
		ArrayList<Road> roadList = new ArrayList<Road>();
		for(int i = 0; i<vexNum; i++){
			vexList.get(i).getHead().cursor = vexList.get(i).getHead().getHead().next;
			for(int j = 0; j<vexList.get(i).getHead().getCount(); j++){
				roadList.add(new Road(vexList.get(i).getName(), vexList.get(i).getHead().cursor.vexName, vexList.get(i).getHead().cursor.weight));
				vexList.get(i).getHead().cursor = vexList.get(i).getHead().cursor.next;
			}
		}
		
		//去重
		for(int i = 0; i<roadList.size(); i++){
			for(int j = 0; j<roadList.size(); j++){
				if (roadList.get(i).vex1.equals(roadList.get(j).vex2) && roadList.get(i).vex2.equals(roadList.get(j).vex1)) {
					roadList.remove(j);
				}
			}
		}
		
		//排序
		for (int i = 0; i < roadList.size(); i++)
        {
            for (int j = i; j < roadList.size(); j++)
            {
                if (roadList.get(i).weight > roadList.get(j).weight)
                {
                    Road temp = roadList.get(i);
                    roadList.set(i, roadList.get(j));
                    roadList.set(j, temp);
                }
            }
        }
		
		//最小生成树
		ArrayList<Integer> mark = new ArrayList<Integer>();
		
		
		graph.add((roadList.get(0).vex1+roadList.get(0).vex2));
		mark.add(0);
		
		for(int i = 1; i<roadList.size(); i++){
			
			int j = 0;
			for (j = 0; j < graph.size(); j++) {
				
				if (graph.get(j).contains(roadList.get(i).vex1) && !graph.get(j).contains(roadList.get(i).vex2)) {
					for (int k = 0; k < graph.size()&&k!=j; k++) {
						if (graph.get(k).contains(roadList.get(i).vex2)) {
							graph.set(j, graph.get(j)+graph.get(k));
							graph.set(k, " ");
							mark.add(i);
							break;
						}
					}
					graph.set(j, graph.get(j)+roadList.get(i).vex2);
					mark.add(i);
					break;
				}
				
				else if(!graph.get(j).contains(roadList.get(i).vex1) && graph.get(j).contains(roadList.get(i).vex2)){
					for (int k = 0; k < graph.size()&&k!=j; k++) {
						if (graph.get(k).contains(roadList.get(i).vex1)) {
							graph.set(j, graph.get(j)+graph.get(k));
							graph.set(k, " ");
							mark.add(i);
							break;
						}
					}
					graph.set(j, graph.get(j)+roadList.get(i).vex1);
					mark.add(i);
					break;
				}
				else if(graph.get(j).contains(roadList.get(i).vex1) && graph.get(j).contains(roadList.get(i).vex2)){
					j = graph.size();
				}
			}
			if (j == graph.size()) {
				graph.add((roadList.get(i).vex1+roadList.get(i).vex2));
				mark.add(i);
			}
			for (int j2 = 0; j2 < graph.size(); j2++) {
				if (graph.get(j2).length() == vexNum) {
					i = roadList.size();
				}
			}
		}
		
		
		//输出
		int j = 0;
		System.out.println("Road need to be built:");
		for (int i = 0; i < roadList.size(); i++) {
			if (j < mark.size() && i == mark.get(j)) {
				j++;
				System.out.println(roadList.get(i).vex1+" to "+roadList.get(i).vex2);
			}
		}
	}
}
