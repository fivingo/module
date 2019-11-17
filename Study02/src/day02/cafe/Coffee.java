package day02.cafe;

public class Coffee {
	private String name;
	private int price;
	
	public Coffee() {
		
	}

	public Coffee(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String toString() {
		String coffeeInfo = this.name + "\t" + Integer.toString(this.price);
		return coffeeInfo;
	}
}
