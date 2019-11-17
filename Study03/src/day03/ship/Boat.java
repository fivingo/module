package day03.ship;

public class Boat extends Ship {
	public Boat() {
		
	}
	
	public Boat(String shipName, int fuelTank) {
		super.setShipName(shipName);
		super.setFuelTank(fuelTank);
	}
	
	@Override
	public void sail(int dist) {
		super.setFuelTank(super.getFuelTank() - (dist * 10));
	}
	
	@Override
	public void refuel(int fuel) {
		super.setFuelTank(super.getFuelTank() + (fuel * 10));
	}
}
