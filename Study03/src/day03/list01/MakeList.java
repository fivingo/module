package day03.list01;

import java.util.ArrayList;

public class MakeList {
	ArrayList<Integer> list;
	double avg = 0;
	
	public MakeList() {
		list = new ArrayList<Integer>();
	}
	
	// ArrayList 생성
	public void makeArray(int size) {
		list.add(size);
	}
	
	// ArrayList의 모든 값의 평균을 계산 하여 리턴
	public double getAverage() {
		for (int i = 0; i < list.size(); i++) {
			avg = avg + list.get(i);
		}
		
		return avg / list.size();
	}
	
	// ArrayList를 리턴
	public ArrayList<Integer> getList() {
		return list;
	}
}
