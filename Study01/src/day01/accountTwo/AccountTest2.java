package day01.accountTwo;

import day01.account.Account;

public class AccountTest2 {
	public static void main(String[] args) {
		Account accountArray[] = new Account[5];
		
		for (int i = 0; i < accountArray.length; i++) {
			accountArray[i] = new Account("221-0101-221" + (i + 1), 100000, 4.5);
		}
		
		for (int i = 0; i < accountArray.length; i++) {
			System.out.println("계좌번호: " + accountArray[i].getAccount()
					+ " 잔액: " + accountArray[i].getBalance() + " 이자율: " + accountArray[i].getInterestRate());
		}
		
		System.out.println();
		
		for (int i = 0; i < accountArray.length; i++) {
			accountArray[i].setInterestRate(3.7);
			System.out.println("계좌번호: " + accountArray[i].getAccount() 
						+ " 잔액: " + accountArray[i].getBalance() 
						+ " 이자율: " + accountArray[i].getInterestRate()
						+ " 이자: " + (int)(accountArray[i].calculateInterest()) + "원");
		}
	}
}
