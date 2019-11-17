package day02.car;

public class L5 extends Car implements Temp {
	public L5() {
		
	}
	
	public L5(String name, String engine, int oilTank,int oilSize, int distance) {
		super.setName(name);
		super.setEngine(engine);
		super.setOilTank(oilTank);
		super.setOilSize(oilSize);
		super.setDistance(distance);
	}

	@Override
	public void go(int distance) {
		super.setOilSize(super.getOilSize() - distance / 8);
		super.setDistance(distance);
	}

	@Override
	public void setOil(int oilSize) {
		super.setOilSize(super.getOilSize() + oilSize);
	}
	
	@Override
	public int getTempGage() {
		int temp = super.getDistance() / 5;
		return temp;
	}
}
