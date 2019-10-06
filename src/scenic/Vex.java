package scenic;

import javax.swing.OverlayLayout;

public class Vex {
	
	private String name = "";
	private String description = "";
	private int popularity = 0;
	private boolean lounge = false;
	private boolean toilet = false;
	private EdgeLinkedList head = new EdgeLinkedList();
	public boolean visited = false;
	public int distance = 32767;
	public String path = "";
	public int in = 0;
	public int out = 0;
	public Vex pre = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public boolean isLounge() {
		return lounge;
	}
	public void setLounge(boolean lounge) {
		this.lounge = lounge;
	}
	public boolean isToilet() {
		return toilet;
	}
	public void setToilet(boolean toilet) {
		this.toilet = toilet;
	}
	public EdgeLinkedList getHead() {
		return head;
	}
}
