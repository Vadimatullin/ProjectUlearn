public class Main {
    public static void main(String[] args) {
        Csv.generate("C:\\ProjectUlearn\\src\\main\\resources\\devices.csv", 15);
        PostgreSQL.fill("C:\\ProjectUlearn\\src\\main\\resources\\devices.csv");
        PostgreSQL.read();
        Graph.get();
    }
}