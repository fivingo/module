package day01.company;

public class Test07 {
	public static void main(String[] args) {
		Company company = new Company(Double.parseDouble(args[0]));
		
		System.out.println("연 기본급 합: " + company.getIncome() + " 세후: " + company.getAfterTaxIncome());
		System.out.println("연 보너스 합: " + company.getBonus() + " 세후: " + company.getAfterTaxBonus());
		System.out.println("연 지급액 합: " + company.getSalary());
	}
}
