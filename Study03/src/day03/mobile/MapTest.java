package day03.mobile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		Map<String, Mobile> map = new HashMap<String, Mobile>();
		double sum = 0.0;
		
		Mobile mobile1 = new Mobile("LU6800", "Optimus 2X", 800000);
		Mobile mobile2 = new Mobile("SU6600", "Optimus Black", 900000);
		Mobile mobile3 = new Mobile("KU5700", "Optimus Big", 700000);
		Mobile mobile4 = new Mobile("SU7600", "Optimus Mach", 950000);
		
		// map에 생성한 4개의 객체를 code를 키 값으로 넣는다.
		map.put("LU6800", mobile1);
		map.put("SU6600", mobile2);
		map.put("KU5700", mobile3);
		map.put("SU7600", mobile4);
		
		// Mobile 객체 정보 출력 및 합계 계산 (K와 V, 순서x)
		Set<Map.Entry<String, Mobile>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, Mobile>> entryIterator = entrySet.iterator();
		
		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, Mobile> entry = entryIterator.next();
			String key = entry.getKey();
			
			System.out.println(map.get(key).printInfo());
			
			sum = sum + map.get(key).getPrice();
		}
		System.out.println("합계: " + sum);
		
		// Mobile 객체의 가격 정보를 10% 증가
		entrySet = map.entrySet();
		entryIterator = entrySet.iterator();
		
		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, Mobile> entry = entryIterator.next();
			String key = entry.getKey();
			
			double price = map.get(key).getPrice() * 110 / 100;
			map.get(key).setPrice(price);
		}
		
		// 변경된 정보를 출력하고 합계를 계산
		entrySet = map.entrySet();
		entryIterator = entrySet.iterator();
		
		System.out.println("\r가격 변경 후");
		sum = 0.0;
		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, Mobile> entry = entryIterator.next();
			String key = entry.getKey();
			
			System.out.println(map.get(key).printInfo());
			
			sum = sum + map.get(key).getPrice();
		}
		System.out.println("합계: " + sum);
	}
}
