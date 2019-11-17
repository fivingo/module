package day01.company;

public class Company {
	private double salary;
	private double income;
	private double afterTaxIncome;
	private double bonus;
	private double afterTaxBonus;
	
	public Company() {
		
	}

	public Company(double salary) {
		this.salary = salary;
	}
	
	public double getIncome() {
		this.income = this.salary * 12;
		return income;
	}
	
	public double getAfterTaxIncome() {
		this.afterTaxIncome = this.income - (this.income * 10 / 100);
		return this.afterTaxIncome;
	}
	
	public double getBonus() {
		this.bonus = this.salary * 20 / 100 * 4;
		return this.bonus;
	}
	
	public double getAfterTaxBonus() {
		this.afterTaxBonus = this.bonus - (this.bonus * 5.5 / 100);
		return this.afterTaxBonus;
	}

	public double getSalary() {
		this.salary = this.afterTaxIncome + this.afterTaxBonus;
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setAfterTaxBonus(double afterTaxBonus) {
		this.afterTaxBonus = afterTaxBonus;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public void setAfterTaxIncome(double afterTaxIncome) {
		this.afterTaxIncome = afterTaxIncome;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
}
