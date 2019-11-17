package day03.mobile;

public class Mobile {
	private String code;
	private String name;
	private double price;
	
	public Mobile() {
		
	}

	public Mobile(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String printInfo() {
		String info = this.code + "\t" + this.name + "\t" + this.price;
		return info;
	}
	
	// getter/setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
