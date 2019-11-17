package day02.cafe;

public class CafeTest {
	public static void main(String[] args) {
		Cafe cafe = new Cafe();
		Coffee coffee1 = new Coffee("Americano", 4000);
		Coffee coffee2 = new Coffee("Caffelatte", 5000);
		Coffee coffee3 = new Coffee("Macchiato", 6000);
		
		cafe.setCoffee(coffee1);
		cafe.setCoffee(coffee2);
		cafe.setCoffee(coffee3);
		
		for (int i = 0; i < cafe.getCoffeeList().length; i++) {
			Coffee[] coffeeInfo = cafe.getCoffeeList();
			System.out.println(coffeeInfo[i].toString());
		}
		
		System.out.println("\rCoffee 가격의 합: " + cafe.totalPrice() + "원");
	}
}
