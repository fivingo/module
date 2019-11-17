package workshp11.date;

import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		ConvertDate cd = new ConvertDate();
		Date date = new Date();
		
		String format1 = cd.convert(date, 1);
		System.out.println(format1);
		
		String format2 = cd.convert(date, 2);
		System.out.println(format2);
		
		String format3 = cd.convert(date, 3);
		System.out.println(format3);
	}
}
