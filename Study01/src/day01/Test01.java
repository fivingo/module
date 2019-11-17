package day01;

public class Test01 {
	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				for (int k = 1; k <= 6; k++) {
					if (((i * j * k) % 3) == 0) {
						num1 = i;
						num2 = j;
						num3 = k;
						
						System.out.println(num1 + " * " + num2 + " * " + num3 + " = " + num1 * num2 * num3);
					}
				}
			}
		}
	}
}
