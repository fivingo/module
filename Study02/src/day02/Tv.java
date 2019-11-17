package day02;

public class Tv {
	private String name;
	private int price;
	private String description;
	
	public Tv() {
		
	}

	public Tv(String name, int price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public String toString() {
		String tvInfo = this.name + "\t" + this.price + "\t" + this.description;
//		String tvInfo = this.name + "\t" + Integer.toString(this.price) + "\t" + this.description;
		return tvInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;//
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
