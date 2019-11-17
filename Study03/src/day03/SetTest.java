package day03;

import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<String>();
		
		String[] str = args[0].split("");
		for (int i = 0; i < str.length; i++) {
			hs.add(str[i]);
		}
		
		// 컬렉션에 저장되어 있는 요소들을 읽어오는 방법의 인터페이스
		Iterator<String> it = hs.iterator();
		
		System.out.print("[");
		for (int i = 0; i < hs.size(); i++) {
			if (i != (hs.size() - 1)) {
				System.out.print(it.next() + ", ");
				continue;
			}
			System.out.print(it.next());
		}
		System.out.print("]");
	}
}
