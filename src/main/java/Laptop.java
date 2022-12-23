import java.util.ArrayList;

public class Laptop extends Device {
    private final int buttonsCount;
    private final int touchpadSize;
    private final boolean isActiveCooling;

    public Laptop(String deviceType, String name, int size, int weight, int buttonsCount, int touchpadSize, boolean isActiveCooling) {
        super(deviceType, name, size, weight);
        this.buttonsCount = buttonsCount;
        this.touchpadSize = touchpadSize;
        this.isActiveCooling = isActiveCooling;
    }

    public int getButtonsCount() {
        return buttonsCount;
    }

    public int getTouchpadSize() {
        return touchpadSize;
    }

    public boolean isActiveCooling() {
        return isActiveCooling;
    }

    public static String findLargestLaptopWithPassiveCooling(ArrayList<Device> devices) {
        try {
            var laptops = devices.stream().filter(l -> l instanceof Laptop).map(l -> (Laptop) l).toList();
            var answer = "";
            var largestSize = 0;
            for (var laptop : laptops) {
                var currentSize = laptop.getSize();
                if (currentSize > largestSize) {
                    answer = laptop.getName();
                    largestSize = currentSize;
                }
                return String.format("The largest laptop with passive cooling: %s", answer);
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
                getButtonsCount(), getTouchpadSize(), isActiveCooling());
    }
}
