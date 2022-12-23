import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Csv {
    public static void generate(String path, int count) {
        try {
            var devices = genDevices(count);
            var w = new CSVWriter(new FileWriter(path));
            var headlines = ("Device type, Model, Size, Weight, Buttons or cameras count, " +
                    "Touchpad size or battery power, Active cooling or face scanner").split(", ");
            w.writeNext(headlines);
            for (var d : devices) {
                w.writeNext(d.toString().split(", "));
            }
            w.close();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public static ArrayList<Device> read(String path) {
        try {
            var r = new CSVReader(new FileReader(path)).readAll();
            var tempList = r.subList(1, r.size());
            var devices = new ArrayList<Device>();
            for (var e : tempList) {
                if (Objects.equals(e[0], "Laptop")) {
                    devices.add(new Laptop(
                            e[0], e[1], Integer.parseInt(e[2]), Integer.parseInt(e[3]), Integer.parseInt(e[4]),
                            Integer.parseInt(e[5]), Boolean.parseBoolean(e[6])));
                } else {
                    devices.add(new Phone(
                            e[0], e[1], Integer.parseInt(e[2]), Integer.parseInt(e[3]), Integer.parseInt(e[4]),
                            Integer.parseInt(e[5]), Boolean.parseBoolean(e[6])));
                }
            }
            return devices;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static ArrayList<Device> genDevices(int count) {
        try {
            var devices = new ArrayList<Device>();

            var laptopsNames = Arrays.asList("Xiaomi Pro 15 2021", "ASUS ExpertBook B3 Flip", "Chuwi CoreBook X",
                    "ASUS Zenbook Pro Duo", "ASUS VivoBook Pro", "HIPER EXPERTBOOK", "Huawei MateBook D 14",
                    "Lenovo IdeaPad 3", "Lenovo ThinkPad T14 Gen 3", "MSI Creator Z16", "Razer Blade 14",
                    "Rombica myBook ECLIPCE", "HP Omen 16", "HASEE S7", "Dream Machines RG3060", "ASUS X415EA",
                    "ASUS TUF Dash", "Apple MacBook Air M1", "Acer Nitro 5", "ASUS TUF Gaming");

            var phonesNames = Arrays.asList("Apple iPhone 13", "Apple iPhone 14 Pro Max", "OPPO Reno7",
                    "POCO M4 Pro", "Realme C11", "Samsung Galaxy A22", "Samsung Galaxy M53", "TECNO Spark 8C",
                    "Xiaomi Redmi 10", "Xiaomi Redmi 9C", "Xiaomi Redmi Note 11S", "Samsung Galaxy A73",
                    "Huawei Nova Y90", "HTC Wildfire E2", "Black Shark 5 Pro", "Apple iPhone 13 mini",
                    "TECNO POVA 2", "Vivo V25", "Vivo Y53s", "Xiaomi Redmi Note 11 Pro 5G");

            for (var i = 0; i < count; i++) {
                if (rand(0, 1) == 1) {
                    devices.add(new Laptop(
                            "Laptop",
                            laptopsNames.get(rand(0, laptopsNames.size() - 1)),
                            rand(15, 22),
                            rand(1, 10),
                            rand(61, 130),
                            rand(3, 7),
                            rand(0, 1) != 0
                    ));
                } else {
                    devices.add(new Phone(
                            "Phone",
                            phonesNames.get(rand(0, phonesNames.size() - 1)),
                            rand(3, 7),
                            rand(1, 2),
                            rand(1, 5),
                            rand(1500, 5000),
                            rand(0, 1) != 0
                    ));
                }
            }
            return devices;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static int rand(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
