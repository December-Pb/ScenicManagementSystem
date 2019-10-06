package scenic;

public class EdgeLinkedList {
	
	private int count = 0;	
	private Node head;
	public Node cursor;
	public Node pre;
	public Node tail;
	
	public class Node{
		public String vexName = " ";
		public int weight = 0;
		public Node next;
		
		
		public Node(String vexName,int weight,Node next){
			this.vexName = vexName;
			this.weight = weight;
			this.next = next;
		}
	}
	
	//构造函数
	public EdgeLinkedList(){
		head = new Node(null,0,null);
		cursor = head;
		tail = head;
	}
	
	//增加边
	public void add(String vexName,int weight,Node next){
		cursor = tail;
		Node node = new Node(vexName,weight,next);
		cursor.next = node;
		cursor = cursor.next;
		tail = cursor;
	}
	
	//查找边
	public int checkForWeight(String vexName){
		cursor = head;
		
		while(true){
			if(cursor == null){
				return 32767;
			}
			if(vexName.equals(cursor.vexName))
				return cursor.weight;
			else{
				cursor = cursor.next;
			}
		}
	}
	
	//删除边
	public void delete(String vexName){
		checkForWeight(vexName);
		if (cursor == null) {
			return;
		}
		pre = head;
		while(true){
			if(pre.next == cursor){
				pre.next = cursor.next;
				break;
			}
			else{
				if(pre.next==null){
					System.out.println("Cannot find this edge, delete error");
					break;
				}
				pre = pre.next;
			}
		}
	}
	
	//是否为空
	public boolean isEmpty(){
		if(count==0)
			return true;
		else 
			return false;
	}
	


	public int getCount() {
		return count;
	}
	public void setCount(){
		Node temp = cursor;
		cursor = head.next;
		while(cursor!=null){
			count++;
			cursor = cursor.next;
		}
		cursor = temp;
	}
	
	public Node getHead(){
		return head;
	}
}
