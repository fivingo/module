package day02;

public class TvTest {
	public static void main(String[] args) {
		Tv tvArray[] = new Tv[3];
		tvArray[0] = new Tv("INFINIA", 1500000, "LED TV");
		tvArray[1] = new Tv("XCANVAS", 1000000, "LCD TV");
		tvArray[2] = new Tv("CINEMA", 2000000, "3D TV");
		
		for (int i = 0; i < tvArray.length; i++) {
			System.out.println(tvArray[i]);
//			System.out.println(tvArray[i].toString());
		}
		
		Tv temp1 = null;
		Tv temp2 = null;
		
		temp1 = tvArray[0].getPrice() > tvArray[1].getPrice() ? tvArray[0] : tvArray[1];
		temp2 = temp1.getPrice() > tvArray[2].getPrice() ? temp1 : tvArray[2];
		
		System.out.println("\r가격이 가장 비싼 제품: " + temp2.getName());
		
		temp1 = tvArray[0].getPrice() < tvArray[1].getPrice() ? tvArray[0] : tvArray[1];
		temp2 = temp1.getPrice() < tvArray[2].getPrice() ? temp1 : tvArray[2];
		
		System.out.println("가격이 가장 저렴한 제품: " + temp2.getName());
	}
}
