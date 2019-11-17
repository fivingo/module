package day02.car;

public class CarTest {
	public static void main(String[] args) {
		Car carArray[] = new Car[2];
		carArray[0] = new L3("L3", "1500", 50, 25, 0);
		carArray[1] = new L5("L5", "2000", 70, 35, 0);
		
		System.out.println("vehicleName\tengineSize\toilTank\toilSize\tdistance\ttemperature");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(carArray[0].getName() + "\t" + carArray[0].getEngine() + "\t"
						+ carArray[0].getOilTank() + "\t" +  carArray[0].getOilSize() + "\t"
						+ carArray[0].getDistance() + "\t" + ((L3)carArray[0]).getTempGage());
		System.out.println(carArray[1].getName() + "\t" + carArray[1].getEngine() + "\t"
						+ carArray[1].getOilTank() + "\t" +  carArray[1].getOilSize() + "\t"
						+ carArray[1].getDistance() + "\t" + ((L5)carArray[1]).getTempGage());
		
		System.out.println("\r25 주유");
		carArray[0].setOil(25);
		carArray[1].setOil(25);
		
		System.out.println("vehicleName\tengineSize\toilTank\toilSize\tdistance\ttemperature");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(carArray[0].getName() + "\t" + carArray[0].getEngine() + "\t"
						+ carArray[0].getOilTank() + "\t" +  carArray[0].getOilSize() + "\t"
						+ carArray[0].getDistance() + "\t" + ((L3)carArray[0]).getTempGage());
		System.out.println(carArray[1].getName() + "\t" + carArray[1].getEngine() + "\t"
						+ carArray[1].getOilTank() + "\t" +  carArray[1].getOilSize() + "\t"
						+ carArray[1].getDistance() + "\t" + ((L5)carArray[1]).getTempGage());
		
		System.out.println("\r80 주행");
		carArray[0].go(80);
		carArray[1].go(80);
		
		System.out.println("vehicleName\tengineSize\toilTank\toilSize\tdistance\ttemperature");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(carArray[0].getName() + "\t" + carArray[0].getEngine() + "\t"
						+ carArray[0].getOilTank() + "\t" +  carArray[0].getOilSize() + "\t"
						+ carArray[0].getDistance() + "\t" + ((L3)carArray[0]).getTempGage());
		System.out.println(carArray[1].getName() + "\t" + carArray[1].getEngine() + "\t"
						+ carArray[1].getOilTank() + "\t" +  carArray[1].getOilSize() + "\t"
						+ carArray[1].getDistance() + "\t" + ((L5)carArray[1]).getTempGage());
	}
}
