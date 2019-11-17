package day03.shape;

public class Circle extends Shape implements Movable {
	private int radius;
	
	public Circle() {
		
	}

	public Circle(int radius, int x, int y) {
		super();
		this.radius = radius;
		point = new Point(x, y);
	}

	@Override
	public double getArea() {
		double area = Math.PI * this.radius * this.radius;
		return area;
	}

	@Override
	public double getCircumference() {
		double circumference = 2 * Math.PI * this.radius;
		return circumference;
	}

	@Override
	public void move(int x, int y) {
		point.setX(point.getX() + x + 1);
		point.setY(point.getY() + y + 1);
	}

	// getter/setter
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
