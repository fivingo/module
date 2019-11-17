package day03.ship;

public class Cruise extends Ship {
	public Cruise() {
		
	}
	
	public Cruise(String shipName, int fuelTank) {
		super.setShipName(shipName);
		super.setFuelTank(fuelTank);
	}
	
	@Override
	public void sail(int dist) {
		super.setFuelTank(super.getFuelTank() - (dist * 13));
	}
	
	@Override
	public void refuel(int fuel) {
		super.setFuelTank(super.getFuelTank() + (fuel * 8));
	}
}
