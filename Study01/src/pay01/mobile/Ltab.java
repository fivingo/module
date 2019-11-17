package pay01.mobile;

public class Ltab extends Mobile {
	public Ltab() {
		
	}
	
	public Ltab(String mobileName, int battery, String osType) {
		super.setMobileName(mobileName);
		super.setBatterySize(battery);
		super.setOsType(osType);
	}

	@Override
	public int operate(int time) {
		super.setBatterySize(super.getBatterySize() - time * 10);
		return super.getBatterySize();
	}

	@Override
	public int charge(int time) {
		super.setBatterySize(super.getBatterySize() + time * 10);
		return super.getBatterySize();
	}
}
