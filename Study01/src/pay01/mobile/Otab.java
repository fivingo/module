package pay01.mobile;

public class Otab extends Mobile {
	public Otab() {
		
	}
	
	public Otab(String mobileName, int battery, String osType) {
		super.setMobileName(mobileName);
		super.setBatterySize(battery);
		super.setOsType(osType);
	}

	@Override
	public int operate(int time) {
		super.setBatterySize(super.getBatterySize() - time * 12);
		return super.getBatterySize();
	}

	@Override
	public int charge(int time) {
		super.setBatterySize(super.getBatterySize() + time * 8);
		return super.getBatterySize();
	}
}
