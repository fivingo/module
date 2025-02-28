package workshp11.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
	public String convert(Date date, int type) {
		String str = null;
		
		if (type == 1) {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			str = format1.format(date);
		} else if (type == 2) {
			SimpleDateFormat format2 = new SimpleDateFormat("yy년 M월 d일 E요일");
			str = format2.format(date);
		} else {
			SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
			str = format3.format(date);
		}
		
		return str;
	}
}
