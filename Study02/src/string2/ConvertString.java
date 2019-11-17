package string2;

import java.util.Random;

public class ConvertString {
	public String convert(String str) {
		Random random = new Random();
		String num = Integer.toString(Integer.parseInt(str) + random.nextInt(999999));
		return num;
	}
}
