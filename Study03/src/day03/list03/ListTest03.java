package day03.list03;

import java.util.ArrayList;
import java.util.Random;

public class ListTest03 {
	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		Random num = new Random();
		
		// 난수 발생 후 ArrayList에 넣기1
		for (int i = 0; i < 10; i++) {
			list1.add(num.nextInt(9));
		}
		
		// 난수 발생 후 ArrayList에 넣기2
		for (int i = 0; i < 10; i++) {
			list2.add(num.nextInt(9));
		}

		// 나눗셈 계산 및 예외처리
		String result = null;
		for (int i = 0; i < list1.size(); i++) {
			try {
				result = Integer.toString(list1.get(i) / list2.get(i));
			} catch (ArithmeticException e) {	// 정수를 0으로 나눌 경우 예외발생	// 실수는 Infinity
				result = "분모가 0입니다";
			}
			System.out.println(list1.get(i) + "/" + list2.get(i) + " " + result);
		}
	}
}
