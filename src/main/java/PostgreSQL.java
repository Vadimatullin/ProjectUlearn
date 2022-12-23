import java.sql.DriverManager;

public class PostgreSQL {
    private static final String username = "postgres";
    private static final String password = "328316";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";

    public static void fill(String path) {
        try {
            var devices = Csv.read(path);

            var connection = DriverManager.getConnection(url, username, password);

            connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS devices").execute();

            connection.prepareStatement("CREATE TABLE devices.laptops (device_type VARCHAR(7) NOT NULL, " +
                    "device_model VARCHAR(50) NOT NULL, device_size INT NOT NULL, device_weight INT NOT NULL," +
                    "buttons_count INT NOT NULL, touchpad_size INT NOT NULL, active_cooling VARCHAR(6) NOT NULL)").execute();

            connection.prepareStatement("CREATE TABLE devices.phones (device_type VARCHAR(7) NOT NULL, " +
                    "device_model VARCHAR(50) NOT NULL, device_size INT NOT NULL, device_weight INT NOT NULL," +
                    "cameras_count INT NOT NULL, battery_power INT NOT NULL, face_scanner VARCHAR(6) NOT NULL)").execute();

            for (var device : devices) {
                if (device.getClass() == Laptop.class) {
                    connection.prepareStatement(String.format("INSERT INTO devices.laptops VALUES ('%s', '%s', '%d', %d, '%d', %d, %s)",
                            device.getDeviceType(), device.getName(), device.getSize(), device.getWeight(),
                            ((Laptop) device).getButtonsCount(), ((Laptop) device).getTouchpadSize(),
                            ((Laptop) device).isActiveCooling())).execute();
                } else {
                    connection.prepareStatement(String.format("INSERT INTO devices.phones VALUES ('%s', '%s', '%d', %d, '%d', %d, %s)",
                            device.getDeviceType(), device.getName(), device.getSize(), device.getWeight(),
                            ((Phone) device).getCamerasCount(), ((Phone) device).getBatteryPower(),
                            ((Phone) device).isFaceScanner())).execute();
                }
            }
            System.out.println("База данных готова!");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public static void read() {
        try (var connection = DriverManager.getConnection(url, username, password);
             var statement = connection.createStatement()) {
            var laptopSet = statement.executeQuery("SELECT device_type, device_model, device_size, " +
                    "device_weight, buttons_count, touchpad_size, active_cooling FROM devices.laptops");
            while (laptopSet.next()) {
                System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
                        laptopSet.getString("device_type"), laptopSet.getString("device_model"),
                        laptopSet.getInt("device_size"), laptopSet.getInt("device_weight"),
                        laptopSet.getInt("buttons_count"), laptopSet.getInt("touchpad_size"),
                        laptopSet.getString("active_cooling"));
            }

            var phoneSet = statement.executeQuery("SELECT device_type, device_model, device_size, " +
                    "device_weight, cameras_count, battery_power, face_scanner FROM devices.phones");
            while (phoneSet.next()) {
                System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
                        phoneSet.getString("device_type"), phoneSet.getString("device_model"),
                        phoneSet.getInt("device_size"), phoneSet.getInt("device_weight"),
                        phoneSet.getInt("cameras_count"), phoneSet.getInt("battery_power"),
                        phoneSet.getString("face_scanner"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
