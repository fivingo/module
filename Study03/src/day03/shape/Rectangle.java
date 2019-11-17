package day03.shape;

public class Rectangle extends Shape implements Movable {
	private int width;
	
	public Rectangle() {
		
	}
	
	public Rectangle(int width, int x, int y) {
		super();
		this.width = width;
		point = new Point(x, y);
	}

	@Override
	public double getArea() {
		double area = this.width * this.width;
		return area;
	}

	@Override
	public double getCircumference() {
		double circumference = this.width * 4;
		return circumference;
	}

	@Override
	public void move(int x, int y) {
		point.setX(point.getX() + x + 2);
		point.setY(point.getY() + y + 2);
	}

	// getter/setter
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
