package pay01.mobile;

public class MobileTest {
	public static void main(String[] args) {
		// instanceof 키워드 사용?
		Mobile ltab = new Ltab("Ltab", 500, "AP-01");
		Mobile otab = new Otab("Otab", 1000, "AND-20");
		
		System.out.println("Mobile\tBatterySize\tOS");
		System.out.println("-------------------------------");
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
		
		System.out.println("\r10분 충전");
		System.out.println("Mobile\tBatterySize\tOS");
		System.out.println("-------------------------------");
		ltab.charge(10);
		otab.charge(10);
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
		
		System.out.println("\r5분 통화");
		System.out.println("Mobile\tBatterySize\tOS");
		System.out.println("-------------------------------");
		ltab.operate(5);
		otab.operate(5);
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
	}
}
