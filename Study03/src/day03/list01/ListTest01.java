package day03.list01;

import java.util.Random;

public class ListTest01 {
	public static void main(String[] args) {
		int arrayListSize = Integer.parseInt(args[0]);
		MakeList list = new MakeList();
		
		// 난수 발생
		Random num = new Random();
		for (int i = 0; i < arrayListSize; i++) {
			list.makeArray(num.nextInt(9) + 10);
		}
		
		// 출력
		for (int i = 0; i < arrayListSize; i++) {
			System.out.print(list.list.get(i) + " ");
		}
		System.out.println("\r평균: " + list.getAverage());
	}
}
