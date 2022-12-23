import java.util.ArrayList;

public class Phone extends Device {
    private final int camerasCount;
    private final int batteryPower;
    private final boolean isFaceScanner;

    public Phone(String deviceType, String name, int size, int weight, int camerasCount, int batteryPower, boolean isFaceScanner) {
        super(deviceType, name, size, weight);
        this.camerasCount = camerasCount;
        this.batteryPower = batteryPower;
        this.isFaceScanner = isFaceScanner;
    }

    public int getCamerasCount() {
        return camerasCount;
    }

    public int getBatteryPower() {
        return batteryPower;
    }

    public boolean isFaceScanner() {
        return isFaceScanner;
    }

    public static String FindPhoneWithLargestBatteryAndtheLargestCamerasCount(ArrayList<Device> devices) {
        try {
            var phones = devices.stream().filter(l -> l instanceof Phone).map(l -> (Phone) l).toList();
            var answer = "";
            var largestBattery = 0;
            var mostCameras = 0;
            for (var phone : phones) {
                var currentBattery = phone.getBatteryPower();
                var currentCamerasCount = phone.getCamerasCount();
                if (currentBattery > largestBattery && currentCamerasCount > mostCameras) {
                    answer = phone.getName();
                    largestBattery = currentBattery;
                    mostCameras = currentCamerasCount;
                }
                return String.format("The phone with the largest battery and the largest number of cameras: %s", answer);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s",
                getDeviceType(), getName(), getSize(), getWeight(),
                getCamerasCount(), getBatteryPower(), isFaceScanner());
    }
}
