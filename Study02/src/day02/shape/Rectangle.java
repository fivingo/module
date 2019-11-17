package day02.shape;

public class Rectangle extends Shape implements Resize {
	public Rectangle() {
		
	}
	
	public Rectangle(int width, int height, String colors) {
		super.setWidth(width);
		super.setHeight(height);
		super.setColors(colors);
	}
	

	@Override
	public double getArea() {
		double area = super.getWidth() * super.getHeight();
		return area;
	}

	@Override
	public void setResize(int size) {
		super.setWidth(super.getWidth() + size);
	}
}
