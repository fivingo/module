package day02.stringbuffer;

public class StringBufferTest {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("NAOSOFT");
		String str = null;
		
		str = sb + "er";
		System.out.println(str);
		
//		String - 원본 불변 (문자열 수정 작업시 마다 새로운 객체 생성 후 참조)
//		StringBuffer - 원본 가변(하나의 객체로 작업)
	}
}
