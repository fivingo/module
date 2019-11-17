package day02.acc;

public class AccountTest {
	public static void main(String[] args) {
		Account account;
		account = new Account("441-0290-1203", 500000, 7.3);
		
		String accountInfo = "계좌정보: " + account.getAccount() + " " + account.getBalance()
							+ " " + account.getInterestRate();
		System.out.println(accountInfo);
		
		try {
			account.deposit(-10);
		} catch (Exception e) {
			System.out.println("입금 금액이 0보다 작습니다.");
			//e.printStackTrace();
		}
		
		try {
			account.withdraw(600000);
		} catch (Exception e) {
			System.out.println("금액이 0보다 적거나 현재 잔액보다 많습니다.");
			//e.printStackTrace();
		}
		
		System.out.println("이자: " + account.calculateInterest());
	}
}
