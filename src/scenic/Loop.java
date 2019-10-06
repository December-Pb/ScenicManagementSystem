package scenic;

import java.util.ArrayList;

public class Loop {
	String[] temp;
	ArrayList<Vex> tempGraph = new ArrayList<Vex>();
	
	//构造函数
	public Loop(String route,ArrayList<Vex> vexList){
		this.temp = route.split(" ");
		for(int i = 0; i<vexList.size(); i++){
			tempGraph.add(new Vex());
			tempGraph.get(i).setName(vexList.get(i).getName());
		}
		for (int i = 0; i < temp.length-1; i++) {
			//循环List
			for(int j = 0; j<tempGraph.size(); j++){
				if (temp[i].equals(tempGraph.get(j).getName())) {
					tempGraph.get(j).getHead().add(temp[i+1], 0, null);
				}
			}
		}
	}
	
	void run(){
		if(temp.length == 0){
			System.out.println("Please create guide map");
			return;
		}
		if(judge()){
			print();
		}
	}
	
	//设置入度
	void setInDegree(){
		for(int i = 0; i<tempGraph.size(); i++){
			tempGraph.get(i).in = 0;
		}
		for(int i = 0; i<tempGraph.size(); i++){
			tempGraph.get(i).getHead().cursor = tempGraph.get(i).getHead().getHead().next;
			tempGraph.get(i).getHead().setCount();
			for(int j = 0; j<tempGraph.get(i).getHead().getCount(); j++){
				for(int k = 0; k<tempGraph.size(); k++){
					if(tempGraph.get(i).getHead().cursor.vexName.equals(tempGraph.get(k).getName()))
						tempGraph.get(k).in++;
				}
				tempGraph.get(i).getHead().cursor = tempGraph.get(i).getHead().cursor.next;
			}
		}
	}
	
	//判断是否有回路
	boolean judge(){
		ArrayList<Vex> graph = new ArrayList<Vex>();
		for (int i = 0; i<tempGraph.size(); i++){
			graph.add(tempGraph.get(i));
		}
		while(true){
			setInDegree();
			int k = 0;
			for(k = 0; k<graph.size(); k++){
				if (graph.get(k).in == 0) {
					graph.remove(k);
					break;
				}
			}
			if (k == graph.size()) {
				System.out.println("Loop exist in line");
				return true;
			}
			if (graph.size() == 0){
				return false;
			}
		}
	}
	
	//输出回路
	void print(){
		ArrayList<String> loop = new ArrayList<String>();
		
		for(int i = 0; i<tempGraph.size(); i++){
			loop.add(tempGraph.get(i).getName());
			tempGraph.get(i).getHead().cursor = tempGraph.get(i).getHead().getHead().next;
			
			for(int j = 0; j<tempGraph.get(i).getHead().getCount(); j++){
				
				for(int k = 0; k<tempGraph.size(); k++){
					if(tempGraph.get(k).getName().equals(tempGraph.get(i).getHead().cursor.vexName)){
						tempGraph.get(k).getHead().cursor = tempGraph.get(k).getHead().getHead().next;
						
						for (int l = 0; l<tempGraph.get(k).getHead().getCount(); l++){
							if (tempGraph.get(k).getHead().cursor.vexName.equals(tempGraph.get(i).getName()))
								loop.set(i, loop.get(i)+tempGraph.get(k).getName());
							tempGraph.get(k).getHead().cursor = tempGraph.get(k).getHead().cursor.next;
						}
					}
				}
				tempGraph.get(i).getHead().cursor = tempGraph.get(i).getHead().cursor.next;
			}
		}
		
		for(int i = 0; i<loop.size(); i++){
			if (loop.get(i).length() == 1){
				loop.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i<loop.size(); i++){
			System.out.println(loop.get(i));
		}
	}
}
