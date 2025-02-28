package day02.vehicle;

public class VehicleTest {
	public static void main(String[] args) {
		Truck car = new Truck(1000, 100, 5);
		
		System.out.println("최대적재중량\t오일탱크크기\t잔여오일량\t현재적재중량\t연비");
		System.out.println("=========================================");
		System.out.println(car.toString());
		
		System.out.println("\r50L 주유 후");
		car.addOil(50);
		System.out.println(car.toString());
		
		System.out.println("\r50Km 주행 후");
		car.moving(50);
		System.out.println(car.toString());
		
		System.out.println("\r100Kg 적재 후");
		car.addWeight(100);
		System.out.println(car.toString());
		
		System.out.println("\r30Km 주행 후");
		car.moving(30);
		System.out.println(car.toString());
		
		System.out.println("\r요금: " + car.getCost(30));
	}
}
