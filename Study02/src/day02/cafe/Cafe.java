package day02.cafe;

public class Cafe {
	private Coffee[] coffeeList = new Coffee[3];
	private int index;
	
	public Cafe() {
		
	}
	
	public void setCoffee(Coffee coffee) {
		if (index != 3) {
			this.coffeeList[index] = coffee;
			index++;
		} else {
			System.out.println("더 이상 저장할 수 없습니다.");
		}
	}
	
	public int totalPrice() {
		int sum = 0;
		
		for (int i = 0; i < coffeeList.length; i++) {
			sum = sum + this.coffeeList[i].getPrice();
		}
		
		return sum;
	}
	
	public Coffee[] getCoffeeList() {
		return this.coffeeList;
	}
}
