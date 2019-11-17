package day03.shape;

public abstract class Shape {
	protected Point point;
	
	public Shape() {
		
	}

	public Shape(Point point) {
		super();
		this.point = point;
	}
	
	public abstract double getArea();	//  넓이
	
	public abstract double getCircumference();	// 둘레

	// getter/setter
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
