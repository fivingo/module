package day03.shape;

import java.util.ArrayList;

public class ShapeTest {
	public static void main(String[] args) {
		ArrayList<Shape> list = new ArrayList<Shape>();
		
		list.add(new Rectangle(4, 7, 5));
		list.add(new Rectangle(5, 4, 6));
		list.add(new Circle(6, 6, 7));
		list.add(new Circle(7, 8, 3));
		
		// 모든 객체의 넓이 정보와 둘레 정보를 출력
		System.out.println("구분\t\t길이\tX좌표\tY좌표\tArea\tCircumference");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Rectangle) {
				System.out.print("Rectangle\t" + ((Rectangle) list.get(i)).getWidth() + "\t");
			} else if (list.get(i) instanceof Circle) {
				System.out.print("Circle\t\t" + ((Circle) list.get(i)).getRadius() + "\t");
			}
			System.out.print(list.get(i).getPoint().getX() + "\t"); 
			System.out.print(list.get(i).getPoint().getY() + "\t");
			System.out.print(Math.round(list.get(i).getArea()) + "\t");
			System.out.println(Math.round(list.get(i).getCircumference()));
		}
		
		// 좌표 변경
		for (int i = 0; i < list.size(); i++) {
			((Movable)list.get(i)).move(10, 10);
		}
		
		// 좌표 변경 후 객체 정보 출력
		System.out.println("이동 후...");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Rectangle) {
				System.out.print("Rectangle\t" + ((Rectangle) list.get(i)).getWidth() + "\t");
			} else if (list.get(i) instanceof Circle) {
				System.out.print("Circle\t\t" + ((Circle) list.get(i)).getRadius() + "\t");
			}
			System.out.print(list.get(i).getPoint().getX() + "\t");
			System.out.println(list.get(i).getPoint().getY());
		}
	}
}
