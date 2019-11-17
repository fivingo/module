package day01.account;

public class AccounTest {
	public static void main(String[] args) {
		Account account;
		account = new Account("441-0290-1203", 500000, 7.3);
		
		System.out.println("계좌정보: " + account.getAccount() + " 현재잔액: " + account.getBalance());
		
		account.withdraw(600000);
		account.deposit(20000);
		System.out.println("계좌정보: " + account.getAccount() + " 현재잔액: " + account.getBalance());
		
		System.out.println("이자: " + account.calculateInterest());
	}
}
