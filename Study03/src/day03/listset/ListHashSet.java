package day03.listset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class ListHashSet {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();
		Random num = new Random();
		
		// ArrayList에 size만큼 랜덤 숫자 입력
		for (int i = 0; i < size; i++) {
			list.add(num.nextInt(10) + 1);
		}
		
		// ArrayList 정보 출력
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		
		// ArrayList의 정보를 HashSet에
		for (int i = 0; i < size; i++) {
			set.add(list.get(i));
		}
		
		// HashSet 정보 출력 (데이터 중복x, 순서x??)
		iterator = set.iterator();
		System.out.print("\r[");
		for (int i = 0; i < set.size(); i++) {
			if (i < set.size() - 1) {
				System.out.print(iterator.next() + ", ");
			} else {
				System.out.print(iterator.next());
			}
		}
		System.out.println("]");
	}
}
