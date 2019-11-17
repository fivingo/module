package day01;

public class Test02 {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int sum = 0;
		String result = args[0];
		
		for (int i = num; i <= 100; i = i + num) {
			sum = sum + i;
			
			if (i == num) {
				continue;
			}
			
			result = result + " + " + Integer.toString(i);
		}
		
		result = result + " = " + Integer.toString(sum);
		
		System.out.println(result);
	}
}
