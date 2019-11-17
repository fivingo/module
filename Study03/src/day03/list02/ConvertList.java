package day03.list02;

import java.util.ArrayList;
import java.util.Collections;

public class ConvertList {
	ArrayList<Integer> list = new ArrayList<Integer>();
	
	// 배열 내용 역순으로 넣기
	public ArrayList<Integer> convertList(int[] array) {
		for (int i = array.length - 1; i >= 0; i--) {
			list.add(array[i]);
		}
		//Collections.reverse(list);	// ArrayList 역순 정렬
		
		return list;
	}
}
