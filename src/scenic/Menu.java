package scenic;

import java.util.Scanner;

public class Menu {
	int cmd = 0;
	Distribution dis = new Distribution();
	Park park = new Park();
	
	public void run(){
		while(true){
			display();
			select();
			System.out.println("Press any key to continue...");
			cmd = new Scanner(System.in).nextInt();
			switch (cmd) {
			default:
				break;
			}
		}
	}
	
	public void display(){
		System.out.println("====================================\n"
				+"                                      Welcome to Scenic Management System\n"
				+"           ***Choose Menu***\n"
				+"====================================\n"
				+"1.Create Scenic Spot Map\n"
				+"2.Output Scenic Spot Map\n"
				+"3.Modify Scenic Information\n"
				+"4.Output Guide Map\n"
				+"5.Output the Loop in Guide Map\n"
				+"6.Get the shortest Path Between Two Spot\n"
				+"7.Output Map of Road Construction\n"
				+"8.Parking Lot History Information\n"
				+"9.Search for Spot Information\n"
				+"10.Spot Sorting\n"
				+"11.Exit\n\n"
				+"Please Choose£º"
				);
	}
	
	public void select(){
		Scanner sc = new Scanner(System.in);
		cmd = sc.nextInt();
		
		while(true){
			switch(cmd){
			case 0:
				return;
			case 1:
				System.out.println("====================================\n");
				dis.createGraph();
				return;
			case 2:
				System.out.println("====================================\n");
				dis.init();
				dis.outputGraph();
				return;
			case 3:
				System.out.println("====================================\n");
				dis.modification();
				return;
			case 4:
				System.out.println("====================================\n");
				for(int i = 0; i<dis.vexNum; i++){
					dis.vexList.get(i).visited = false;
				}
				System.out.println("Please input start point:");
				dis.guideGraph(new Scanner(System.in).nextLine());
				System.out.println(dis.route);
				return;
			case 5:
				System.out.println("====================================\n");
				Loop loop = new Loop(dis.route,dis.vexList);
				loop.run();
				return;
			case 6:
				System.out.println("====================================\n");
				System.out.println("Please input start point: ");
				ShortestPath shortestPath = new ShortestPath(dis.vexList, new Scanner(System.in).nextLine());
				shortestPath.run();
				return;
			case 7:
				System.out.println("====================================\n");
				dis.roadPlanning();
				return;
			case 8:
				System.out.println("====================================\n");
				park.menu();
				return;
			case 9:
				System.out.println("====================================\n");
				dis.find();
				return;
			case 10:
				System.out.println("====================================\n");
				dis.sort(0,dis.vexList.size()-1);
				for(int i = 0; i<dis.vexList.size(); i++){
					System.out.print(dis.vexList.get(i).getName()+" "+dis.vexList.get(i).getPopularity()+"\n");
				}
				return;
			case 11:
				System.out.println("====================================\n");
				System.exit(0);
				
			default:
				System.out.println("Error Input");
				return;
			}
		}
	}
}
