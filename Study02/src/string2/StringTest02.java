package string2;

import java.text.DecimalFormat;

public class StringTest02 {
	public static void main(String[] args) {
		ConvertString cs = new ConvertString();
		
		String num = cs.convert("3000000");
		
		String won = null;
		DecimalFormat df = new DecimalFormat("#,##0");
		won = df.format(Integer.parseInt(num)) + "원";
//		won = num.substring(0, 1) + "," + num.substring(1, 4) + "," + num.substring(4) + "원";
		
		System.out.println(won);
	}
}
