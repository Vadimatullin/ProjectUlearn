public class Device {
    private final String deviceType;
    private final String name;
    private final int size;
    private final int weight;

    public Device(String deviceType, String name, int size, int weight) {
        this.deviceType = deviceType;
        this.name = name;
        this.size = size;
        this.weight = weight;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
