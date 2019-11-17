package day02.shape;

public class Triangle extends Shape implements Resize {
	public Triangle() {
		
	}
	
	public Triangle(int width, int height, String colors) {
		super.setWidth(width);
		super.setHeight(height);
		super.setColors(colors);
	}
	

	@Override
	public double getArea() {
		double area = super.getWidth() * super.getHeight() / 2;
		return area;
	}

	@Override
	public void setResize(int size) {
		super.setHeight(super.getHeight() + size);
	}
}
