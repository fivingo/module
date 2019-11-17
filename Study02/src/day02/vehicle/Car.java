package day02.vehicle;

public class Car extends Vehicle {
	private double restOil;
	private int curWeight;
	
	public Car() {
		
	}
	
	public Car(int maxWeight, double oilTankSize, double efficiency) {
		super.setMaxWeight(maxWeight);
		super.setOilTankSize(oilTankSize);
		super.setEfficiency(efficiency);
	}
	
	public void addOil(int oil) {
		if (this.restOil + oil < super.getOilTankSize()) {
			this.restOil = this.restOil + oil; 
		} else {
			this.restOil = this.restOil + (super.getOilTankSize() - this.restOil + oil);
		}
	}
	
	public void moving (int distance) {
		this.restOil = this.restOil - distance / super.getEfficiency();
	}
	
	public void addWeight(int weight) {
		if (this.curWeight + weight < super.getMaxWeight()) {
			this.curWeight = this.curWeight + weight; 
		} else {
			this.curWeight = this.curWeight + (super.getMaxWeight() - this.curWeight + weight);
		}
	}
	
	public String toString() {
		String carInfo = super.toString() + "\t" + Double.toString(this.restOil)
						+ "\t" + Integer.toString(this.curWeight);
		return carInfo;
	}

	// getter / setter
	
	public double getRestOil() {
		return restOil;
	}

	public void setRestOil(double restOil) {
		this.restOil = restOil;
	}

	public int getCurWeight() {
		return curWeight;
	}

	public void setCurWeight(int curWeight) {
		this.curWeight = curWeight;
	}
}
