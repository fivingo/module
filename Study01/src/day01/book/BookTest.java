package day01.book;

public class BookTest {
	public static void main(String[] args) {
		Book book1 = new Book("SQL Plus", 50000, 5.0);
		Book book2 = new Book("Java 2.0", 40000, 3.0);
		Book book3 = new Book("JSPServlet", 60000, 6.0);
		
		System.out.println("책이름\t" + "가격\t" + "할인율\t" + "할인후금액");
		System.out.println("------------------------------------------------");
		System.out.println(book1.getBookName() + "\t"
						+ book1.getBookPrice() + "원\t"
						+ book1.getBookDiscountRate() + "%\t"
						+ book1.getDiscountBookPrice() + "원");
		System.out.println(book2.getBookName() + "\t"
						+ book2.getBookPrice() + "원\t"
						+ book2.getBookDiscountRate() + "%\t"
						+ book2.getDiscountBookPrice() + "원");
		System.out.println(book3.getBookName() + "\t"
						+ book3.getBookPrice() + "원\t"
						+ book3.getBookDiscountRate() + "%\t"
						+ book3.getDiscountBookPrice() + "원");
	}
}
