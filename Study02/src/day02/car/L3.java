package day02.car;

public class L3 extends Car implements Temp {
	public L3() {
		
	}
	
	public L3(String name, String engine, int oilTank,int oilSize, int distance) {
		super.setName(name);
		super.setEngine(engine);
		super.setOilTank(oilTank);
		super.setOilSize(oilSize);
		super.setDistance(distance);
	}

	@Override
	public void go(int distance) {
		super.setOilSize(super.getOilSize() - distance / 10);
		super.setDistance(distance);
	}

	@Override
	public void setOil(int oilSize) {
		super.setOilSize(super.getOilSize() + oilSize);
	}
	
	@Override
	public int getTempGage() {
		int temp = super.getDistance() / 10;
		return temp;
	}
}
