package day02.vehicle;

public class Truck extends Car{
	public Truck() {
		
	}
	
	public Truck(int maxWeight, double oilTankSize, double efficiency) {
		super.setMaxWeight(maxWeight);
		super.setOilTankSize(oilTankSize);
		super.setEfficiency(efficiency);
	}
	
	public double getEfficiency() {
		double efficiency = super.getEfficiency() - super.getCurWeight() / 5  * 0.2;
		return efficiency;
	}
	
	public void moving(int distance) {
		double oil = super.getRestOil() - this.carcOil(distance);
		super.setRestOil(oil);
	}
	
	private double carcOil(int distance) {
		double oil = distance / this.getEfficiency();
		return oil;
	}
	
	public int getCost(int distance) {
		int cost = (int)(this.carcOil(distance) * 3000);
//		int cost = (int)this.carcOil(distance) * 3000;
		return cost;
	}
	
	public String toString() {
		String truckInfo = super.toString() + "\t" + this.getEfficiency();
		return truckInfo;
	}
}
