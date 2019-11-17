package day03.list02;

import java.util.ArrayList;

public class ListTest02 {
	public static void main(String[] args) {
		int[] array = {3, 4, 2, 5, 2, 3, 6, 7, 5, 7, 9};
		
		ConvertList cl = new ConvertList();
		
		// ArrayList에 배열 내용 넣기
		ArrayList<Integer> list = cl.convertList(array);
		
		// ArrayList 내용 출력
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
