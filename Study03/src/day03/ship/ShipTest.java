package day03.ship;

import java.util.ArrayList;

public class ShipTest {
	public static void main(String[] args) {
		ArrayList<Ship> list = new ArrayList<Ship>();
		list.add(new Boat("Boat01", 500));
		list.add(new Cruise("Cruise01", 1000));
		
		// 생성된 객체의 기본 정보 출력
		System.out.println("shipName\tfuelTank");
		System.out.println("-------------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getShipName() + "\t" + list.get(i).getFuelTank());
		}
		
		// 10 운항 후 객체 정보 출력
		System.out.println("\r10 운항");
		System.out.println("shipName\tfuelTank");
		System.out.println("-------------------------");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).sail(10);
			System.out.println(list.get(i).getShipName() + "\t" + list.get(i).getFuelTank());
		}
		
		// 50 주유 후 객체 정보 출력
		System.out.println("\r50 주유");
		System.out.println("shipName\tfuelTank");
		System.out.println("-------------------------");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).refuel(50);
			System.out.println(list.get(i).getShipName() + "\t" + list.get(i).getFuelTank());
		}
	}
}
